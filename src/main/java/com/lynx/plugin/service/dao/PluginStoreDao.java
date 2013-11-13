package com.lynx.plugin.service.dao;

import com.lynx.plugin.entity.Plugin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-11-13 下午5:46
 */
public interface PluginStoreDao {

	List<Plugin> getAllPlugins();

	List<Plugin> getPluginByCategory(@Param(value = "category") int category);

	List<Plugin> getPluginByKeyword(@Param(value = "keyworrd") String keyword);
}
