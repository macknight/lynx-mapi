package com.lynx.plugin.entity;

import com.google.gson.annotations.Expose;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-11-13 下午5:35
 */
public class Plugin {
	@Expose
	private String name;
	@Expose
	private String icon;
	@Expose
	private String desc;
	@Expose
	private String url;
	@Expose
	private String md5;
	@Expose
	private String clazz;
	@Expose
	private int category;

	public Plugin() {

	}

	public Plugin(String name, String icon, String desc, String url,
	              String md5, String clazz, int category) {
		this.name = name;
		this.icon = icon;
		this.desc = desc;
		this.url = url;
		this.md5 = md5;
		this.clazz = clazz;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
}
