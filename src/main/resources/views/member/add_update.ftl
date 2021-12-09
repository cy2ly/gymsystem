<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <input type="hidden" name="memberId" value="${(member.memberId)!}">
        <#-- 会员卡类型  -->
        <input type="hidden" name="memberType" value="${(member.memberType)!}">
        <label class="layui-form-label">会员名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required" name="memberName" id="memberName"
                   value="${(member.memberName)!}" placeholder="请会员客户名称">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">会员电话</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="memberPhone" id="memberPhone"
                   value="${(member.memberPhone)!}" placeholder="会员电话">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">会员性别</label>
            <div class="layui-input-block">
                    <select name="memberSex" id="memberSex">
                        <option value="">请选择</option>
                        <option value="1" <#if (member.memberSex)??><#if member.memberSex==1 >selected="selected"</#if></#if>>男</option>
                        <option value="0" <#if (member.memberSex)??><#if member.memberSex==0 >selected="selected"</#if></#if>>女</option>
                    </select>
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">会员年龄</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="memberAge" lay-verify="required" value="${(member.memberAge)!}"
                   placeholder="请输入会员年龄">
        </div>
    </div>


    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">会员卡类型</label>
        <div class="layui-input-block">
                <select name="memberType" id="memberType">
                    <option value="">请选择</option>
<#--                    <option value="1" <#if (member.memberType)??><#if member.memberType==1> selected="selected" </#if></#if>>季卡</option>-->
<#--                    <option value="2" <#if (member.memberType)??><#if member.memberType==2> selected="selected" </#if></#if>>月卡</option>-->
<#--                    <option value="3" <#if (member.memberType)??><#if member.memberType==3> selected="selected" </#if></#if>>年卡</option>-->
<#--                    <option value="4" <#if (member.memberType)??><#if member.memberType==4> selected="selected" </#if></#if>>周卡</option>-->
                </select>
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">会员生日</label>
        <div class="layui-input-block">
            <input type="date"  class="layui-input" name="memberBirthday"
<#--                   <#if (member.memberBirthday)??>value="${(member.memberBirthday)?string('yyyy-MM-dd')}"</#if>-->
                   placeholder="请输入会员生日">
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateMember">确认</button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="quxiao">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/member/add_update.js"></script>
</body>
</html>