package com.lynx.weather.service;

import com.lynx.weather.entity.CityInfo;
import com.lynx.weather.entity.WeatherInfo;

import java.util.List;

/**
 * @author chris.liu
 * @version 3/23/14 11:44 AM
 */
public interface WeatherService {

	/**
	 * 获取城市列表
	 * 
	 * @return
	 */
    List<CityInfo> getCityList();

	/**
	 * 根据城市Id获取天气信息
	 * 
	 * @param cityId
	 * @return
	 */
	WeatherInfo getWeatherInfo(int cityId);

}
