package com.wolf.dao.daoimpl;

import com.wolf.dao.IUserDao;
import com.wolf.dao.base.MybatisSupportBaseDao;
import com.wolf.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by wolf on 15/11/18.
 */
@Repository("userDao")
public class UserDaoImpl extends MybatisSupportBaseDao implements IUserDao{

    public int saveUser(User user) {
        return sqlSessionTemplate.insert("com.wolf.entity.User.saveUser", user);
    }

    public User getUserById(String user) {
        User u = sqlSessionTemplate.selectOne("com.wolf.entity.User.getUserById", user);
        return u;
    }

    public User getUserByLoginName(String loginName) {
        return sqlSessionTemplate.selectOne("com.wolf.entity.User.getUserByLoginName", loginName);
    }
}
