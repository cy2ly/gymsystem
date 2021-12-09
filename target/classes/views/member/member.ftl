<!DOCTYPE html>
<html>
<head>
	<title>会员列表</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" name="memberName" class="layui-input
					searchVal" placeholder="会员名" />
				</div>
				<div class="layui-input-inline">
                    <select name="memberType"  id="memberType">
                        <option value="">卡型</option>
                        <option value="1">白银</option>
                        <option value="2">黄金</option>
						<option value="3">钻石</option>
						<option value="4">至尊</option>
					</select>
				</div>
				<a class="layui-btn search_btn" data-type="reload"><i class="layui-icon">&#xe615;</i> 搜索</a>
			</div>
		</form>
	</blockquote>
	<table id="memberList" class="layui-table"  lay-filter="member"></table>


	<script type="text/html" id="toolbarDemo">
		<div class="layui-btn-container">
			<a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
				<i class="layui-icon">&#xe608;</i>
				添加
			</a>
			<a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
				<i class="layui-icon">&#xe608;</i>
				删除
			</a>
		</div>
	</script>


	<!--操作-->
	<script id="saleChanceListBar" type="text/html">
		<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">退卡</a>
	</script>

</form>
<script type="text/javascript" src="${ctx}/js/member/member.js"></script>

</body>
</html>