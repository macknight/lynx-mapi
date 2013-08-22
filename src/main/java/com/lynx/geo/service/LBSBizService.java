package com.lynx.geo.service;

import com.lynx.geo.entity.Campaign;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-8-19 上午10:15
 */
public interface LBSBizService {
    List<Campaign> campaignNearby(double lat, double lng, int range) throws Exception;
}
