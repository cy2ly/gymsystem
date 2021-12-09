package com.yjxxt.gymsystem.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.yjxxt.gymsystem.base.BaseService;
import com.yjxxt.gymsystem.bean.Member;
import com.yjxxt.gymsystem.mapper.MemberMapper;
import com.yjxxt.gymsystem.query.MemberQuery;
import com.yjxxt.gymsystem.utils.AssertUtil;
import com.yjxxt.gymsystem.utils.PhoneUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


@Service
public class MemberService extends BaseService<Member, Integer> {
    @Resource
    private MemberMapper memberMapper;

    public Map<String, Object> findUserByParams(MemberQuery memberQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(memberQuery.getPage(), memberQuery.getLimit());
        PageInfo<Member> pageInfo = new PageInfo<>(memberMapper.selectByParams(memberQuery));
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    /**
     * 添加新的用户
     */
    @Transactional
    public void addMember(Member member) {
        checkMember(member.getMemberName(), member.getMemberPhone(), member.getMemberAge(), member.getMemberType());
        member.setIsValid(1);
        member.setCreateDate(new Date());
        Calendar instance = Calendar.getInstance();
        if (member.getMemberType()==1){
            instance.add(Calendar.DATE,90);
        }else if (member.getMemberType()==2){
            instance.add(Calendar.DATE,180);
        }else if (member.getMemberType()==3){
            instance.add(Calendar.DATE,365);
        }else if (member.getMemberType()==4){
            instance.add(Calendar.DATE,3650);
        }
        member.setMemberRenew(instance.getTime());
        AssertUtil.isTrue(memberMapper.insertSelective(member)<1,"添加会员失败");
    }

    /**
     * 检测
     */
    public void checkMember(String memberName, String phone, Integer age, Integer type) {
        AssertUtil.isTrue(StringUtils.isBlank(memberName), "请输入会员名称");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone), "请输入正确是手机号");
        AssertUtil.isTrue(age < 0 || age > 100, "请输入正确的年龄");
        AssertUtil.isTrue(type == null, "会员卡类型不正确");
    }

    public void modifyMember(Member member) {
        member.setIsValid(1);
        Member temp = memberMapper.selectByPrimaryKey(member.getMemberId());
        AssertUtil.isTrue(temp==null,"修改的会员信息不存在！");
        checkMember(member.getMemberName(),member.getMemberPhone(),member.getMemberAge(),member.getMemberType());
        AssertUtil.isTrue( memberMapper.updateByPrimaryKeySelective(member)<1,"会员信息更新失败！");
    }

    /**
     * 删除用户信息
     * @param ids
     */
    public void dropMembers(Integer [] ids) {
        AssertUtil.isTrue(ids==null || ids.length==0,"请选择要删除的数据");
        System.out.println(memberMapper.deleteBatch(ids));
        AssertUtil.isTrue(memberMapper.deleteBatch(ids) < 1,"用户记录删除失败!");
    }

    /**
     * 会员卡类型
     * @return
     */
    public List<Map<String, Object>> queryTypes() {
        List<Map<String, Object>> list = memberMapper.selectTypes();
        return list;
    }
    /**
     * 查询过期会员
     * */
    public Map<String, Object> selectParams(MemberQuery memberQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(memberQuery.getPage(), memberQuery.getLimit());
        PageInfo<Member> pageInfo = new PageInfo<>(memberMapper.selectByParam(memberQuery));
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }
    /**
     * 删除过期会员信息
     * @param ids
     */
    public void dropOutMembers(Integer[] ids) {
        AssertUtil.isTrue(ids==null || ids.length==0,"请选择要删除的数据");
        System.out.println(memberMapper.deleteBatchs(ids));
        AssertUtil.isTrue(memberMapper.deleteBatchs(ids) < 1,"用户记录删除失败!");
    }
}
