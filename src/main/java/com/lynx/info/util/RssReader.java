package com.lynx.info.util;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import sun.misc.BASE64Encoder;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * 
 * @author zhufeng.liu
 * 
 * @addtime 13-12-2 下午5:32
 */
public class RssReader {

	public void readFeedXml() {
		try {
			System.setProperty("http.proxyHost", "10.191.131.13");
			System.setProperty("http.proxyPort", "3128");
			String authStr = "account:password";
			String auth = "Basic "
					+ new BASE64Encoder().encode(authStr.getBytes());
			URL feedurl = new URL("http://rss.sina.com.cn/blog/index/cul.xml"); // 指定rss位置
			URLConnection uc = feedurl.openConnection();
			// 设定代理
			uc.setRequestProperty("Proxy-Authorization", auth);
			uc.addRequestProperty("Referer", "localhost");
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(uc));
			List entries = feed.getEntries();
			for (int i = 0; i < entries.size(); i++) {
				SyndEntry entry = (SyndEntry) entries.get(i);
				System.out.println(entry.getTitle().trim());
				System.out.println(entry.getDescription().getValue().trim());
				System.out.println(entry.getLink().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		RssReader reader = new RssReader();
		reader.readFeedXml();
	}
}