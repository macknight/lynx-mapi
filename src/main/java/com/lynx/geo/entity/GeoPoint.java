package com.lynx.geo.entity;

import com.google.gson.annotations.Expose;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 13-8-8 下午3:27
 */
public class GeoPoint {
    @Expose
    private double lat;
    @Expose
    private double lng;

    public GeoPoint() {

    }

    public GeoPoint(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
