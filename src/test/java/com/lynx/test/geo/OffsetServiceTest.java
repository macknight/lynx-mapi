package com.lynx.test.geo;

import com.lynx.geo.entity.Coord.CoordType;
import com.lynx.geo.entity.GeoPoint;
import com.lynx.geo.service.OffsetService;
import com.lynx.geo.util.FormatUtil;
import com.lynx.test.BasicTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-8-15 下午4:55
 */
public class OffsetServiceTest extends BasicTest {

    @Autowired
    private OffsetService offsetService;

    @Test
    public void gps2AMapTest() {
        GeoPoint origin = new GeoPoint();
        GeoPoint offset = offsetService.offset(origin, CoordType.GPS, CoordType.GAODE);
        if (offset != null) {
            System.out.println(FormatUtil.format(offset));
        }
    }

    @Test
    public void gps2BMapTest() {
        GeoPoint origin = new GeoPoint();
        GeoPoint offset = offsetService.offset(origin, CoordType.GPS, CoordType.BAIDU);
        if (offset != null) {
            System.out.println(FormatUtil.format(offset));
        }
    }

    @Test
    public void gps2GMapTest() {
        GeoPoint origin = new GeoPoint();
        GeoPoint offset = offsetService.offset(origin, CoordType.GPS, CoordType.GOOGLE);
        if (offset != null) {
            System.out.println(FormatUtil.format(offset));
        }
    }

    @Test
    public void gps2MapbarTest() {
        GeoPoint origin = new GeoPoint();
        GeoPoint offset = offsetService.offset(origin, CoordType.GPS, CoordType.MAPBAR);
        if (offset != null) {
            System.out.println(FormatUtil.format(offset));
        }
    }
}
