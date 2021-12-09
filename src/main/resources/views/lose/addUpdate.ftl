<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input type="hidden" name="loseId" value="${(lose.loseId)!}">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">物品名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="loseName"
                   id="chanceSource" value="${(lose.loseName)!}" placeholder="请输入物品名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">丢失位置</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="loseAddress"
                   lay-verify="required" value="${(lose.loseAddress)!}" placeholder="请输入丢失位置">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">谁捡到的</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="scAvenger"
                   name="scAvenger" value="${(lose.scAvenger)!}" id="scAvenger" placeholder="请输入谁捡到的">
        </div>
    </div>
<#--    <div class="layui-form-item layui-row layui-col-xs12">-->
<#--        <label class="layui-form-label">捡到时间</label>-->
<#--        <div class="layui-input-block">-->
<#--            <input type="text" class="layui-input"-->
<#--                   name="overview" value="${(lose.loseJdate)!}" id="loseJdate" placeholder="请输入捡到时间">-->
<#--        </div>-->
<#--    </div>-->
    <#--<div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">失主电话</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="phone" value="${(lose.phone)!}"
                   placeholder="请输入失主电话">
        </div>-->
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">取回时间</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="overview" value="${(lose.overview)!}" id="overview" placeholder="请输入取回时间">
        </div>
    </div>
    </div>
    <#--<div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="cgjl" value="${(saleChance.cgjl)!}"
                   placeholder="请输入状态">
        </div>-->
    <#--<div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">操作</label>
        <div class="layui-input-block">
            <select name="assignMan" id="assignMan">
                <option value="${(saleChance.assignMan)!}">请选择</option>
            </select>
        </div>
    </div>-->
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateLose">
                添加

            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">
                取消
            </button>
        </div>
    </div>
</form>
<script type="text/javascript"
        src="${ctx}/js/lose/addUpdate.js"></script>
</body>
</html>