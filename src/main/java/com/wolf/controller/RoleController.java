package com.wolf.controller;

import com.wolf.entity.Role;
import com.wolf.service.IRoleService;
import com.wolf.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by wolf on 16/3/13.
 *
 * 角色管理
 */

@RequestMapping(value = "/role", produces = "application/x-www-form-urlencoded;charset=UTF-8")
@Controller
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 角色列表
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String roleList(ModelMap model, @RequestParam Map params) {
        List list  = roleService.qryRoles(params);
        model.put("roleName", params.get("roleName"));
        model.put("list", list);
        model.put("totalCount",list.size());
        return "manager/role/role_list";
    }

    /**
     * 添加角色
     * @param roleName
     * @param roleId
     * @param roleDesc
     * @return
     */
    @RequestMapping("add")
    public String addRole(String roleName, String roleId, String roleDesc) throws Exception {

        Role role = new Role();
        role.setRoleId(roleId);
        role.setRoleName(roleName);
        role.setRoleDesc(roleDesc);
        int res = roleService.addRole(role);
        if(res > 0) {
            return "common/successMsg";
        }
        return "common/failedMsg";
    }

    /**
     * 根据roleid删除
     * @param roleId
     * @return
     */
    @RequestMapping("deleteRole")
    public String deleteRole(String roleId) {
        int res = roleService.deleteRole(roleId);
        if (res > 0) {
            return "common/ajaxDone";
        }
        return "common/failedMsg";
    }

    /**
     * 批量删除
     * @param roleIds
     * @return
     */
    @RequestMapping("deleteRoles")
    public String deleteRoles(String[] roleIds) {
        for (int i = 0; i < roleIds.length; i++) {
            roleService.deleteRole(roleIds[i]);
        }
        return "common/ajaxDone";
    }

    /**
     * 跳转到更新页面
     * @return
     */
    @RequestMapping("toUpdateRole")
    public String toUpdateRole(ModelMap model, String roleId) {
        Role role = roleService.getRoleByRoleId(roleId);
        model.put("role", role);
        return "manager/role/role_update";
    }

    /**
     * 更新
     * @return
     */
    @RequestMapping("updateRole")
    public String updateRole(String roleName, String roleId, String roleDesc) {
        Role role = new Role();
        role.setRoleId(roleId);
        role.setRoleName(roleName);
        role.setRoleDesc(roleDesc);
        int res = roleService.updateRole(role);
        if(res > 0)
            return "manager/role/show_roles";

        return "common/failedMsg";
    }



}
