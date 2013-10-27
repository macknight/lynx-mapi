package com.lynx.config.service.entity;

import com.google.gson.annotations.Expose;

/**
 * Created by chris.liu
 * Created at 13-10-27-下午12:22.
 */
public class ConfigEntity {
	@Expose
	private String module;
	@Expose
	private String clazz;
	@Expose
	private String version;
	@Expose
	private String url;
	@Expose
	private String md5;
	@Expose
	private String desc;

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
