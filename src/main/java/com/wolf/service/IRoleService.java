package com.wolf.service;

import com.wolf.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by wolf on 16/3/13.
 */
public interface IRoleService {
    List qryRoles(Map params);

    int addRole(Role role) throws Exception;

    int deleteRole(String roleId);

    Role getRoleByRoleId(String roleId);

    int updateRole(Role role);
}
