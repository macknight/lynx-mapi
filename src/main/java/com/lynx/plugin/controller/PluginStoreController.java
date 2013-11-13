package com.lynx.plugin.controller;

import com.lynx.core.BasicController;
import com.lynx.core.Result;
import com.lynx.core.util.EncryptUtil;
import com.lynx.plugin.entity.Plugin;
import com.lynx.plugin.service.PluginStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 13-11-13 下午5:34
 */
@Controller
@RequestMapping(value = "/pluginstore", method = RequestMethod.POST,
		produces = "text/plain;charset=UTF-8")
public class PluginStoreController extends BasicController {

	@Autowired
	private PluginStoreService pluginStoreService;

	@RequestMapping(value = "/allplugin")
	public ResponseEntity<String> location() {
		Result result = null;
		try {
			List<Plugin> plugins = pluginStoreService.getAllPlugins();
			if (plugins != null && plugins.size() > 0) {
				result = new Result(Result.RS_OK, plugins);
			} else {
				result = new Result(Result.RS_FAIL, "no plugin in store yet");
			}
		} catch (Exception e) {
			log.info(e.getStackTrace());
			result = new Result(Result.RS_ERROR, "server inner error");
		}
		return new ResponseEntity<String>(EncryptUtil.format(result), HttpStatus.OK);
	}
}
