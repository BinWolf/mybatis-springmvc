package com.cn.wolf.service;

import com.cn.wolf.mapping.User;

/**
 * Created by wolf on 15/11/18.
 */
public interface IUserService {

    int saveUser(User user);

    User getUserById(String id);
}
