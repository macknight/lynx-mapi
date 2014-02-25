package com.lynx.dexconfig.dao;

import com.lynx.dexconfig.entity.Config;
import com.lynx.dexconfig.entity.Plugin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @author zhufeng.liu
 * 
 * @version 13-11-13 下午5:46
 */
public interface DexConfigDao {

	/**
	 * 获取android service动态更新配置
	 * 
	 * @return
	 */
	List<Config> getDexServiceConfig();

	List<Plugin> getAllPlugins();

	List<Plugin> getPluginByCategory(@Param(value = "category") int category);

	List<Plugin> getPluginByKeyword(@Param(value = "keyword") String keyword);

	List<Plugin> getMyPlugins(@Param(value = "categories") List<String> categories);
}
