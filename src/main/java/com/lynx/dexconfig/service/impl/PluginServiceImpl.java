package com.lynx.dexconfig.service.impl;

import java.util.List;

import com.lynx.dexconfig.entity.AndroidPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lynx.dexconfig.service.PluginService;
import com.lynx.mapper.cb.dexconfig.DexConfigMapper;

/**
 * 
 * @author zhufeng.liu
 * 
 * @version 13-11-13 下午5:49
 */
@Service("pluginStoreService")
public class PluginServiceImpl implements PluginService {

	@Autowired
	private DexConfigMapper dexConfigMapper;

	@Override
	public List<AndroidPlugin> allPlugins() {
		return dexConfigMapper.getAllPlugins();
	}

	@Override
	public List<AndroidPlugin> pluginByCategory(int category) {
		return dexConfigMapper.getPluginByCategory(category);
	}

	@Override
	public List<AndroidPlugin> searchPlugin(String keyword) {
		String param = "%" + keyword + "%";
		return dexConfigMapper.getPluginByKeyword(param);
	}

	@Override
	public List<AndroidPlugin> myPlugins(List<String> categorys) {
		try {
			return dexConfigMapper.getMyPlugins(categorys);
		} catch (Exception e) {

		}
		return null;
	}
}
