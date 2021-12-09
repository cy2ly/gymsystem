package com.yjxxt.gymsystem.mapper;

import com.yjxxt.gymsystem.base.BaseMapper;
import com.yjxxt.gymsystem.bean.MemberType;

public interface MemberTypeMapper extends BaseMapper<MemberType,Integer> {
    Integer selectCountByTypeId(Integer typeId);
}