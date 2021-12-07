package com.yjxxt.gymsystem.controller;

import com.yjxxt.gymsystem.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {

    @RequestMapping({"/","index"})
    public String index(){
        return "index";
    }

    @RequestMapping("main")
    public String main(){
        return "main";
    }
}
