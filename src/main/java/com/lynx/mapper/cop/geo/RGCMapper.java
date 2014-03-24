package com.lynx.mapper.cop.geo;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lynx.geo.entity.Address;

/**
 * 
 * @author zhufeng.liu
 * @version 13-8-8 下午3:40
 */
@Repository(value = "rgcMapper")
public interface RGCMapper {

	Address getAddress(@Param(value = "lat") double lat, @Param(value = "lng") double lng);

}
