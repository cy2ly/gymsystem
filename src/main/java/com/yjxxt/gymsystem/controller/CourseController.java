package com.yjxxt.gymsystem.controller;

import com.yjxxt.gymsystem.base.BaseController;
import com.yjxxt.gymsystem.base.ResultInfo;
import com.yjxxt.gymsystem.bean.Course;
import com.yjxxt.gymsystem.query.CourseQuery;
import com.yjxxt.gymsystem.service.CoachService;
import com.yjxxt.gymsystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("course")
public class CourseController extends BaseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CoachService coachService;

    /*跳转列表页面*/
    @RequestMapping("index")
    public String index(){
        return "course/course";
    }
    /*分页查询列表*/
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryCourseParams(CourseQuery courseQuery){
        return courseService.queryCourseParams(courseQuery);
    }

    /*添加跳转-修改*/
    @RequestMapping("addOrUpdateCoursePage")
    public String add(Integer id, Model model){
        System.out.println(id);
        if (id !=null){
            Course course = courseService.selectByPrimaryKey(id);
            course.setCoachName(coachService.selectByPrimaryKey(course.getCoachId()).getCoachName());
            model.addAttribute("course",course);
        }
        return "course/add_update";
    }

    /*添加*/
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(Course course){
        courseService.saveCourse(course);
        return success("添加成功");
    }
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(Course course){
        courseService.updateCourse(course);
        return success("修改成功");
    }
    /*删除*/
    @RequestMapping("dels")
    @ResponseBody
    public ResultInfo detele(Integer[] ids){
        courseService.deleteCourse(ids);
        return success("删除成功");
    }
}
