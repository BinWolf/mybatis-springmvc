package com.wolf.entity;

import java.io.Serializable;

/**
 * Created by wolf on 16/3/5.
 *
 * 用户和角色的对应
 */
public class UserRole implements Serializable {

    //用户id
    private String userId;
    //角色id
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
