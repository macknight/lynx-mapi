package com.lynx.geo.entity;

/**
 * 
 * @author zhufeng.liu
 * 
 * @version 13-8-8 下午3:27
 */
public class Coord {
	private GeoPoint geoPoint;
	private CoordType type;
	private CoordSource source;

	public Coord(GeoPoint geoPoint, CoordType type, CoordSource source) {
		this.geoPoint = geoPoint;
		this.type = type;
		this.source = source;
	}

	public GeoPoint getGeoPoint() {
		return geoPoint;
	}

	public void setGeoPoint(GeoPoint geoPoint) {
		this.geoPoint = geoPoint;
	}

	public CoordType getType() {
		return type;
	}

	public void setType(CoordType type) {
		this.type = type;
	}

	public CoordSource getSource() {
		return source;
	}

	public void setSource(CoordSource source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return String.format("%s,%s,%s", geoPoint.toString(), type.name(),
				source.name());
	}

	public enum CoordType {
		GPS, // gps坐标系
		GOOGLE, // google地图坐标系即02坐标系
		BAIDU, // 百度地图坐标系
		GAODE, // 高德地图坐标系
		MAPBAR, // 图吧地图坐标系
		SOUGOU // 搜狗地图坐标系
	}

	public enum CoordSource {
		GPS, // android GPS芯片定位
		NETWORK, // android系统定位
		GOOGLE, // google定位数据
		BAIDU, // 百度定位数据
		GAODE, // 高德定位数据
		LCELL, // lynx 基站定位数据
		LWIFI // lynx wifi定位数据
	}
}
