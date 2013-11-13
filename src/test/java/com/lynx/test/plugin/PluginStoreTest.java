package com.lynx.test.plugin;

import com.lynx.core.util.EncryptUtil;
import com.lynx.plugin.entity.Plugin;
import com.lynx.plugin.service.dao.PluginStoreDao;
import com.lynx.test.BasicTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-11-13 下午6:15
 */
public class PluginStoreTest extends BasicTest {

	@Autowired
	private PluginStoreDao pluginStoreDao;

	@Test
	public void getPluginTest() {
		List<Plugin> plugins = pluginStoreDao.getAllPlugins();
		System.out.println(EncryptUtil.format(plugins));
	}
}
