package com.lynx.info.controller;

import com.lynx.core.BasicController;
import com.lynx.core.Result;
import com.lynx.core.util.EncryptUtil;
import com.lynx.info.entity.RSS;
import com.lynx.info.service.RSSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 
 * @author zhufeng.liu
 * 
 * @addtime 13-12-2 上午11:50
 */
@Controller
@RequestMapping(value = "/rss", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
public class RSSController extends BasicController {

	@Autowired
	private RSSService rssService;

	/**
	 * 获取资讯分类频道
	 * 
	 * @return
	 */
	@RequestMapping(value = "/category")
	public ResponseEntity<String> category() {
		Result result = null;
		try {
			List<RSS> rss = rssService.getSinaRSS();
			if (rss != null && rss.size() > 0) {
				result = new Result(Result.RS_OK, rss);
			} else {
				result = new Result(Result.RS_FAIL, "暂无订阅频道");
			}
		} catch (Exception e) {
			result = new Result(Result.RS_ERROR, "server inner error");
		}
		return new ResponseEntity<String>(EncryptUtil.format(result),
				HttpStatus.OK);
	}

	/**
	 * 根据频道和页数获取资讯
	 * 
	 * @param category
	 *            分类
	 * @param page
	 *            页数
	 * @param updateTime
	 *            更新时间
	 * @return
	 */
	@RequestMapping(value = "/info")
	public ResponseEntity<String> info(
			@RequestParam(value = "category", required = false) int category,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "updateTime", required = false) long updateTime) {
		Result result;
		try {
			result = new Result(Result.RS_OK, null);
		} catch (Exception e) {
			result = new Result(Result.RS_ERROR, "server inner error");
		}
		return new ResponseEntity<String>(EncryptUtil.format(result),
				HttpStatus.OK);
	}
}
