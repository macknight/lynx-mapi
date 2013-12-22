package com.lynx.geo.entity;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author zhufeng.liu
 * 
 * @addtime 13-11-2-上午9:10
 */
public class Location {
	@Expose
	private double lat;
	@Expose
	private double lng;
	@Expose
	private int acc;
	@Expose
	private Address addr; // with format of province|city|region|street|streetNo

	public Location() {

	}

	public Location(double lat, double lng, int acc, Address addr) {
		this.lat = lat;
		this.lng = lng;
		this.acc = acc;
		this.addr = addr;
	}

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

	public Address getAddr() {
		return addr;
	}

	public void setAddr(Address addr) {
		this.addr = addr;
	}
}
