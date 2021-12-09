package com.yjxxt.gymsystem.mapper;

import com.yjxxt.gymsystem.base.BaseMapper;
import com.yjxxt.gymsystem.bean.Course;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper extends BaseMapper<Course,Integer> {
    int insert(Course record);

}