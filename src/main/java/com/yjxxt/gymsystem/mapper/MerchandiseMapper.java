package com.yjxxt.gymsystem.mapper;

import com.yjxxt.gymsystem.base.BaseMapper;
import com.yjxxt.gymsystem.bean.Merchandise;

import java.util.List;

public interface MerchandiseMapper extends BaseMapper<Merchandise,Integer> {
    Merchandise selectByProductName(String ProductName);

    List<String> queryAllSales();

    double selectbyprice(String name);
    Integer sleetById(String name);
}