package com.hallaji.farzad.nimkatxorshidi.service;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.hallaji.farzad.nimkatxorshidi.BuildConfig;
import com.hallaji.farzad.nimkatxorshidi.R;
import com.hallaji.farzad.nimkatxorshidi.WeatherApp;
import com.hallaji.farzad.nimkatxorshidi.dao.OrmCity;
import com.hallaji.farzad.nimkatxorshidi.dao.OrmWeather;
import com.hallaji.farzad.nimkatxorshidi.data.FileManager;
import com.hallaji.farzad.nimkatxorshidi.data.local.ILocalDataSource;
import com.hallaji.farzad.nimkatxorshidi.data.model.Current;
import com.hallaji.farzad.nimkatxorshidi.data.model.ForecastDay;
import com.hallaji.farzad.nimkatxorshidi.data.model.ForecastWeather;
import com.hallaji.farzad.nimkatxorshidi.data.model.Hour;
import com.hallaji.farzad.nimkatxorshidi.data.remote.OpenWeatherService;
import com.hallaji.farzad.nimkatxorshidi.data.remote.WeatherApiClient;
import com.hallaji.farzad.nimkatxorshidi.events.LoadingStatusEvent;
import com.hallaji.farzad.nimkatxorshidi.util.LocaleHelper;
import com.hallaji.farzad.nimkatxorshidi.util.PreferencesHelper;
import com.hallaji.farzad.nimkatxorshidi.util.Utility;


import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;

/**
 *
 * Created by Igor Havrylyuk on 14.02.2017.
 */

public class WeatherService extends IntentService {

    public static final int FORECAST_COUNT_DAYS = 7;

    public static final String EXTRA_KEY_SYNC =
            "com.havrylyuk.weather.intent.action.EXTRA_KEY_SYNC" ;
    public static final String ACTION_DATA_UPDATED =
            "com.havrylyuk.weather.app.ACTION_DATA_UPDATED";

    private static final String LOG_TAG = WeatherService.class.getSimpleName();
    public static final int SYNC_ERROR = 0;
    public static final int SYNC_START = 1;
    public static final int SYNC_END = 2;
    public static final int SYNC_NO_INTERNET = 3;

    private ILocalDataSource localDataSource;
    private final SimpleDateFormat fmt;
    private boolean isMetric;
    private FileManager fileManager;
    private String selectedLang;

    public WeatherService() {
        super("WeatherService");
        fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null ) {
            if (BuildConfig.DEBUG) Log.d(LOG_TAG, " Beginning network data synchronization ");
            PreferencesHelper pref = PreferencesHelper.getInstance();
            selectedLang = LocaleHelper.getLanguage(getApplicationContext());
            fileManager = FileManager.getInstance(getAssets());
            isMetric = getString(R.string.pref_unit_default_value)
                    .equals(pref.getUnits(this));
            updateSyncStatus(SYNC_START);
            if (Utility.isNetworkAvailable(getApplicationContext())) {
                localDataSource = ((WeatherApp) getApplicationContext()).getLocalDataSource();
                final OpenWeatherService service = WeatherApiClient.getClient().create(OpenWeatherService.class);
                List<OrmCity> cities = localDataSource.getCityList();
                if (cities != null && !cities.isEmpty()) {
                    localDataSource.deleteAllForecast();//delete old forecast data
                    for (OrmCity city : cities) {
                        getWeatherForCity(service, city);
                    }

                }
            } else    {
                Log.d(LOG_TAG, getString(R.string.no_internet));
                updateSyncStatus(SYNC_NO_INTERNET);
            }
            if (BuildConfig.DEBUG) Log.d(LOG_TAG, " End network data synchronization ");
            updateSyncStatus(SYNC_END);
        }
    }

    private void getWeatherForCity(OpenWeatherService service, OrmCity city) {
        String latLng = String.valueOf(city.getLat()) + " , " + String.valueOf(city.getLon());
        Call<ForecastWeather> responseCall =
                service.getWeather(BuildConfig.WEATHER_API_KEY, latLng, String.valueOf(FORECAST_COUNT_DAYS));
        try {
            ForecastWeather response = responseCall.execute().body();
            if (response.getError() == null) {
                List<OrmWeather> ormWeatherList = new ArrayList<>();
                ormWeatherList.add(getCurrentOrmWeather(city.get_id(),response));
                getHourlyOrmWeather(city.get_id(), response, ormWeatherList);
                localDataSource.saveForecast(ormWeatherList);
            } else {
                Log.e(LOG_TAG, response.getError().getMessage());
                Toast.makeText(getApplicationContext(), response.getError().toString()+
                        ":"+response.getError().getMessage(),Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            updateSyncStatus(SYNC_ERROR);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private OrmWeather getCurrentOrmWeather(long cityId, ForecastWeather response ) throws ParseException {
        Current current = response.getCurrent();
        ForecastDay forecastDay = response.getForecast().getForecastday().get(0);
        OrmWeather weather = new OrmWeather();
        weather.setCity_id(cityId);
        weather.setCity_name(response.getLocation().getName());
        weather.setDt(fmt.parse(current.getLastUpdated()));
        weather.setClouds(current.getCloud());
        weather.setHumidity(current.getHumidity());
        weather.setPressure(isMetric ? convertMbToMmHg(current.getPressureMb()) : current.getPressureIn());
        weather.setTemp(isMetric?current.getTempC():current.getTempF());
        weather.setTemp_min(isMetric ?forecastDay.getDay().getMintempC():forecastDay.getDay().getMintempF());
        weather.setTemp_max(isMetric ?forecastDay.getDay().getMaxtempC():forecastDay.getDay().getMaxtempF());
        weather.setIs_day(current.getIs_day()==1);
        weather.setIcon(current.getCondition().getIcon());
        String localizedCondition =  fileManager.getCondition(current.getCondition().getCode(), selectedLang);
        if (TextUtils.isEmpty(localizedCondition)) {
            localizedCondition = current.getCondition().getText();
        }
        weather.setCondition_text(localizedCondition);
        weather.setCondition_code(current.getCondition().getCode());
        weather.setWind_speed(isMetric ? convertKphToMps(current.getWindKph()) : current.getWindMph());
        weather.setWind_dir(response.getCurrent().getWindDir());
        return weather;
    }

    private void getHourlyOrmWeather(long cityId, ForecastWeather response, List<OrmWeather> ormWeatherList) throws ParseException {
        for (ForecastDay forecastDay : response.getForecast().getForecastday()) {
            for (Hour hour : forecastDay.getHours()) {
                if (fmt.parse(hour.getTime()).after(Calendar.getInstance().getTime())) {//no save old forecast
                    OrmWeather weather = new OrmWeather();
                    weather.setCity_id(cityId);
                    weather.setCity_name(response.getLocation().getName());
                    weather.setDt(fmt.parse(hour.getTime()));
                    weather.setClouds(hour.getCloud());
                    weather.setHumidity(hour.getHumidity());
                    weather.setPressure(isMetric ? convertMbToMmHg(hour.getPressureMb()) : hour.getPressureIn());
                    weather.setTemp(isMetric ? hour.getTempC() : hour.getTempF());
                    weather.setTemp_min(isMetric ?forecastDay.getDay().getMintempC():forecastDay.getDay().getMintempF());
                    weather.setTemp_max(isMetric ?forecastDay.getDay().getMaxtempC():forecastDay.getDay().getMaxtempF());
                    weather.setIcon(hour.getCondition().getIcon());
                    weather.setWind_speed(isMetric ? convertKphToMps(hour.getWindKph()) : hour.getWindMph());
                    weather.setWind_dir(hour.getWindDir());
                    weather.setRain(hour.getWillItRain());
                    weather.setSnow(hour.getWillItSnow());
                    String localizedCondition =  fileManager.getCondition(hour.getCondition().getCode(), selectedLang);
                    if (TextUtils.isEmpty(localizedCondition)) {
                        localizedCondition = hour.getCondition().getText();
                    }
                    weather.setCondition_text(localizedCondition);
                    weather.setCondition_code(hour.getCondition().getCode());
                    weather.setIs_day(hour.getIs_day()==1);
                    ormWeatherList.add(weather);
                }
            }
        }
    }

    private void updateSyncStatus(int status) {
        EventBus.getDefault().post(new LoadingStatusEvent(status));
    }

    //convert MilliBars to mmHg.
    private Double convertMbToMmHg(Double pressureInMb){
        return pressureInMb * 0.750062;
    }

    // convert Km per hour to m/s
    private Double convertKphToMps(Double windKph){
        return windKph * 0.277778;
    }

}
