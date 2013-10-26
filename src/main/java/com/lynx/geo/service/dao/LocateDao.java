package com.lynx.geo.service.dao;

import com.lynx.geo.entity.GeoPoint;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by chris.liu
 * Created at 13-10-26-上午11:14.
 */
public interface LocateDao {

	GeoPoint getCDMALocate(@Param(value = "mcc") int mcc,
	                       @Param(value = "sid") int sid,
	                       @Param(value = "nid") int nid,
	                       @Param(value = "bid") int bid);

	GeoPoint getGSMLocate(@Param(value = "mcc") int mcc,
	                      @Param(value = "mnc") int mnc,
	                      @Param(value = "lac") int lac,
	                      @Param(value = "cid") int cid);

	List<GeoPoint> getWifiLocate(@Param(value = "wifis") List<Long> wifis);
}
