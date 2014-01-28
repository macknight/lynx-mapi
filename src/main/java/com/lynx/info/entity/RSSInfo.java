package com.lynx.info.entity;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author zhufeng.liu
 * 
 * @version 13-12-2 上午11:51
 */
public class RSSInfo {
	private int id;
	@Expose
	private String title; // 标题
	@Expose
	private String summary; // 摘要
	@Expose
	private String link; // 链接
	private int rssId;
	@Expose
	private long pubDate; // 发布时间

	public RSSInfo() {

	}

	public RSSInfo(int id, String title, String summary, String link,
			int rssId, long pubDate) {
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.link = link;
		this.rssId = rssId;
		this.pubDate = pubDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getRssId() {
		return rssId;
	}

	public void setRssId(int rssId) {
		this.rssId = rssId;
	}

	public long getPubDate() {
		return pubDate;
	}

	public void setPubDate(long pubDate) {
		this.pubDate = pubDate;
	}
}
