package com.lynx.core;

/**
 * 
 * @author zhufeng.liu
 * @version 13-8-19 上午10:23
 */
public class BasicException extends Exception {
	protected int errCode; // 错误码

	public BasicException(String message, int errCode) {
		super(message);
		this.errCode = errCode;
	}
}
