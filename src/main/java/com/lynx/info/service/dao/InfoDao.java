package com.lynx.info.service.dao;

import com.lynx.info.entity.RSS;
import com.lynx.info.entity.RSSInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @author zhufeng.liu
 * 
 * @addtime 13-12-3 下午8:40
 */
public interface InfoDao {

	List<RSS> getSinaRSSes();

	String getSinaRSS(@Param(value = "id") int id);

	/**
	 * 获取某个rss的资讯
	 * 
	 * @param rssId
	 * @param page
	 * @return
	 */
	List<RSSInfo> getSinaRSSInfo(@Param(value = "rssId") int rssId,
			@Param(value = "page") int page);

	/**
	 * 删除某个rss的资讯
	 * 
	 * @param rssId
	 * @return
	 */
	int deleteRSSInfoByRSSId(@Param(value = "rssId") int rssId);

	/**
	 * 插入某个rss下的资讯
	 * 
	 * @param rssInfos
	 * @return
	 */
	int insertRSSInfos(@Param(value = "rssInfos") List<RSSInfo> rssInfos);

}
