package com.yjxxt.gymsystem.mapper;

import com.yjxxt.gymsystem.base.BaseMapper;
import com.yjxxt.gymsystem.bean.MemberType;

import java.util.List;
import java.util.Map;

public interface MemberTypeMapper extends BaseMapper<MemberType,Integer> {
    Integer selectCountByTypeId(Integer typeId);
    //查询书籍类型
    List<Map<String, Object>> findType();
}