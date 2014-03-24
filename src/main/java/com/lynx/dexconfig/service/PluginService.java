package com.lynx.dexconfig.service;

import com.lynx.dexconfig.entity.AndroidPlugin;

import java.util.List;

/**
 * 
 * @author zhufeng.liu
 * @version 13-11-13 下午5:34
 */
public interface PluginService {

	/**
	 * 获取所有插件
	 * 
	 * @return
	 */
	List<AndroidPlugin> allPlugins();

	/**
	 * 根据类别获取插件
	 * 
	 * @param category
	 * @return
	 */
	List<AndroidPlugin> pluginByCategory(int category);

	/**
	 * 根据关键字获取插件
	 * 
	 * @param keyword
	 * @return
	 */
	List<AndroidPlugin> searchPlugin(String keyword);

	/**
	 * 获取我的插件配置
	 * 
	 * @param categorys
	 * @return
	 */
	List<AndroidPlugin> myPlugins(List<String> categorys);
}
