package com.wolf.service.serviceimpl;

import com.wolf.dao.IRoleDao;
import com.wolf.entity.Role;
import com.wolf.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wolf on 16/3/13.
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    public List qryRoles(Map params) {
        return roleDao.qryRoles(params);
    }

    public int addRole(Role role) throws Exception {
        //添加之前先检查是否可用
        /*Role r = roleDao.checkRole(role);
        if(r != null ){
            throw new Exception("用户已存在!");
            return 0;
        }*/
        return roleDao.addRole(role);
    }

    public int deleteRole(String roleId) {
        //删除角色
        int res = roleDao.deleteRole(roleId);
        //删除用户角色中间表
        roleDao.deleteUserRoleByRoleId(roleId);
        return res;
    }

    public Role getRoleByRoleId(String roleId) {
        return roleDao.getRoleByRoleId(roleId);
    }

    public int updateRole(Role role) {
        return roleDao.updateRole(role);
    }
}
