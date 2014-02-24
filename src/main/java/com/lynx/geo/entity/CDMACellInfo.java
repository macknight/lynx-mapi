package com.lynx.geo.entity;

/**
 * 
 * @author chris.liu
 * 
 * @version 13-12-23 下午3:20
 */
public class CDMACellInfo {
	private int mcc; // Mobile Country Code
	private int sid; // System ID
	private int nid; // Network ID
	private int bid; // Basestation ID
	private double lat;
	private double lng;
	private String addr;

	public int getMcc() {
		return mcc;
	}

	public void setMcc(int mcc) {
		this.mcc = mcc;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
}
