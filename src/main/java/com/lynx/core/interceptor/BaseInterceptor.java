package com.lynx.core.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 13-12-23 上午10:01
 */
public class BaseInterceptor extends AbstractInterceptor {

	public BaseInterceptor() {
		super();
	}

	@Override
	public void init() {
		super.init();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		return null;
	}
}
