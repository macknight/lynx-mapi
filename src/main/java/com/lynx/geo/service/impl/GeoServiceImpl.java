package com.lynx.geo.service.impl;

import com.lynx.core.BasicService;
import com.lynx.geo.entity.*;
import com.lynx.geo.service.GeoService;
import com.lynx.geo.service.dao.LocateDao;
import com.lynx.geo.service.dao.RGCDao;
import com.lynx.geo.util.BMapAPIUtil;
import com.lynx.geo.util.GeoUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-8-8 下午3:38
 */
@Service("geoService")
public class GeoServiceImpl extends BasicService implements GeoService {

	@Autowired
	private LocateDao locateDao;

	@Autowired
	private RGCDao rgcDao;

//	private Logger locateLog = Logger.getLogger("locate-log");
//	private Logger rgcLog = Logger.getLogger("rgc-log");

	@PostConstruct
	private void init() {

	}

	@Override
	public Address revGeocoding(Coord coord) {
		BMapAPIUtil.revGeocoding(coord.getGeoPoint());
		return null;
	}

	@Override
	public GeoPoint geocoding(Address address) {
		String addr = "";
		addr = address.getCountry() == null ? addr : String.format("%s%s", addr, address.getCountry());
		addr = address.getProvince() == null ? addr : String.format("%s%s", addr, address.getProvince());
		addr = address.getCity() == null ? addr : String.format("%s%s", addr, address.getCity());
		addr = address.getSubCity() == null ? addr : String.format("%s%s", addr, address.getSubCity());
		addr = address.getStreet() == null ? addr : String.format("%s%s", addr, address.getStreet());
		addr = address.getNum() == null ? addr : String.format("%s%s", addr, address.getNum());
		return BMapAPIUtil.geocoding(addr);
	}

	@Override
	public List<GeoPoint> locate(List<Cell> cells, List<Wifi> wifis, List<Coord> coords) {
//		locate2log(cells, wifis, coords);

		List<GeoPoint> result = new ArrayList<GeoPoint>();

		// 获取最佳基站定位
		try {
			GeoPoint cellLocate = getCellLocate(cells);
			if (cellLocate != null) {
				result.add(cellLocate);
			}
		} catch (Exception e) {

		}

		// 获取最佳Wifi定位
		try {
			GeoPoint wifiLocate = getWifiLocate(wifis);
			if (wifiLocate != null) {
				result.add(wifiLocate);
			}
		} catch (Exception e) {

		}

		// TODO: 需加入综合定位

		return result.size() == 0 ? null : result;
	}

	private GeoPoint getCellLocate(List<Cell> cells) throws Exception {
		Map<Cell, GeoPoint> result = new HashMap<Cell, GeoPoint>();
		for (Cell cell : cells) {
			switch (cell.type()) {
				case CDMA: {
					CDMACell cdma = (CDMACell) cell;
					GeoPoint gp = locateDao.getCDMALocate(cdma.getMcc(), cdma.getSid(),
							cdma.getNid(), cdma.getBid());
					if (gp != null) {
						result.put(cell, gp);
					}
					break;
				}
				case GSM: {
					GSMCell gsm = (GSMCell) cell;
					GeoPoint gp = locateDao.getCDMALocate(gsm.getMcc(), gsm.getMnc(),
							gsm.getLac(), gsm.getCid());
					if (gp != null) {
						result.put(cell, gp);
					}
					break;
				}
				default:
					break;
			}
		}
		return getBstCellCoord(result);
	}

	private GeoPoint getBstCellCoord(Map<Cell, GeoPoint> cellCoords) {
		if (cellCoords == null || cellCoords.size() == 0) {
			return null;
		}

		List<GeoPoint> coords = new ArrayList<GeoPoint>();
		for (Cell cell : cellCoords.keySet()) {
			coords.add(cellCoords.get(cell));
		}

		double minDist = Double.MAX_VALUE;
		int idx = -1;
		for (int i = 0; i < coords.size(); ++i) {
			double dists = 0;
			for (int j = 0; j < coords.size(); ++j) {
				dists += GeoUtil.distanceTo(coords.get(i),
						coords.get(j));
			}
			if (minDist > dists) {
				minDist = dists;
				idx = i;
			}
		}
		if (idx > -1) {
			return coords.get(idx);
		}
		return null;
	}

	private GeoPoint getWifiLocate(List<Wifi> wifis) throws Exception {
		List<Long> macs = new ArrayList<Long>();
		for (Wifi wifi : wifis) {
			macs.add(GeoUtil.mac2long(wifi.getMac()));
		}
		List<GeoPoint> result = locateDao.getWifiLocate(macs);
		return getBstWifiCoord(result);
	}

	private GeoPoint getBstWifiCoord(List<GeoPoint> coords) {
		if (coords == null || coords.size() == 0) {
			return null;
		}
		double minDist = Double.MAX_VALUE;
		int idx = -1;
		for (int i = 0; i < coords.size(); ++i) {
			double dists = 0;
			for (int j = 0; j < coords.size(); ++j) {
				dists += GeoUtil.distanceTo(coords.get(i), coords.get(j));
			}
			if (minDist > dists) {
				minDist = dists;
				idx = i;
			}
		}
		if (idx > -1) {
			return coords.get(idx);
		}
		return null;
	}

	// http://api.map.baidu.com/geocoder/v2/?ak=您的密钥&callback=renderOption&output=json&address=百度大厦&city=北京市
	private List<Place> getSuggestionPlaces(double lat, double lng) {
		List<Place> result = null;
		try {

		} catch (Exception e) {
			result = null;
		}
		return result;
	}

	private void locate2log(List<Cell> cells, List<Wifi> wifis, List<Coord> coords) {
		StringBuilder sb = new StringBuilder();
		// add cell info
		String cellsInfo = cells2log(cells);
		if (StringUtils.isNotBlank(cellsInfo)) {
			sb.append(cellsInfo);
		}

		// add wifi info
		String wifisInfo = wifis2log(wifis);
		if (StringUtils.isNotBlank(wifisInfo)) {
			sb.append("&");
			sb.append(wifisInfo);
		}

		// add external locate coordinate info
		if (coords != null && coords.size() > 0) {
			String str = "";
			for (Coord coord : coords) {
				str = String.format("%s|%s", str, coord.toString());
			}
			if (str.length() > 1) {
				sb.append("&");
				sb.append(String.format("coords=%s", str.substring(1)));
			}
		}

//		locateLog.info(sb.toString());
	}

	private String cells2log(List<Cell> cells) {
		if (cells == null) {
			return "";
		}
		String type = cells.get(0).type().name();
		String str = "";
		for (Cell sbcell : cells) {
			str = String.format("%s|%s", str, sbcell.toLogStr());
		}
		return String.format("%s=%s", type, str.substring(1));
	}

	private String wifis2log(List<Wifi> wifis) {
		if (wifis == null || wifis.size() == 0) {
			return "";
		}
		String str = "";
		for (Wifi wifi : wifis) {
			str = String.format("%s|%s", str, wifi.toString());
		}
		return String.format("wifi=%s", str.substring(1));
	}
}
