package com.lynx.geo.entity;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author zhufeng.liu
 * @version 13-8-8 下午6:05
 */
public class Wifi {
	@Expose
	private String ssid;
	@Expose
	private String mac;
	@Expose
	private int dbm;

	public Wifi(String ssid, String mac, int dbm) {
		this.ssid = ssid;
		this.mac = mac;
		this.dbm = dbm;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public int getDbm() {
		return dbm;
	}

	public void setDbm(int dbm) {
		this.dbm = dbm;
	}

	@Override
	public String toString() {
		return String.format("%s,%s,%s", ssid, mac, dbm);
	}
}
