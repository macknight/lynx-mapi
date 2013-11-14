package com.lynx.plugin.service.impl;

import com.lynx.plugin.entity.Plugin;
import com.lynx.plugin.service.PluginStoreService;
import com.lynx.plugin.service.dao.PluginStoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-11-13 下午5:49
 */
@Service("pluginStoreService")
public class PluginStoreServiceImpl implements PluginStoreService {

	@Autowired
	private PluginStoreDao pluginStoreDao;

	@Override
	public List<Plugin> allPlugins() {
		return pluginStoreDao.getAllPlugins();
	}

	@Override
	public List<Plugin> pluginByCategory(int category) {
		return pluginStoreDao.getPluginByCategory(category);
	}

	@Override
	public List<Plugin> searchPlugin(String keyword) {
		String param = "%" + keyword + "%";
		return pluginStoreDao.getPluginByKeyword(param);
	}
}
