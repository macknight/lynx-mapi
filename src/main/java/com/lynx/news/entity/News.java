package com.lynx.news.entity;

import com.google.gson.annotations.Expose;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-12-2
 * Time: 上午11:51
 */
public class News {
    @Expose
    private String title; // 标题
    @Expose
    private String summary; // 摘要
    @Expose
    private String imgUrl;

    public News(String title, String summary, String imgUrl) {
        this.title = title;
        this.summary = summary;
        this.imgUrl = imgUrl;
    }
}
