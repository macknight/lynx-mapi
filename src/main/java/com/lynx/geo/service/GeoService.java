package com.lynx.geo.service;

import com.lynx.geo.entity.Address;
import com.lynx.geo.entity.Coord;
import com.lynx.geo.entity.Coord.CoordSource;
import com.lynx.geo.entity.GeoPoint;
import com.lynx.geo.entity.Wifi;
import javafx.scene.control.Cell;

import java.util.List;
import java.util.Map;

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
    Address revGeoCoding(Coord coord);

    /**
     * 地址解析
     *
     * @param address
     * @return
     */
    GeoPoint geoCoding(Address address);


    List<Coord> locate(List<Cell> cells, List<Wifi> wifis, Map<CoordSource, Coord> coords);

}
