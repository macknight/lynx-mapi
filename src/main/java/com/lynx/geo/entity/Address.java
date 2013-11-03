package com.lynx.geo.entity;

import com.google.gson.annotations.Expose;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 13-8-8 下午3:43
 */
public class Address {
    @Expose
    private String province; // 省份
    @Expose
    private String city; //城市
    @Expose
    private String region; // 城区信息
    @Expose
    private String street; // 街道
    @Expose
    private String num; // 门牌号

    public Address() {

    }

    public Address(String province, String city, String region,
                   String street, String num) {
        this.province = province;
        this.city = city;
        this.region = region;
        this.street = street;
        this.num = num;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

}
