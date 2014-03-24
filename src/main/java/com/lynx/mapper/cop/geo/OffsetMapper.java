package com.lynx.mapper.cop.geo;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lynx.geo.entity.GeoPoint;

/**
 * 
 * @author zhufeng.liu
 * @version 13-8-8 下午5:22
 */
@Repository(value = "offsetMapper")
public interface OffsetMapper {

	GeoPoint getGPS2MapbarOffset(@Param(value = "lat") double lat, @Param(value = "lng") double lng);

	GeoPoint getGPS2GMapOffset(@Param(value = "lat") double lat, @Param(value = "lng") double lng);

	GeoPoint getGPS2BMapOffset(@Param(value = "lat") double lat, @Param(value = "lng") double lng);

	GeoPoint getGPS2AMapOffset(@Param(value = "lat") double lat, @Param(value = "lng") double lng);
}
