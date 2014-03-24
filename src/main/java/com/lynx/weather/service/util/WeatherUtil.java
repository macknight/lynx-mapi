package com.lynx.weather.service.util;

import com.lynx.geo.util.FormatUtil;
import com.lynx.weather.entity.WeatherInfo;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * @author chris.liu
 * @version 3/24/14 1:33 PM
 */
public class WeatherUtil {

    private static final String WEATHER_API = "http://m.weather.com.cn/data/#citycode#.html";

    public static WeatherInfo getCityWeatherInfo(String cityCode) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = null;
        InputStream instream = null;
        try {
            httpGet = new HttpGet(WEATHER_API.replace("#citycode#", cityCode));
            HttpResponse httpResp = httpClient.execute(httpGet);

            if (httpResp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return null;
            }

            instream = httpResp.getEntity().getContent();
            String result = FormatUtil.stream2string(instream, "utf-8");
            JSONObject joResult = new JSONObject(result).getJSONObject("weatherinfo");
            String temp1 = joResult.getString("temp1");
            String temp2 = joResult.getString("temp2");
            String temp3 = joResult.getString("temp3");
            String temp4 = joResult.getString("temp4");
            String temp5 = joResult.getString("temp5");
            String temp6 = joResult.getString("temp6");

            String weather1 = joResult.getString("weather1");
            String weather2 = joResult.getString("weather2");
            String weather3 = joResult.getString("weather3");
            String weather4 = joResult.getString("weather4");
            String weather5 = joResult.getString("weather5");
            String weather6 = joResult.getString("weather6");

            String wind1 = joResult.getString("wind1");
            String wind2 = joResult.getString("wind2");
            String wind3 = joResult.getString("wind3");
            String wind4 = joResult.getString("wind4");
            String wind5 = joResult.getString("wind5");
            String wind6 = joResult.getString("wind6");

            String suggestion = joResult.getString("index_d");
            WeatherInfo weatherInfo = new WeatherInfo();
            weatherInfo.setTemp(new String[]{temp1, temp2, temp3, temp4, temp5, temp6});
            weatherInfo.setWeather(new String[]{weather1, weather2, weather3, weather4, weather5, weather6});
            weatherInfo.setWind(new String[]{wind1, wind2, wind3, wind4, wind5, wind6});
            weatherInfo.setSuggestion(suggestion);
            return weatherInfo;
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
}
