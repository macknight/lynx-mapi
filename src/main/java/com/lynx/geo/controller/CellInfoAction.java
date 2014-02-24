package com.lynx.geo.controller;

import com.lynx.core.BaseAction;
import com.lynx.geo.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author chris.liu
 * 
 * @version 13-12-23 下午2:59
 */
public class CellInfoAction extends BaseAction {

	private int recordCount;
	private int pageNumber;

	@Autowired
	private GeoService geoService;

	@Override
	public String execute() throws Exception {

		geoService.cdmaCellInfo(pageNumber, 10);
		return super.execute();
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
}
