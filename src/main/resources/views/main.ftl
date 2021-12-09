<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>健身房管理系统</title>
    <#include "common.ftl">
</head>
<body class="layui-layout-body layuimini-all">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header">
        <div class="layui-logo">
            <a href="">
                <img src="images/2.jpg" alt="logo">
                <h1>健身房管理</h1>
            </a>
        </div>
        <a>
            <div class="layuimini-tool"><i title="展开" class="fa fa-outdent" data-side-fold="1"></i></div>
        </a>
        <div id="space">
            <a href="#">
                <marquee id="right" crollamount="5">特别公告:本系统为测试版，有问题请联系我们工作人员，电话:147147141748</marquee>
            </a>
        </div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;" data-check-screen="full"><i class="fa fa-arrows-alt"></i></a>
            </li>
            <li class="layui-nav-item layuimini-setting">
                <a href="javascript:;">${(user.userName) !""}</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx}/user/toSettingPage" data-title="基本资料" data-icon="fa fa-gears">基本资料</a>
                    </dd>
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx}/user/toPasswordPage" data-title="修改密码" data-icon="fa fa-gears">修改密码</a>
                    </dd>
                    <dd>
                        <a href="javascript:;" class="login-out">退出登录</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item layuimini-select-bgcolor mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;"></a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll layui-left-menu">
                <ul class="layui-nav layui-nav-tree layui-left-nav-tree layui-this" id="currency">
                        <li class="layui-nav-item">
                            <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-street-view"></i><span class="layui-left-nav"> 会员管理</span> <span class="layui-nav-more"></span></a>
                            <dl class="layui-nav-child">
                                    <dd>
                                      <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-1" data-tab="sale_chance/index" target="_self"><i class="fa fa-tty"></i><span class="layui-left-nav"> 会员列表</span></a>
                                     </dd>
                                    <dd>
                                        <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-2" data-tab="cus_dev_plan/index" target="_self"><i class="fa fa-ellipsis-h"></i><span class="layui-left-nav"> 会员到期</span></a>
                                    </dd>
                                    <dd>
                                        <a href="javascript:;" class="layui-menu-tips">
                                            <i class="fa fa-flag">
                                            </i><span class="layui-left-nav"> 会员充值管理</span>
                                            <span class="layui-nav-more"></span>
                                        </a>
                                        <dl class="layui-nav-child">
                                            <dd>
                                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-3" data-tab="customer/index" target="_self"><i class="fa fa-exchange"></i><span class="layui-left-nav"> 会员卡续卡</span></a>
                                            </dd>
                                            <dd>
                                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-4" data-tab="customer_loss/index" target="_self"><i class="fa fa-user-times"></i><span class="layui-left-nav"> 会员卡余额充值</span></a>
                                            </dd>
                                            <dd>
                                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-5" data-tab="customer_loss/index" target="_self"><i class="fa fa-user-times"></i><span class="layui-left-nav"> 续费续卡记录</span></a>
                                            </dd>
                                            <dd>
                                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-6" data-tab="customer_loss/index" target="_self"><i class="fa fa-user-times"></i><span class="layui-left-nav"> 会员卡类型充值</span></a>
                                            </dd>
                                        </dl>
                                    </dd>
                            </dl>
                        </li>
                    <li class="layui-nav-item">
                            <a href="javascript:;" class="layui-menu-tips">
                                <i class="fa fa-flag">
                                </i><span class="layui-left-nav"> 教练管理</span>
                                    <span class="layui-nav-more"></span>
                            </a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-7" data-tab="coach/index" target="_self"><i class="fa fa-exchange"></i><span class="layui-left-nav"> 教练列表</span></a>
                            </dd>
                            <#--<dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-8" data-tab="course/index" target="_self"><i class="fa fa-user-times"></i><span class="layui-left-nav"> 会员私教课程列表</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-9" data-tab="customer_loss/index" target="_self"><i class="fa fa-user-times"></i><span class="layui-left-nav"> 会员私教详情</span></a>
                            </dd>-->
                        </dl>
                    </li>
                        <li class="layui-nav-item">
                            <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-desktop"></i><span class="layui-left-nav"> 课程管理</span> <span class="layui-nav-more"></span></a>
                            <dl class="layui-nav-child">
                                    <dd>
                                        <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-10" data-tab="customer_serve/index/1" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 课程列表</span></a>
                                    </dd>
                            </dl>
                        </li>
                        <li class="layui-nav-item">
                            <a href="javascript:;" class="layui-menu-tips">
                                <i class="fa fa-home"></i>
                                <span class="layui-left-nav"> 器材管理</span>
                                <span class="layui-nav-more"></span>
                            </a>
                            <dl class="layui-nav-child">
                                <dd>
                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-10" data-tab="report/0" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 器材信息</span></a>
                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-10" data-tab="equipment/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 器材信息</span></a>
                                </dd>
                            </dl>
                        </li>
                        <li class="layui-nav-item">
                            <a href="javascript:;" class="layui-menu-tips">
                                <i class="fa fa-home"></i>
                                <span class="layui-left-nav"> 物品遗失</span>
                                <span class="layui-nav-more"></span>
                            </a>
                            <dl class="layui-nav-child">
                                <dd>
                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="report/0" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 物品归还</span></a>
                                </dd>
                            </dl>
                        </li>
                        <li class="layui-nav-item">
                            <a href="javascript:;" class="layui-menu-tips">
                                <i class="fa fa-home"></i>
                                <span class="layui-left-nav"> 商品管理</span>
                                <span class="layui-nav-more"></span>
                            </a>
                            <dl class="layui-nav-child">
                                <dd>
                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-12" data-tab="report/0" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 商品列表</span></a>
                                </dd>
                                <dd>
                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="report/0" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 商品售卖信息</span></a>
                                </dd>
                            </dl>
                        </li>
                        <li class="layui-nav-item">
                            <a href="javascript:;" class="layui-menu-tips">
                                <i class="fa fa-home"></i>
                                <span class="layui-left-nav"> 统计信息</span>
                                <span class="layui-nav-more"></span>
                            </a>
                            <dl class="layui-nav-child">
                                <dd>
                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-14" data-tab="report/0" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 收入统计</span></a>
                                </dd>
                            </dl>
                        </li>
                    <span class="layui-nav-bar" style="top: 201px; height: 0px; opacity: 0;"></span>
                </ul>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab" lay-filter="layuiminiTab" id="top_tabs_box">
            <ul class="layui-tab-title" id="top_tabs">
                <li class="layui-this" id="layuiminiHomeTabId" lay-id="welcome"><i class="fa fa-home"></i> <span>首页</span></li>
            </ul>

            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:;"> <i class="fa fa-dot-circle-o"></i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-page-close="other"><i class="fa fa-window-close"></i> 关闭其他</a></dd>
                        <dd><a href="javascript:;" data-page-close="all"><i class="fa fa-window-close-o"></i> 关闭全部</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div id="layuiminiHomeTabIframe" class="layui-tab-item layui-show">
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript" src="${ctx}/js/main.js"></script>
</body>
</html>
