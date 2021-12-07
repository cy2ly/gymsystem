package com.yjxxt.gymsystem.interceptors;

<<<<<<< HEAD
import com.yjxxt.gymsystem.exceptions.NoLoginException;
import com.yjxxt.gymsystem.service.UserService;
=======
import com.yjxxt.gymsystem.Service.UserService;
import com.yjxxt.gymsystem.exceptions.NoLoginException;
>>>>>>> 0f74b5b36de1deae12cf73804c578cf09b2f2f97
import com.yjxxt.gymsystem.utils.LoginUserUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* 非法访问拦截
* */
public class NoLoginInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        if (userId == null || userService.selectByPrimaryKey(userId) == null){
            throw new NoLoginException("用户未登录");
        }
        return true;
    }

}
