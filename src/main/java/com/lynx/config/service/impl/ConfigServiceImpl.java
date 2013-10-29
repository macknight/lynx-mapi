package com.lynx.config.service.impl;

import com.lynx.config.service.ConfigService;
import com.lynx.config.service.dao.ConfigDao;
import com.lynx.config.service.entity.AndroidFrameworkConfig;
import com.lynx.config.service.entity.ConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by chris.liu
 * Created at 13-10-27-下午12:11.
 */
@Service("configService")
public class ConfigServiceImpl implements ConfigService {

	@Autowired
	private ConfigDao configDao;


	@PostConstruct
	private void init() {
		
	}

	/**
	 * android动态更新配置
	 *
	 * @return
	 */
	@Override
	public AndroidFrameworkConfig getAndroidFrameworkConfig(String ua, String token) {
		List<ConfigEntity> result = null;

		try {
			List<ConfigEntity> configServcie = configDao.getAndroidServiceConfig();
			List<ConfigEntity> configUI = configDao.getAndroidUIConfig();
			return new AndroidFrameworkConfig(configServcie, configUI);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
