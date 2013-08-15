package com.lynx.geo.entity;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 13-8-8 下午3:27
 */
public class Coord {
    private CoordType coordType;
    private GeoPoint geoPoint;


    public Coord(GeoPoint geoPoint, CoordType coordType) {
        this.coordType = coordType;
        this.geoPoint = geoPoint;
    }

    public CoordType getCoordType() {
        return coordType;
    }

    public void setCoordType(CoordType coordType) {
        this.coordType = coordType;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public enum CoordType {
        GPS,
        GOOGLE,
        BAIDU,
        GAODE,
        MAPBAR,
        SOUGOU
    }

    public enum CoordSource {
        GPS, // android GPS芯片定位
        NETWORK, // android系统定位
        GOOGLE, // google定位数据
        BAIDU, // 百度定位数据
        GAODE, // 高德定位数据
        LCELL, // lynx 基站定位数据
        LWIFI // lynx wifi定位数据
    }
}
