package com.lynx.weather.entity;

import com.google.gson.annotations.Expose;

/**
 *
 * @author chris.liu
 * @version 3/24/14 2:13 PM
 */
public class CityInfo {
    @Expose
    private int id;
    @Expose
    private String nameCH;
    @Expose
    private String nameEN;

    public CityInfo(int id, String nameCH, String nameEN) {
        this.id = id;
        this.nameCH = nameCH;
        this.nameEN = nameEN;
    }
}
