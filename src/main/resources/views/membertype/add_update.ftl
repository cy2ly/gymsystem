<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <input type="hidden" name="typeId" value="${(memberType.typeId)!}">
        <#-- 会员卡类型  -->
        <label class="layui-form-label">会员卡名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required" name="typeName" id="typeName"
                   value="${(memberType.typeName)!}" placeholder="请会员卡名称">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">时长</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="typeDay" id="typeDay"
                   value="${(memberType.typeDay)!}" placeholder="时长">
        </div>
    </div>



    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">会员价格</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="typeMoney" lay-verify="required" value="${(memberType.typeMoney)!}"
                   placeholder="请输入会员价格">
        </div>
    </div>


    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateMemberType">确认</button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="quxiao">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/membertype/add_update.js"></script>
</body>
</html>