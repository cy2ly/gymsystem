package com.yjxxt.gymsystem.mapper;

import com.yjxxt.gymsystem.base.BaseMapper;
import com.yjxxt.gymsystem.bean.Member;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface MemberMapper extends BaseMapper<Member,Integer> {
    @MapKey(value = "")
    List<Map<String, Object>> selectTypes();
}