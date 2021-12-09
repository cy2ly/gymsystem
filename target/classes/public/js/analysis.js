layui.use(['element', 'echarts', 'layer'], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        echarts = layui.echarts;

    var chartZhu2 = echarts.init(document.getElementById('main2'));

    $.ajax({
        type:"post",
        url:ctx+"/membertype/findType",
        dataType:"json",
        success:function (obj){
            var date = new Array();
            for (var x in obj){
                date[x] = obj[x].name;
            }
            var optionchartBing = {
                title: {
                    text: '会员分类统计',
                    subtext: '分类情况', //副标题
                    x: 'center' //标题居中
                },
                tooltip: {
                    trigger: 'item' //悬浮显示对比
                },
                legend: {
                    orient: 'vertical', //类型垂直,默认水平
                    left: 'left', //类型区分在左 默认居中
                    data: date
                },
                series: [{
                    type: 'pie', //饼状
                    radius: '100%', //圆的大小
                    center: ['50%', '50%'], //居中
                    data: obj
                }]
            };
            chartZhu2.setOption(optionchartBing, true);
        }
    });
});
