package com.wolf.dao;

import com.wolf.entity.User;

/**
 * Created by wolf on 15/11/18.
 */
public interface IUserDao {
    int saveUser(User user);

    User getUserById(String id);

    User getUserByLoginName(String loginName);
}
