package com.lynx.weather.service.impl;

import com.lynx.mapper.cb.weather.WeatherCityPO;
import com.lynx.weather.entity.CityInfo;
import com.lynx.weather.entity.WeatherInfo;
import com.lynx.weather.service.WeatherService;
import com.lynx.weather.service.util.WeatherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lynx.mapper.cb.weather.WeatherCityMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author chris.liu
 * @version 3/23/14 11:44 AM
 */
@Service("weatherService")
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private WeatherCityMapper weatherCityMapper;

	@Override
	public List<CityInfo> getCityList() {
		List<WeatherCityPO> cityPOs = weatherCityMapper.getAllWeatherCity();

        List<CityInfo> cityInfos = new ArrayList<CityInfo>();
        if (cityPOs != null && cityPOs.size() > 0) {
            for (WeatherCityPO po : cityPOs) {
                CityInfo cityInfo = new CityInfo(po.getId(), po.getNameCH(), po.getNameEN());
                cityInfos.add(cityInfo);
            }
        }
		return cityInfos;
	}

	@Override
	public WeatherInfo getWeatherInfo(int cityId) {
		WeatherCityPO weatherCityPO = weatherCityMapper.getWeatherCityById(cityId);
		if (weatherCityPO != null) {
			return WeatherUtil.getCityWeatherInfo(weatherCityPO.getCode());
		} else {
			return null;
		}
	}

}
