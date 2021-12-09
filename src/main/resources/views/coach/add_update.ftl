<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="coachId" type="hidden" value="${(coach.coachId)!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">教练名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input coachName"
                   lay-verify="required" name="coachName" id="coachName"  value="${(coach.coachName)!}" placeholder="请输入教练姓名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">教练年龄</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input coachAge"
                   lay-verify="required" name=" coachAge" id=" coachSex" value="${(coach.coachAge)!}" placeholder="请输入教练年龄">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">教练电话</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input coachPhone"
                   lay-verify="phone" name="coachPhone" value="${(coach.coachPhone)!}"
                   id="coachPhone"
                   placeholder="请输入教练电话">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">家庭住址</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input coachAddress"
                   lay-verify="required" name="coachAddress" value="${(coach.coachAddress)!}" id="coachAddress" placeholder="请输入家庭住址">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">上班时间</label>
        <div class="layui-input-block">
            <input type="text" name="coachDate" id="coachDate"
                   lay-verify="date" placeholder="请输入开始上班时间" autocomplete="off" class="layui-input coachDate" value="${((coach.coachDate)?string("yyyy-MM-dd"))!}">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">教龄</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input coachYear"
                   lay-verify="required" name="coachYear" value="${(coach.coachYear)!}"
                   id="coachYear"
                   placeholder="请输入教龄">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">薪资</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input coachSalary"
                   lay-verify="required" name="coachSalary" value="${(coach.coachSalary)!}"
                   id="coachSalary"
                   placeholder="请输入薪资">
        </div>
    </div>

    <div class="magb15 layui-col-md4 layui-col-xs12">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <select name="coachSex">
                <option value="">请选择性别</option>
                <option value="1">男</option>
                <option value="2">女</option>
            </select>
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateCoach">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/coach/add_update.js"></script>
</body>
</html>