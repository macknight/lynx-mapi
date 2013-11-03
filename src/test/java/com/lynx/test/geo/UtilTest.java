package com.lynx.test.geo;

import com.lynx.core.util.EncryptUtil;
import com.lynx.geo.entity.GeoPoint;
import com.lynx.geo.util.BMapAPIUtil;
import com.lynx.geo.util.FormatUtil;
import com.lynx.geo.util.GMapAPIUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-8-14 上午10:00
 */
public class UtilTest {

    @Test
    public void unicodeTest() {
        String unicode = "\\u9053\\u8def";
        String str = FormatUtil.fromUnicode(unicode);
        System.out.println(str);


        String tmp = "renderReverse&&renderReverse({})";
        System.out.println(tmp.substring(29, tmp.length() - 1));
    }

    @Test
    public void bmapGeocodingTest() {
        String addr = "上海市闵行区东美南路4号";
        System.out.println(EncryptUtil.format(BMapAPIUtil.geocoding(addr)));
    }

    @Test
    public void bmapRevGeoCodingTest() {
        GeoPoint geoPoint = new GeoPoint(31.222784, 121.340507);
        System.out.println(EncryptUtil.format(BMapAPIUtil.revGeocoding(geoPoint)));
    }

    @Test
    public void gmapRegionRelationTest() {
        GMapAPIUtil.fetchChinaRegionRelation();
    }

    @Test
    public void geoInfoSpider() throws Exception {
        int bufSize = 100;
        File fin = new File("D:\\data\\geo-data\\cdmacoord");
        File fout = new File("D:\\data\\geo-data\\cdmacoord-full");

        FileChannel fcin = new RandomAccessFile(fin, "r").getChannel();
        ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);

        FileChannel fcout = new RandomAccessFile(fout, "rws").getChannel();
        ByteBuffer wBuffer = ByteBuffer.allocateDirect(bufSize);


        readFileByLine(bufSize, fcin, rBuffer, fcout, wBuffer);

        System.out.print("OK!!!");
    }

    public static void readFileByLine(int bufSize, FileChannel fcin, ByteBuffer rBuffer, FileChannel fcout, ByteBuffer wBuffer) {
        String enterStr = "\n";
        try {
            byte[] bs = new byte[bufSize];

            int size = 0;
            StringBuffer strBuf = new StringBuffer("");
            //while((size = fcin.read(buffer)) != -1){
            while (fcin.read(rBuffer) != -1) {
                int rSize = rBuffer.position();
                rBuffer.rewind();
                rBuffer.get(bs);
                rBuffer.clear();
                String tempString = new String(bs, 0, rSize);
                //System.out.print(tempString);
                //System.out.print("<200>");

                int fromIndex = 0;
                int endIndex = 0;
                while ((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1) {
                    String line = tempString.substring(fromIndex, endIndex);
                    line = new String(strBuf.toString() + line);
                    String[] tmp = line.split("\\t");
                    double lat = Double.parseDouble(tmp[4]);
                    double lng = Double.parseDouble(tmp[5]);
                    String addr = getPOI(lat, lng);
                    String str = line.replaceAll("\\t", ",");
                    str = String.format("%s,%s\n", str, addr);
                    writeFileByLine(fcout, wBuffer, str);
                    strBuf.delete(0, strBuf.length());
                    fromIndex = endIndex + 1;
                }
                if (rSize > tempString.length()) {
                    strBuf.append(tempString.substring(fromIndex, tempString.length()));
                } else {
                    strBuf.append(tempString.substring(fromIndex, rSize));
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void writeFileByLine(FileChannel fcout, ByteBuffer wBuffer, String line) {
        try {
            fcout.write(wBuffer.wrap(line.getBytes()), fcout.size());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private static final String AK = "EAaacd071ed10ccc5653a49b9fbd2923";
    private static final String BMAP_URL = "http://api.map.baidu.com/geocoder/v2/?ak=%s&location=%s,%s&output=json&pois=0";


    @Test
    public void test() {
        System.out.println(getPOI(31.222784, 121.340507));
    }

    public static String getPOI(double lat, double lng) {
        String url = String.format(BMAP_URL, AK, lat, lng);

        HttpClient client = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpResp = client.execute(httpGet);
            if (httpResp != null && httpResp.getEntity() != null) {
                InputStream instream = httpResp.getEntity().getContent();
                String result = instream2string(instream);
                JSONObject joResult = new JSONObject(result);
                if (joResult != null && joResult.getInt("status") == 0) {
                    JSONObject joAddr = joResult.getJSONObject("result")
                            .getJSONObject("addressComponent");

                    String city = joAddr.getString("city");
                    String district = joAddr.getString("district");
                    String province = joAddr.getString("province");
                    String street = joAddr.getString("street");
                    String streetNo = joAddr.getString("street_number");

                    String tmp = String.format("%s|%s|%s|%s|%s", province,
                            city, district, street, streetNo);

                    return tmp;
                }
                System.out.println(result);
            }
        } catch (Exception e) {

        } finally {
            if (client != null) {
                client.getConnectionManager().shutdown();
                client = null;
            }

            if (httpGet != null) {
                httpGet.abort();
                httpGet = null;
            }
        }
        return "";
    }


    public static String instream2string(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }
}
