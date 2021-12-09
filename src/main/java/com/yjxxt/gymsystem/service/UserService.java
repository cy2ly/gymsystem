package com.yjxxt.gymsystem.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.gymsystem.base.BaseService;
import com.yjxxt.gymsystem.bean.User;
import com.yjxxt.gymsystem.bean.UserRole;
import com.yjxxt.gymsystem.mapper.UserMapper;
import com.yjxxt.gymsystem.mapper.UserRoleMapper;
import com.yjxxt.gymsystem.model.UserModel;
import com.yjxxt.gymsystem.query.UserQuery;
import com.yjxxt.gymsystem.utils.AssertUtil;
import com.yjxxt.gymsystem.utils.Md5Util;
import com.yjxxt.gymsystem.utils.PhoneUtil;
import com.yjxxt.gymsystem.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserService extends BaseService<User,Integer> {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

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
    /**
     * 用户模块的列表查询
     * 条件
     *
     * @param userQuery 查询条件
     * @return
     */

    public Map<String, Object> findUserByParams(UserQuery userQuery) {
        //实例化map
        Map<String, Object> map = new HashMap<String, Object>();
        //初始化分页单位
        PageHelper.startPage(userQuery.getPage(), userQuery.getLimit());
        //开始分页
        PageInfo<User> plist = new PageInfo<User>(selectByParams(userQuery));

        //准备数据
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", plist.getTotal());
        map.put("data", plist.getList());
        //返回目标map
        return map;

    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user) {
        //验证
        checkUser(user.getUserName(), user.getPhone());
        //用户名唯一
        User temp = userMapper.selectByUserName(user.getUserName());
        AssertUtil.isTrue(temp != null, "用户名已经存在");
        //设定默认
        user.setIsValid(1);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        //密码加密
        user.setUserPwd(Md5Util.encode("123456"));
        //验证是否成功
        //AssertUtil.isTrue(insertSelective(user) < 1, "添加失败了");
        AssertUtil.isTrue(insertHasKey(user) < 1, "添加失败了");
        //****
        relaionUserRole(user.getId(),user.getRoleIds());
    }
    private void checkUser(String userName, String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(phone), "手机号不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone), "输入合法的手机号");
    }
    private void relaionUserRole(Integer userId, String roleIds) {
        //准备集合存储对象
        List<UserRole> urlist=new ArrayList<UserRole>();
        //userId,roleId;
        AssertUtil.isTrue(StringUtils.isBlank(roleIds),"请选择角色信息");
        //统计当前用户有多少个角色
        int count=userRoleMapper.countUserRoleNum(userId);
        //删除当前用户的角色
        if(count>0){
            AssertUtil.isTrue( userRoleMapper.deleteUserRoleByUserId(userId)!=count,"用户角色删除失败");
        }
        //删除原来的角色
        String[] RoleStrId = roleIds.split(",");
        //遍历
        for (String rid:RoleStrId) {
            //准备对象
            UserRole userRole=new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(Integer.parseInt(rid));
            userRole.setCreateDate(new Date());
            userRole.setUpdateDate(new Date());
            //存放到集合
            urlist.add(userRole);
        }
        //批量添加
        AssertUtil.isTrue(userRoleMapper.insertBatch(urlist)!=urlist.size(),"用户角色分配失败");
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeUser(User user) {
        //根据ID获取用户的信息
        User temp = userMapper.selectByPrimaryKey(user.getId());
        //判断
        AssertUtil.isTrue(temp == null, "待修改的记录不存在");
        //验证参数
        checkUser(user.getUserName(), user.getPhone());
        //修改用户名已经存在问题
        User temp2 = userMapper.selectByUserName(user.getUserName());
        AssertUtil.isTrue(temp2 != null && !(temp2.getId().equals(user.getId())), "用户名称已经存在");
        //设定默认值
        user.setUpdateDate(new Date());
        //判断修改是否成功
        AssertUtil.isTrue(updateByPrimaryKeySelective(user) < 1, "修改失败了");
        //
        relaionUserRole(user.getId(),user.getRoleIds());
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeUserIds(Integer[] ids){
        //验证
        AssertUtil.isTrue(ids==null|| ids.length==0,"请选择删除数据");
        //遍历对象
        for (Integer userId: ids) {
            //统计当前用户有多少个角色
            int count=userRoleMapper.countUserRoleNum(userId);
            //删除当前用户的角色
            if(count>0){
                AssertUtil.isTrue( userRoleMapper.deleteUserRoleByUserId(userId)!=count,"用户角色删除失败");
            }
        }
        //判断删除成功与否
        AssertUtil.isTrue(userMapper.deleteBatch(ids)<1,"删除失败了");
    }
}