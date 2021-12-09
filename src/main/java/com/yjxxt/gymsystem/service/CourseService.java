package com.yjxxt.gymsystem.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.gymsystem.base.BaseService;
import com.yjxxt.gymsystem.bean.Course;
import com.yjxxt.gymsystem.mapper.CoachMapper;
import com.yjxxt.gymsystem.mapper.CourseMapper;
import com.yjxxt.gymsystem.query.CourseQuery;
import com.yjxxt.gymsystem.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CourseService extends BaseService<Course,Integer> {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CoachMapper coachMapper;

    /*分页查询数据*/
    public Map<String,Object> queryCourseParams(CourseQuery courseQuery){
        Map<String,Object> map = new HashMap<>();
        //分页查询
        PageHelper.startPage(courseQuery.getPage(),courseQuery.getLimit());
        PageInfo<Course> pageInfo = new PageInfo<>(courseMapper.selectByParams(courseQuery));
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    /*添加课程*/
    public void saveCourse(Course course){
        //验证是否有空
        checkIsBlank(course.getCourseName(),course.getCoachName(),course.getCoursePrice(),course.getCourseDay());
        //判断健身房是否有该教练
        Integer coachId = coachMapper.selectCoachIdByName(course.getCoachName());
        System.out.println(course.getCoachId());
        AssertUtil.isTrue(coachId==null,"没有此教练");
        //设置教练
        course.setCoachId(coachId);
        //设置时间
        course.setCreateDate(new Date());
        course.setUpdateDate(new Date());
        course.setOpenDate(new Date());
        course.setIsValid(1);
        //验证是否添加成功
        AssertUtil.isTrue(courseMapper.insertSelective(course)<1,"添加课程失败");
    }

    private void checkIsBlank(String courseName, String coachName, Integer coursePrice, String courseDay) {
        AssertUtil.isTrue(StringUtils.isBlank(courseName),"请输入课程名称");
        AssertUtil.isTrue(StringUtils.isBlank(coachName),"请输入教练");
        AssertUtil.isTrue(StringUtils.isBlank(String.valueOf(coursePrice)),"请输入课程价格");
        AssertUtil.isTrue(StringUtils.isBlank(courseDay),"请输入每周上课时间");
    }
    /*修改课程*/
    public void updateCourse(Course course){
        System.out.println(course.getCourseId());
        AssertUtil.isTrue(courseMapper.selectByPrimaryKey(course.getCourseId())==null,"待更新课程不存在");
        checkIsBlank(course.getCourseName(),course.getCoachName(),course.getCoursePrice(),course.getCourseDay());
        //设置修改时间
        course.setUpdateDate(new Date());
        //判断是否更新成功
        AssertUtil.isTrue(courseMapper.updateByPrimaryKeySelective(course)<1,"更新失败");
    }

    /*删除课程*/
    public void deleteCourse(Integer[] ids){
        AssertUtil.isTrue(ids == null || ids.length == 0,"请选择需要删除的课程");
        //判断是否删除
        AssertUtil.isTrue(courseMapper.deleteBatch(ids)<1,"删除课程失败");
    }
}
