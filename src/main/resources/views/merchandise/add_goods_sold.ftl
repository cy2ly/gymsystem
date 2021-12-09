<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <#--商品信息的id-->
        <input type="hidden" name="productId" value="${(Sell.sellId)!}">
        <input type="hidden" name="name" value="${(Sell.sellName)!}">
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">商品名称</label>
            <div class="layui-input-block">
                <select name="sellName" id="sellName">
                    <option value="">-请选择-</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">商品消耗数量</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="sellAmount"
                   id="sellAmount" value="${(Sell.sellAmount)!}" placeholder="请输入商品消耗">
        </div>
    </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="sellRemark" value="${(Sell.sellRemark)!}" id="sellRemark" placeholder="请输入备注">
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateSell">
                提交
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">关闭</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/merchandise/add_goods_sold.js"></script>
</body>
</html>