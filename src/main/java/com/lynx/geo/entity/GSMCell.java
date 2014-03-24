package com.lynx.geo.entity;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author zhufeng.liu
 * @version 13-8-8 下午6:22
 */
public class GSMCell implements Cell {
	@Expose
	private int mcc; // Mobile Country Code
	@Expose
	private int mnc; // Mobile Network Code
	@Expose
	private int lac; // Local Area Code
	@Expose
	private int cid; // Cell ID
	@Expose
	private int asu; // single strength

    public GSMCell() {

    }

    public GSMCell(int mcc, int mnc, int lac, int cid, int asu) {
		this.mcc = mcc;
		this.mnc = mnc;
		this.lac = lac;
		this.cid = cid;
		this.asu = asu;
	}

	public int getMcc() {
		return mcc;
	}

	public void setMcc(int mcc) {
		this.mcc = mcc;
	}

	public int getMnc() {
		return mnc;
	}

	public void setMnc(int mnc) {
		this.mnc = mnc;
	}

	public int getLac() {
		return lac;
	}

	public void setLac(int lac) {
		this.lac = lac;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getAsu() {
		return asu;
	}

	public void setAsu(int asu) {
		this.asu = asu;
	}

	@Override
	public CellType type() {
		return CellType.GSM;
	}

	@Override
	public String toLogStr() {
		return String.format("%s,%s,%s,%s", mcc, mnc, lac, cid);
	}
}
