<%--
  Created by IntelliJ IDEA.
  User: 程
  Date: 2016/11/2
  Time: 1:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="/static/js/jquery_min_2_1_4.js"></script>
<script type="text/javascript" src="/static/js/amazeui.min.js"></script>
<script type="text/javascript" src="/static/js/app.js"></script>
<script type="text/javascript" src="/static/js/blockUI.js"></script>
<script type="text/javascript" src="/static/js/charts/echarts.min.js"></script>
<script type="text/javascript" src="/static/js/charts/indexChart.js"></script>
<script type="text/javascript" src="/static/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/static/js/doT.min.js"></script>
<script type="text/javascript">
    var pageNum;
    function goToNextPage() {
        pageNum = parseInt(pageNum) + 1;
        $.ajax({
            type: "GET",
            url: '/actionLog/findLogList?pageNum=' + pageNum + '&pageSize=15',
            dataType: 'json', //当这里指定为json的时候，获取到了数据后会自己解析的，只需要 返回值.字段名称 就能使用了
            cache: false,
            success: function (data) {
                if (data.code == 1) {
                    updateList(data);
                    pageNum = data.pageNum;
                    $("#log-controller-now").html(pageNum);
                }
            }
        });
    }

    function goToLastPage() {
        pageNum = parseInt(pageNum) - 1;
        $.ajax({
            type: "GET",
            url: '/actionLog/findLogList?pageNum=' + pageNum + '&pageSize=15',
            dataType: 'json', //当这里指定为json的时候，获取到了数据后会自己解析的，只需要 返回值.字段名称 就能使用了
            cache: false,
            success: function (data) {
                if (data.code == 1) {
                    updateList(data);
                    pageNum = data.pageNum;
                    $("#log-controller-now").html(pageNum);
                }
            }
        });
    }
</script>

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>行为日志表</title>
    <link rel="stylesheet" href="/static/css/amazeui.css"/>
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/css/core.css"/>
    <link rel="stylesheet" href="/static/css/menu.css"/>
    <link rel="stylesheet" href="/static/css/index.css"/>
    <link rel="stylesheet" href="/static/css/admin.css"/>
    <link rel="stylesheet" href="/static/css/page/typography.css"/>
    <link rel="stylesheet" href="/static/css/page/form.css"/>
</head>
<body>

<!-- Begin page -->
<header class="am-topbar am-topbar-fixed-top">
    <div class="am-topbar-left am-hide-sm-only">
        <a href="/mvc/home" class="logo"><span>Go<span>Home</span></span><i
                class="zmdi zmdi-layers"></i></a>
    </div>

    <div class="contain">
        <ul class="am-nav am-navbar-nav am-navbar-left">

            <li>
                <h4 class="page-title">行为日志表</h4></li>
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
                    <img src="/static/images/avatar-1.jpg" alt="user-img" title="Mat Helme"
                         class="img-circle img-thumbnail img-responsive">
                    <div class="user-status offline"><i class="am-icon-dot-circle-o" aria-hidden="true"></i></div>
                </div>
                <h5><a href="#">Mat Helme</a></h5>
                <ul class="list-inline">
                    <li>
                        <a href="#">
                            <i class="am-icon-cog" aria-hidden="true"></i>
                        </a>
                    </li>

                    <li>
                        <a href="#" class="text-custom">
                            <i class="am-icon-cog" aria-hidden="true"></i>
                        </a>
                    </li>
                </ul>
            </div>
            <!-- End User -->

            <ul class="am-list admin-sidebar-list">
                <li><a href="/mvc/home"><span class="am-icon-home"></span> 首页</a></li>
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#collapse-nav1'}"><span class="am-icon-table"></span>
                        表格 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav1">
                        <li><a href="table_basic.html" class="am-cf"> 基本表格</a></li>
                        <li><a href="table_complete.html">完整表格</a></li>
                    </ul>
                </li>
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#collapse-nav2'}"><i class="am-icon-line-chart"
                                                                                      aria-hidden="true"></i> 统计图表 <span
                            class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav2">
                        <li><a href="chart_line.html" class="am-cf"> 折线图</a></li>
                        <li><a href="chart_columnar.html" class="am-cf"> 柱状图</a></li>
                        <li><a href="chart_pie.html" class="am-cf"> 饼状图</a></li>
                    </ul>
                </li>
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#collapse-nav5'}"><span class="am-icon-file"></span> 表单
                        <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav5">
                        <li><a href="list_action_log.html" class="am-cf"> 基本表单</a></li>
                        <li><a href="form_validate.html">表单验证</a></li>
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

            <!-- Row start -->
            <div class="am-g">

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
                                <script id="pagetmpl" type="text/x-dot-template">
                                    {{ for(var i=0; i < it.length ; i++){  }}
                                    <tr>
                                        <td> {{=it[i].id}}</td>
                                        <td>{{=it[i].ipAddrV4}}</td>
                                        <td>{{=it[i].osName}}</td>
                                        <td><span class="label label-danger">{{=it[i].description}}</span></td>
                                        <td>{{=it[i].sessionId}}</td>
                                        <td>{{=it[i].broName}}</td>
                                    </tr>
                                    {{ } }}
                                </script>
                                <hr>
                                </tbody>
                            </table>

                            <div class="am-cf">
                                <div class="am-fr">
                                    <ul class="am-pagination tpl-pagination" id="log-table-page-controller">
                                        <li><a id="log-controller-last" onclick='goToLastPage()'>上一页</a></li>
                                        <li class="am-disabled"><a id="log-controller-now">1</a></li>
                                        <li><a id="log-controller-next" onclick="goToNextPage()">下一页</a></li>
                                    </ul>
                                </div>
                            </div>
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

<!-- navbar -->
<a href="admin-offcanvas" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu"
   data-am-offcanvas="{target: '#admin-offcanvas'}"><!--<i class="fa fa-bars" aria-hidden="true"></i>--></a>

</body>
<script type="application/javascript">
    $.ajax({
        type: "GET",
        url: '/actionLog/findLogList?pageNum=1&pageSize=15',
        dataType: 'json', //当这里指定为json的时候，获取到了数据后会自己解析的，只需要 返回值.字段名称 就能使用了
        cache: false,
        success: function (data) {
            if (data.code == 1) {
                pagefn = doT.template($("#pagetmpl").html());   //初始化列表模板
                updateList(data);   //更新数据
                pageNum = data.pageNum;
            }
        }
    });

    function updateList(data) {
        $("#pagetmpl").empty(); //清空模板数据
        $("#log-table-body").html(pagefn(data.data));   //加入数据到模板
    }

</script>
</html>