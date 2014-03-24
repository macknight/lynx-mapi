package com.lynx.geo.util;

import com.lynx.geo.entity.Address;
import com.lynx.geo.entity.GeoPoint;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author zhufeng.liu
 * @version 13-8-13 下午5:25
 */
public class BMapAPIUtil {

	private static final String BMAP_API_KEY = "EAaacd071ed10ccc5653a49b9fbd2923";
	private static final String BMAP_API_GEOCODER = "http://api.map.baidu.com/geocoder/v2/";

	public static Address revGeocoding(GeoPoint geoPoint) {
		return revGeocoding(geoPoint.getLat(), geoPoint.getLng());
	}

	public static Address revGeocoding(double lat, double lng) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = null;
		InputStream instream = null;
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("ak", BMAP_API_KEY));
			params.add(new BasicNameValuePair("location", lat + "," + lng));
			params.add(new BasicNameValuePair("output", "json"));
			params.add(new BasicNameValuePair("pois", "0"));
			String param = URLEncodedUtils.format(params, "UTF-8");
			httpGet = new HttpGet(String.format("%s?%s", BMAP_API_GEOCODER, param));
			HttpResponse httpResp = httpClient.execute(httpGet);

			if (httpResp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				return null;
			}

			instream = httpResp.getEntity().getContent();
			String result = FormatUtil.stream2string(instream, "utf-8");
			JSONObject joResult = new JSONObject(result);
			if (joResult == null || joResult.getInt("status") != 0) {
				return null;
			}
			JSONObject joAddr = joResult.getJSONObject("result").getJSONObject("addressComponent");
			String province = joAddr.getString("province");
			String city = joAddr.getString("city");
			String district = joAddr.getString("district");
			String street = joAddr.getString("street");
			String streetNo = joAddr.getString("street_number");

			if (StringUtils.isNotBlank(city) && StringUtils.isNotBlank(district)
					&& StringUtils.isNotBlank(province) && StringUtils.isNotBlank(street)) {
                Address addr = new Address();
                addr.setProvince(province);
                addr.setCity(city);
                addr.setRegion(district);
                addr.setStreet(street);
                addr.setNum(streetNo);
				return addr;
			}
		} catch (Exception e) {

		} finally {
			if (instream != null) {
				try {
					instream.close();
				} catch (Exception e) {
				}
			}
			if (httpGet != null) {
				httpGet.abort();
				httpGet = null;
			}
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
				httpClient = null;
			}
		}
		return null;
	}

	public static GeoPoint geocoding(String address) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(BMAP_API_GEOCODER);
		InputStream instream = null;
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("ak", BMAP_API_KEY));
			params.add(new BasicNameValuePair("address", address));
			params.add(new BasicNameValuePair("output", "json"));
			String param = URLEncodedUtils.format(params, "UTF-8");
			httpGet = new HttpGet(String.format("%s?%s", BMAP_API_GEOCODER, param));

			HttpResponse httpResp = httpClient.execute(httpGet);
			if (httpResp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				instream = httpResp.getEntity().getContent();
				String tmp = FormatUtil.stream2string(instream, "UTF-8");
				JSONObject jo = new JSONObject(tmp);
				if (jo.getInt("status") != 0) {
					return null;
				}
				JSONObject joLoc = jo.getJSONObject("result").getJSONObject("location");
				double lat = joLoc.getDouble("lat");
				double lng = joLoc.getDouble("lng");
				return new GeoPoint(lat, lng);
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
				httpGet.abort();
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
	 * 根据给定地址给出周边POI推荐
	 * 
	 * @param address
	 * @param city
	 */
	public static void placeSuggession(String address, String city) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(BMAP_API_GEOCODER);
		InputStream instream = null;
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("ak", BMAP_API_KEY));
			params.add(new BasicNameValuePair("output", "json"));
			params.add(new BasicNameValuePair("address", address));
			params.add(new BasicNameValuePair("city", city));
			String param = URLEncodedUtils.format(params, "UTF-8");
			httpGet = new HttpGet(String.format("%s?%s", BMAP_API_GEOCODER, param));

			HttpResponse httpResp = httpClient.execute(httpGet);
			if (httpResp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				instream = httpResp.getEntity().getContent();
				String tmp = FormatUtil.stream2string(instream, "UTF-8");
				JSONObject jo = new JSONObject(tmp);
				if (jo.getInt("status") != 0) {
					return;
				}
				JSONObject joLoc = jo.getJSONObject("result").getJSONObject("location");
				double lat = joLoc.getDouble("lat");
				double lng = joLoc.getDouble("lng");
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
				httpGet.abort();
				httpGet = null;
			}
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
				httpClient = null;
			}
		}
	}

}
