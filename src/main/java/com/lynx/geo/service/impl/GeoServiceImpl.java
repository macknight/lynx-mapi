package com.lynx.geo.service.impl;

import com.lynx.core.BasicService;
import com.lynx.geo.entity.*;
import com.lynx.geo.service.GeoService;
import com.lynx.geo.service.dao.RGCDao;
import com.lynx.geo.util.BMapAPIUtil;
import javafx.scene.control.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

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
    public Address revGeoCoding(Coord coord) {
        return null;
    }

    @Override
    public GeoPoint geoCoding(Address address) {
        String addr = "";
        addr = address.getCountry() == null ? addr : String.format("%s%s", addr, address.getCountry());
        addr = address.getProvince() == null ? addr : String.format("%s%s", addr, address.getProvince());
        addr = address.getCity() == null ? addr : String.format("%s%s", addr, address.getCity());
        addr = address.getSubCity() == null ? addr : String.format("%s%s", addr, address.getSubCity());
        addr = address.getStreet() == null ? addr : String.format("%s%s", addr, address.getStreet());
        addr = address.getNum() == null ? addr : String.format("%s%s", addr, address.getNum());
        return BMapAPIUtil.geoCoding(addr);
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
