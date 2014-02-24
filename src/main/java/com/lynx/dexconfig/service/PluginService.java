package com.lynx.dexconfig.service;

import com.lynx.dexconfig.entity.Plugin;

import java.util.List;

/**
 * 
 * @author zhufeng.liu
 * 
 * @version 13-11-13 下午5:34
 */
public interface PluginService {

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

	/**
	 * 获取我的插件配置
	 * 
	 * @param categorys
	 * @return
	 */
	List<Plugin> myPlugins(List<String> categorys);
}
