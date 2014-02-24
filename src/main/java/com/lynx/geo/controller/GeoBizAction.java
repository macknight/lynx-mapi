package com.lynx.geo.controller;

import com.lynx.core.BaseAction;
import com.opensymphony.xwork2.ActionSupport;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author chris.liu
 * 
 * @version 13-12-23 下午1:23
 */
public class GeoBizAction extends BaseAction {

	/**
	 * 生成基于个性信息的推荐活动
	 */
	public void newAd() throws JSONException {
		JSONObject joResult = new JSONObject();
		try {
			int id = Integer.parseInt(getRequest().getParameter("id"));
			int shopId = Integer.parseInt(getRequest().getParameter("shopId"));
			String forwardOpId = getRequest().getParameter("forwardOpId");

		} catch (Exception e) {
			joResult.put("code", "error");
		}
		writeResponse(joResult.toString());
	}

	public String cellInfo() {
		return ActionSupport.SUCCESS;
	}

}
