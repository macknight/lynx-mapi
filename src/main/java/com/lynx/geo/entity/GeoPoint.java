package com.lynx.geo.entity;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author zhufeng.liu
 * 
 * @addtime 13-8-8 下午3:27
 */
public class GeoPoint {
	@Expose
	private double lat;
	@Expose
	private double lng;
	@Expose
	private int acc;

	public GeoPoint() {

	}

	public GeoPoint(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
		this.acc = 0;
	}

	public GeoPoint(double lat, double lng, int acc) {
		this.lat = lat;
		this.lng = lng;
		this.acc = acc;
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

	@Override
	public String toString() {
		return String.format("%s,%s,%s", lat, lng, acc);
	}
}
