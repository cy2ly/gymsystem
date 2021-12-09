package com.yjxxt.gymsystem.controller;

import com.yjxxt.gymsystem.base.BaseController;
import com.yjxxt.gymsystem.base.ResultInfo;
import com.yjxxt.gymsystem.bean.Coach;
import com.yjxxt.gymsystem.query.CoachQuery;
import com.yjxxt.gymsystem.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("coach")
public class CoachController extends BaseController {

    @Autowired
    private CoachService coachService;

    /**
     * 多条件分页查询
     * @param coachQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(CoachQuery coachQuery){
        return coachService.list(coachQuery);
    }

    /**
     * 跳转到列表
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "coach/coach";
    }

    @RequestMapping("addAndUpdatePage")
    public String addAndUpdatePage(HttpServletRequest request, Integer coachId){
        if (coachId!=null) {
            Coach coach = coachService.selectByPrimaryKey(coachId);
            request.setAttribute("coach",coach);
        }
        return "coach/add_update";
    }

    /**
     * 添加教练
     * @param coach
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo addCoach(Coach coach){
        coachService.addCoach(coach);
        return success("添加成功!");
    }

    /**
     * 修改教练
     * @param coach
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateCoach(Coach coach){
        coachService.updateCoach(coach);
        return success("修改成功!");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteCoach(Integer[] ids){
        coachService.deleteCoach(ids);
        return success("删除成功!");
    }
}
