package com.wolf.service;

import com.wolf.entity.User;

/**
 * Created by wolf on 15/11/18.
 */
public interface IUserService {

    int saveUser(User user);

    User getUserById(String id);

    User getUserByLoginName(String loginName);
}
