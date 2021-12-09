layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    $("#quxiao").click(function (){
        var index = parent.layer.getFrameIndex(window.name);
        window.parent.layer.close(index);
    })

    form.on('submit(addOrUpdateMemberType)', function (data) {

        var index = layer.msg("数据正在提交中，请稍等！", {icon: 16, time: false, shade: 0.8});

        var filedData = data.field;

        var url = ctx + "/membertype/save"
        //判断是添加还是修改
        if ($("input[name=typeId]").val()) {
            url = ctx + "/membertype/update"
        }

        $.ajax({
            type: 'post',
            url: url,
            data: filedData,
            dateType: "json",
            success: function (data) {
                if (data.code == 200) {
                    layer.msg("操作成功了！", {icon: 5});
                    //关闭加载层
                    layer.close(index);
                    //关闭弹出层
                    layer.closeAll("iframe");
                    window.parent.location.reload();
                } else {
                    layer.msg(data.msg);
                }
            }
        })
        //取消跳转
        return false;
    })

});