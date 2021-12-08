package com.yjxxt.gymsystem.service;

import com.yjxxt.gymsystem.base.BaseService;
import com.yjxxt.gymsystem.bean.User;
import com.yjxxt.gymsystem.mapper.UserMapper;
import com.yjxxt.gymsystem.model.UserModel;
import com.yjxxt.gymsystem.utils.AssertUtil;
import com.yjxxt.gymsystem.utils.Md5Util;
import com.yjxxt.gymsystem.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends BaseService<User,Integer> {
    @Autowired
    private UserMapper userMapper;

    //用户登录
    public UserModel login(String userName,String pwd){
        //验证用户名和密码不为空
        checkIsBlank(userName,pwd);
        //验证用户是否存在
        //根据用户名查询是否有该用户
        User user = userMapper.selectByUserName(userName);
        AssertUtil.isTrue(user==null,"该用户不存在");
        //验证密码是否正确
        AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(pwd)),"密码错误");
        return buildUser(user);
    }

    public UserModel buildUser(User user) {
        UserModel userModel = new UserModel();
        userModel.setUserName(user.getUserName());
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        return userModel;
    }

    public void checkIsBlank(String userName, String pwd) {
        //用户名不能为空
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空");
        //密码不能为空
        AssertUtil.isTrue(StringUtils.isBlank(pwd),"密码不能为空");
    }

    /**
     * 修改密码
     * @param userId
     * @param oldPwd
     * @param newPwd
     * @param confirmPwd
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePassword(Integer userId,String oldPwd,String newPwd,String confirmPwd){
        //校验数据
        User user = userMapper.selectByPrimaryKey(userId);
        checkUpdatePassword(user,oldPwd,newPwd,confirmPwd);
        //设置新密码
        user.setUserPwd(Md5Util.encode(confirmPwd));
        //修改密码
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)<1,"密码修改失败");
    }

    public void checkUpdatePassword(User user, String oldPwd, String newPwd, String confirmPwd) {
        AssertUtil.isTrue(user==null,"用户不存在或用户未登录");
        AssertUtil.isTrue(oldPwd.isBlank(),"旧密码不能为空");
        AssertUtil.isTrue(!(user.getUserPwd().equals(Md5Util.encode(oldPwd))),"旧密码输入错误");
        AssertUtil.isTrue(newPwd.isBlank(),"新密码不能为空");
        AssertUtil.isTrue(newPwd.equals(oldPwd),"新密码不能和旧密码一致");
        AssertUtil.isTrue(confirmPwd.isBlank(),"确认密码不能为空");
        AssertUtil.isTrue(!(confirmPwd.equals(newPwd)),"确认密码必须与新密码一致");
    }
}