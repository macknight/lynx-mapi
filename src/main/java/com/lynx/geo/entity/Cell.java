package com.lynx.geo.entity;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 13-8-8 下午5:49
 */
public interface Cell {

    /**
     * 获取基站类型
     *
     * @return
     */
    CellType type();

	String toLogStr();


    public enum CellType {
        UNKNOWN, GSM, CDMA
    }
}
