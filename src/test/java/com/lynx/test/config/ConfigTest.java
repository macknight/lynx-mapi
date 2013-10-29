package com.lynx.test.config;

import com.lynx.config.service.ConfigService;
import com.lynx.config.service.entity.AndroidFrameworkConfig;
import com.lynx.core.util.EncryptUtil;
import com.lynx.test.BasicTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 13-10-28 下午7:08
 */
public class ConfigTest extends BasicTest {

	@Autowired
	private ConfigService configService;

	@Test
	public void androidFrameworkConfigTest() {
		String ua = "";
		String token = "";
		AndroidFrameworkConfig config = configService.getAndroidFrameworkConfig(ua, token);
		System.out.println(EncryptUtil.format(config));
	}
}
