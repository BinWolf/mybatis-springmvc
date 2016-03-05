package com.wolf.entity;

import java.io.Serializable;

/**
 * Created by wolf on 16/3/5.
 *
 * 用户和菜单对应关系
 */
public class UserMenu implements Serializable {

    private String userId;

    private String menuId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
