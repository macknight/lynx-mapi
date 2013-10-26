package com.lynx.test.geo;

import com.lynx.core.Result;
import com.lynx.core.util.EncryptUtil;
import com.lynx.geo.entity.*;
import com.lynx.geo.service.GeoService;
import com.lynx.geo.service.dao.LocateDao;
import com.lynx.geo.util.BMapAPIUtil;
import com.lynx.geo.util.GMapAPIUtil;
import com.lynx.test.BasicTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-8-13 下午5:51
 */
public class GeoServiceTest extends BasicTest {

	@Autowired
	private GeoService geoService;

	@Autowired
	private LocateDao locateDao;


	@Test
	public void bmapGeoCodingTest() {
		Address addr = new Address("中国", "上海市", "上海市", "长宁区", "安化路", 492, null);
		GeoPoint result = geoService.geocoding(addr);
		if (result != null) {
			System.out.println(EncryptUtil.format(result));
		}

		addr = new Address();
		addr.setCity("中国");
		result = geoService.geocoding(addr);
		if (result != null) {
			System.out.println(EncryptUtil.format(result));
		}

		addr = new Address();
		addr.setCity("上海");
		result = geoService.geocoding(addr);
		if (result != null) {
			System.out.println(EncryptUtil.format(result));
		}

		addr = new Address();
		addr.setCity("合肥");
		result = geoService.geocoding(addr);
		if (result != null) {
			System.out.println(EncryptUtil.format(result));
		}

		addr = new Address();
		addr.setCity("重庆");
		result = geoService.geocoding(addr);
		if (result != null) {
			System.out.println(EncryptUtil.format(result));
		}
	}

	@Test
	public void bmapRevGeoCodingTest() {
		GeoPoint geoPoint = new GeoPoint(31.222784, 121.340507);
		BMapAPIUtil.revGeocoding(geoPoint);
	}


	@Test
	public void gmapRegionRelationTest() {
		GMapAPIUtil.fetchChinaRegionRelation();
	}

	@Test
	public void cellLocate() {
		Cell cdma = new CDMACell(460, 13824, 1, 13393, 0, 0);
		Cell gsm = new GSMCell(460, 2, 384, 5090, 0);

		List<Cell> cells = new ArrayList<Cell>();
		cells.add(cdma);
		List<GeoPoint> gps = geoService.locate(cells, null, null);
		Result result = new Result(Result.RS_OK, gps);
		System.out.println("cdma:" + EncryptUtil.format(result));

		cells.clear();
		cells.add(gsm);
		gps = geoService.locate(cells, null, null);
		result = new Result(Result.RS_OK, gps);
		System.out.println("gsm:" + EncryptUtil.format(result));
	}
}
