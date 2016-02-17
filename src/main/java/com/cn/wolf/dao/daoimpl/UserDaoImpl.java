package com.cn.wolf.dao.daoimpl;

import com.cn.wolf.dao.IUserDao;
import com.cn.wolf.dao.base.MybatisSupportBaseDao;
import com.cn.wolf.mapping.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by wolf on 15/11/18.
 */
@Repository("userDao")
public class UserDaoImpl extends MybatisSupportBaseDao implements IUserDao{

    public int saveUser(User user) {
//       return  sqlSessionManager.insert("com.cn.wolf.mapping.User.saveUser", user);
        return sqlSessionTemplate.insert("com.cn.wolf.mapping.User.saveUser", user);
    }

    public User getUserById(String user) {
//        User u = sqlSessionManager.selectOne("com.cn.wolf.mapping.User.getUserById", user);
        User u = sqlSessionTemplate.selectOne("com.cn.wolf.mapping.User.getUserById", user);
        return u;
    }
}
