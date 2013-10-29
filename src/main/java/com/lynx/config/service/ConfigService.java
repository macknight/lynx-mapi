package com.lynx.config.service;

import com.lynx.config.service.entity.AndroidFrameworkConfig;

/**
 * Created by chris.liu
 * Created at 13-10-27-下午12:10.
 */
public interface ConfigService {

    /**
     * 获取android ui模块以及service动态更新配置
     *
     * @param ua
     * @param token
     * @return
     */
    AndroidFrameworkConfig getAndroidFrameworkConfig(String ua, String token);

}
