package com.lynx.geo.dao.entity;

/**
 * 
 * @author zhufeng.liu
 * 
 * @version 13-11-2-下午4:09
 */
public class LocationPo {
	private double lat;
	private double lng;
	private int acc;
	private String addr;

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public int getAcc() {
		return acc;
	}

	public void setAcc(int acc) {
		this.acc = acc;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
}
