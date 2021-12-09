layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 器材列表展示
     */
    var  tableIns = table.render({
        elem: '#eqList',
        url : ctx+'/equipment/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "eqListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "eqId", title:'编号',fixed:"true", width:80},
            {field: 'eqName', title: '器材名字', minWidth:50, align:"center"},
            {field: 'eqText', title: '器材功能', minWidth:100, align:'center'},
            // {field: 'eqImage', title: '器材图片', minWidth:100, align:'center', templet: function (e){
            //         return "<img src = '"+e.pic+"' height='100%' width='100%'></img>"
            //     }},
            {field: 'eqNumber', title: '器材个数', align:'center'},
            {title: '操作', minWidth:150, templet:'#eqListBar',fixed:"right",align:"center"}
        ]]
    });



    /*实现搜索功能，页面重载*/
    $(".search_btn").click(function(){
        //这里以搜索为例
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                eqName:$("input[name=eqName]").val(),
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });
    /*头部工具栏绑定*/
    //头工具栏事件
    table.on('toolbar(eqs)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'add':
                //layer.msg("添加");
                openAddOrUpdateEquipmentPage();
                break;
            case 'del':
               // layer.msg("dels");
                deleteEquipment(checkStatus.data);
                break;

        };
    });



    function deleteEquipment(datas){
        if(datas.length==0){
            layer.msg("请选择要要删除的数据");
            return ;
        }
        layer.confirm("好兄弟，你确定删除数据吗?",{
            btn:["确认","取消"]
        },function(index){
            //关闭
            layer.close(index);
            //收集数据
            var ids="&ids=";
            for (var i = 0; i < datas.length ; i++) {
                if(i< datas.length -1){
                    ids=ids+datas[i].eqId+"&ids=";
                }else{
                    ids=ids+datas[i].eqId;
                }
            }
            console.log(ids);
            //发送ajax删除数据
            $.post(ctx+"/equipment/delete",ids,function(result){
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
     * 添加-更新的
     * @param eqId
     */
    function openAddOrUpdateEquipmentPage(eqId){
        console.log(eqId);
        var title="<h2>器材模块---添加</h2>";
        var url=ctx+"/equipment/addOrUpdatePage";

        //判断是否修改还是添加
        if(eqId){
            title="<h2>器材模块---更新</h2>";
            url=url+"?eqId="+eqId;
        }
        //
        layer.open({
            title:title,
            content:url,
            type:2,//iframe
            area:["650px","400px"],
            maxmin:true,
        })
    }


    /*行内工具栏的绑定*/
    //监听行工具事件
    table.on('tool(eqs)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm("好兄弟，你确定删除数据吗?",{
                btn:["确认","取消"]
            },function(index){
                //关闭
                layer.close(index);
                //发送ajax删除数据
                $.post(ctx+"/equipment/delete",{ids:data.eqId},function(result){
                    if(result.code==200){
                        //重新加载数据
                        tableIns.reload();
                    }else{
                        //提示一下
                        layer.msg(result.msg,{icon:5 });
                    }
                },"json");
            });
        } else if(obj.event === 'edit'){
            openAddOrUpdateEquipmentPage(data.eqId);
        }
    });
});