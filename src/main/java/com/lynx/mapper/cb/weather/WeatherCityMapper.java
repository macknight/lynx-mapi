package com.lynx.mapper.cb.weather;

import com.lynx.weather.entity.WeatherInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chris.liu
 * @version 3/23/14 3:13 PM
 */
@Repository("weatherCityMapper")
public interface WeatherCityMapper {


    List<WeatherCityPO> getAllWeatherCity();

    /**
     * 更具城市Id获取城市信息
     *
     * @param cityId
     * @return
     */
    WeatherCityPO getWeatherCityById(@Param(value = "cityId") int cityId);

	/**
	 * 根据城市名获取城市信息
	 * 
	 * @param cityName
	 * @return
	 */
	WeatherCityPO getWeatherCityByName(@Param(value = "cityName") String cityName);


}
