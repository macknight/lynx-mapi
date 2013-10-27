package com.lynx.config.controller;

import com.lynx.core.BaseController;
import com.lynx.core.Result;
import com.lynx.core.util.EncryptUtil;
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
public class ConfigController extends BaseController {

	@RequestMapping("/ui")
	public ResponseEntity<String> uiConfig(@RequestParam(value = "ua", required = true) String ua,
	                                       @RequestParam(value = "token", required = false) String token) {
		Result result = null;
		try {

		} catch (Exception e) {

		}
		return new ResponseEntity<String>(EncryptUtil.format(result), HttpStatus.OK);
	}


	@RequestMapping("/service")
	public ResponseEntity<String> serviceConfig(@RequestParam(value = "ua", required = true) String ua,
	                                            @RequestParam(value = "token", required = false) String token) {
		Result result = null;
		try {

		} catch (Exception e) {

		}
		return new ResponseEntity<String>(EncryptUtil.format(result), HttpStatus.OK);
	}

}
