package com.lynx.dexconfig.entity;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author zhufeng.liu
 * 
 * @version 13-11-13 下午5:35
 */
public class Plugin extends Config {
	@Expose
	private String name;
	@Expose
	private String icon;
	@Expose
	private int category;

	public Plugin() {

	}

	public Plugin(String module, int version, String name, String icon,
			String desc, String url, String md5, String clazz, int category) {
		super(module, version, url, md5, desc, clazz);
		this.name = name;
		this.icon = icon;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
}
