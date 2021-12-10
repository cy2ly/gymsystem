package com.yjxxt.gymsystem.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.gymsystem.base.BaseService;
import com.yjxxt.gymsystem.bean.Coach;
import com.yjxxt.gymsystem.mapper.CoachMapper;
import com.yjxxt.gymsystem.query.CoachQuery;
import com.yjxxt.gymsystem.utils.AssertUtil;
import com.yjxxt.gymsystem.utils.PhoneUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class CoachService extends BaseService<Coach,Integer> {

    @Resource
    private CoachMapper coachMapper;

    /**
     * 多功能分页查询
     * @param coachQuery
     * @return
     */
    public Map<String,Object> list(CoachQuery coachQuery){
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(coachQuery.getPage(),coachQuery.getLimit());
        PageInfo<Coach> pageInfo = new PageInfo<>(coachMapper.selectByParams(coachQuery));
        map.put("code",0);
        map.put("msg","");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    /**
     * 添加教练
     * @param coach
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addCoach(Coach coach){
        //校验数据
        AssertUtil.isTrue(coach.getCoachPhone()==null||coach.getCoachPhone()=="","电话号码不能为空!");
        AssertUtil.isTrue(!PhoneUtil.isMobile(coach.getCoachPhone()),"电话号码格式不正确!");
        //设置属性
        coach.setIsValid(1);
        //执行添加
        AssertUtil.isTrue(insertSelective(coach)<1,"教练添加失败!");
    }

    /**
     * 修改教练
     * @param coach
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCoach(Coach coach){
        //校验数据
        AssertUtil.isTrue(coach.getCoachPhone()==null||coach.getCoachPhone()=="","电话号码不能为空!");
        AssertUtil.isTrue(!PhoneUtil.isMobile(coach.getCoachPhone()),"电话号码格式不正确!");
        //设置属性
        coach.setIsValid(1);
        //执行修改
        AssertUtil.isTrue(updateByPrimaryKeySelective(coach)<1,"教练修改失败");
    }

    /**
     *删除教练
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCoach(Integer[] ids){
        //数据校验
        AssertUtil.isTrue(ids==null||ids.length==0,"请选择待删除的用户记录!");
        //执行删除
        AssertUtil.isTrue(coachMapper.deleteBatch(ids)<1,"用户删除失败!");
    }
}
