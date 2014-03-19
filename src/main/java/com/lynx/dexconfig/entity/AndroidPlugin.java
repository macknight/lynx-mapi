package com.lynx.dexconfig.entity;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author zhufeng.liu
 * 
 * @version 13-11-13 下午5:35
 */
public class AndroidPlugin extends AndroidDexModule {
	@Expose
	private String name;
	@Expose
	private String icon;
	@Expose
	private int category;

	public AndroidPlugin() {

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
