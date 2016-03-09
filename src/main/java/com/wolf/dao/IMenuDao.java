package com.wolf.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by wolf on 16/3/7.
 */
public interface IMenuDao {

    List<Map<String,Object>> queryChildMenus(Map param);
}
