package com.lynx.geo.dao.entity;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author zhufeng.liu
 * 
 * @version 13-8-9 下午1:28
 */
public class GeoAreaPo {
	public static final int GA_LEVEL_COUNTRY = 0;
	public static final int GA_LEVEL_PROVINCE = 0;
	public static final int GA_LEVEL_CITY = 0;
	public static final int GA_LEVEL_SUBCITY = 0;

	@Expose
	private int id;
	@Expose
	private String name;
	@Expose
	private int parentId;
	private int level;

	public GeoAreaPo(int id, String name, int parentId, int level) {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.level = level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
