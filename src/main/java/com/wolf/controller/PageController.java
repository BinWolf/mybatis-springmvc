package com.wolf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wolf on 16/3/6.
 * 
 * 页面跳转通用类
 */

@RequestMapping(value = "page" , produces = "application/x-www-form-urlencoded;charset=UTF-8")
@Controller
public class PageController {


    public String toManagerPage(){
        return "manager/index";
    }
    
}
