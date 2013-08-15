package com.lynx.geo.service;

import com.lynx.geo.entity.Coord.CoordType;
import com.lynx.geo.entity.GeoPoint;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 13-8-8 下午5:14
 */
public interface OffsetService {

    /**
     * 经纬度纠偏
     *
     * @param origin 原始经纬度
     * @param oct    原始经纬度所属坐标系
     * @param dct    目标坐标系
     * @return
     */
    GeoPoint offset(GeoPoint origin, CoordType oct, CoordType dct);
}
