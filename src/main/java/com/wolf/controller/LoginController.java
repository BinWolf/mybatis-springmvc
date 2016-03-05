package com.wolf.controller;

import com.wolf.common.ConstValue;
import com.wolf.util.HmacSha256;
import com.wolf.util.RedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.UUID;

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
    public ModelAndView doLogin(@RequestParam HashMap param,HttpServletRequest request,HttpServletResponse response) {

        ModelAndView mv = new ModelAndView();
        String pwd = HmacSha256.HMAC_SHA256("wolf", "wolf");
        String passwd = param.get("passwd").toString();
        if (pwd.equals(passwd)) {
            String sUUID = UUID.randomUUID().toString();
            System.out.println("uuid =   "+sUUID);
            RedisUtil.setExpireTime(sUUID, ConstValue.sessionTimeLong);//会话时长延续ConstValue.sessionTimeLong秒
            RedisUtil.hset(sUUID,"jSessionId",sUUID);
            RedisUtil.hset(sUUID, "user_id", "user_id");//user_id  先设死了
            saveCookie(response, "JSESSIONID", sUUID, ConstValue.sessionTimeLong);//2*24*60*60  10*60  -1为本次登陆有效关闭页面失效  0为不存储会话
            mv.addObject("jSessionId", sUUID);
            request.setAttribute("jSessionId", sUUID);
        }
        mv.setViewName("/user/saveUser");
        return mv;
    }
    protected void saveCookie(HttpServletResponse response,String name, String value, int maxAge)
    {	//maxAge单位秒
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @RequestMapping(value = "/single", produces = "application/x-www-form-urlencoded;charset=UTF-8")
    public ModelAndView singleLogin() {
        ModelAndView mv = new ModelAndView("/test/test");
        return mv;
    }
}
