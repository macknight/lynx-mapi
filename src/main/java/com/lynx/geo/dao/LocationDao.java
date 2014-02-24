package com.lynx.geo.dao;

import com.lynx.geo.entity.CDMACellInfo;
import com.lynx.geo.entity.GSMCellInfo;
import com.lynx.geo.dao.entity.LocationPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @author zhufeng.liu
 * 
 * @addtime 13-10-26-上午11:14
 */
public interface LocationDao {

	LocationPo getCDMALocation(@Param(value = "mcc") int mcc, @Param(value = "sid") int sid,
			@Param(value = "nid") int nid, @Param(value = "bid") int bid);

	LocationPo getGSMLocation(@Param(value = "mcc") int mcc, @Param(value = "mnc") int mnc,
			@Param(value = "lac") int lac, @Param(value = "cid") int cid);

	List<LocationPo> getWifiLocation(@Param(value = "wifis") List<Long> wifis);

	void updateCDMAAddress(@Param(value = "mcc") int mcc, @Param(value = "sid") int sid,
			@Param(value = "nid") int nid, @Param(value = "bid") int bid,
			@Param(value = "addr") String addr);

	void updateGSMAddress(@Param(value = "mcc") int mcc, @Param(value = "mnc") int mnc,
			@Param(value = "lac") int lac, @Param(value = "cid") int cid,
			@Param(value = "addr") String addr);

	List<CDMACellInfo> getCDMACellInfo(@Param(value = "offset") int offset,
			@Param(value = "limit") int limit);

	List<GSMCellInfo> getGSMCellInfo(@Param(value = "offset") int offset,
			@Param(value = "limit") int limit);
}
