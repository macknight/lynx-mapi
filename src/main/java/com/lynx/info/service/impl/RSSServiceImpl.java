package com.lynx.info.service.impl;

import com.lynx.info.entity.RSS;
import com.lynx.info.entity.RSSInfo;
import com.lynx.info.service.RSSService;
import com.lynx.info.service.dao.InfoDao;
import com.lynx.info.util.SinaRSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 
 * @author zhufeng.liu
 * 
 * @addtime 13-12-3 下午8:40
 */
@Service("infoService")
public class RSSServiceImpl implements RSSService {

	@Autowired
	private InfoDao infoDao;

	@PostConstruct
	private void init() {
		// Timer timer = new Timer("if consumer listener alive", true);
		// timer.schedule(new TimerTask() {
		// @Override
		// public void run() {
		// updateSinaRSS();
		// }
		// }, 0, 60 * 60 * 1000);
	}

	@Override
	public List<RSS> getSinaRSS() {
		return infoDao.getSinaRSSes();
	}

	@Override
	public List<RSSInfo> getSinaRSSInfo(String rss, int num, long updateTime) {

		return null;
	}

	public void updateSinaRSSInfo(int rssId) {
		String url = infoDao.getSinaRSS(rssId);
		List<RSSInfo> rssInfos = SinaRSSUtil.sinaRssNews(rssId, url);
		if (rssInfos != null && rssInfos.size() > 0) {
			infoDao.deleteRSSInfoByRSSId(rssId);
			infoDao.insertRSSInfos(rssInfos);
		}
	}
}
