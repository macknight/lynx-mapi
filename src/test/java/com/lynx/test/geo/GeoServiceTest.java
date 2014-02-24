package com.lynx.test.geo;

import com.lynx.core.Result;
import com.lynx.core.util.EncryptUtil;
import com.lynx.geo.entity.*;
import com.lynx.geo.service.GeoService;
import com.lynx.geo.dao.LocationDao;
import com.lynx.test.BasicTest;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.*;
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
    private LocationDao locationDao;


    @Test
    public void geoCodingTest() {
        Address addr = new Address("上海市", "上海市", "长宁区", "安化路", "492");
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
    public void cellLocation() {
        Cell cdma = new CDMACell(460, 13840, 16, 12941, 0, 0);
        Cell gsm = new GSMCell(460, 0, 61, 139, 0);

        List<Cell> cells = new ArrayList<Cell>();
        cells.add(cdma);
        List<Location> locs = geoService.location(cells, null, null);
        Result result = new Result(Result.RS_OK, locs);
        System.out.println("cdma:" + EncryptUtil.format(result));

        cells.clear();
        cells.add(gsm);
        locs = geoService.location(cells, null, null);
        result = new Result(Result.RS_OK, locs);
        System.out.println("gsm:" + EncryptUtil.format(result));
    }

    @Test
    public void updateGeoInfoTest() {
        try {
            locationDao.updateCDMAAddress(460, 13840, 12941, 16, "上海市|上海市|闵行区|华翔路|2025弄-18");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void newsparseTest() throws IOException, SAXException,
            TikaException {
        String PATH = "e:\\test.htm";

        String OUTPATH = PATH + ".out";
        Parser parser = new HtmlParser();
        /**
         * 处理指定编码的html.
         */
        InputStream iStream = new BufferedInputStream(new FileInputStream(
                new File(PATH)));
        OutputStream oStream = new BufferedOutputStream(new FileOutputStream(
                new File(OUTPATH)));
        ContentHandler iHandler = new BodyContentHandler(oStream);
        Metadata meta = new Metadata();
        meta.add(Metadata.CONTENT_ENCODING, "utf-8");
        parser.parse(iStream, iHandler, meta, new ParseContext());
    }
}
