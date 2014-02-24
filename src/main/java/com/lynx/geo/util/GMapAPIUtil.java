package com.lynx.geo.util;

import com.google.gson.annotations.Expose;
import com.lynx.core.util.EncryptUtil;
import com.lynx.geo.entity.GeoPoint;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author zhufeng.liu
 * 
 * @version 13-8-14 下午2:03
 */
public class GMapAPIUtil {

	private static final String GMAP_API_REGION = "http://ditu.google.cn/maps/br?brtype=2&brstart=0x31508e64e5c642c1:0x951daa7c349f366f&brv=25.1-549461b1_212dafdc_d6d3fe92_88a7ae9f_a1fc3f75&jsv=460c&ll=%s,%s&spn=0,0&z=0&vpsrc=0&hl=zh-CN&gl=cn";

	public static List<String> geoarea(GeoPoint geoPoint, int level) {
		String url = String.format(GMAP_API_REGION, geoPoint.getLat(), geoPoint.getLng());
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		InputStream instream = null;
		try {
			HttpResponse httpResp = httpClient.execute(httpGet);
			if (httpResp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				instream = httpResp.getEntity().getContent();
				String tmp = FormatUtil.stream2string(instream, "GBK");
				JSONObject jo = new JSONObject(tmp);
				List<String> provinces = parseProvince(jo, level);
				return provinces;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (instream != null) {
				try {
					instream.close();
				} catch (Exception e) {
				}
			}
			if (httpGet != null) {
				httpGet = null;
			}
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
				httpClient = null;
			}
		}
		return null;
	}

	/**
	 * @param data
	 * @param level
	 *            0:country;1:province;2:city;3:subcity
	 * @return
	 */
	private static List<String> parseProvince(JSONObject data, int level) {
		try {
			JSONArray jaLevel = data.getJSONArray("levels");
			if (jaLevel.length() >= level + 1) {
				List<String> areas = new ArrayList<String>();
				JSONObject joSubLevel = jaLevel.getJSONObject(level);
				JSONArray jaSubNode = joSubLevel.getJSONArray("next_level_nodes");
				for (int i = 0; i < jaSubNode.length(); ++i) {
					String area = jaSubNode.getJSONObject(i).getJSONObject("name")
							.getString("display_text");
					areas.add(area);
				}
				return areas;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void fetchChinaRegionRelation() {
		GeoPoint origin = new GeoPoint(31.866942260687, 117.28269909168);
		GeoArea china = new GeoArea();
		china.setRegionName("中国");
		List<String> provinces = GMapAPIUtil.geoarea(origin, 1);
		if (provinces == null || provinces.size() == 0) {
			return;
		}
		List<GeoArea> gaProvinces = new ArrayList<GeoArea>();
		for (String province : provinces) {
			GeoPoint cityCenter = BMapAPIUtil.geocoding(province);
			if (cityCenter == null) {
				continue;
			}
			List<String> cities = GMapAPIUtil.geoarea(cityCenter, 2);
			if (cities == null || cities.size() == 0) {
				continue;
			}
			List<GeoArea> gaCities = new ArrayList<GeoArea>();
			for (String city : cities) {
				GeoPoint subCityCenter = BMapAPIUtil.geocoding(city);
				List<String> subCities = GMapAPIUtil.geoarea(subCityCenter, 3);
				if (subCities == null || subCities.size() == 0) {
					continue;
				}
				List<GeoArea> gaSubCities = new ArrayList<GeoArea>();
				for (String subCity : subCities) {
					GeoArea gaSubCity = new GeoArea(subCity, null);
					gaSubCities.add(gaSubCity);
				}
				GeoArea gaCity = new GeoArea(city, gaSubCities);
				gaCities.add(gaCity);
			}
			GeoArea gaProvince = new GeoArea(province, gaCities);
			System.out.println(gaProvince);
			gaProvinces.add(gaProvince);
		}
		china.setSubRegion(gaProvinces);
	}

	private static class GeoArea {
		@Expose
		private String name;
		@Expose
		private List<GeoArea> subRegion;

		public GeoArea() {

		}

		public GeoArea(String name, List<GeoArea> subRegion) {
			this.name = name;
			this.subRegion = subRegion;
		}

		public String getName() {
			return name;
		}

		public void setRegionName(String name) {
			this.name = name;
		}

		public List<GeoArea> getSubRegion() {
			return subRegion;
		}

		public void setSubRegion(List<GeoArea> subRegion) {
			this.subRegion = subRegion;
		}

		@Override
		public String toString() {
			return EncryptUtil.format(this);
		}
	}
}
