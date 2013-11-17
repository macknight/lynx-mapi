package com.lynx.dexconfig.entity;

import com.google.gson.annotations.Expose;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-11-17 下午2:15
 */
public class Config {
    @Expose
    private String module;
    @Expose
    private int version;
    @Expose
    private String url;
    @Expose
    private String md5;
    @Expose
    private String desc;
    @Expose
    private String clazz;

    public Config() {

    }

    public Config(String module, int version, String url, String md5, String desc, String clazz) {
        this.module = module;
        this.version = version;
        this.url = url;
        this.md5 = md5;
        this.desc = desc;
        this.clazz = clazz;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
