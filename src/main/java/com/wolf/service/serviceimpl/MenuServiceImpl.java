package com.wolf.service.serviceimpl;

import com.wolf.dao.IMenuDao;
import com.wolf.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wolf on 16/3/7.
 *
 * 菜单
 */

@Service("menuService")
public class MenuServiceImpl implements IMenuService{

    @Autowired
    private IMenuDao menuDao;

    /**
     * 获取菜单列表
     * @param param
     * @return
     */
    public List qryMenusByUserId(Map param) {
        return queryChildMenus(param);
    }

    /**
     *  迭代查询所有菜单
     * @param param
     * @return
     */
    private List queryChildMenus(Map param){
        List<Map<String,Object>> list = menuDao.queryChildMenus(param);
        if(list == null || list.isEmpty()){
            return null;
        }

        for(Map<String,Object> map : list){
            String menuId = (String) map.get("menuId");
            Boolean isLast = (Boolean) map.get("last");
            if(isLast){
                continue;
            }
            param.put("parentMenuId",menuId);
            List child = queryChildMenus(param);
            map.put("child",child);
        }
        return list;
    }
}
