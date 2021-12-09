package com.yjxxt.gymsystem.mapper;

import com.yjxxt.gymsystem.base.BaseMapper;
import com.yjxxt.gymsystem.base.BaseQuery;
import com.yjxxt.gymsystem.bean.Member;
import com.yjxxt.gymsystem.query.MemberQuery;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface MemberMapper extends BaseMapper<Member,Integer> {
    @MapKey(value = "")
    List<Map<String, Object>> selectTypes();
    List<Member> selectByParam(MemberQuery memberQuery);
    Integer deleteBatchs(Integer[] ids);
}