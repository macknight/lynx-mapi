package com.lynx.geo.service.impl;

import com.lynx.core.BasicService;
import com.lynx.geo.entity.*;
import com.lynx.geo.service.GeoService;
import com.lynx.geo.dao.LocationDao;
import com.lynx.geo.dao.RGCDao;
import com.lynx.geo.dao.entity.LocationPo;
import com.lynx.geo.util.BMapAPIUtil;
import com.lynx.geo.util.FormatUtil;
import com.lynx.geo.util.GeoUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author zhufeng.liu
 * 
 * @addtime 13-8-8 下午3:38
 */
@Service("geoService")
public class GeoServiceImpl extends BasicService implements GeoService {

	private static final int THREAD_POOL_SIZE = 5;

	@Autowired
	private LocationDao locationDao;

	@Autowired
	private RGCDao rgcDao;

	// private Logger locateLog = Logger.getLogger("locate-log");
	// private Logger rgcLog = Logger.getLogger("rgc-log");

	private ExecutorService executorService = null;

	@PostConstruct
	private void init() {
		executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
	}

	@Override
	public Address revGeocoding(Coord coord) {
		BMapAPIUtil.revGeocoding(coord.getGeoPoint());
		return null;
	}

	@Override
	public GeoPoint geocoding(Address address) {
		String addr = "";
		addr = address.getProvince() == null ? addr : String.format("%s%s", addr,
				address.getProvince());
		addr = address.getCity() == null ? addr : String.format("%s%s", addr, address.getCity());
		addr = address.getRegion() == null ? addr : String
				.format("%s%s", addr, address.getRegion());
		addr = address.getStreet() == null ? addr : String
				.format("%s%s", addr, address.getStreet());
		addr = address.getNum() == null ? addr : String.format("%s%s", addr, address.getNum());
		return BMapAPIUtil.geocoding(addr);
	}

	@Override
	public List<Location> location(List<Cell> cells, List<Wifi> wifis, List<Coord> coords) {
		// locate2log(cells, wifis, coords);

		List<Location> result = new ArrayList<Location>();

		// 获取最佳基站定位
		try {
			if (cells != null && cells.size() > 0) {
				Location cellLocation = getCellLocation(cells);
				if (cellLocation != null) {
					result.add(cellLocation);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获取最佳Wifi定位
		if (wifis != null && wifis.size() > 0) {
			Location wifiLocation = getWifiLocation(wifis);
			if (wifiLocation != null) {
				result.add(wifiLocation);
			}
		}

		// TODO: 加入综合定位

		return result.size() == 0 ? null : result;
	}

	@Override
	public List<GSMCellInfo> gsmCellInfo(int page, int pageSize) {
		return locationDao.getGSMCellInfo(page * pageSize, pageSize);
	}

	@Override
	public List<CDMACellInfo> cdmaCellInfo(int page, int pageSize) {
		return locationDao.getCDMACellInfo(page * pageSize, pageSize);
	}

	private Location getCellLocation(List<Cell> cells) {
		try {
			Map<Cell, Location> result = new HashMap<Cell, Location>();
			for (Cell cell : cells) {
				LocationPo locPo = null;
				switch (cell.type()) {
				case CDMA: {
					CDMACell cdma = (CDMACell) cell;
					locPo = locationDao.getCDMALocation(cdma.getMcc(), cdma.getSid(),
							cdma.getNid(), cdma.getBid());
					break;
				}
				case GSM: {
					GSMCell gsm = (GSMCell) cell;
					locPo = locationDao.getGSMLocation(gsm.getMcc(), gsm.getMnc(), gsm.getLac(),
							gsm.getCid());
					break;
				}
				default:
					break;
				}
				if (locPo != null) {
					Address addr = FormatUtil.str2address(locPo.getAddr());
					if (addr == null) {
						// 需要数据补充,将请求放入线程池中执行更新
						System.out.println("need to update geo info");
						UpdateGeoInfoTask task = new UpdateGeoInfoTask(cell, locPo.getLat(),
								locPo.getLng());
						executorService.submit(task);
					}
					Location location = new Location(FormatUtil.format(locPo.getLat(), 4),
							FormatUtil.format(locPo.getLng(), 4), locPo.getAcc(), addr);
					result.put(cell, location);
				}
			}
			return getBstCellCoord(result);
		} catch (Exception e) {

		}
		return null;
	}

	private Location getBstCellCoord(Map<Cell, Location> cellLocations) {
		if (cellLocations == null || cellLocations.size() == 0) {
			return null;
		}

		List<Location> locations = new ArrayList<Location>();
		for (Cell cell : cellLocations.keySet()) {
			locations.add(cellLocations.get(cell));
		}

		double minDist = Double.MAX_VALUE;
		int idx = -1;
		for (int i = 0; i < locations.size(); ++i) {
			double dists = 0;
			for (int j = 0; j < locations.size(); ++j) {
				dists += GeoUtil.distanceTo(locations.get(i), locations.get(j));
			}
			if (minDist > dists) {
				minDist = dists;
				idx = i;
			}
		}
		if (idx > -1) {
			return locations.get(idx);
		}
		return null;
	}

	private Location getWifiLocation(List<Wifi> wifis) {
		try {
			List<Long> macs = new ArrayList<Long>();
			for (Wifi wifi : wifis) {
				macs.add(GeoUtil.mac2long(wifi.getMac()));
			}
			List<LocationPo> result = locationDao.getWifiLocation(macs);
			return getBstWifiCoord(result);
		} catch (Exception e) {

		}
		return null;
	}

	private Location getBstWifiCoord(List<LocationPo> locationPos) {
		return null;
	}

	private class UpdateGeoInfoTask implements Runnable {
		private Cell cell;
		private double lat;
		private double lng;

		public UpdateGeoInfoTask(Cell cell, double lat, double lng) {
			this.cell = cell;
			this.lat = lat;
			this.lng = lng;
		}

		@Override
		public void run() {
			try {
				Address addr = BMapAPIUtil.revGeocoding(lat, lng);
				if (addr == null) {
					return;
				}
				System.out.println(cell.toLogStr());
				if (cell instanceof CDMACell) {
					CDMACell cdma = (CDMACell) cell;
					locationDao.updateCDMAAddress(cdma.getMcc(), cdma.getSid(), cdma.getNid(),
							cdma.getBid(), FormatUtil.address2str(addr));
				} else if (cell instanceof GSMCell) {
					GSMCell gsm = (GSMCell) cell;
					locationDao.updateGSMAddress(gsm.getMcc(), gsm.getMnc(), gsm.getLac(),
							gsm.getCid(), FormatUtil.address2str(addr));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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

		// locateLog.info(sb.toString());
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
