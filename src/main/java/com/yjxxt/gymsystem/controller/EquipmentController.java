package com.yjxxt.gymsystem.controller;

import com.yjxxt.gymsystem.base.BaseController;
import com.yjxxt.gymsystem.base.ResultInfo;
import com.yjxxt.gymsystem.bean.Equipment;
import com.yjxxt.gymsystem.query.EquipmentQuery;
import com.yjxxt.gymsystem.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("equipment")
public class EquipmentController extends BaseController {

    @Autowired
    private EquipmentService equipmentService;


    @RequestMapping("index")
    public String index(){
        return "equipment/equipment";
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String ,Object> saylist(EquipmentQuery equipmentQuery){
        //调用方法获取数据
        System.out.println(equipmentQuery);
        Map<String,Object> map = equipmentService.findEquipmentByParams(equipmentQuery);
        //map 转 json
        //返回目标map
        return map;
    }

    @RequestMapping("addOrUpdatePage")
    public String addOrUpdate(Integer eqId, Model model){
        System.out.println(eqId);
        //判断
        if(eqId!=null){
            //查询用户信息
            Equipment equipment =  equipmentService.selectByPrimaryKey(eqId);
            //存储
            System.out.println(equipment.getEqId());
            model.addAttribute("equipment",equipment);
        }
        return "equipment/add_update";
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(Equipment equipment){
        //用户的添加
        equipmentService.addEquipment(equipment);
        System.out.println(equipment);
        //返回目标数据对象
        return success("器材添加成功!");
    }



    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(Equipment equipment){
        System.out.println(equipment);
        //用户的修改
        equipmentService.changeEquipment(equipment);
        //返回目标数据对象
        return success("用户修改成功!");
    }


    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer[] ids){
        //用户的删除
        System.out.println(ids);
        equipmentService.deleteEquipment(ids);
        //返回目标数据对象
        return success("批量删除用户成功!");
    }


}
