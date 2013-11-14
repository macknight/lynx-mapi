package com.lynx.plugin.service;

import com.lynx.plugin.entity.Plugin;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-11-13 下午5:34
 */
public interface PluginStoreService {

	/**
	 * 获取所有插件
	 *
	 * @return
	 */
	List<Plugin> allPlugins();

	/**
	 * 根据类别获取插件
	 *
	 * @param category
	 * @return
	 */
	List<Plugin> pluginByCategory(int category);

	/**
	 * 根据关键字获取插件
	 *
	 * @param keyword
	 * @return
	 */
	List<Plugin> searchPlugin(String keyword);
}
