package com.cn.wolf.controller;

import com.cn.wolf.mapping.User;
import com.cn.wolf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * Created by wolf on 16/2/16.
 */

@Controller
@RequestMapping(value = "/user", produces = "application/x-www-form-urlencoded;charset=UTF-8")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/saveUser")
    public ModelAndView saveUser(@RequestParam HashMap params){
        ModelAndView mv = new ModelAndView();
        User user = new User();
        user.setId((String) params.get("id"));
        int age = Integer.valueOf((String)params.get("age"));
        user.setAge(age);
        user.setNote((String)params.get("note"));
        user.setUserName((String) params.get(("userName")));
        user.setUserId((String) params.get("userId"));
        int res = userService.saveUser(user);
        System.out.println(res);
        mv.setViewName("/user/succee");
        return mv;
    }
}
