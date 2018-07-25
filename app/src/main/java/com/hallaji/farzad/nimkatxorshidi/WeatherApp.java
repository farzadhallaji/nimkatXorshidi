package com.hallaji.farzad.nimkatxorshidi;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.hallaji.farzad.nimkatxorshidi.data.local.ILocalDataSource;
import com.hallaji.farzad.nimkatxorshidi.data.local.LocalDataSource;
import com.hallaji.farzad.nimkatxorshidi.util.LocaleHelper;



public class WeatherApp extends Application {

    public static SharedPreferences sSharedPreferences;
    private ILocalDataSource localDataSource;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        localDataSource = LocalDataSource.getInstance(this);
        Fresco.initialize(this);
    }

    public static SharedPreferences getSharedPreferences() {
        return sSharedPreferences;
    }

    public ILocalDataSource getLocalDataSource() {
        return localDataSource;
    }
}
