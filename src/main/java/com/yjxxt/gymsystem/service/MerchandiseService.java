package com.yjxxt.gymsystem.service;




import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.gymsystem.base.BaseService;
import com.yjxxt.gymsystem.bean.Merchandise;
import com.yjxxt.gymsystem.mapper.MerchandiseMapper;
import com.yjxxt.gymsystem.query.MerchandiseQuery;
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
public class MerchandiseService extends BaseService<Merchandise,Integer> {
    @Autowired(required = false)
    MerchandiseMapper merchandiseMapper;


    //查询商品信息
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> selectByCondition(MerchandiseQuery merchandiseQuery){
        //准备数据
        HashMap<String, Object> map = new HashMap<>();
        //实例化分页
        PageHelper.startPage(merchandiseQuery.getPage(), merchandiseQuery.getLimit());
        //开始分页
        PageInfo<Merchandise> pageInfo = new PageInfo<>(selectByParams(merchandiseQuery));
        //填充数据
        map.put("code",0);
        map.put("mgs","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    //商品信息添加
    @Transactional(propagation = Propagation.REQUIRED)
    public void addMerchandise(Merchandise merchandise) {
        System.out.println("你好");
        Merchandise mec = merchandiseMapper.selectByProductName(merchandise.getProductName());
        AssertUtil.isTrue(null!=mec,"已有该商品,请选择修改");
        //判断商品数据
        judge(merchandise);
        //添加默认值
        merchandise.setIsValid(1);
        //执行添加,判断结果
        AssertUtil.isTrue(merchandiseMapper.insertSelective(merchandise)<1,"添加商品信息失败");
    }
    //商品信息修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMerchandise(Merchandise merchandise) {
        System.out.println("进来了");
        //判断修改数据是否存在
        Merchandise me = merchandiseMapper.selectByPrimaryKey(merchandise.getProductId());
        AssertUtil.isTrue(null==me||"".equals(me),"修改数据不存在");
        //判断商品数据
        judge(merchandise);
        //添加默认值
        merchandise.setIsValid(1);
        //执行添加,判断结果
        AssertUtil.isTrue(merchandiseMapper.updateByPrimaryKeySelective(merchandise)<1,"修改商品信息失败");
    }
    //校验数据
    public void judge(Merchandise merchandise){
        AssertUtil.isTrue(StringUtils.isBlank(merchandise.getProductName()),"请输入商品名称");
        AssertUtil.isTrue(null==merchandise.getPurchasingPrice()||0==merchandise.getPurchasingPrice(),"请输入正确的商品进价");
        AssertUtil.isTrue(null==merchandise.getSellingPrice(),"请定义商品售价");
        AssertUtil.isTrue(0==merchandise.getSellingPrice(),"你想亏本吗");
        AssertUtil.isTrue(null==merchandise.getStock(),"请输入你要购买商品的数量");
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void delectsMerchandise(Integer[] ids) {
        //判断数据是否存在
        AssertUtil.isTrue(0== ids.length|null==ids,"要删除商品信息不存在");
        //执行删除,判断结果
        AssertUtil.isTrue(merchandiseMapper.deleteBatch(ids)!=ids.length,"删除失败");
    }
    //查询所有商品名称
    public List<String> queryAllSales() {
        return merchandiseMapper.queryAllSales();
    }


}
