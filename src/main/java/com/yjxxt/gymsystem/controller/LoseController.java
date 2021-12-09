package com.yjxxt.gymsystem.controller;

import com.yjxxt.gymsystem.base.BaseController;
import com.yjxxt.gymsystem.base.ResultInfo;
import com.yjxxt.gymsystem.bean.Lose;
import com.yjxxt.gymsystem.query.LoseQuery;
import com.yjxxt.gymsystem.service.LoseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("lose")
public class LoseController extends BaseController {

    @Resource
    private LoseService loseService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryLoseByParams(LoseQuery query){
        return loseService.queryLoseByName(query);
    }

    @RequestMapping("index")
    public String index(){
        return "lose/lose";
    }

    //添加跳转页面
    @RequestMapping("addOrUpdateLosePage")
    public String addUserPage(Integer id, Model model) {
        if (id != null) {
            model.addAttribute("lose", loseService.selectByPrimaryKey(id));
        }
        return "lose/addUpdate";
    }
    //添加丢失物品
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveRole(Lose lose){
        System.out.println(lose);
        loseService.saveLose(lose);
        return  success("丢失物品添加成功");
    }
    /*//删除丢失物品
    @RequestMapping("dels")
    @ResponseBody
    public ResultInfo deletesLose(Integer[] ids){
        loseService.removeLose(ids);
        return  success("删除成功");
    }*/
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteLose(Integer[] ids){
        loseService.deleteLose(ids);
        return  success("删除成功");
    }
    @RequestMapping("openAddOrUpdateLoseDialog")
    public String addOrUpdatelose(Integer id, Model model) {
        if (null != id) {
            // 通过主键查询数据
            Lose lose = loseService.selectByPrimaryKey(id);
            System.out.println(lose.getLoseId() + lose.getLoseName());
            // 将数据存到作用域中
            model.addAttribute("lose", lose);
        }
        return "lose/addUpdate";
    }


}
