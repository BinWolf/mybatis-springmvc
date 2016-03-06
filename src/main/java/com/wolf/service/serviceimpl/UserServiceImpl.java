package com.wolf.service.serviceimpl;

import com.wolf.dao.IUserDao;
import com.wolf.entity.User;
import com.wolf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wolf on 15/11/18.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    public int saveUser(User user){
            userDao.saveUser(user);
            userDao.saveUser(user);
        return 1;
    }

    public User getUserById(String user) {
        return userDao.getUserById(user);
    }

    public User getUserByLoginName(String loginName) {
        return userDao.getUserByLoginName(loginName);
    }
}
