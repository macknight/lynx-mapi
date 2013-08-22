package com.lynx.geo.action;

import com.lynx.geo.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.json.annotations.JSON;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-8-22 上午10:11
 */
@SuppressWarnings("serial")
public class UserAction extends ActionSupport {
    private User user;

    @JSON(name="User")
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
