package com.lynx.geo.service.impl;

import com.lynx.core.BasicService;
import com.lynx.geo.service.OffsetService;
import com.lynx.geo.service.dao.OffsetDao;
import com.lynx.geo.entity.Coord;
import com.lynx.geo.entity.Coord.CoordType;
import com.lynx.geo.entity.GeoPoint;
import com.lynx.geo.util.GeoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 
 * @author zhufeng.liu
 * 
 * @version 13-8-8 下午5:23
 */
@Service("offsetService")
public class OffsetServiceImpl extends BasicService implements OffsetService {
	@Autowired
	private OffsetDao offsetDao;

	@Override
	public GeoPoint offset(GeoPoint origin, CoordType oct, CoordType dct) {
		return offset(origin.getLat(), origin.getLng(), oct, dct);
	}

	private GeoPoint offset(double lat, double lng, CoordType sct, CoordType dct) {
		GeoPoint result = new GeoPoint(lat, lng);
		switch (sct) {
		case GPS: // 原始经纬度
			switch (dct) {
			case GOOGLE:
				return getFixedGeoPoint(result, sct, dct);
			case MAPBAR:
				GeoPoint tmpGp = getFixedGeoPoint(result, sct, CoordType.GOOGLE);
				return getFixedGeoPoint(tmpGp, CoordType.GOOGLE, dct);
			case BAIDU:
			case GAODE:
				return getFixedGeoPoint(result, sct, dct);
			default:
				return result;
			}
		case GOOGLE: // google地图经纬度
			switch (dct) {
			case GPS:
				return getFixedGeoPoint(result, sct, dct);
			case MAPBAR:
				return getFixedGeoPoint(result, sct, dct);
			case BAIDU:
				GeoPoint tmpGp = getFixedGeoPoint(result, sct, CoordType.GPS);
				return getFixedGeoPoint(tmpGp, CoordType.GPS, dct);
			case GAODE:
				GeoPoint tmpGp2 = getFixedGeoPoint(result, sct, CoordType.GPS);
				return getFixedGeoPoint(tmpGp2, CoordType.GPS, dct);
			default:
				return result;
			}
		case MAPBAR: // Mapbar地图经纬度
			switch (dct) {
			case GOOGLE:
				return getFixedGeoPoint(result, sct, dct);
			case BAIDU:
				GeoPoint tmpGp = getFixedGeoPoint(result, sct, CoordType.GOOGLE);
				GeoPoint tmpGp2 = getFixedGeoPoint(tmpGp, CoordType.GOOGLE,
						CoordType.GPS);
				return getFixedGeoPoint(tmpGp2, CoordType.GPS, dct);
			case GPS:
				GeoPoint tmpGp3 = getFixedGeoPoint(result, sct,
						CoordType.GOOGLE);
				return getFixedGeoPoint(tmpGp3, CoordType.GOOGLE, dct);
			case GAODE:
				GeoPoint tmpGp4 = getFixedGeoPoint(result, sct,
						CoordType.GOOGLE);
				GeoPoint tmpGp5 = getFixedGeoPoint(tmpGp4, CoordType.GOOGLE,
						Coord.CoordType.GPS);
				return getFixedGeoPoint(tmpGp5, CoordType.GPS, dct);
			default:
				return result;
			}

		case BAIDU: // Baidu地图经纬度
			switch (dct) {
			case GOOGLE:
				GeoPoint tmpGp = getFixedGeoPoint(result, sct, CoordType.GPS);
				return getFixedGeoPoint(tmpGp, CoordType.GPS, dct);
			case MAPBAR:
				GeoPoint tmpGp2 = getFixedGeoPoint(result, sct, CoordType.GPS);
				GeoPoint tmpGp3 = getFixedGeoPoint(tmpGp2, CoordType.GPS,
						CoordType.GOOGLE);
				return getFixedGeoPoint(tmpGp3, CoordType.GOOGLE, dct);
			case GPS:
				return getFixedGeoPoint(result, sct, dct);
			case GAODE:
				GeoPoint tmpGp4 = getFixedGeoPoint(result, sct, CoordType.GPS);
				return getFixedGeoPoint(tmpGp4, CoordType.GPS, dct);
			default:
				return result;
			}
		default: // 高德地图经纬度
			switch (dct) {
			case GPS:
				return getFixedGeoPoint(result, sct, dct);
			case GOOGLE:
				GeoPoint tmpGp = getFixedGeoPoint(result, sct, CoordType.GPS);
				return getFixedGeoPoint(tmpGp, CoordType.GPS, dct);
			case BAIDU:
				GeoPoint tmpGp2 = getFixedGeoPoint(result, sct, CoordType.GPS);
				return getFixedGeoPoint(tmpGp2, CoordType.GPS, dct);
			case MAPBAR:
				GeoPoint tmpGp3 = getFixedGeoPoint(result, sct, CoordType.GPS);
				GeoPoint tmpGp4 = getFixedGeoPoint(tmpGp3, CoordType.GPS,
						CoordType.GOOGLE);
				return getFixedGeoPoint(tmpGp4, CoordType.GOOGLE, dct);
			default:
				return result;
			}
		}
	}

	/**
	 * 坐标系之间的转换
	 * 
	 * @param origin
	 * @param sct
	 * @param dct
	 * @return
	 */
	private GeoPoint getFixedGeoPoint(GeoPoint origin, CoordType sct,
			CoordType dct) {
		if (origin == null) {
			return origin;
		}

		// STEP1: format lat and lng value to xxx.xx
		DecimalFormat df = new DecimalFormat("###.00");
		df.setRoundingMode(RoundingMode.DOWN);
		double lat = GeoUtil.format(origin.getLat(), 2);
		double lng = GeoUtil.format(origin.getLng(), 2);

		// STEP3: get offset value of lat and lng
		GeoPoint offset = getOffset(lat, lng, sct, dct);
		if (offset == null) {
			return origin;
		}

		// STEP4: get fixed lat and lng value
		if ((sct == CoordType.GPS && dct == CoordType.GOOGLE)
				|| (sct == CoordType.GPS && dct == CoordType.BAIDU)
				|| (sct == CoordType.GOOGLE && dct == CoordType.MAPBAR)
				|| (sct == CoordType.GPS && dct == CoordType.GAODE)) {
			lat = GeoUtil.format(origin.getLat() + offset.getLat(), 5);
			lng = GeoUtil.format(origin.getLng() + offset.getLng(), 5);
			return new GeoPoint(lat, lng);

		} else if ((sct == CoordType.GOOGLE && dct == CoordType.GPS)
				|| (sct == CoordType.BAIDU && dct == CoordType.GPS)
				|| (sct == CoordType.MAPBAR && dct == CoordType.GOOGLE)
				|| (sct == CoordType.GAODE && dct == CoordType.GPS)) {
			lat = GeoUtil.format(origin.getLat() - offset.getLat(), 5);
			lng = GeoUtil.format(origin.getLng() - offset.getLng(), 5);
			return new GeoPoint(lat, lng);

		} else {
			return origin;
		}
	}

	private GeoPoint getOffset(double lat, double lng, CoordType sct,
			CoordType dct) {
		GeoPoint offsetGp = null;
		try {
			if ((sct == CoordType.GPS && dct == CoordType.GOOGLE)
					|| (sct == CoordType.GOOGLE && dct == CoordType.GPS)) {
				offsetGp = offsetDao.getGPS2GMapOffset(lat, lng);
			} else if ((sct == CoordType.GPS && dct == CoordType.BAIDU)
					|| (sct == CoordType.BAIDU && dct == CoordType.GPS)) {
				offsetGp = offsetDao.getGPS2BMapOffset(lat, lng);
			} else if ((sct == CoordType.GOOGLE && dct == CoordType.MAPBAR)
					|| (sct == CoordType.MAPBAR && dct == CoordType.GOOGLE)) {
				offsetGp = offsetDao.getGPS2MapbarOffset(lat, lng);
			} else {
				offsetGp = offsetDao.getGPS2AMapOffset(lat, lng);
			}
		} catch (Exception e) {
			log.error("get offset geopoint error", e);
		}
		return offsetGp;
	}
}
