package com.wolf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wolf on 16/3/6.
 * 
 * 页面跳转通用类
 */

@RequestMapping(value = "/page" , produces = "application/x-www-form-urlencoded;charset=UTF-8")
@Controller
public class PageController {

    /**
     * jsp在二级目录下
     * @param operateName
     * @param pageName
     * @return
     */
    @RequestMapping("{operateName}/{pageName}")
    public String toManagerPage(@PathVariable("operateName") String operateName,
                                @PathVariable("pageName") String pageName){
        return operateName+"/"+pageName;
    }

    /**
     * jsp在3级目录下
     * @param dirName
     * @param operateName
     * @param pageName
     * @return
     */
    @RequestMapping("{dirName}/{operateName}/{pageName}")
    public String toPage(@PathVariable("dirName") String dirName,
                         @PathVariable("operateName") String operateName,
                         @PathVariable("pageName") String pageName) {

        StringBuilder sb = new StringBuilder();
        sb.append(dirName).append("/").append(operateName).append("/").append(pageName);
        return sb.toString();
    }
    
}
