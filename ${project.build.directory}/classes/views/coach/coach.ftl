<!DOCTYPE html>
<html>
<head>
    <title>教练管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="coachName" class="layui-input searchVal" placeholder="教练名" />
                </div>
                <div class="layui-input-inline">
                    <select name="coachSex"  id="coachSex">
                        <option value="" >请选择</option>
                        <option value="1">男</option>
                        <option value="2" >女</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="minYear" class="layui-input searchVal" placeholder="最小教龄" />
                </div>
                -
                <div class="layui-input-inline">
                    <input type="text" name="maxYear" class="layui-input searchVal" placeholder="最大教龄" />
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i>搜索
                </a>
            </div>
        </form>
    </blockquote>
    <table id="coachList" class="layui-table" lay-filter="coaches">
    </table>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>添加教练
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>删除教练
            </a>
        </div>
    </script>
    <!--操作-->
    <script id="coachListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>
</form>
<script type="text/javascript" src="${ctx}/js/coach/coach.js"></script>
</body>
</html>