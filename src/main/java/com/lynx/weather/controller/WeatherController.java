package com.lynx.weather.controller;

import com.lynx.weather.entity.CityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lynx.core.BasicController;
import com.lynx.core.Result;
import com.lynx.core.util.EncryptUtil;
import com.lynx.weather.entity.WeatherInfo;
import com.lynx.weather.service.WeatherService;

import java.util.List;

/**
 * @author chris.liu
 * @version 3/23/14 2:53 PM
 */
@Controller
@RequestMapping(value = "/weather", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
public class WeatherController extends BasicController {

	@Autowired
	private WeatherService weatherService;

	@RequestMapping("/citylist")
	public ResponseEntity<String> cityList(@RequestParam(value = "ua", required = true) String ua,
			@RequestParam(value = "token", required = false) String token) {
		Result result = null;
		try {
			List<CityInfo> cityInfos = weatherService.getCityList();
            result = new Result(Result.RS_OK, cityInfos);
		} catch (Exception e) {
			result = new Result(Result.RS_ERROR, "server inner error");
		}
		return new ResponseEntity<String>(EncryptUtil.format(result), HttpStatus.OK);
	}

	@RequestMapping("forecast")
	public ResponseEntity<String> forecast(@RequestParam(value = "ua", required = true) String ua,
			@RequestParam(value = "token", required = false) String token,
			@RequestParam(value = "cityId", required = true) int cityId) {
		Result result = null;
		try {
			WeatherInfo weatherInfo = weatherService.getWeatherInfo(cityId);
			if (weatherInfo != null) {
				result = new Result(Result.RS_OK, weatherInfo);
			} else {
				result = new Result(Result.RS_FAIL, "未能获取对应城市天气信息");
			}
		} catch (Exception e) {
			result = new Result(Result.RS_ERROR, "server inner error");
		}
		return new ResponseEntity<String>(EncryptUtil.format(result), HttpStatus.OK);
	}
}
