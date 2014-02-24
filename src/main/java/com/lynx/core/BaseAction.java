package com.lynx.core;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: chris Date: 13-12-23 下午12:09
 */
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware,
		SessionAware {

	private HttpServletRequest request;
	private HttpServletResponse response;

	protected boolean writeResponse(String s) {
		try {
			this.getResponse().setContentType("text/javascript;charset=utf-8");// 解决中文乱码
			this.getResponse().getWriter().write(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setSession(Map<String, Object> session) {

	}
}
