package com.lynx.config.service.impl;

import com.lynx.config.service.dao.ConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chris.liu
 * Created at 13-10-27-下午12:11.
 */
@Service("configService")
public class ConfigServiceImpl {

	@Autowired
	private ConfigDao configDao;



}
