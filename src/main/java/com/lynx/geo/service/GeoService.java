package com.lynx.geo.service;

import com.lynx.geo.entity.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 13-8-8 下午3:25
 */
public interface GeoService {

    /**
     * 反向地址解析
     *
     * @param coord
     * @return
     */
    Address revGeocoding(Coord coord);

    /**
     * 地址解析
     *
     * @param address
     * @return
     */
    GeoPoint geocoding(Address address);


    List<Location> location(List<Cell> cells, List<Wifi> wifis, List<Coord> coords);

}
