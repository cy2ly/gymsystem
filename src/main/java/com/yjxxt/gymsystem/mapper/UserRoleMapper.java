package com.yjxxt.gymsystem.mapper;


import com.yjxxt.gymsystem.base.BaseMapper;
import com.yjxxt.gymsystem.bean.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole,Integer> {

    int countUserRoleNum(Integer userId);

    int deleteUserRoleByUserId(Integer userId);
}