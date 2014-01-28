package com.lynx.dexconfig.service.impl;

import com.lynx.dexconfig.entity.Config;
import com.lynx.dexconfig.service.DexConfigService;
import com.lynx.dexconfig.service.dao.DexConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 
 * @author zhufeng.liu
 * 
 * @version 13-10-27-下午12:11
 */
@Service("configService")
public class DexConfigServiceImpl implements DexConfigService {

	@Autowired
	private DexConfigDao dexConfigDao;

	@PostConstruct
	private void init() {

	}

	/**
	 * android动态更新配置
	 * 
	 * @return
	 */
	@Override
	public List<Config> getDexServiceConfig(String ua, String token) {
		try {
			return dexConfigDao.getDexServiceConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
