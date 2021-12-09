package com.yjxxt.gymsystem.controller;

import com.yjxxt.gymsystem.base.BaseController;
import com.yjxxt.gymsystem.base.BaseMapper;
import com.yjxxt.gymsystem.base.ResultInfo;
import com.yjxxt.gymsystem.bean.Sell;
import com.yjxxt.gymsystem.query.MerchandiseQuery;
import com.yjxxt.gymsystem.query.SellQuery;
import com.yjxxt.gymsystem.service.SellService;
import com.yjxxt.gymsystem.utils.LoginUserUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("sell")
public class SellController extends BaseController {
    @Autowired
    SellService sellService;
    //跳转到商品消费界面
    @RequestMapping("index")
    public String index(){
        return "merchandise/goods_sold";
    }
    //商品售卖列表查询
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectmer(SellQuery sellQuery){
        Map<String, Object> map = sellService.selectByCondition(sellQuery);
        return map;
    }

    //添加数据
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveSaleChance(Sell sell){
        System.out.println(sell);
        sellService.saveSell(sell);
        return success("添加成功");
    }


    //* 数据添加与更新表单页面视图转发
    @RequestMapping("addPages")
    public  String addOrUpdateSaleChancePage(Integer id , Model model){
        return "merchandise/add_goods_sold";
    }
    //删除
    //商品删除
    @RequestMapping("dels")
    @ResponseBody
    public ResultInfo delectMerchandise(Integer[] its){
        System.out.println(its.length);
        sellService.delectsMerchandise(its);
        return success("删除成功");
    }


}
