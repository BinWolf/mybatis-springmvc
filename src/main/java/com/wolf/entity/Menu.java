package com.wolf.entity;

import java.io.Serializable;

/**
 * Created by wolf on 16/3/5.
 *
 * 后台侧栏菜单
 * 权限管理用中间表,把菜单权限跟用户联系起来
 */
public class Menu implements Serializable {

    //菜单id
    private String menuId;
    //菜单名
    private String menuName;
    //父级菜单id
    private String parentMenuId;
    //该菜单请求地址
    private String menuUrl;
    //排序值
    private int orderId;
    //第几级菜单
    private int level;
    //是否是叶子
    private boolean last;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}
