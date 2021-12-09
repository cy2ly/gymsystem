layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    /**
     * 监听submit事件
     *
     */
    form.on("submit(addOrUpdateLose)", function (data) {
// 提交数据时的加载层 （https://layer.layui.com/）
        var index = layer.msg("数据提交中,请稍后...", {
            icon: 16, // 图标
            time: false, // 不关闭
            shade: 0.8 // 设置遮罩的透明度
        });
// 请求的地址
        var url = ctx + "/lose/save";
// 判断隐藏域中的ID是否为空，如果不为空则为修改操作
        if ($("input[name='id']").val()) {
            url = ctx + "/lose/dels";
        }
// 发送ajax请求
        $.post(url, data.field, function (result) {
// 操作成功
            if (result.code == 200) {
// 提示成功
                layer.msg("操作成功！");
// 关闭加载层
                layer.close(index);
// 关闭弹出层
                layer.closeAll("iframe");
// 刷新父页面，重新渲染表格数据
                parent.location.reload();
            } else {
                layer.msg(result.msg);
            }
        });
        return false; // 阻止表单提交
    });

    /**
     * 关闭弹出层
     */
    $("#closeBtn").click(function () {
// 先得到当前iframe层的索引
        var index = parent.layer.getFrameIndex(window.name);
// 再执行关闭
        parent.layer.close(index);
    });


   /*  $.post(ctx+"/user/sales",function (data) {
         var assignMan = $("input[name='man']").val();
         for (var i = 0; i <data.length ; i++) {
             if (assignMan==data[i].id){
                 $("#assignMan").append('<option value="'+data[i].id+'"selected>'+data[i].loseName+'</option>');
             }else {
                 $("#assignMan").append('<option value="'+data[i].id+'">'+data[i].loseName+'</option>');

             }
         }
// 重新渲染下拉框内容
        layui.form.render("select");
    });*/
});