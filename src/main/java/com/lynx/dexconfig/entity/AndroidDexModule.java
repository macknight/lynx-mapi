package com.lynx.dexconfig.entity;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author zhufeng.liu
 * @version 13-11-17 下午2:15
 */
public class AndroidDexModule {
	@Expose
	private String module;
	@Expose
	private int version;
	@Expose
	private String url;
	@Expose
	private String md5;
	@Expose
	private String desc;
	@Expose
	private String clazz;

	private String test;

	public AndroidDexModule() {

	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
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

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
}
