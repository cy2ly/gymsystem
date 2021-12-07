package com.yjxxt.gymsystem.controller;

import com.yjxxt.gymsystem.base.BaseController;
<<<<<<< HEAD
<<<<<<< HEAD
import com.yjxxt.gymsystem.bean.User;
import com.yjxxt.gymsystem.service.UserService;
import com.yjxxt.gymsystem.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
=======
>>>>>>> d0981d11b6fb92faa685001d22960117ee957cbd
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

=======
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

>>>>>>> 0f74b5b36de1deae12cf73804c578cf09b2f2f97
@Controller
public class IndexController extends BaseController {
<<<<<<< HEAD
    @Autowired
    private UserService userService;

    /*登陆主界面*/
    @RequestMapping("index")
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

=======

    @RequestMapping({"/","index"})
    public String index(){
        return "index";
    }

    @RequestMapping("main")
    public String main(){
        return "main";
    }
>>>>>>> d0981d11b6fb92faa685001d22960117ee957cbd
}
