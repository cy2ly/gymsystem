layui.use(['table','layer'],function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    /**
     *
     */
    var tableIns = table.render({
        elem: '#loseList', // 表格绑定的ID
        url: ctx + '/lose/list', // 访问数据的地址
        cellMinWidth: 95,
        page: true, // 开启分页
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        toolbar: "#toolbarDemo",
        id: "loseListTable",
        cols: [[
            {type: "checkbox", fixed: "center"},
            {field: "loseId", title: '丢失物品编号', fixed: "true"},
            {field: 'loseName', title: '丢失物品名称', align: "center"},
            // {field: 'loseImages', title: '丢失物品图片', align: 'center'},
            {field: 'loseAddress', title: '丢失位置', align: 'center'},
            {field: 'loseJdate', title: '丢失时间', align: 'center'},
            {field: 'loseStatus', title: '丢失状态', align: 'center',templet: function (status) {
                return formatterSex(status.loseStatus);
             }},
            {field: 'scAvenger', title: '拾取者', align: 'center'},
            // {field: 'loseLdate', title: '取回时间', align: 'center'},
            {title: '操作', templet: '#loseListBar', fixed: "right", align: "center", minWidth: 150}
        ]]
    });


    /*实现搜索功能，页面重载*/
    $(".search_btn").click(function () {
        //这里以搜索为例
        // tableIns.reload({
        //     where: { //设定异步数据接口的额外参数，任意设
        //         customerName: $("input[name=customerName]").val(),
        //         createMan:$("input[name=createMan]").val(),
        //         state:$("#state").val()
        //     }
        //     ,page: {
        //         curr: 1 //重新从第 1 页开始
        //     }
        // })

        table.reload("loseListTable", {
            where: { //设定异步数据接口的额外参数，任意设
                loseName: $("input[name=loseName]").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });


        /*绑定头部工具栏*/
        //触发事件
        table.on('toolbar(lose)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            console.log(checkStatus);
            console.log(checkStatus.data);
            switch (obj.event) {
                case 'add':
                    //layer.msg('添加');
                    openAddOrUpdateLoseDialog();
                    break;
                case 'del':
                    //layer.msg('删除');
                    deleteLose(checkStatus.data);
                    break;
            }
            ;
        });
        /**
         *
         */
        function openAddOrUpdateLoseDialog() {
            var title = "<h2>丢失物品- 添加</h2>";
            var url = ctx + "/lose/addOrUpdateLosePage";
            layui.layer.open({
                title: title,
                type: 2, //iframe
                content: url,
                area: ["500px", "620px"],
                maxmin: true
            });
        }


        /**
         * 添加修改函数
         * @param
         */
        function addOrUpdateLoseDialog(loseId) {
            var title = "<h3>丢失物品-更新</h3>";
            var url = ctx + "/lose/addOrUpdateLosePage";
            console.log(loseId);
            //判断 非空即为true
            if (loseId) {
                url = url + "?id=" + loseId;
            }
            /*弹出层*/
            layui.layer.open({
                title: title,
                content: url,
                type: 2,//ifream
                area: ["500px", "620px"],
                maxmin: true
            })
        }
    function deleteLose(datas){
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
                    ids=ids+datas[i].loseId+"&ids=";
                }else{
                    ids=ids+datas[i].loseId;
                }
            }
            console.log(ids);
            //发送ajax删除数据
            $.post(ctx+"/lose/delete",ids,function(result){
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
    //状态
    function formatterSex(memberSex) {
        if (memberSex == 1) {
            return "<div style='color: red'>未取</div>";
        } else if (memberSex == 0) {
            return "<div style='color: green'>已取</div>";
        }
    }
    /*行内工具栏的绑定*/
    //监听行工具事件
    table.on('tool(lose)', function(obj){
        var data = obj.data;
        if(obj.event === 'del') {
            layer.confirm("主人，你确定狠心删除数据吗?", {
                btn: ["确认", "取消"]
            }, function (index) {
                //关闭
                layer.close(index);
                //发送ajax删除数据
                $.post(ctx + "/lose/delete", {ids: data.loseId}, function (result) {
                    if (result.code == 200) {
                        //重新加载数据
                        tableIns.reload();
                    } else {
                        //提示一下
                        layer.msg(result.msg, {icon: 5});
                    }
                }, "json");
            });
        }
    });

});

