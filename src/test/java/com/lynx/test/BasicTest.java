package com.lynx.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-8-13 下午5:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:config/spring/appcontext-*.xml",
        "classpath*:config/spring/appcontext-*.xml"
})
public class BasicTest {

}
