package com.yjxxt.gymsystem.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.gymsystem.base.BaseService;
import com.yjxxt.gymsystem.bean.Equipment;
import com.yjxxt.gymsystem.mapper.EquipmentMapper;
import com.yjxxt.gymsystem.query.EquipmentQuery;
import com.yjxxt.gymsystem.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class EquipmentService extends BaseService<Equipment,Integer> {

    @Resource
    private EquipmentMapper equipmentMapper;

    /**
     * 器材模块列表查询
     * 条件
     * @param EquipmentQuery 查询条件
     * @return
     */
    public Map<String,Object> findEquipmentByParams(EquipmentQuery EquipmentQuery){
        //实例化map
        Map<String,Object> map = new HashMap<String,Object>();
        //初始化分页单位
        PageHelper.startPage(EquipmentQuery.getPage(),EquipmentQuery.getLimit());
        //开始分页
        PageInfo<Equipment> plist = new PageInfo<Equipment>(selectByParams(EquipmentQuery));
        //准备数据
        map.put("code",0);
        map.put("msg","success");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());
        //返回目标map
        return map;
    }
    @Transactional
    public void addEquipment(Equipment equipment) {
        //校验
        checkParam(equipment.getEqName(), equipment.getEqText());
        //合格
        equipment.setIsValid(1);
        AssertUtil.isTrue( equipmentMapper.insertSelective(equipment)<1,"添加器材失败");
    }

    @Transactional
    public void changeEquipment(Equipment equipment) {
        //验证
        Equipment temp = selectByPrimaryKey(equipment.getEqId());
        AssertUtil.isTrue(temp == null,"待修改的记录不存在-.-");
        //校验
        checkParam(equipment.getEqName(), equipment.getEqText());
        //合格
        AssertUtil.isTrue( equipmentMapper.updateByPrimaryKeySelective(equipment)<1,"添加器材失败");
    }

    /**
     * @param eqName
     * @param eqText
     */
    private void checkParam(String eqName,String eqText) {
        AssertUtil.isTrue(StringUtils.isBlank(eqName),"请输入器材名称");
        AssertUtil.isTrue(StringUtils.isBlank(eqText),"器材功能");
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteEquipment(Integer[] ids){
        //验证
        AssertUtil.isTrue(ids==null || ids.length==0,"请选择删除数据" );

        //判断删除成功与否
        AssertUtil.isTrue(equipmentMapper.deleteBatch(ids)<1,"删除失败了");
    }


}
