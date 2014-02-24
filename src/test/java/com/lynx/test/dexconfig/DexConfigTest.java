package com.lynx.test.dexconfig;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lynx.core.util.EncryptUtil;
import com.lynx.dexconfig.dao.DexConfigDao;
import com.lynx.dexconfig.entity.Config;
import com.lynx.dexconfig.entity.Plugin;
import com.lynx.test.BasicTest;

/**
 * 
 * @author chris.liu
 * 
 * @version 13-11-13 下午6:15
 */
public class DexConfigTest extends BasicTest {

	@Autowired
	private DexConfigDao dexConfigDao;

	@Test
	public void getDexServiceConfigTest() {
		List<Config> configs = dexConfigDao.getDexServiceConfig();
		System.out.println(EncryptUtil.format(configs));
	}

	@Test
	public void getPluginStoreTest() {
		List<Plugin> plugins = dexConfigDao.getAllPlugins();
		System.out.println(EncryptUtil.format(plugins));
	}

	@Test
	public void getMyPluginTest() {
		List<String> myPlugins = new ArrayList<String>();
		myPlugins.add("local");
		myPlugins.add("female");
		myPlugins.add("male");
		List<Plugin> plugins = dexConfigDao.getMyPlugins(myPlugins);
		System.out.println(EncryptUtil.format(plugins));
	}

	@Test
	public void test() {
		String hello = null;
		String world = "test";
		System.out.println(String.format("%s%s", hello, world));
	}

}
