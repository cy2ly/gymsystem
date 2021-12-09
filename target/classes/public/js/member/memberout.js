layui.use(['table', 'layer'], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 营销机会列表展示
     */
    var tableIns = table.render({
        elem: '#memberList', // 表格绑定的ID
        url: ctx + '/member/list1', // 访问数据的地址
        cellMinWidth: 95,
        page: true, // 开启分页
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        toolbar: "#toolbarDemo",
        id: "saleChanceListTable",
        cols: [[
            {type: "checkbox", fixed: "center"},
            {field: "memberId", title: '会员编号', fixed: "true"},
            {field: 'memberName', title: '会员名字', align: "center"},
            {field: 'memberPhone', title: '会员电话', align: 'center'},
            {
                field: 'memberSex', title: '会员性别', align: 'center', templet: function (d) {
                    return formatterSex(d.memberSex);
                }
            },
            {field: 'memberAge', title: '会员年龄', align: 'center'},
            {
                field: 'memberType', title: '会员类型', align: 'center', templet: function (d) {
                    return formatterType(d.memberType);
                }
            },
            {field: 'createDate', title: '办卡时间', align: 'center'},
            {field: 'memberBirthday', title: '会员生日', align: 'center'},
            {field: 'memberRenew', title: '会员到期时间', align: 'center'},
            {field: 'isValid', title: '状态',align: 'center',templet:function(d){
                    return formatterValid(d.isValid);
                }},
            {title: '操作', templet: '#saleChanceListBar', fixed: "right", align: "center", minWidth: 150}
        ]]
    });

    /**
     * 格式化会员类型
     */
    function formatterType(memberType) {
        if (memberType == 1) {
            return "<div style='color: yellow'>白银</div>";
        } else if (memberType == 2) {
            return "<div style='color: green'>黄金</div>";
        } else if (memberType == 3) {
            return "<div style='color: red'>钻石</div>";
        } else {
            return "<div style='color: cornflowerblue'>至尊</div>";
        }
    }
    function formatterValid(isValid) {
        if (isValid == 0) {
            return "<div style='color: red'>过期</div>";
        } else if (isValid == 1) {
            return "<div style='color: green'>未过期</div>";
        }
    }

    /**
     * 格式化性别
     */
    function formatterSex(memberSex) {
        if (memberSex == 1) {
            return "<div style='color: goldenrod'>男</div>";
        } else if (memberSex == 0) {
            return "<div style='color: goldenrod'>女</div>";
        }
    }


    //实现搜索功能 实现表格内容的重载
    //只重装数据
    $(".search_btn").click(function () {
        var v = $("#memberType").val()
        tableIns.reload({
            where: {
                //设定异步数据接口的额外参数，任意设
                memberName: $("input[name='memberName']").val(),
                'memberType': v,
                ka: v,
            }
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    })

    //绑定头部工具栏
    table.on('toolbar(member)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'add':
                openAddOrUpdateMembereDialog();
                break;
            case 'del':
                deleteMember(checkStatus.data);
                break;
        }
        ;
    });


    function openAddOrUpdateMembereDialog(memberId) {
        var title = "<h2>会员管理 - 会员添加</h2>";
        var url = ctx + "/member/addOrUpdateMemberPage";

        //判断传id
        if (memberId) {
            url = url + "?memberId=" + memberId;
        }

        layui.layer.open({
            title: title,
            type: 2, //iframe
            content: url,
            area: ["500px", "620px"],
            maxmin: true
        });
    }


    function deleteMember(datas){
        if(datas.length==0){
            layer.msg("请选择要要删除的数据");
            return ;
        }
        layer.confirm("主人，你确定狠心删除数据吗?",{
            btn:["确认","取消"]
        },function(index){
            //关闭
            layer.close(index);
            //收集数据
            var ids="&ids=";
            for (var i = 0; i < datas.length ; i++) {
                if(i< datas.length -1){
                    ids=ids+datas[i].memberId+"&ids=";
                }else{
                    ids=ids+datas[i].memberId;
                }
            }
            console.log(ids);
            //发送ajax删除数据
            $.post(ctx+"/member/deletes",ids,function(result){
                if(result.code==200){
                    //重新加载数据
                    tableIns.reload();
                }else{
                    //提示一下
                    layer.msg(result.msg,{icon:5 });
                }
            },"json");
        });
    }
    /**
     * 行监听事件
     */
    table.on("tool(member)", function (obj) {
        var layEvent = obj.event;
        console.log(obj.data.id);
        // 监听编辑事件
        if (layEvent === "edit") {
            openAddOrUpdateMembereDialog(obj.data.memberId);
        } else if (layEvent === "del") {
            // 监听删除事件
            layer.confirm('确定删除当前用户？', {icon: 3, title: "用户管理"}, function (index) {
                $.post(ctx + "/member/deletes", {ids: obj.data.memberId}, function (data) {
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


});
