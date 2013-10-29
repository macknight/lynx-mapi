package com.lynx.config.service.entity;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 13-10-28 下午7:05
 */
public class AndroidFrameworkConfig {
    @Expose
    private List<ConfigEntity> service;
    @Expose
    private List<ConfigEntity> ui;

    public AndroidFrameworkConfig(List<ConfigEntity> service, List<ConfigEntity> ui) {
        this.service = service;
        this.ui = ui;
    }

    public List<ConfigEntity> getService() {
        return service;
    }

    public void setService(List<ConfigEntity> service) {
        this.service = service;
    }

    public List<ConfigEntity> getUi() {
        return ui;
    }

    public void setUi(List<ConfigEntity> ui) {
        this.ui = ui;
    }
}
