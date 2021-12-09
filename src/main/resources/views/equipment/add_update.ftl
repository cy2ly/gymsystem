<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="eqId" type="hidden" value="${(equipment.eqId)!}"/>
<#--    <div class="layui-form-item layui-row layui-col-xs12">-->
<#--        <label class="layui-form-label">器材编号</label>-->
<#--        <div class="layui-input-block">-->
<#--            <input type="text" class="layui-input eqId"-->
<#--                   lay-verify="required" name="eqId" id="eqId"  value="${(equipment.eqId)!}" placeholder="请输入器材编号">-->
<#--        </div>-->
<#--    </div>-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">器材名字</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input eqName"
                   lay-verify="required" name="eqName" id="eqName" value="${(equipment.eqName)!}" placeholder="请输入器材名字">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">器材功能</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input eqText"
                   lay-verify="eqText" name="eqText" value="${(equipment.eqText)!}"
                   placeholder="请输入器材功能">
        </div>
    </div>

<#--    <div class="layui-form-item layui-row layui-col-xs12">-->
<#--        <label class="layui-form-label">器材图片</label>-->
<#--        <div class="layui-input-block">-->
<#--            <input type="text" class="layui-input userEmail"-->
<#--                   lay-verify="eqImage" name="eqImage" value="${(equipment.eqImage)!}" id="eqImage" placeholder="请放入图片">-->
<#--        </div>-->
<#--    </div>-->


    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">器材个数</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail"
                   lay-verify="eqNumber" name="eqNumber" value="${(equipment.eqNumber)!}" placeholder="请输入器材个数">
        </div>
    </div>


    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateEquipment">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>

<script type="text/javascript" src="${ctx}/js/equipment/add_update.js"></script>
</body>
</html>