package com.lynx.geo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lynx.core.BasicService;
import com.lynx.geo.entity.Address;
import com.lynx.geo.entity.Cell;
import com.lynx.geo.entity.Coord;
import com.lynx.geo.entity.GeoPoint;
import com.lynx.geo.entity.Place;
import com.lynx.geo.entity.Wifi;
import com.lynx.geo.service.GeoService;
import com.lynx.geo.service.dao.RGCDao;
import com.lynx.geo.util.BMapAPIUtil;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-8-8 下午3:38
 */
@Service("geoService")
public class GeoServiceImpl extends BasicService implements GeoService {

    @Autowired
    private RGCDao rgcDao;


    @PostConstruct
    private void init() {

    }

    @Override
    public Address revGeocoding(Coord coord) {
        BMapAPIUtil.revGeocoding(coord.getGeoPoint());
        return null;
    }

    @Override
    public GeoPoint geocoding(Address address) {
        String addr = "";
        addr = address.getCountry() == null ? addr : String.format("%s%s", addr, address.getCountry());
        addr = address.getProvince() == null ? addr : String.format("%s%s", addr, address.getProvince());
        addr = address.getCity() == null ? addr : String.format("%s%s", addr, address.getCity());
        addr = address.getSubCity() == null ? addr : String.format("%s%s", addr, address.getSubCity());
        addr = address.getStreet() == null ? addr : String.format("%s%s", addr, address.getStreet());
        addr = address.getNum() == null ? addr : String.format("%s%s", addr, address.getNum());
        return BMapAPIUtil.geocoding(addr);
    }

    @Override
    public List<Coord> locate(List<Cell> cells, List<Wifi> wifis, Map<Coord.CoordSource, Coord> coords) {
        return null;
    }


    // http://api.map.baidu.com/geocoder/v2/?ak=您的密钥&callback=renderOption&output=json&address=百度大厦&city=北京市
    private List<Place> getSuggestionPlaces(double lat, double lng) {
        List<Place> result = null;
        try {

        } catch (Exception e) {
            result = null;
        }
        return result;
    }
}
