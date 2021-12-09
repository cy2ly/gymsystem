<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input type="hidden" name="courseId" value="${(course.courseId)!}">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">课程名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="courseName" id="courseName" value="${(course.courseName)!}"
                   placeholder="请输入课程名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">教练</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="coachName"
                   id="coachName" value="${(course.coachName)!}" placeholder="请输入教练">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">课程价格</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="coursePrice"
                   id="coursePrice" value="${(course.coursePrice)!}" placeholder="请输入价格">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">上课时间</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="courseDay"
                   id="courseDay" value="${(course.courseDay)!}" placeholder="请输入上课时间">
        </div>
    </div>
   <br>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateCourse">
                添加
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">
                取消
            </button>
        </div>
    </div>
</form>
<script type="text/javascript"
        src="${ctx}/js/course/add_update.js"></script>
</body>
</html>