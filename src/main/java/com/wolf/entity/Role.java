package com.wolf.entity;

import java.io.Serializable;

/**
 * Created by wolf on 16/3/5.
 *
 * 角色.
 */
public class Role implements Serializable{

    //角色id
    private String roleId;
    //角色名
    private String roleName;
    //角色描述
    private String roleDesc;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}
