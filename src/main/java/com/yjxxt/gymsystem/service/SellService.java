package com.yjxxt.gymsystem.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.gymsystem.base.BaseService;
import com.yjxxt.gymsystem.bean.Sell;
import com.yjxxt.gymsystem.mapper.MerchandiseMapper;
import com.yjxxt.gymsystem.mapper.SellMapper;
import com.yjxxt.gymsystem.query.SellQuery;
import com.yjxxt.gymsystem.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SellService extends BaseService<Sell,Integer> {
    @Autowired(required = false)
    SellMapper sellMapper;
    @Autowired(required = false)
    MerchandiseMapper merchandiseMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> selectByCondition(SellQuery sellQuery) {
        //准备数据
        HashMap<String, Object> map = new HashMap<>();
        //实例化分页
        PageHelper.startPage(sellQuery.getPage(), sellQuery.getLimit());
        //开始分页
        PageInfo<Sell> pageInfo = new PageInfo<>(sellMapper.selectByParams(sellQuery));
        //填充数据
        map.put("code",0);
        map.put("mgs","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    //添加商品
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveSell(Sell sell) {
        //校验
        AssertUtil.isTrue(StringUtils.isBlank(sell.getSellName()), "请选择商品名称");
        AssertUtil.isTrue(null == sell.getSellAmount() , "请输入商品数量");
        //设置默认值
        Integer se = sell.getSellAmount();
        //根据id查询售价
        double v = se * merchandiseMapper.selectbyprice(sell.getSellName());
        sell.setSellPrice(v);
        sell.setIsVaild(1);
        //根据商品名称查询商品id
        sell.setProductId(merchandiseMapper.sleetById(sell.getSellName()));

        //3.执行添加 判断结果
        AssertUtil.isTrue(insertSelective(sell) < 1, "添加失败");
    }
    //删除
    @Transactional(propagation = Propagation.REQUIRED)
    public void delectsMerchandise(Integer[] ids) {
        //判断数据是否存在
        AssertUtil.isTrue(0== ids.length|null==ids,"要删除商品信息不存在");
        //执行删除,判断结果
        AssertUtil.isTrue(sellMapper.deleteBatch(ids)!=ids.length,"删除失败");
    }
}
