package com.lynx.dexconfig.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import com.lynx.dexconfig.entity.AndroidDexModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lynx.dexconfig.service.DexConfigService;
import com.lynx.mapper.cb.dexconfig.DexConfigMapper;

/**
 * 
 * @author zhufeng.liu
 * 
 * @version 13-10-27-下午12:11
 */
@Service("configService")
public class DexConfigServiceImpl implements DexConfigService {

	@Autowired
	private DexConfigMapper dexConfigMapper;

	@PostConstruct
	private void init() {

	}

	/**
	 * android动态更新配置
	 * 
	 * @return
	 */
	@Override
	public List<AndroidDexModule> getDexServiceConfig(String ua, String token) {
		try {
			return dexConfigMapper.getDexServiceConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
