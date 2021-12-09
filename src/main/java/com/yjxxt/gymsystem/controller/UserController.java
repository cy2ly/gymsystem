package com.yjxxt.gymsystem.controller;

import com.yjxxt.gymsystem.annotation.RequiredPermission;
import com.yjxxt.gymsystem.base.BaseController;
import com.yjxxt.gymsystem.base.ResultInfo;
import com.yjxxt.gymsystem.bean.User;
import com.yjxxt.gymsystem.model.UserModel;
import com.yjxxt.gymsystem.query.UserQuery;
import com.yjxxt.gymsystem.service.UserService;
import com.yjxxt.gymsystem.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    //用户登录
    @RequestMapping("login")
    @ResponseBody
    public ResultInfo login(String userName,String userPwd){
        ResultInfo resultInfo = new ResultInfo();
        System.out.println(userName+userPwd);
        UserModel userModel = userService.login(userName, userPwd);
        System.out.println(userModel);
        resultInfo.setResult(userModel);
        return resultInfo;
    }
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(UserQuery userQuery) {
        return userService.findUserByParams(userQuery);
    }

    /**
     * 跳转到修改资料界面
     * @param request
     * @return
     */
    @RequestMapping("toSettingPage")
    public String toUserPage(HttpServletRequest request){
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userService.selectByPrimaryKey(userId);
        request.setAttribute("user",user);
        return "user/setting";
    }

    /**
     * 修改资料
     * @param user
     * @return
     */
    @RequestMapping("setInfo")
    @ResponseBody
    public ResultInfo updateUser(User user){
        userService.updateByPrimaryKeySelective(user);
        return success("修改成功");
    }

    /**
     * 跳转到修改密码界面
     * @return
     */
    @RequestMapping("toPasswordPage")
    public String toPasswordPage(){
        return "user/password";
    }

    @RequestMapping("updatePwd")
    @ResponseBody
    public ResultInfo updatePassword(HttpServletRequest request,String oldPwd,String newPwd,String confirmPwd){
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        userService.updatePassword(userId,oldPwd,newPwd,confirmPwd);
        return success("密码修改成功!");
    }
    @RequestMapping("/index")
    public String index(){
        return "/user/user";
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(User user) {
        //用户的添加
        userService.addUser(user);
        //返回目标数据对象
        return success("用户添加OK");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(User user) {
        //用户的添加
        userService.changeUser(user);
        //返回目标数据对象
        return success("用户修改OK");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer[] ids) {
        //用户的添加
        userService.removeUserIds(ids);
        //返回目标数据对象
        return success("批量删除用户OK");
    }

    @RequestMapping("addOrUpdateUserPage")
    public String addOrUpdatePage(Integer id, Model model) {
        if(id!=null){
            User user = userService.selectByPrimaryKey(id);
            model.addAttribute("user",user);
        }
        return "user/add_update";
    }

}
