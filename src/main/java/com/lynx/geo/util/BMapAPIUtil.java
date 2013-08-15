package com.lynx.geo.util;

import com.lynx.geo.entity.Address;
import com.lynx.geo.entity.GeoPoint;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-8-13 下午5:25
 */
public class BMapAPIUtil {

    private static final String BMAP_API_KEY = "EAaacd071ed10ccc5653a49b9fbd2923";
    private static final String BMAP_API_GEOCODING_URL = "http://api.map.baidu.com/geocoder/v2/?address=%s&output=json&ak=%s";
    private static final String BMAP_API_REVGEOCODING_URL = "http://api.map.baidu.com/geocoder/v2/?ak=%s&callback=renderReverse&location=%s,%s&output=json&pois=1";


    public static String revGeoCoding(GeoPoint geoPoint) {
        String url = String.format(BMAP_API_GEOCODING_URL, BMAP_API_KEY, geoPoint.getLat(), geoPoint.getLng());
        Address result = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        InputStream instream = null;
        try {
            HttpResponse httpResp = httpClient.execute(httpGet);
            if (httpResp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                instream = httpResp.getEntity().getContent();
                String tmp = FormatUtil.stream2string(instream, "UTF-8");
                System.out.println(tmp);
                JSONObject jo = new JSONObject(tmp);

            } else {

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
                httpGet = null;
            }
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
                httpClient = null;
            }
        }
        return null;
    }

    public static GeoPoint geoCoding(String address) {
        String url = String.format(BMAP_API_GEOCODING_URL, address, BMAP_API_KEY);
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        InputStream instream = null;
        try {
            HttpResponse httpResp = httpClient.execute(httpGet);
            if (httpResp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                instream = httpResp.getEntity().getContent();
                String tmp = FormatUtil.stream2string(instream, "UTF-8");
                JSONObject jo = new JSONObject(tmp);
                if (jo.getInt("status") == 0) {
                    JSONObject joLoc = jo.getJSONObject("result").getJSONObject("location");
                    double lat = joLoc.getDouble("lat");
                    double lng = joLoc.getDouble("lng");
                    return new GeoPoint(lat, lng);
                }
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
}
