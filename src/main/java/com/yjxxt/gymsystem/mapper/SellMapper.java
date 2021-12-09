package com.yjxxt.gymsystem.mapper;

import com.yjxxt.gymsystem.base.BaseMapper;
import com.yjxxt.gymsystem.bean.Sell;

import java.util.List;

public interface SellMapper extends BaseMapper<Sell,Integer> {

    double selectByProductId(Integer productId);


}