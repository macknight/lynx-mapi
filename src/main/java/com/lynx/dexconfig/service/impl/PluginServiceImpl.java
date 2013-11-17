package com.lynx.dexconfig.service.impl;

import com.lynx.dexconfig.entity.Plugin;
import com.lynx.dexconfig.service.PluginService;
import com.lynx.dexconfig.service.dao.DexConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-11-13 下午5:49
 */
@Service("pluginStoreService")
public class PluginServiceImpl implements PluginService {

    @Autowired
    private DexConfigDao dexConfigDao;

    @Override
    public List<Plugin> allPlugins() {
        return dexConfigDao.getAllPlugins();
    }

    @Override
    public List<Plugin> pluginByCategory(int category) {
        return dexConfigDao.getPluginByCategory(category);
    }

    @Override
    public List<Plugin> searchPlugin(String keyword) {
        String param = "%" + keyword + "%";
        return dexConfigDao.getPluginByKeyword(param);
    }

    @Override
    public List<Plugin> myPlugins(List<String> categorys) {
        try {
            return dexConfigDao.getMyPlugins(categorys);
        } catch (Exception e) {

        }
        return null;
    }
}
