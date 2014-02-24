package com.lynx.dexconfig.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.lynx.dexconfig.entity.Config;
import com.lynx.dexconfig.entity.Plugin;
import com.lynx.dexconfig.service.DexConfigService;
import com.lynx.dexconfig.service.PluginService;

/**
 * 
 * @author zhufeng.liu
 * 
 * @version 13-10-27-下午12:09
 */
@Controller
@RequestMapping(value = "/dex", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
public class DexConfigController extends BasicController {

	@Autowired
	private DexConfigService dexConfigService;

	@Autowired
	private PluginService pluginService;

	/**
	 * 获取框架service配置
	 * 
	 * @param ua
	 * @param token
	 * @return
	 */
	@RequestMapping("/service")
	public ResponseEntity<String> frameworkConfig(
			@RequestParam(value = "ua", required = true) String ua,
			@RequestParam(value = "token", required = false) String token) {
		Result result = null;
		try {
			List<Config> config = dexConfigService.getDexServiceConfig(ua, token);
			if (config != null) {
				result = new Result(Result.RS_OK, config);
			} else {
				result = new Result(Result.RS_FAIL, "cant get framework dexconfig");
			}
		} catch (Exception e) {
			result = new Result(Result.RS_ERROR, "server inner error");
		}
		return new ResponseEntity<String>(EncryptUtil.format(result), HttpStatus.OK);
	}

	/**
	 * 获取插件更新配置
	 * 
	 * @return
	 */
	@RequestMapping(value = "/myplugin")
	public ResponseEntity<String> myPlguins(
			@RequestParam(value = "apps", required = true) String data) {
		Result result = null;
		try {
			String[] tmp = data.split("\\|");
			List<String> apps = new ArrayList<String>();
			for (String cat : tmp) {
				apps.add(cat);
			}
			if (apps.size() <= 0) {
				result = new Result(Result.RS_FAIL, "参数错误");
			} else {
				List<Plugin> plugins = pluginService.myPlugins(apps);
				if (plugins != null && plugins.size() > 0) {
					result = new Result(Result.RS_OK, plugins);
				} else {
					result = new Result(Result.RS_FAIL, "no dexconfig in store yet");
				}
			}
		} catch (Exception e) {
			log.info(e.getStackTrace());
			result = new Result(Result.RS_ERROR, "server inner error");
		}
		return new ResponseEntity<String>(EncryptUtil.format(result), HttpStatus.OK);
	}

	/**
	 * 插件商店
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pluginstore")
	public ResponseEntity<String> pluginsAtStore() {
		Result result = null;
		try {
			List<Plugin> plugins = pluginService.allPlugins();
			if (plugins != null && plugins.size() > 0) {
				result = new Result(Result.RS_OK, plugins);
			} else {
				result = new Result(Result.RS_FAIL, "no dexconfig in store yet");
			}
		} catch (Exception e) {
			log.info(e.getStackTrace());
			result = new Result(Result.RS_ERROR, "server inner error");
		}
		return new ResponseEntity<String>(EncryptUtil.format(result), HttpStatus.OK);
	}
}
