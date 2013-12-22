package com.lynx.info.service;

import com.lynx.info.entity.RSS;
import com.lynx.info.entity.RSSInfo;

import java.util.List;

/**
 * 
 * @author zhufeng.liu
 * 
 * @addtime 13-12-2 上午11:51
 */
public interface RSSService {

	/**
	 * 获取sina RSS列表
	 * 
	 * @return
	 */
	List<RSS> getSinaRSS();

	/**
	 * 获取sina对应RSS资讯
	 * 
	 * @param rss
	 * @param num
	 * @param updateTime
	 * @return
	 */
	List<RSSInfo> getSinaRSSInfo(String rss, int num, long updateTime);

	void updateSinaRSSInfo(int rssId);
}
