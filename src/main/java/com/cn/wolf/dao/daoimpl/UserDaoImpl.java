package com.cn.wolf.dao.daoimpl;

import com.cn.wolf.dao.IUserDao;
import com.cn.wolf.mapping.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by wolf on 15/11/18.
 */
@Repository("userDao")
public class UserDaoImpl extends SqlSessionDaoSupport implements IUserDao{
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public int saveUser(User user) {
       return  getSqlSession().insert("com.cn.wolf.mapping.User.saveUser", user);
    }

    public User getUserById(String user) {
        User u = getSqlSession().selectOne("com.cn.wolf.mapping.User.getUserById", user);
        return u;
    }
}
