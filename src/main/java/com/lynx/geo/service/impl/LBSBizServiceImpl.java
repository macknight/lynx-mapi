package com.lynx.geo.service.impl;

import com.lynx.core.BasicService;
import com.lynx.geo.entity.Campaign;
import com.lynx.geo.service.LBSBizService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-8-19 上午10:20
 */
@Service("lbsBizService")
public class LBSBizServiceImpl extends BasicService implements LBSBizService {
    @Override
    public List<Campaign> campaignNearby(double lat, double lng, int range) {
        List<Campaign> camps = null;
        try {

        } catch (Exception e) {

        }

        return camps;
    }
}
