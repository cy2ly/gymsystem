layui.use(['table', 'layer'], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 营销机会列表展示
     */
    var tableIns = table.render({
        elem: '#memberTypeList', // 表格绑定的ID
        url: ctx + '/membertype/list', // 访问数据的地址
        cellMinWidth: 95,
        page: true, // 开启分页
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        toolbar: "#toolbarDemo",
        id: "memberTypeTable",
        cols: [[
            {type: "checkbox", fixed: "center"},
            {field: "typeId", title: '会员卡编号', fixed: "true"},
            {field: 'typeName', title: '会员卡名字', align: "center"},
            {field: 'typeDay', title: '会员持续时间(天)', align: 'center'},
            {field: 'typeMoney', title: '会员价格（元）', align: 'center'},
            {title: '操作', templet: '#saleChanceListBar', fixed: "right", align: "center", minWidth: 150}
        ]]
    });

    //实现搜索功能 实现表格内容的重载
    //只重装数据
    $(".search_btn").click(function () {
        tableIns.reload({
            where: {
                //设定异步数据接口的额外参数，任意设
                memberTypeName: $("input[name='memberTypeName']").val(),
            }
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    })

    //绑定头部工具栏
    table.on('toolbar(memberType)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'add':
                openAddOrUpdateMemberTypeDialog();
                break;
            case 'del':
                deleteMemberType(checkStatus.data);
                break;
        };
    });


    function openAddOrUpdateMemberTypeDialog(typeId) {
        var title = "<h2>会员卡管理 - 会员卡添加</h2>";
        var url = ctx + "/membertype/addOrUpdateMemberPage";

        //判断传id
        if (typeId) {
            url = url + "?typeId=" + typeId;
        }

        layui.layer.open({
            title: title,
            type: 2, //iframe
            content: url,
            area: ["500px", "300px"],
            maxmin: true
        });
    }

    function deleteMemberType(data) {
        if (data.length == 0) {
            layer.msg("请选择数据");
            return;
        }
        //询问是否要真的删除数据
        layer.confirm("你确定要真的删除数据吗？", {
            btn: ['确认', '取消'],
        }, function (index) {
            //关闭询问框
            layer.close(index);
            //收集数据 发送ajax请求
            //收集数据
            var ids = [];
            //遍历
            for (var x in data) {
                ids.push(data[x].typeId);
            }

            console.log(ids.toString())
            //发送ajax 进行删除
            $.ajax({
                type: "post",
                url: ctx + "/membertype/dels",
                data: {ids: ids.toString()},
                dataType: "json",
                success: function (result) {
                    if (result.code == 200) {
                        layer.msg("删除成功！");
                        //页面重载
                        tableIns.reload();
                    } else {
                        layer.msg("删除失败！");
                    }
                },
            });
        });

    }

    //绑定行内工具栏
    table.on('tool(memberType)', function (obj) {
        //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
        console.log(obj.data)
        if (layEvent === 'edit') { //查看
            openAddOrUpdateMemberTypeDialog(data.typeId)
        } else if (layEvent === 'del') { //删除
            layer.confirm('真的删除行么', function (index) {
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);
                //收集数据
                var ids = [];
                ids.push(obj.data.typeId);
                //发送ajax 进行删除
                $.ajax({
                    type: "post",
                    url: ctx + "/membertype/dels",
                    data: {'ids': ids.toString()},
                    dataType: "json",
                    success: function (result) {
                        if (result.code == 200) {
                            layer.msg("删除成功！");
                            //页面重载
                            tableIns.reload();
                        } else {
                            layer.msg("删除失败！");
                        }
                    },
                })
            });
        }
    });


});
