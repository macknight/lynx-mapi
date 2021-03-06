package com.lynx.geo.entity;

/**
 * 
 * @author zhufeng.liu
 * @version 13-8-14 下午3:45
 */
public class NameValuePair<T> {
	private String name;
	private T value;

	public NameValuePair(String name, T value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}
