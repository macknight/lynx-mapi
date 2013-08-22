package com.lynx.geo.entity;

import com.google.gson.annotations.Expose;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-8-19 上午10:16
 */
public class Campaign {
    @Expose
    private int id;
    @Expose
    private String title;
    @Expose
    private String content;
    @Expose
    private long beginTime;
    @Expose
    private long endTime;
    @Expose
    private int shopId;

    public Campaign(int id, String title, String content, long beginTime, long endTime, int shopId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.shopId = shopId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }
}
