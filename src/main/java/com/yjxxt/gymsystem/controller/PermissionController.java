package com.yjxxt.gymsystem.controller;


import com.yjxxt.gymsystem.base.BaseController;
import com.yjxxt.gymsystem.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("permission")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;




}
