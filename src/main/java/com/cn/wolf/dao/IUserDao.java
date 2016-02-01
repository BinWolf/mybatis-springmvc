package com.cn.wolf.dao;

import com.cn.wolf.mapping.User;

/**
 * Created by wolf on 15/11/18.
 */
public interface IUserDao {
    int saveUser(User user);

    User getUserById(String id);
}
