package com.cn.wolf.controller;

import com.cn.wolf.util.HmacSha256;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * Created by wolf on 15/11/23.
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "/login/login";
    }

    @RequestMapping(value = "/doLogin", produces = "application/x-www-form-urlencoded;charset=UTF-8")
    @ResponseBody
    public ModelAndView doLogin(@RequestParam HashMap param) {
        System.out.println(param.toString());

        ModelAndView mv = new ModelAndView();
        String pwd = HmacSha256.HMAC_SHA256("wolf", "wolf");
        String passwd = param.get("passwd").toString();
        if (pwd.equals(passwd)) {
            System.out.println("====================");
        }
        mv.setViewName("/user/saveUser");
        return mv;
    }
}
