package com.lynx.dexconfig.service;

import com.lynx.dexconfig.entity.Config;

import java.util.List;

/**
 * Created by chris.liu
 * Created at 13-10-27-下午12:10.
 */
public interface DexConfigService {

    /**
     * 获取android service动态更新配置
     *
     * @param ua
     * @param token
     * @return
     */
    List<Config> getDexServiceConfig(String ua, String token);

}
