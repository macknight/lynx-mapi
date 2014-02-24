package com.lynx.geo.util;

import com.lynx.geo.entity.GeoPoint;
import com.lynx.geo.entity.Location;

import java.util.StringTokenizer;

/**
 * 
 * @author zhufeng.liu
 * 
 * @addtime 13-8-8 下午4:07
 */
public class GeoUtil {
	public static final double EARTH_RADIUS = 6371000;

	public static double distanceTo(Location loc1, Location loc2) {
		return distanceTo(loc1.getLat(), loc1.getLng(), loc2.getLat(), loc2.getLng());
	}

	public static double distanceTo(GeoPoint g1, GeoPoint g2) {
		return distanceTo(g1.getLat(), g1.getLng(), g2.getLat(), g2.getLng());
	}

	public static double distanceTo(double a1, double a2, double b1, double b2) {
		double lat1 = a1 / 180 * Math.PI;
		double lon1 = a2 / 180 * Math.PI;
		double lat2 = b1 / 180 * Math.PI;
		double lon2 = b2 / 180 * Math.PI;
		double dlat = lat2 - lat1;
		double dlon = lon2 - lon1;
		double tmpA = Math.sin(dlat / 2) * Math.sin(dlat / 2) + Math.cos(lat1) * Math.cos(lat2)
				* Math.sin(dlon / 2) * Math.sin(dlon / 2);
		double tempC = 2.0 * Math.atan2(Math.sqrt(tmpA), Math.sqrt(1.0 - tmpA));
		return (int) (Math.ceil(EARTH_RADIUS * tempC));
	}

	public static double format(double in, int n) {
		double p = Math.pow(10, n);
		return Math.round(in * p) / p;
	}

	public static double coordToRadians(double lat) {
		return lat * Math.PI / 180;
	} // latToRadians

	public static long mac2long(String mac) {
		StringTokenizer st = new StringTokenizer(mac, ":");
		if (st.countTokens() != 6) {
			return -1l;
		}
		try {
			long macLong = Long.parseLong(st.nextToken(), 16);
			while (st.hasMoreTokens()) {
				macLong = macLong * 256 + Long.parseLong(st.nextToken(), 16);
			}
			return macLong;
		} catch (NumberFormatException e) {
			return -1l;
		}
	}
}
