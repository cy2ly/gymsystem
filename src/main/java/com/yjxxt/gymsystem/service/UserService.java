package com.yjxxt.gymsystem.service;

import com.yjxxt.gymsystem.base.BaseService;
import com.yjxxt.gymsystem.bean.User;
import com.yjxxt.gymsystem.mapper.UserMapper;
import com.yjxxt.gymsystem.model.UserModel;
import com.yjxxt.gymsystem.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User,Integer> {

    @Autowired
    private UserMapper userMapper;
    //用户登录验证
    public UserModel login(String username,String pwd){
        checkIsBlank(username,pwd);
        //验证用户是否存在

    }

    private void checkIsBlank(String username, String pwd) {
        AssertUtil.isTrue(StringUtils.isBlank(username),"用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(pwd),"密码不能为空");
    }
}
