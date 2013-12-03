package com.lynx.news.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-12-2
 * Time: 上午11:57
 */
public class SinaNewsUtil {

    private static HttpClient httpClient = new DefaultHttpClient();
    private static final String SINA_RSS_URL = "http://rss.sina.com.cn/sina_all_opml.xml";

    /**
     * 获取新浪所有RSS
     */
    public static void parseSinaRSS() {
        try {
            String xml = getSinaRSS();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getSinaRSS() throws Exception {
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(SINA_RSS_URL);
            httpGet.setHeader("Content-Type", "text/xml; charset=utf-8");
            HttpResponse resp = httpClient.execute(httpGet);
            String respXML = EntityUtils.toString(resp.getEntity());
            System.out.println(respXML);
            return respXML;
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpGet != null) {
                httpGet.abort();
            }
            if (httpClient != null) {
                httpClient.getConnectionManager().closeExpiredConnections();
            }
        }
    }

}
