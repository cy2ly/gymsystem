package com.yjxxt.gymsystem.mapper;

import com.yjxxt.gymsystem.base.BaseMapper;
import com.yjxxt.gymsystem.bean.Lose;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoseMapper extends BaseMapper<Lose,Integer> {

      public void queryLoseByName();

     public void insert(Lose lose);
}