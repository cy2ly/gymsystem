<!DOCTYPE html>
<html>
<head>
    <title>商品售卖信息</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="productId"
                           class="layui-input
					searchVal" placeholder="消费物品编号" />
                </div>
            </div>
            <a class="layui-btn search_btn" data-type="reload"><i
                        class="layui-icon">&#xe615;</i> 搜索</a>
            </div>
        </form>
    </blockquote>
    <table id="sellList" class="layui-table"  lay-filter="sells"></table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                添加消费记录
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                批量删除删除
            </a>
        </div>
    </script>


    <!--操作-->
    <script id="sellListBar" type="text/html">
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>

</form>
<script type="text/javascript" src="${ctx}/js/merchandise/goods_sold.js"></script>

</body>
</html>