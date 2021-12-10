<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <#--商品信息的id-->
        <input type="hidden" name="productId" value="${(Merchandise.productId)!}">
        <label class="layui-form-label">商品名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="productName" id="productName"  value="${(Merchandise.productName)!}" placeholder="请输入商品名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">商品进价</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="purchasingPrice"
                   id="purchasingPrice" value="${(Merchandise.purchasingPrice)!}" placeholder="请输入商品进价">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">商品售价</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="sellingPrice"
                   lay-verify="required"  value="${(Merchandise.sellingPrice)!}" placeholder="请输入商品售价">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">商品数量</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="stock"
                   name="stock" value="${(Merchandise.stock)!}" id="stock" placeholder="请输入商品数量">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="remarks" value="${(Merchandise.remarks)!}" id="remarks" placeholder="请输入备注">
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateMerchandise">
               提交
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">关闭</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/merchandise/add.merchandise.js"></script>
</body>
</html>