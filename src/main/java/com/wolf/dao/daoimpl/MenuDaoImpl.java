package com.wolf.dao.daoimpl;

import com.wolf.dao.IMenuDao;
import com.wolf.dao.base.MybatisSupportBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wolf on 16/3/7.
 */

@Repository("menuDao")
public class MenuDaoImpl extends MybatisSupportBaseDao implements IMenuDao {
    public List<Map<String, Object>> queryChildMenus(Map param) {
        return sqlSessionTemplate.selectList("com.wolf.entity.Menu.queryChildMenus",param);
    }
}