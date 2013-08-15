package com.lynx.geo.controller;

import com.lynx.geo.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 13-8-8 下午3:16
 */
@Controller
public class GeoInfoController {

    @Autowired
    private GeoService geoService;
}
