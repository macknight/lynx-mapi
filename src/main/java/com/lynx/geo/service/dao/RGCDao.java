package com.lynx.geo.service.dao;

import com.lynx.geo.entity.Address;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 13-8-8 下午3:40
 */
public interface RGCDao {

    Address getAddress(@Param(value = "lat") double lat,
                       @Param(value = "lng") double lng);

}
