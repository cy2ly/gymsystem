package com.yjxxt.gymsystem.controller;

import com.yjxxt.gymsystem.base.BaseController;
import com.yjxxt.gymsystem.bean.User;
import com.yjxxt.gymsystem.service.UserService;
import com.yjxxt.gymsystem.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseController {
    @Autowired
    private UserService userService;

    /*登陆主界面*/
    @RequestMapping({"/","index"})
    public String index(){
        return "index";
    }

    @RequestMapping("main")
    public String main(HttpServletRequest request){
        int userId = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userService.selectByPrimaryKey(userId);
        request.setAttribute("user",user);
        return "main";
    }

    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }
}
