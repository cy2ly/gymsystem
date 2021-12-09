package com.yjxxt.gymsystem.mapper;

import com.yjxxt.gymsystem.base.BaseMapper;
import com.yjxxt.gymsystem.bean.Equipment;

public interface EquipmentMapper extends BaseMapper<Equipment,Integer> {

    //根据器材名字查询器材
    Equipment queryEquipByEqName(String eqName);
}