package com.lynx.config.controller;

import com.lynx.config.service.ConfigService;
import com.lynx.config.service.entity.AndroidFrameworkConfig;
import com.lynx.core.BasicController;
import com.lynx.core.Result;
import com.lynx.core.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by chris.liu
 * Created at 13-10-27-下午12:09.
 */
@Controller
@RequestMapping(value = "/config", method = RequestMethod.POST,
		produces = "text/plain;charset=UTF-8")
public class ConfigController extends BasicController {

	@Autowired
	private ConfigService configService;

	/**
	 * 获取框架配置
	 *
	 * @param ua
	 * @param token
	 * @return
	 */
	@RequestMapping("/framework")
	public ResponseEntity<String> frameworkConfig(@RequestParam(value = "ua", required = true) String ua,
	                                              @RequestParam(value = "token", required = false) String token) {
		Result result = null;
		try {
			AndroidFrameworkConfig config = configService.getAndroidFrameworkConfig(ua, token);
			if (config != null) {
				result = new Result(Result.RS_OK, config);
			} else {
				result = new Result(Result.RS_FAIL, "cant get framework config");
			}
		} catch (Exception e) {
			result = new Result(Result.RS_ERROR, "server inner error");
		}
		return new ResponseEntity<String>(EncryptUtil.format(result), HttpStatus.OK);
	}

	/**
	 * 获取业务配置
	 *
	 * @param ua
	 * @param token
	 * @return
	 */
	@RequestMapping("/biz")
	public ResponseEntity<String> bizConfig(@RequestParam(value = "ua", required = true) String ua,
	                                        @RequestParam(value = "token", required = false) String token) {
		Result result = null;
		try {

		} catch (Exception e) {

		}
		return new ResponseEntity<String>(EncryptUtil.format(result), HttpStatus.OK);
	}

}
