package com.lynx.mapper.cb.dexconfig;

import java.util.List;

import com.lynx.dexconfig.entity.AndroidDexModule;
import com.lynx.dexconfig.entity.AndroidPlugin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author zhufeng.liu
 * @version 13-11-13 下午5:46
 */
@Repository(value = "dexConfigMapper")
public interface DexConfigMapper {

	/**
	 * 获取android service动态更新配置
	 * 
	 * @return
	 */
	List<AndroidDexModule> getDexServiceConfig();

	List<AndroidPlugin> getAllPlugins();

	List<AndroidPlugin> getPluginByCategory(@Param(value = "category") int category);

	List<AndroidPlugin> getPluginByKeyword(@Param(value = "keyword") String keyword);

	List<AndroidPlugin> getMyPlugins(@Param(value = "categories") List<String> categories);
}
