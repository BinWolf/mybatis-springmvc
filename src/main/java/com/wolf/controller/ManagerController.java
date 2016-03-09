package com.wolf.controller;

import com.wolf.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wolf on 16/3/7.
 */

@RequestMapping(value = "manage" , produces = "application/x-www-form-urlencoded;charset=UTF-8")
@Controller
public class ManagerController {

    @Autowired
    private IMenuService menuService;

}
