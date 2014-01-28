package com.lynx.geo.controller;

import com.lynx.core.BasicController;
import com.lynx.core.Result;
import com.lynx.core.util.EncryptUtil;
import com.lynx.geo.entity.Cell;
import com.lynx.geo.entity.Cell.CellType;
import com.lynx.geo.entity.Location;
import com.lynx.geo.entity.Wifi;
import com.lynx.geo.service.GeoService;
import com.lynx.geo.util.FormatUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 
 * @author zhufeng.liu
 * 
 * @version 13-8-8 下午3:16
 */
@Controller
@RequestMapping(value = "/geo", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
public class GeoInfoController extends BasicController {

	@Autowired
	private GeoService geoService;

	@RequestMapping(value = "/location")
	public ResponseEntity<String> location(
			@RequestParam(value = "gsm", required = false) String gsm,
			@RequestParam(value = "cdma", required = false) String cdma,
			@RequestParam(value = "wifi", required = false) String wifi) {
		Result result = null;
		try {
			List<Cell> cells = null;
			if (StringUtils.isNotBlank(gsm)) {
				cells = FormatUtil.parseCells(CellType.GSM, gsm);
			} else if (StringUtils.isNotBlank(cdma)) {
				cells = FormatUtil.parseCells(CellType.CDMA, cdma);
			}
			List<Wifi> wifis = FormatUtil.parseWifi(wifi);
			List<Location> locs = geoService.location(cells, wifis, null);
			if (locs != null && locs.size() > 0) {
				result = new Result(Result.RS_OK, locs);
			} else {
				result = new Result(Result.RS_FAIL, "cant locate");
			}
		} catch (Exception e) {
			log.info(e.getStackTrace());
			result = new Result(Result.RS_ERROR, "server inner error");
		}
		return new ResponseEntity<String>(EncryptUtil.format(result),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/lbsAd")
	public ResponseEntity<String> lbsAd(@RequestParam("lat") double lat,
			@RequestParam("lng") double lng) {
		String result = null;
		try {

		} catch (Exception e) {

		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
