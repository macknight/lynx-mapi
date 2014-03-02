package com.lynx.geo.dao;

import com.lynx.geo.entity.Address;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author zhufeng.liu
 * 
 * @version 13-8-8 下午3:40
 */
@Repository(value = "rgcDao")
public interface RGCDao {

	Address getAddress(@Param(value = "lat") double lat, @Param(value = "lng") double lng);

}
