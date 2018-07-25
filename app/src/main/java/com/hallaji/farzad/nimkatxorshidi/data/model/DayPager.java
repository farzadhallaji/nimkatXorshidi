package com.hallaji.farzad.nimkatxorshidi.data.model;

import com.hallaji.farzad.nimkatxorshidi.dao.OrmWeather;

import java.util.List;

/**
 *
 * Created by Igor Havrylyuk on 17.02.2017.
 */

public class DayPager {

    private List<OrmWeather> hours;

    public DayPager() {
    }

    public List<OrmWeather> getHours() {
        return hours;
    }

    public void setHours(List<OrmWeather> hours) {
        this.hours = hours;
    }
}
