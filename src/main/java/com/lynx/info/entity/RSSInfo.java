package com.lynx.info.entity;

import com.google.gson.annotations.Expose;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-12-2
 * Time: 上午11:51
 */
public class RSSInfo {
    @Expose
    private String title; // 标题
    @Expose
    private String summary; // 摘要
    @Expose
    private String link; // 链接
    @Expose
    private long pubDate; // 发布时间

    public RSSInfo(String title, String summary, String link, long pubDate) {
        this.title = title;
        this.summary = summary;
        this.link = link;
        this.pubDate = pubDate;
    }
}
