package com.lynx.geo.entity;

import com.google.gson.annotations.Expose;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 13-8-12 上午10:20
 */
public class Place {
    @Expose
    private String name;
    @Expose
    private String address;
    @Expose
    private String telephone;
    @Expose
    private double lat;
    @Expose
    private double lng;

    public Place(String name, String address, String telephone, double lat, double lng) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
