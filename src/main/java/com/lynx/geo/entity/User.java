package com.lynx.geo.entity;

/**
 * 
 * @author zhufeng.liu
 * @version 13-8-22 上午10:12
 */
public class User {
	private int id;
	private String name;
	private int sex;

	public User(int id, String name, int sex) {
		this.id = id;
		this.name = name;
		this.sex = sex;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}
}
