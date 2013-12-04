package com.lynx.test.info;

import com.lynx.core.util.EncryptUtil;
import com.lynx.news.entity.Info;
import com.lynx.news.util.SinaNewsUtil;
import com.lynx.test.BasicTest;
import org.junit.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-12-3
 * Time: 下午3:09
 */
public class InfoServiceTest extends BasicTest {

    @Test
    public void sinaRSSTest() {
        try {
            SinaNewsUtil.sinaRSS();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sinaNewsTest() {
        try {
            List<Info> infos = SinaNewsUtil.sinaRssNews("http://rss.sina.com.cn/eladies/son.xml");
            for (Info info : infos) {
                System.out.println(EncryptUtil.format(info));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
