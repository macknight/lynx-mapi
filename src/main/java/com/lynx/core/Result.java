package com.lynx.core;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author zhufeng.liu
 * 
 * @version 13-10-26-下午2:17
 */
public class Result {
	public static final int RS_OK = 200;
	public static final int RS_FAIL = 400;
	public static final int RS_ERROR = 500;

	@Expose
	private int status;
	@Expose
	private Object data;

	public Result(int status, Object data) {
		this.status = status;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
