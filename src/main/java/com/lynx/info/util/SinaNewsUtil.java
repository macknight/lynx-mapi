package com.lynx.info.util;

import com.lynx.info.entity.Info;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-12-2
 * Time: 上午11:57
 */
public class SinaNewsUtil {

    private static HttpClient httpClient = new DefaultHttpClient();
    private static final String SINA_RSS_URL = "http://rss.sina.com.cn/sina_all_opml.xml";
    private static final SimpleDateFormat sdf;

    static {
        sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    }

    /**
     * 获取新浪所有RSS
     */
    public static void sinaRSS() {
        try {
            String xml = getSinaRSS();
            Document doc = DocumentHelper.parseText(xml);
            Element eleRoot = doc.getRootElement();
            Element eleBody = eleRoot.element("body");
            List nodes = eleBody.elements("outline");
            for (Iterator it = nodes.iterator(); it.hasNext(); ) {
                Element eleOutline = (Element) it.next();
                String title = eleOutline.attributeValue("title");
                String[] tmp = title.split("\\-");
                System.out.println(tmp[0]);

                List subNodes = eleOutline.elements("outline");
                for (Iterator subIt = subNodes.iterator(); subIt.hasNext(); ) {
                    Element eleSubOutline = (Element) subIt.next();
                    String subTitle = eleSubOutline.attributeValue("title");
                    String xmlUrl = eleSubOutline.attributeValue("xmlUrl");
                    System.out.println(subTitle + "--" + xmlUrl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    private static String getSinaRSS() throws Exception {
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(SINA_RSS_URL);
            httpGet.setHeader("Content-Type", "text/xml; charset=utf-8");
            HttpResponse resp = httpClient.execute(httpGet);
            String respXML = EntityUtils.toString(resp.getEntity(), "utf-8");
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

    public static List<Info> sinaRssNews(String data) {
        try {
            String xml = getSinaRSSNews(data);
            Document doc = DocumentHelper.parseText(xml);
            Element eleRoot = doc.getRootElement();
            Element eleBody = eleRoot.element("channel");

            List nodes = eleBody.elements("item");
            List<Info> newsList = new ArrayList<Info>();
            for (Iterator it = nodes.iterator(); it.hasNext(); ) {
                Element eleItem = (Element) it.next();
                String strTitle = eleItem.element("title").getTextTrim();
                String strDesc = eleItem.element("description").getTextTrim();
                String strPubDate = eleItem.element("pubDate").getTextTrim();
                String strLink = eleItem.element("link").getTextTrim();
                String[] tmp = strLink.split("\\=");
                long pubDate = -1l;
                try {
                    pubDate = sdf.parse(strPubDate).getTime();
                } catch (Exception e) {

                }
                Info news = new Info(strTitle, strDesc, tmp[1], pubDate);
                newsList.add(news);
            }
            return newsList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }

    private static String getSinaRSSNews(String url) throws Exception {
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(url);
            httpGet.setHeader("Content-Type", "text/xml; charset=utf-8");
            HttpResponse resp = httpClient.execute(httpGet);
            String respXML = EntityUtils.toString(resp.getEntity(), "utf-8");
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
