package com.lynx.dexconfig.service;

import com.lynx.dexconfig.entity.AndroidDexModule;

import java.util.List;

/**
 * 
 * @author zhufeng.liu
 * 
 * @version 13-10-27-下午12:10
 */
public interface DexConfigService {

	/**
	 * 获取android service动态更新配置
	 * 
	 * @param ua
	 * @param token
	 * @return
	 */
	List<AndroidDexModule> getDexServiceConfig(String ua, String token);

}
