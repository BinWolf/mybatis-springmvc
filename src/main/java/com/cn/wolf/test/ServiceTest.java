package com.cn.wolf.test;

import com.cn.wolf.mapping.User;
import com.cn.wolf.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wolf on 15/11/18.
 */
public class ServiceTest {
    private ApplicationContext ac = null;

    @Autowired
    private IUserService userService;

    /*@Before
    public void before() {
        ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        userService = (IUserService) ac.getBean("userService");
    }*/


    @Test
    public void testSaveUser() throws Exception{
        User user = new User();
        user.setId("id000000");
        user.setAge(12);
        user.setNote("this is wolf-2147482646 first mybatis-spring11");
        user.setUserId("user0000");
        user.setUserName("wolf*bin1");
        int res = userService.saveUser(user);
        System.out.println(res);

    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId("id0000002");
        User u = userService.getUserById("id0000002");
        System.out.println(u.toString());

    }

}
