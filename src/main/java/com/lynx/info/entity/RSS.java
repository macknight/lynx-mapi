package com.lynx.info.entity;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author zhufeng.liu
 * 
 * @addtime 13-12-2 下午1:36
 */
public class RSS {
	@Expose
	private int id;
	private String url;
	@Expose
	private String category;
	@Expose
	private String title;

	private long updateTime;

	public RSS() {

	}

	public RSS(String url, String category, String title) {
		this.url = url;
		this.category = category;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

}