layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 课程列表展示
     */
    var  tableIns = table.render({
        elem: '#courseList', // 表格绑定的ID
        url : ctx + '/course/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "courseListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "courseId", title:'课程编号',fixed:"true"},
            {field: 'courseName', title: '课程名称',align:"center"},
            {field: 'coachName', title: '教练',  align:'center'},
            {field: 'coursePrice', title: '课程价格', align:'center'},
            {field: 'courseDay', title: '上课时间',  align:'center'},
            {field: 'createDate', title: '创建时间', align:'center'},
            {field: 'updateDate', title: '更改时间',  align:'center'},
            {field: 'openDate', title: '开课时间', align:'center'},
            {title: '操作', templet:'#courseListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });
    /*实现搜索功能，页面重载*/
    $(".search_btn").click(function(){
        table.reload("courseListTable",{
            where: { //设定异步数据接口的额外参数，任意设
                courseName: $("input[name=courseName]").val(),
                coachName:$("input[name=coachName]").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });
    /*绑定头部工具栏*/
    //触发事件
    table.on('toolbar(course)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        console.log(checkStatus);
        console.log(checkStatus.data);
        switch(obj.event){
            case 'add':
                //layer.msg('添加');
                openAddOrUpdateCourseDialog();
                break;
            case 'del':
                //layer.msg('删除');
                deleteCourse(checkStatus.data);
                break;
        };
    });

    function  deleteCourse(data){
        //判断
        if(data.length == 0){
            layer.msg("请选择删除的数据");
            return ;
        }
        layer.confirm("你确定要删除这些数据吗?",{
            btn:["确认","取消"],
        },function(index){
            layer.close(index);
            var ids = "ids=";
            for (var i = 0; i < data.length; i++) {
                if (i < data.length - 1) {
                    ids = ids + data[i].coachId + "&ids=";
                } else {
                    ids = ids + data[i].coachId
                }
            }
            //发送请求删除数据
            //发送ajax删除
            $.ajax({
                type:"post",
                url:ctx+"/course/dels",
                data:ids,
                dataType:"json",
                success:function(result){
                    if(result.code==200){
                        layer.msg("删除OK",{icon : 5 });
                        //重新加载一下数据
                        tableIns.reload();
                    }else{
                        //提示
                        layer.msg(result.msg);
                    }
                }
            });
        });



    }
    // 打开添加页面
    function openAddOrUpdateCourseDialog(id){
        var url = ctx+"/course/addOrUpdateCoursePage";
        var title="课程-添加";
        if(id){
            url = url+"?id="+id;
            title="课程-更新";
        }
        layui.layer.open({
            title : title,
            type : 2,
            area:["600px","330px"],
            maxmin:true,
            content : url
        });
    }
    /*绑定行内工具栏*/
    //工具条事件
    table.on('tool(course)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        console.log(data.courseId+"--->"+data);
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

        if(layEvent === 'del'){ //删除
            layer.confirm("你确定要删除这些数据吗?",{
                btn:["确认","取消"],
            },function(index){
                //关闭询问框
                layer.close(index);
                //发送ajax删除
                $.ajax({
                    type:"post",
                    url:ctx+"/course/dels",
                    data:{"ids":data.courseId},
                    dataType:"json",
                    success:function(result){
                        if(result.code==200){
                            layer.msg("删除OK",{icon : 5 });
                            //重新加载一下数据
                            tableIns.reload();
                        }else{
                            //提示
                            layer.msg(result.msg);
                        }
                    }
                });
            });
        } else if(layEvent === 'edit'){ //编辑
            openAddOrUpdateCourseDialog(data.courseId);
        }
    });
});
