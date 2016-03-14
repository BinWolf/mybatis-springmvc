package com.wolf.dao.daoimpl;

import com.wolf.dao.IRoleDao;
import com.wolf.dao.base.MybatisSupportBaseDao;
import com.wolf.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wolf on 16/3/13.
 */

@Repository("roleDao")
public class RoleDaoImpl extends MybatisSupportBaseDao implements IRoleDao {

    public List qryRoles(Map params) {
        return sqlSessionTemplate.selectList("com.wolf.entity.Role.qryRoles",params);
    }

    public int addRole(Role role) {
        return sqlSessionTemplate.insert("com.wolf.entity.Role.saveRole",role);
    }

    public int deleteRole(String roleId) {
        return sqlSessionTemplate.delete("com.wolf.entity.Role.deleteRole",roleId);
    }

    public void deleteUserRoleByRoleId(String roleId) {
        sqlSessionTemplate.delete("com.wolf.entity.UserRole.deleteUserRoleByRoleId", roleId);
    }

    public Role getRoleByRoleId(String roleId) {
        return sqlSessionTemplate.selectOne("com.wolf.entity.Role.getRoleByRoleId", roleId);
    }

    public int updateRole(Role role) {
        return sqlSessionTemplate.update("com.wolf.entity.Role.updateRole", role);
    }

}
