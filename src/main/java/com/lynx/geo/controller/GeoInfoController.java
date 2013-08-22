package com.lynx.geo.controller;

import com.lynx.geo.entity.Cell;
import com.lynx.geo.entity.Cell.CellType;
import com.lynx.geo.entity.Wifi;
import com.lynx.geo.service.GeoService;
import com.lynx.geo.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 13-8-8 下午3:16
 */
@Controller
public class GeoInfoController {

    @Autowired
    private GeoService geoService;

    @RequestMapping(value = "/location", method = RequestMethod.POST)
    public ResponseEntity<String> location(@RequestParam("ua") String ua,
                                           @RequestParam("token") String token,
                                           @RequestParam("gsm") String gsm,
                                           @RequestParam("cdma") String cdma,
                                           @RequestParam("wifi") String wifi) {
        String result = null;
        try {
            List<Cell> gsmCells = FormatUtil.parseCells(CellType.GSM, gsm);
            List<Cell> cdmaCells = FormatUtil.parseCells(CellType.CDMA, cdma);
            List<Wifi> wifis = FormatUtil.parseWifi(wifi);
//                             geoService.locate()
        } catch (Exception e) {

        }
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/lbsAd", method = RequestMethod.POST)
    public ResponseEntity<String> lbsAd(@RequestParam("lat") double lat,
                                      @RequestParam("lng") double lng) {
        String result = null;
        try {

        } catch (Exception e) {

        }
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

}
