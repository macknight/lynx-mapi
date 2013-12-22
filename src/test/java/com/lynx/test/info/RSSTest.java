package com.lynx.test.info;

import com.lynx.core.util.EncryptUtil;
import com.lynx.info.entity.RSS;
import com.lynx.info.entity.RSSInfo;
import com.lynx.info.service.RSSService;
import com.lynx.info.util.SinaRSSUtil;
import com.lynx.test.BasicTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author zhufeng.liu
 * 
 * @addtime 13-12-3 下午3:09
 */
public class RSSTest extends BasicTest {

	@Autowired
	private RSSService infoService;

	@Test
	public void sinaRSSTest() {
		try {
			List<RSS> rsses = SinaRSSUtil.sinaRSS();
			System.out.println(EncryptUtil.format(rsses));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getSinRSSTest() {
		try {
			List<RSS> rsses = infoService.getSinaRSS();
			System.out.println(EncryptUtil.format(rsses));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void onlineSinaNewsTest() {
		try {
			List<RSSInfo> infos = SinaRSSUtil.sinaRssNews(1,
					"http://rss.sina.com.cn/eladies/son.xml");
			System.out.println(EncryptUtil.format(infos));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		try {
			infoService.updateSinaRSSInfo(85);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void sinNewsInDBTest() {
		try {
			List<RSSInfo> infos = infoService.getSinaRSSInfo("亲子", 0,
					new Date().getTime());
			System.out.println(EncryptUtil.format(infos));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}