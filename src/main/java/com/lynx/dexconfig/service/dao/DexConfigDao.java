package com.lynx.dexconfig.service.dao;

import com.lynx.dexconfig.entity.Config;
import com.lynx.dexconfig.entity.Plugin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-11-13 下午5:46
 */
public interface DexConfigDao {

    /**
     * 获取android service动态更新配置
     *
     * @return
     */
    List<Config> getDexServiceConfig();

    List<Plugin> getAllPlugins();

    List<Plugin> getPluginByCategory(@Param(value = "category") int category);

    List<Plugin> getPluginByKeyword(@Param(value = "keyword") String keyword);

    List<Plugin> getMyPlugins(@Param(value = "categories") List<String> categories);
}
