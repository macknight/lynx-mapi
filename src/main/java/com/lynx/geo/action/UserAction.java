package com.lynx.geo.action;

import com.lynx.geo.entity.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author zhufeng.liu
 * 
 * @create at 2013年10月12日 下午9:37:38
 */
@SuppressWarnings("serial")
public class UserAction extends ActionSupport {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String execute() throws Exception {
		user = new User(1, "chris", 1);
		return SUCCESS;
	}
}
