package com.wolf.dao;

import com.wolf.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by wolf on 16/3/13.
 */
public interface IRoleDao {
    List qryRoles(Map params);

    int addRole(Role role);

    int deleteRole(String roleId);

    void deleteUserRoleByRoleId(String roleId);

    Role getRoleByRoleId(String roleId);

    int updateRole(Role role);
}
