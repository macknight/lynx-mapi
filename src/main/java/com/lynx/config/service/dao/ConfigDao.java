package com.lynx.config.service.dao;

import com.lynx.config.service.entity.ConfigEntity;

import java.util.List;

/**
 * Created by chris.liu
 * Created at 13-10-27-下午12:10.
 */
public interface ConfigDao {

	/**
	 * 获取android ui模块动态更新配置
	 *
	 * @return
	 */
	List<ConfigEntity> getAndroidUIConfig();

	/**
	 * 获取android service动态更新配置
	 *
	 * @return
	 */
	List<ConfigEntity> getAndroidServiceConfig();
}
