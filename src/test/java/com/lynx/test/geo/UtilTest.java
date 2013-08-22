package com.lynx.test.geo;

import com.lynx.geo.util.FormatUtil;
import org.junit.Test;

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

}
