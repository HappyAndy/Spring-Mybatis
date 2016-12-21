<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<script type="text/javascript" src="/static/js/jquery_min_2_1_4.js"></script>
<script type="text/javascript" src="/static/js/amazeui.min.js"></script>
<script type="text/javascript" src="/static/js/app.js"></script>
<script type="text/javascript" src="/static/js/blockUI.js"></script>
<script type="text/javascript" src="/static/js/charts/echarts.min.js"></script>
<script type="text/javascript" src="/static/js/charts/indexChart.js"></script>
<script type="text/javascript" src="/static/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript">
    var fileName;

    function uploadFile() {
        fileName = document.getElementById('changeHeadPic').value;
        $.ajaxFileUpload({
            url: "<%=request.getContextPath()%>/userAction/uploadHeadPic",
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: 'changeHeadPic', //文件上传域的ID
            dataType: 'json', //返回值类型 一般设置为json
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function(data) {
                alert(data.msg);
            }

        });
    }

    function changeUserInfo() {
        $('#my-prompt').modal({
            relatedTarget: this,
            onConfirm: function() {
                uploadFile();
            },
            onCancel: function(e) {}
        });
    }
</script>
<html>

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>仓库系统→后台管理</title>
    <link rel="stylesheet" href="/static/css/amazeui.css" />
    <link rel="stylesheet" href="/static/css/core.css" />
    <link rel="stylesheet" href="/static/css/menu.css" />
    <link rel="stylesheet" href="/static/css/index.css" />
    <link rel="stylesheet" href="/static/css/admin.css" />
    <link rel="stylesheet" href="/static/css/page/typography.css" />
    <link rel="stylesheet" href="/static/css/page/form.css" />
    <link rel="stylesheet" href="/static/css/component.css" />
</head>

<body>

<!-- Begin page -->
<header class="am-topbar am-topbar-fixed-top">
    <div class="am-topbar-left am-hide-sm-only">
        <a href="<%=request.getContextPath()%>/mvc/home" class="logo"><span>Go<span>Home</span></span><i class="zmdi zmdi-layers"></i></a>
    </div>

    <div class="contain">
        <ul class="am-nav am-navbar-nav am-navbar-left">

            <li>
                <h4 class="page-title">基本表格</h4></li>
        </ul>

        <ul class="am-nav am-navbar-nav am-navbar-right">
            <li class="inform"><i class="am-icon-bell-o" aria-hidden="true"></i></li>
            <li class="hidden-xs am-hide-sm-only">
                <form role="search" class="app-search">
                    <input type="text" placeholder="Search..." class="form-control">
                    <a href=""><img src="/static/images/search.png"></a>
                </form>
            </li>
        </ul>
    </div>
</header>
<!-- end page -->

<div class="am-modal am-modal-prompt" tabindex="-1" id="my-prompt">
    <div class="am-modal-dialog">
        <div class="am-modal-hd">用户信息修改</div>
        <div class="am-modal-bd">
            <form enctype="multipart/form-data" accept-charset="UTF-8">
                姓名：
                <input type="text" class="am-modal-prompt-input" id="changeName"> 性别：
                <input type="text" class="am-modal-prompt-input" id="changeSex"> 手机号：
                <input type="text" class="am-modal-prompt-input" id="changeCell"> 年龄：
                <input type="text" class="am-modal-prompt-input" id="changeAge"> 头像：
                <div class="am-modal-prompt-input">

                    <input type="file" name="file" id="changeHeadPic" size="28" />

                </div>
            </form>
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>取消</span>
            <span class="am-modal-btn" data-am-modal-confirm>上传</span>
        </div>
    </div>
</div>

<div class="admin">
    <!--<div class="am-g">-->
    <!-- ========== Left Sidebar Start ========== -->
    <!--<div class="left side-menu am-hide-sm-only am-u-md-1 am-padding-0">
<div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 548px;">
    <div class="sidebar-inner slimscrollleft" style="overflow: hidden; width: auto; height: 548px;">-->
    <!-- sidebar start -->
    <div class="admin-sidebar am-offcanvas  am-padding-0" id="admin-offcanvas">
        <div class="am-offcanvas-bar admin-offcanvas-bar">
            <!-- User -->
            <div class="user-box am-hide-sm-only">
                <div class="user-img">
                    <img src="/static/images/avatar-1.jpg" alt="user-img" title="Mat Helme" class="img-circle img-thumbnail img-responsive" onclick="changeUserInfo()">

                    <div class="user-status offline">
                        <i class="am-icon-dot-circle-o" aria-hidden="true"></i>
                    </div>
                </div>
                <h5><a href="#">Mat Helme</a></h5>
                <ul class="list-inline">
                    <li>
                        <a href="#">
                            <i class="fa fa-cog" aria-hidden="true"></i>
                        </a>
                    </li>

                    <li>
                        <a href="#" class="text-custom">
                            <i class="fa fa-cog" aria-hidden="true"></i>
                        </a>
                    </li>
                </ul>
            </div>
            <!-- End User -->

            <ul class="am-list admin-sidebar-list">
                <li>
                    <a href="<%=request.getContextPath()%>/mvc/home"><span class="am-icon-home"></span> 首页</a>
                </li>
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#collapse-nav1'}"><span class="am-icon-table"></span> 表格 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav1">
                        <li>
                            <a href="/static/view/table_basic.html" class="am-cf"> 基本表格</a>
                        </li>
                        <li>
                            <a href="/static/view/table_complete.html">完整表格</a>
                        </li>
                    </ul>
                </li>
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#collapse-nav2'}"><i class="am-icon-line-chart" aria-hidden="true"></i> 统计图表 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav2">
                        <li>
                            <a href="/static/view/chart_line.html" class="am-cf"> 折线图</a>
                        </li>
                        <li>
                            <a href="/static/view/chart_columnar.html" class="am-cf"> 柱状图</a>
                        </li>
                        <li>
                            <a href="/static/view/chart_pie.html" class="am-cf"> 饼状图</a>
                        </li>
                    </ul>
                </li>
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#collapse-nav5'}"><span class="am-icon-file"></span> 表单
                        <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav5">
                        <li>
                            <a href="<%=request.getContextPath()%>/mvc/listActionLog" class="am-cf">行为日志表</a>
                        </li>
                        <li>
                            <a href="/static/view/form_validate.html">表单验证</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!-- sidebar end -->

    <!--</div>
</div>
</div>-->
    <!-- ========== Left Sidebar end ========== -->

    <!--	<div class="am-g">-->
    <!-- ============================================================== -->
    <!-- Start right Content here -->
    <div class="content-page">
        <!-- Start content -->
        <div class="content">
            <div class="am-g">
                <!-- Row start -->
                <div class="am-u-md-3">
                    <div class="card-box">
                        <h4 class="header-title m-t-0 m-b-30">总收入</h4>

                        <div class="widget-chart-1 am-cf">
                            <div id="widget-chart-box-1" style="height: 110px;width: 110px;float: left;">
                            </div>

                            <div class="widget-detail-1" style="float: right;">
                                <h2 class="p-t-10 m-b-0"> 256 </h2>

                                <p class="text-muted">今日收入</p>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- col end -->
                <div class="am-u-md-3">
                    <div class="card-box">
                        <h4 class="header-title m-t-0 m-b-30">销售分析</h4>

                        <div class="widget-box-2">
                            <div class="widget-detail-2">
										<span class="badge  pull-left m-t-20  am-round" style="color: #fff; background: #0e90d2;">32% <i
                                                class="zmdi zmdi-trending-up"></i> </span>

                                <h2 class="m-b-0"> 8451 </h2>

                                <p class="text-muted m-b-25">Revenue today</p>
                            </div>
                            <div class="am-progress am-progress-xs am-margin-bottom-0">
                                <div class="am-progress-bar" style="width: 80%"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- col end -->
                <div class="am-u-md-3">
                    <div class="card-box">
                        <h4 class="header-title m-t-0 m-b-30">总收入</h4>

                        <div class="widget-chart-1 am-cf">
                            <div id="widget-chart-box-2" style="height: 110px;width: 110px;float: left;">
                            </div>

                            <div class="widget-detail-1" style="float: right;">
                                <h2 class="p-t-10 m-b-0"> 256 </h2>

                                <p class="text-muted">今日收入</p>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- col end -->
                <div class="am-u-md-3">
                    <div class="card-box">
                        <h4 class="header-title m-t-0 m-b-30">销售分析</h4>

                        <div class="widget-box-2">
                            <div class="widget-detail-2">
										<span class="badge  pull-left m-t-20  am-round progress-bar-pink" style="color: #fff;">32% <i
                                                class="zmdi zmdi-trending-up"></i> </span>

                                <h2 class="m-b-0"> 8451 </h2>

                                <p class="text-muted m-b-25">Revenue today</p>
                            </div>
                            <div class="am-progress am-progress-xs am-margin-bottom-0" style="background: rgba(255, 138, 204, 0.2);">
                                <div class="am-progress-bar progress-bar-pink" style="width: 80%"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Row end -->
            </div>

            <div class="am-g">
                <!-- Row start -->
                <div class="am-u-md-4">
                    <div class="card-box">
                        <h4 class="header-title m-t-0">环形图</h4>

                        <div id="index-pie-1" style="height: 345px;height: 300px;"></div>
                    </div>
                </div>

                <div class="am-u-md-4">
                    <div class="card-box">
                        <h4 class="header-title m-t-0">环形图</h4>

                        <div id="index-bar-1" style="height: 345px;height: 300px;"></div>
                    </div>
                </div>

                <div class="am-u-md-4">
                    <div class="card-box">
                        <h4 class="header-title m-t-0">环形图</h4>

                        <div id="index-line-1" style="height: 345px;height: 300px;"></div>
                    </div>
                </div>
                <!-- Row end -->
            </div>

            <div class="am-g">
                <!-- Row start -->
                <div class="am-u-md-3">
                    <div class="card-box widget-user">
                        <div>
                            <img src="/static/images/avatar-3.jpg" class="img-responsive img-circle" alt="user">

                            <div class="wid-u-info">
                                <h4 class="m-t-0 m-b-5 font-600">Chadengle</h4>

                                <p class="text-muted m-b-5 font-13">coderthemes@gmail.com</p>
                                <small class="text-warning"><b>管理员</b></small>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- col end -->
                <div class="am-u-md-3">
                    <div class="card-box widget-user">
                        <div>
                            <img src="/static/images/avatar-2.jpg" class="img-responsive img-circle" alt="user">

                            <div class="wid-u-info">
                                <h4 class="m-t-0 m-b-5 font-600">Chadengle</h4>

                                <p class="text-muted m-b-5 font-13">coderthemes@gmail.com</p>
                                <small class="text-custom"><b>网络组主管</b></small>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- col end -->
                <div class="am-u-md-3">
                    <div class="card-box widget-user">
                        <div>
                            <img src="/static/images/avatar-4.jpg" class="img-responsive img-circle" alt="user">

                            <div class="wid-u-info">
                                <h4 class="m-t-0 m-b-5 font-600">Chadengle</h4>

                                <p class="text-muted m-b-5 font-13">coderthemes@gmail.com</p>
                                <small class="text-success"><b>设计师</b></small>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- col end -->
                <div class="am-u-md-3">
                    <div class="card-box widget-user">
                        <div>
                            <img src="/static/images/avatar-10.jpg" class="img-responsive img-circle" alt="user">

                            <div class="wid-u-info">
                                <h4 class="m-t-0 m-b-5 font-600">Chadengle</h4>

                                <p class="text-muted m-b-5 font-13">coderthemes@gmail.com</p>
                                <small class="text-info"><b>开发者</b></small>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- col end -->
                <!-- Row end -->
            </div>

            <!-- Row start -->
            <div class="am-g">
                <!-- col start -->
                <div class="am-u-md-4">
                    <div class="card-box">
                        <h4 class="header-title m-t-0 m-b-30">收件箱</h4>

                        <div class="inbox-widget nicescroll" style="height: 315px; overflow: hidden; outline: none;" tabindex="5000">
                            <a href="#">
                                <div class="inbox-item">
                                    <div class="inbox-item-img"><img src="/static/images/avatar-1.jpg" class="img-circle" alt=""></div>
                                    <p class="inbox-item-author">Chadengle</p>

                                    <p class="inbox-item-text">Hey! there I'm available...</p>

                                    <p class="inbox-item-date">13:40 PM</p>
                                </div>
                            </a>
                            <a href="#">
                                <div class="inbox-item">
                                    <div class="inbox-item-img"><img src="/static/images/avatar-2.jpg" class="img-circle" alt=""></div>
                                    <p class="inbox-item-author">Shahedk</p>

                                    <p class="inbox-item-text">Hey! there I'm available...</p>

                                    <p class="inbox-item-date">10:15 AM</p>
                                </div>
                            </a>
                            <a href="#">
                                <div class="inbox-item">
                                    <div class="inbox-item-img"><img src="/static/images/avatar-10.jpg" class="img-circle" alt=""></div>
                                    <p class="inbox-item-author">Tomaslau</p>

                                    <p class="inbox-item-text">I've finished it! See you so...</p>

                                    <p class="inbox-item-date">13:34 PM</p>
                                </div>
                            </a>
                            <a href="#">
                                <div class="inbox-item">
                                    <div class="inbox-item-img"><img src="/static/images/avatar-4.jpg" class="img-circle" alt=""></div>
                                    <p class="inbox-item-author">Stillnotdavid</p>

                                    <p class="inbox-item-text">This theme is awesome!</p>

                                    <p class="inbox-item-date">13:17 PM</p>
                                </div>
                            </a>
                            <a href="#">
                                <div class="inbox-item">
                                    <div class="inbox-item-img"><img src="/static/images/avatar-5.jpg" class="img-circle" alt=""></div>
                                    <p class="inbox-item-author">Kurafire</p>

                                    <p class="inbox-item-text">Nice to meet you</p>

                                    <p class="inbox-item-date">12:20 PM</p>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
                <!-- col end -->

                <!-- col start -->
                <div class="am-u-md-8">
                    <div class="card-box">
                        <h4 class="header-title m-t-0 m-b-30">最新项目</h4>

                        <div class="am-scrollable-horizontal am-text-ms" style="font-family: '微软雅黑';">
                            <table class="am-table   am-text-nowrap">
                                <thead>
                                <tr>
                                    <th>id</th>
                                    <th>IP地址</th>
                                    <th>系统名称</th>
                                    <th>访问地址</th>
                                    <th>SessionId</th>
                                    <th>浏览器名</th>
                                </tr>
                                </thead>
                                <tbody id="log-table-body">

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- col end -->
            </div>
            <!-- Row end -->

        </div>
    </div>
    <!-- end right Content here -->
    <!--</div>-->
</div>
</div>

<!-- navbar -->
<a href="admin-offcanvas" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}">
    <!--<i class="fa fa-bars" aria-hidden="true"></i>--></a>

</body>
<script type="application/javascript">
    $.ajax({
        type: "GET",
        url: '<%=request.getContextPath()%>/actionLog/findLogList?pageNum=1&pageSize=10',
        dataType: 'json', //当这里指定为json的时候，获取到了数据后会自己解析的，只需要 返回值.字段名称 就能使用了
        cache: false,
        success: function(data) {
            if(data.code == 1) {
                for(var i = 0; i < 10; i++) {
                    $("#log-table-body").append("<tr><td>" + data.data[i].id + "</td><td>" +
                            data.data[i].ipAddrV4 + "</td><td>01/01/2016</td><td>" +
                            data.data[i].osName + "</td><td><span class=\"label label-danger\">" +
                            data.data[i].description + "</span></td><td>" +
                            data.data[i].sessionId + "</td><td>" +
                            data.data[i].broName + "</td></tr>");
                }
            }
        }
    });
</script>

</html>