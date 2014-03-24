package com.lynx.test.dexconfig;

import java.util.ArrayList;
import java.util.List;

import com.lynx.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lynx.core.util.EncryptUtil;
import com.lynx.dexconfig.entity.AndroidDexModule;
import com.lynx.dexconfig.entity.AndroidPlugin;
import com.lynx.mapper.cb.dexconfig.DexConfigMapper;

/**
 * 
 * @author chris.liu
 * 
 * @version 13-11-13 下午6:15
 */
public class DexConfigTest extends BaseTest {

	@Autowired
	private DexConfigMapper dexConfigMapper;

	@Test
	public void getDexServiceConfigTest() {
		List<AndroidDexModule> configs = dexConfigMapper.getDexServiceConfig();
		System.out.println(EncryptUtil.format(configs));
	}

	@Test
	public void getPluginStoreTest() {
		List<AndroidPlugin> plugins = dexConfigMapper.getAllPlugins();
		System.out.println(EncryptUtil.format(plugins));
	}

	@Test
	public void getMyPluginTest() {
		List<String> myPlugins = new ArrayList<String>();
		myPlugins.add("local");
		myPlugins.add("parenting");
		myPlugins.add("timemachine");
		List<AndroidPlugin> plugins = dexConfigMapper.getMyPlugins(myPlugins);
		System.out.println(EncryptUtil.format(plugins));
	}

	@Test
	public void test() {
		String hello = null;
		String world = "test";
		System.out.println(String.format("%s%s", hello, world));
	}

}
