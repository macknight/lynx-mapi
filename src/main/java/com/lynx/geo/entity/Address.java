package com.lynx.geo.entity;

import com.google.gson.annotations.Expose;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 13-8-8 下午3:43
 */
public class Address {
    @Expose
    private String country; // 国家
    @Expose
    private String province; // 省份
    @Expose
    private String city; //城市
    @Expose
    private String subCity; // 城区信息
    @Expose
    private String street; // 街道
    @Expose
    private Integer num; // 门牌号
    @Expose
    private String bizRegion; // 所属商圈

    public Address() {

    }

    public Address(String country, String province, String city, String subCity, String street, Integer num, String bizRegion) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.subCity = subCity;
        this.street = street;
        this.num = num;
        this.bizRegion = bizRegion;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getSubCity() {
        return subCity;
    }

    public void setSubCity(String subCity) {
        this.subCity = subCity;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBizRegion() {
        return bizRegion;
    }

    public void setBizRegion(String bizRegion) {
        this.bizRegion = bizRegion;
    }
}
