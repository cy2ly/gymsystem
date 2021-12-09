package com.yjxxt.gymsystem.controller;



import com.yjxxt.gymsystem.base.BaseController;
import com.yjxxt.gymsystem.base.ResultInfo;
import com.yjxxt.gymsystem.bean.Merchandise;
import com.yjxxt.gymsystem.query.MerchandiseQuery;
import com.yjxxt.gymsystem.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.spec.PSource;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("merchandise")
public class MerchandiseController extends BaseController {
    @Autowired
    MerchandiseService merchandiseService;


    //跳转到商品列表页面
    @RequestMapping("index")
    public String index(){
        return "merchandise/merchandise";
    }
    //商品列表查询
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectmer(MerchandiseQuery merchandiseQuery){
        Map<String, Object> map = merchandiseService.selectByCondition(merchandiseQuery);
        return map;
    }

    //跳转到添加或修改页面
    @RequestMapping("addandupdate")
    public String addAndUpdate(Integer id, Model model){
        System.out.println(id);
        if (null!=id){
            Merchandise merchandise = merchandiseService.selectByPrimaryKey(id);
            model.addAttribute("Merchandise",merchandise);
        }
        return "merchandise/add_merchandise";
    }


    //商品添加
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addMerchandise(Merchandise merchandise){
        merchandiseService.addMerchandise(merchandise);
        return success("添加成功");
    }
    //商品修改
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateMerchandise(Merchandise merchandise){
        merchandiseService.updateMerchandise(merchandise);
        return success("商品修改成功");
    }
    //商品删除
    @RequestMapping("dels")
    @ResponseBody
    public ResultInfo delectMerchandise(Integer[] its){
        merchandiseService.delectsMerchandise(its);
        return success("删除成功");
    }
    //查询所有商品的名称
    @RequestMapping("query")
    @ResponseBody
    public List<String> selectByMerkey(){

        return merchandiseService.queryAllSales();

    }



}
