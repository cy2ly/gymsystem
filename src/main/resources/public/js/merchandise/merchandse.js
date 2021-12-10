layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
        /**
         * 商品列表
         */
        var  tableIns = table.render({
            elem: '#merchandiseList', // 表格绑定的ID
            url : ctx + '/merchandise/list', // 访问数据的地址
            cellMinWidth : 95,
            page : true, // 开启分页
            height : "full-125",
            limits : [10,15,20,25],
            limit : 10,
            toolbar: "#toolbarDemo",
            id : "merchandiseListTable",
            cols : [[
                {type: "checkbox", fixed:"center"},
                {field: "productId", title:'商品编号',fixed:"true"},
                {field: 'productName', title: '商品名称',align:"center"},
                {field: 'purchasingPrice', title: '进价',  align:'center'},
                {field: 'sellingPrice', title: '售价', align:'center'},
                {field: 'stock', title: '库存', align:'center'},
                {field: 'remarks', title: '备注',  align:'center'},
                {title: '操作', templet:'#merchandiseListBar',fixed:"right",align:"center", minWidth:150}
            ]]
        });
    //绑定搜索事假
    $(".search_btn").click(function () {
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                productName: $("input[name='productName']").val() // 商品名称
            }
            ,page: {
                curr: 1 // 重新从第 1 页开始
            }
        }); // 只重载数据
    });

    table.on('toolbar(merchandises)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'add':
// 点击添加按钮，打开添加营销机会的对话框
                openAddOrUpdateSaleChanceDialog();
                break;
            case 'del':
                // 点击删除按钮，将对应选中的记录删除
                deleteSaleChance(checkStatus.data)
                break;
        };
    });
    function openAddOrUpdateSaleChanceDialog(productIda){
        var title = "<h2>增加商品信息</h2>";
        var url = ctx + "/merchandise/addandupdate";
        // 通过id判断是添加操作还是修改操作
        if (productIda) {
            // 如果id不为空，则为修改操作
            title = "<h2>商品信息修改</h2>";
            url = url + "?id=" + productIda;
        }
        layui.layer.open({
            title:title,
            type:2, //iframe
            content: url,
            area:["500px","620px"],
            maxmin:true
        });

    };
    /**
     * 表格行 监听事件
     * saleChances为table标签的lay-filter 属性值
     */
    table.on('tool(merchandises)', function(obj){
        var data = obj.data; // 获得当前行数据
        var layEvent = obj.event; // 获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        // 判断事件类型
        if(layEvent === 'edit'){ // 编辑操作
            // 获取当前要修改的行的id
            var productIda = data.productId;
            console.log(data.productId+"一");
            // 点击表格行的编辑按钮，打开更新营销机会的对话框
            openAddOrUpdateSaleChanceDialog(productIda);
        }
        else if (layEvent == "del") { // 删除操作
// 询问是否确认删除
            layer.confirm("确定要删除这条记录吗？", {icon: 3, title:"商品信息管理"},
                function (index) {
// 关闭窗口
                    layer.close(index);
// 发送ajax请求，删除记录
                    $.ajax({
                        type:"post",
                        url: ctx + "/merchandise/dels",
                        data:{
                            its:data.productId
                        },
                        dataType:"json",
                        success:function (result) {
                            if (result.code == 200) {
// 加载表格
                                layer.msg(result.msg);
                                tableIns.reload();
                            } else {
                                layer.msg(result.msg, {icon: 5});
                            }
                        }
                    });
                });
        }
    });
    //删除
    /**
     * 删除营销机会数据
     * @param data
     */

    function deleteSaleChance(data) {
// 判断用户是否选择了要删除的记录
        if (data.length == 0) {
            layer.msg("请选择要删除的记录！");
            return;
        }
// 询问用户是否确认删除
        layer.confirm("您确定要删除选中的记录吗？",{
            btn:["确认","取消"],
        },function (index) {
// 关闭确认框
            layer.close(index);
// ids=1&ids=2&ids=3
            var its = "its=";
// 遍历获取对应的id

            for (var i = 0; i < data.length; i++) {
                if (i < data.length - 1) {
                    its = its + data[i].productId + "&ids=";
                } else {
                    its = its + data[i].productId;
                }
            }
// 发送ajax请求，删除记录
            $.ajax({
                type:"post",
                url: ctx + "/merchandise/dels",
                data:its, // 参数传递的是数组
                dataType:"json",
                success:function (result) {
                    if (result.code == 200) {
// 加载表格
                        layer.msg(result.msg);
                        tableIns.reload();
                    } else {
                        layer.msg(result.msg, {icon: 5});
                    }
                }
            });
        });
    }

    });


