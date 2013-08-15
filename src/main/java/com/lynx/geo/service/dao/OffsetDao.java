package com.lynx.geo.service.dao;

import com.lynx.geo.entity.GeoPoint;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 13-8-8 下午5:22
 */
public interface OffsetDao {

    GeoPoint getGPS2MapbarOffset(@Param(value = "lat") double lat,
                                     @Param(value = "lng") double lng);

    GeoPoint getGPS2GMapOffset(@Param(value = "lat") double lat,
                                  @Param(value = "lng") double lng);

    GeoPoint getGPS2BMapOffset(@Param(value = "lat") double lat,
                                 @Param(value = "lng") double lng);

    GeoPoint getGPS2AMapOffset(@Param(value = "lat") double lat,
                                @Param(value = "lng") double lng);
}
