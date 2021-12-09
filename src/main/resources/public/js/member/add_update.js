layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    $("#quxiao").click(function (){
        var index = parent.layer.getFrameIndex(window.name);
        window.parent.layer.close(index);
    })

    /**
     * 添加或更新用户
     */
    form.on("submit(addOrUpdateMember)",function(data){
        var index=top.layer.msg("数据正在加载中...",{time:false,shade:0.8,icon:16});
        //判断添加还是修改
        var url=ctx+"/member/save";
        //判断是否做修改操作
        if($("input[name='memberId']").val()){
            url=ctx+"/member/update";
        }
        /*发送ajax添加*/
        $.post(url,data.field,function(result){
            if(result.code==200){
                //定时执行，定时器
                setTimeout(function(){
                    //关闭加载层
                    top.layer.close("index");
                    //提示消息
                    top.layer.msg("添加成功了");
                    //关闭所有的iframe;
                    layer.closeAll("iframe");
                    //刷新
                    parent.location.reload();
                },500);
            }else{
                layer.msg(result.msg,{icon : 5 });
            }
        },"json");
        //取消默认跳转
        return false;
    });

    var memberType = $("input[name=memberType]").val();
    //添加下拉框
    $.ajax({
        type:"post",
        url:ctx+"/member/types",
        dataType:"json",
        success:function (data){
            for (var x in data){
                if (memberType == data[x].typeId){
                    $("#memberType").append("<option selected value="+data[x].typeId+">"+data[x].typeName+'</option>');
                }else {
                    $("#memberType").append("<option value="+data[x].typeId+">"+data[x].typeName+'</option>');
                }
            }
            //渲染下拉框
            layui.form.render("select");
        }
    })
});