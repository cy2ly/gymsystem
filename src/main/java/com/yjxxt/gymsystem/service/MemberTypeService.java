package com.yjxxt.gymsystem.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.gymsystem.base.BaseService;
import com.yjxxt.gymsystem.bean.MemberType;
import com.yjxxt.gymsystem.mapper.MemberTypeMapper;
import com.yjxxt.gymsystem.query.MemberTypeQuery;
import com.yjxxt.gymsystem.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class MemberTypeService extends BaseService<MemberType, Integer> {
    @Resource
    private MemberTypeMapper memberTypeMapper;

    /**
     * 表格数据信息
     *
     * @param memberTypeQuery
     * @return
     */
    public Map<String, Object> queryByParams(MemberTypeQuery memberTypeQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(memberTypeQuery.getPage(), memberTypeQuery.getLimit());
        PageInfo<MemberType> pageInfo = new PageInfo<>(memberTypeMapper.selectByParams(memberTypeQuery));
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    /**
     * 添加会员卡
     *
     * @param
     */
    @Transactional
    public void addMemberType(MemberType memberType) {
        AssertUtil.isTrue(StringUtils.isBlank(memberType.getTypeName()), "会员卡名字不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(memberType.getTypeDay()), "会员卡有效时长不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(memberType.getTypeMoney()), "会员卡价格不能为空");
        memberType.setIsValid(1);
        AssertUtil.isTrue(memberTypeMapper.insertSelective(memberType) < 1, "会员卡添加失败");
    }


    /**
     * 跟新会员卡信息
     *
     * @param memberType
     */
    @Transactional
    public void modifyMemberType(MemberType memberType) {
        MemberType temp = memberTypeMapper.selectByPrimaryKey(memberType.getTypeId());
        AssertUtil.isTrue(temp == null, "要更新的会员卡信息不存在！");
        AssertUtil.isTrue(StringUtils.isBlank(memberType.getTypeName()), "会员卡名字不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(memberType.getTypeDay()), "会员卡有效时长不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(memberType.getTypeMoney()), "会员卡价格不能为空");
        AssertUtil.isTrue(memberTypeMapper.updateByPrimaryKeySelective(memberType) < 1, "会员卡更新失败");
    }


    /**
     * 删除会员卡信息
     *
     * @param ids
     */
    @Transactional
    public void dropMemberType(String ids) {
        AssertUtil.isTrue(ids == null && ids.trim().equals(""), "请选择要删除的会员信息");
        String[] split = ids.split(",");
        Integer[] idss = new Integer[split.length];
        for (int i = 0; i < split.length; i++) {
            idss[i] = Integer.parseInt(split[i]);
            System.out.println(idss[i]);
            //如果该会员卡还有人办理，不能删除
            //查看用户下拥有该会员卡的人数
            AssertUtil.isTrue(memberTypeMapper.selectCountByTypeId(idss[i])>0,"该会员卡还有人办理，不能删除");
        }
        System.out.println(idss.length);
        memberTypeMapper.deleteBatch(idss);
    }
}
