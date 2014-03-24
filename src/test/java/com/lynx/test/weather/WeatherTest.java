package com.lynx.test.weather;

import com.google.gson.annotations.Expose;
import com.lynx.core.util.EncryptUtil;
import com.lynx.mapper.cb.weather.WeatherCityMapper;
import com.lynx.mapper.cb.weather.WeatherCityPO;
import com.lynx.test.BaseTest;
import com.lynx.weather.entity.WeatherInfo;
import com.lynx.weather.service.WeatherService;
import com.lynx.weather.service.util.WeatherUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chris.liu
 * @version 3/23/14 3:52 PM
 */
public class WeatherTest extends BaseTest {

	@Autowired
	private WeatherCityMapper weatherCityMapper;

	@Autowired
	private WeatherService weatherService;

	@Test
	public void weatherCityTest() {
		try {
			int cityId = 1;
			WeatherCityPO cityPO = weatherCityMapper.getWeatherCityById(cityId);
			System.out.println(cityPO.getCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getWeatherInfoTest() {
		try {
            int cityId = 1;
			WeatherInfo weatherInfo = weatherService.getWeatherInfo(cityId);
            if (weatherInfo != null) {
                System.out.println(EncryptUtil.format(weatherInfo));
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
