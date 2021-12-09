package com.yjxxt.gymsystem.controller;

import com.yjxxt.gymsystem.annotation.RequiredPermission;
import com.yjxxt.gymsystem.base.BaseController;
import com.yjxxt.gymsystem.base.ResultInfo;
import com.yjxxt.gymsystem.bean.Member;
import com.yjxxt.gymsystem.query.MemberQuery;
import com.yjxxt.gymsystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController extends BaseController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/index")
    public String index(){
        return "/member/member";
    }
    @RequestMapping("/outdate")
    public String outdate(){
        return "/member/memberout";
    }

    @RequestMapping("/list1")
    @ResponseBody
//    @RequiredPermission(code = "101001")
    public Map<String, Object> list1(MemberQuery memberQuery,Integer ka){
        memberQuery.setMemerType(ka);
        Map<String, Object> userByParams = memberService.selectParams(memberQuery);
        return userByParams;
    }
    @RequestMapping("/list")
    @ResponseBody
//    @RequiredPermission(code = "101001")
    public Map<String, Object> list(MemberQuery memberQuery,Integer ka){
        memberQuery.setMemerType(ka);
        Map<String, Object> userByParams = memberService.findUserByParams(memberQuery);
        return userByParams;
    }


    @RequestMapping("/addOrUpdateMemberPage")
    public String addOrUpdateMemberPage(Integer memberId, Model model){
        if (memberId !=null && memberId !=0){
            Member member = memberService.selectByPrimaryKey(memberId);
            System.out.println(member);
            model.addAttribute("member",member);
        }
        return "/member/add_update";
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo save(Member member){
        System.out.println(member);
        memberService.addMember(member);
        return success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo update(Member member){
        memberService.modifyMember(member);
        return success();
    }
    @RequestMapping("/dels")
    @ResponseBody
    public ResultInfo dels(Integer[] ids){
        System.out.println(ids);
        memberService.dropMembers(ids);
        return success("批量删除成功");
    }
    @RequestMapping("/deletes")
    @ResponseBody
    public ResultInfo deletes(Integer[] ids){
        System.out.println(ids);
        memberService.dropOutMembers(ids);
        return success("批量删除成功");
    }
    @RequestMapping("/types")
    @ResponseBody
    public List<Map<String, Object>> findSales(){
        List<Map<String, Object>> list = memberService.queryTypes();
        return list;
    }


}
