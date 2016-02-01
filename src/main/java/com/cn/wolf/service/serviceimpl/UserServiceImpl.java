package com.cn.wolf.service.serviceimpl;

import com.cn.wolf.dao.IUserDao;
import com.cn.wolf.mapping.User;
import com.cn.wolf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wolf on 15/11/18.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    public int saveUser(User user) {
        return userDao.saveUser(user);
    }

    public User getUserById(String user) {
        return userDao.getUserById(user);
    }
}
