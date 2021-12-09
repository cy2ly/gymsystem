package com.yjxxt.gymsystem.controller;

import com.yjxxt.gymsystem.base.BaseController;
import com.yjxxt.gymsystem.base.ResultInfo;
import com.yjxxt.gymsystem.bean.MemberType;
import com.yjxxt.gymsystem.query.MemberTypeQuery;
import com.yjxxt.gymsystem.service.MemberTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/membertype")
public class MemberTypeController extends BaseController {
    @Autowired
    private MemberTypeService memberTypeService;

    @RequestMapping("/index")
    public String index() {
        return "/membertype/membertype";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(MemberTypeQuery memberTypeQuery) {
        return memberTypeService.queryByParams(memberTypeQuery);
    }

    @RequestMapping("/addOrUpdateMemberPage")
    public String addOrUpdateMemberPage(Integer typeId, Model model) {
        if (typeId != null && typeId != 0) {
            MemberType memberType = memberTypeService.selectByPrimaryKey(typeId);
            model.addAttribute("memberType", memberType);
        }
        return "/membertype/add_update";
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo save(MemberType memberType) {
        memberTypeService.addMemberType(memberType);
        return success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo update(MemberType memberType) {
        System.out.println(memberType.getTypeId());
        memberTypeService.modifyMemberType(memberType);
        return success();
    }

    @RequestMapping("/dels")
    @ResponseBody
    public ResultInfo dels(String ids) {
        System.out.println("ids = " + ids);
        memberTypeService.dropMemberType(ids);
        return success();
    }
}
