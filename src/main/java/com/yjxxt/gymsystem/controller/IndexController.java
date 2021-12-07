package com.yjxxt.gymsystem.controller;

import com.yjxxt.gymsystem.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;

public class IndexController extends BaseController {

    @RequestMapping({"/","index"})
    public String index(){
        return "";
    }

    @RequestMapping("main")
    public String main(){
        return "";
    }
}
