package com.lynx.mapper.cb.weather;

/**
 * @author chris.liu
 * @version 3/23/14 3:16 PM
 */
public class WeatherCityPO {
	private int id;
	private String nameCH; // 城市中文名
	private String nameEN; // 城市英文名
	private String code; // 天气网城市编号

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameCH() {
		return nameCH;
	}

	public void setNameCH(String nameCH) {
		this.nameCH = nameCH;
	}

	public String getNameEN() {
		return nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
