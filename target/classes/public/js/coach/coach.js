layui.use(['table','layer'],function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 用户列表展示
     */
    var tableIns = table.render({
        elem: '#coachList',
        url: ctx + '/coach/list',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        toolbar: "#toolbarDemo",
        id: "coachListTable",
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: "coachId", title: '编号', fixed: "true", width: 80},
            {field: 'coachName', title: '教练名称', minWidth: 50, align: "center"},
            {field: 'coachPhone', title: '教练电话', minWidth: 100, align: 'center'},
            {field: 'coachSex', title: '性别', minWidth: 50, align: 'center', templet: function (s){
                    return formatCoachSex(s.coachSex);
                }
            },
            {field: 'coachAddress', title: '家庭住址', align: 'center',minWidth: 150},
            {field: 'coachDate', title: '工作时间', align: 'center', minWidth: 150},
            {field: 'coachYear', title: '教龄', align: 'center', minWidth: 50},
            {field: 'coachSalary', title: '工资', align: 'center', minWidth: 100},
            {
                title: '操作', minWidth: 150,
                templet: '#coachListBar', fixed: "right", align: "center"
            }
        ]]
    });

    /**
     * 格式化性别
     * 1为男
     * 2为女
     * @param coachSex
     * @returns {string}
     */
    function formatCoachSex(coachSex){
        if(coachSex==1) {
            return "<div>男</div>";
        } else if(coachSex==2) {
            return "<div>女</div>";
        }
    }
    /**
     * 头部工具栏事件
     */
    table.on("toolbar(coaches)", function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case "add":
                openAddOrUpdateCoachDialog();
                break;
            case "del":
                deleteUser(checkStatus.data);
                break;
        }
    });

    /**
     * 批量删除用户
     * @param datas
     */
    function deleteUser(datas) {
        if (datas.length == 0) {
            layer.msg("请选择删除记录!", {icon: 5});
            return;
        }
        layer.confirm('确定删除选中的用户记录？', {
            btn: ['确定', '取消'] //按钮
        }, function (index) {
            layer.close(index);
            var ids = "ids=";
            for (var i = 0; i < datas.length; i++) {
                if (i < datas.length - 1) {
                    ids = ids + datas[i].coachId + "&ids=";
                } else {
                    ids = ids + datas[i].coachId
                }
            }
            $.ajax({
                type:"post",
                url: ctx + "/coach/delete",
                data: ids,
                dataType: "json",
                success:
                    function (data) {
                        if (data.code == 200) {
                            tableIns.reload();
                            layer.msg(data.msg)
                        } else {
                            layer.msg(data.msg, {icon: 5});
                        }
                    }
            })
        });
    }

    /**
     * 行监听事件
     */
    table.on("tool(coaches)", function (obj) {
        var layEvent = obj.event;
        console.log(obj.data.id);
        // 监听编辑事件
        if (layEvent === "edit") {
            openAddOrUpdateCoachDialog(obj.data.coachId);
        } else if (layEvent === "del") {
            // 监听删除事件
            layer.confirm('确定删除当前用户？', {icon: 3, title: "用户管理"}, function (index) {
                $.post(ctx + "/coach/delete", {ids: obj.data.coachId}, function (data) {
                    if (data.code == 200) {
                        layer.msg("操作成功！");
                        tableIns.reload();
                    } else {
                        layer.msg(data.msg, {icon: 5});
                    }
                });
            });
        }
    });
    /**
     * 绑定搜索按钮的点击事件
     */
    $(".search_btn").on("click",function(){
        table.reload("coachListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                coachName: $("input[name='coachName']").val(), //用户名
                coachSex: $("#coachSex").val(), //性别
                minYear: $("input[name='minYear']").val(), //最小教龄
                maxYear: $("input[name='maxYear']").val() //最大教龄
            }
        })
    });
    /**
     * 打开用户添加或更新对话框
     */
    function openAddOrUpdateCoachDialog(coachId) {
        var url = ctx + "/coach/addAndUpdatePage";
        var title = "教龄管理-添加教练信息";
        if (coachId) {
            url = url + "?coachId=" + coachId;
            title = "教龄管理-修改教练信息";
        }
        layui.layer.open({
            title: title,
            type: 2,
            area: ["650px", "400px"],
            maxmin: true,
            content: url
        });
    }

})