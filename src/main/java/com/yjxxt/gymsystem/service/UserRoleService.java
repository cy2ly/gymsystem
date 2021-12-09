package com.yjxxt.gymsystem.service;

import com.yjxxt.gymsystem.base.BaseService;
import com.yjxxt.gymsystem.bean.UserRole;
import com.yjxxt.gymsystem.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserRoleService extends BaseService<UserRole,Integer> {


    @Resource
    private UserRoleMapper userRoleMapper;



}
