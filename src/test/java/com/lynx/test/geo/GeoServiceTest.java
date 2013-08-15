package com.lynx.test.geo;

import com.lynx.geo.entity.Address;
import com.lynx.geo.entity.GeoPoint;
import com.lynx.geo.service.GeoService;
import com.lynx.geo.util.FormatUtil;
import com.lynx.geo.util.GMapAPIUtil;
import com.lynx.test.BasicTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-8-13 下午5:51
 */
public class GeoServiceTest extends BasicTest {

    @Autowired
    private GeoService geoService;


    @Test
    public void bmapGeoCodingTest() {
        Address addr = new Address("中国", "上海市", "上海市", "长宁区", "安化路", 492);
        GeoPoint result = geoService.geoCoding(addr);
        if (result != null) {
            System.out.println(FormatUtil.format(result));
        }

        addr = new Address();
        addr.setCity("中国");
        result = geoService.geoCoding(addr);
        if (result != null) {
            System.out.println(FormatUtil.format(result));
        }

        addr = new Address();
        addr.setCity("上海");
        result = geoService.geoCoding(addr);
        if (result != null) {
            System.out.println(FormatUtil.format(result));
        }

        addr = new Address();
        addr.setCity("合肥");
        result = geoService.geoCoding(addr);
        if (result != null) {
            System.out.println(FormatUtil.format(result));
        }

        addr = new Address();
        addr.setCity("重庆");
        result = geoService.geoCoding(addr);
        if (result != null) {
            System.out.println(FormatUtil.format(result));
        }
    }

    @Test
    public void bmapRevGeoCodingTest() {
        GeoPoint gepPoint = new GeoPoint(31.483424, 121.322987);
    }


    @Test
    public void gmapRegionRelationTest() {
        GMapAPIUtil.fetchChinaRegionRelation();
    }
}