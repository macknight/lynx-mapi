package com.lynx.geo.entity;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author zhufeng.liu
 * 
 * @version 13-8-8 下午6:22
 */
public class CDMACell implements Cell {
	@Expose
	private int mcc; // Mobile Country Code
	@Expose
	private int sid; // System ID->mnc
	@Expose
	private int nid; // Network ID->lac
	@Expose
	private int bid; // Basestation ID->cid
	@Expose
	private long lat;
	@Expose
	private long lng;

	public CDMACell(int mcc, int sid, int nid, int bid, long lat, long lng) {
		this.mcc = mcc;
		this.sid = sid;
		this.nid = nid;
		this.bid = bid;
		this.lat = lat;
		this.lng = lng;
	}

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

	public long getLat() {
		return lat;
	}

	public void setLat(long lat) {
		this.lat = lat;
	}

	public long getLng() {
		return lng;
	}

	public void setLng(long lng) {
		this.lng = lng;
	}

	@Override
	public CellType type() {
		return CellType.CDMA;
	}

	@Override
	public String toLogStr() {
		return String.format("%s,%s,%s,%s", mcc, sid, nid, bid);
	}
}
