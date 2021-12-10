package com.yjxxt.gymsystem.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.gymsystem.base.BaseService;
import com.yjxxt.gymsystem.bean.Lose;
import com.yjxxt.gymsystem.mapper.LoseMapper;
import com.yjxxt.gymsystem.query.LoseQuery;
import com.yjxxt.gymsystem.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoseService extends BaseService<Lose,Integer> {
    @Autowired
    private LoseMapper loseMapper;

    //查找丢失物品通过名称
    public Map<String,Object>  queryLoseByName(LoseQuery query){
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(query.getPage(),query.getLimit());
        PageInfo<Lose> pageInfo = new PageInfo<>(loseMapper.selectByParams(query));
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
   //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveLose(Lose lose){
        //校验
        checkParams(lose.getLoseName(),lose.getLoseAddress());
        //判断是否输入
        AssertUtil.isTrue(StringUtils.isBlank(lose.getLoseName()),"请输入丢失物品名称");
        //设定默认值
        lose.setIsValid(1);
        lose.setLoseStatus(1);
        lose.setLoseJdate(new Date());
        //判断是否添加成功
        AssertUtil.isTrue(insertSelective(lose)<1,"添加失败");

    }
      //校验
    private void checkParams(String loseName, String loseAddress) {
        AssertUtil.isTrue(StringUtils.isBlank(loseName),"丢失物品名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(loseAddress),"丢失物品位置不能为空");
    }
   //删除(被丢物品已被取走)
   @Transactional(propagation = Propagation.REQUIRED)
   public void deleteLose(Integer[] ids) {
       AssertUtil.isTrue(ids==null||ids.length==0,"请选择删除的数据");
       AssertUtil.isTrue(!(loseMapper.deleteBatch(ids)==ids.length),"数据删除失败");
   }
   /*//批量删除
   @Transactional(propagation = Propagation.REQUIRED)
   public void removeLose(Integer [] ids){
       AssertUtil.isTrue(ids==null||ids.length==0,"请选择删除的物品");
       AssertUtil.isTrue(!(loseMapper.deleteBatch(ids)==ids.length),"物品删除失败");
   }*/
}

