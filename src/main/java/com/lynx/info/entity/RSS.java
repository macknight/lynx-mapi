package com.lynx.info.entity;

import com.google.gson.annotations.Expose;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-12-2
 * Time: 下午1:36
 */
public class RSS {
    @Expose
    private String text;
    @Expose
    private String title;
    @Expose
    private String url;

    public RSS(String title, String text, String url) {
        this.title = title;
        this.text = text;
        this.url = url;
    }
}
