package com.lynx.geo.entity;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author zhufeng.liu
 * @version 13-8-19 上午10:16
 */
public class Campaign {
	@Expose
	private int id;
	@Expose
	private String title;
	@Expose
	private String content;
	@Expose
	private long beginTime;
	@Expose
	private long endTime;
	@Expose
	private int shopId;

	public Campaign() {

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
}
