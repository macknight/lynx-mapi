package com.lynx.config.service;

import com.lynx.config.service.entity.ConfigEntity;

import java.util.List;

/**
 * Created by chris.liu
 * Created at 13-10-27-下午12:10.
 */
public interface ConfigService {

	/**
	 * 获取ui模块动态更新配置
	 *
	 * @param ua
	 * @param token
	 * @return
	 */
	List<ConfigEntity> getUIConfig(String ua, String token);

	/**
	 * 获取service动态更新配置
	 *
	 * @param ua
	 * @param token
	 * @return
	 */
	List<ConfigEntity> getServiceConfig(String ua, String token);
}
