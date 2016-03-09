package com.wolf.controller;

import com.wolf.common.ConstValue;
import com.wolf.entity.User;
import com.wolf.service.IUserService;
import com.wolf.util.HmacSha256;
import com.wolf.util.RedisUtil;
import com.wolf.util.LocalStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IUserService userService;

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/doLogin", produces = "application/x-www-form-urlencoded;charset=UTF-8")
    @ResponseBody
    public void doLogin(@RequestParam HashMap param,HttpServletRequest request,HttpServletResponse response) throws Exception{

        User user = null;
        String loginName = (String) param.get("loginName");
        String passwd = (String) param.get("passWord");
        if (!LocalStringUtils.isEmpty(loginName) && !LocalStringUtils.isEmpty(passwd)) {
            user = userService.getUserByLoginName(loginName);
        } else {
            //密码和登录名至少有一个为空
            response.getWriter().write("3");
            return;
        }

        if (user != null) { //该用户存在
            String dbpwd = HmacSha256.HMAC_SHA256(loginName, user.getPassWord());
            if (dbpwd.equals(passwd)) {
                String sUUID = UUID.randomUUID().toString();
                //会话时长延续ConstValue.sessionTimeLong秒
                RedisUtil.setExpireTime(sUUID, ConstValue.sessionTimeLong);
                RedisUtil.hset(sUUID, "jSessionId", sUUID);
                RedisUtil.hset(sUUID, "userId", user.getUserId());
                saveCookie(response, "JSESSIONID", sUUID, ConstValue.sessionTimeLong);
                request.setAttribute("jSessionId", sUUID);
                response.getWriter().write("0");
            } else {
                //密码错误
                response.getWriter().write("1");
            }
        } else { //该用户不存在
            response.getWriter().write("2");
        }
    }

    protected void saveCookie(HttpServletResponse response,String name, String value, int maxAge) {
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
