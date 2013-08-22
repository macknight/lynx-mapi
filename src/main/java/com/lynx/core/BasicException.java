package com.lynx.core;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-8-19 上午10:23
 */
public class BasicException extends Exception {
    protected int errCode; // 错误码

    public BasicException(String message, int errCode) {
        super(message);
        this.errCode = errCode;
    }
}
