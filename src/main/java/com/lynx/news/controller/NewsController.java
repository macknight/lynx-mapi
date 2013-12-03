package com.lynx.news.controller;

import com.lynx.core.BasicController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-12-2
 * Time: 上午11:50
 */
@Controller
@RequestMapping(value = "/news", method = RequestMethod.POST,
        produces = "text/plain;charset=UTF-8")
public class NewsController extends BasicController {


}
