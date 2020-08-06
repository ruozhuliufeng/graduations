<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>兴趣内容管理</title>
    <link href="../admin/css/bootstrap.min.css" rel="stylesheet">
    <link href="../admin/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="../admin/css/style.min.css" rel="stylesheet">
</head>

<body>
<div class="lyear-layout-web">
    <div class="lyear-layout-container">
        <!--左侧导航-->
        <aside class="lyear-layout-sidebar">
            <#include "left.ftl">
        </aside>
        <!--End 左侧导航-->
        <!--头部信息-->
        <header class="lyear-layout-header">
            <#include "header.ftl">
        </header>
        <!--End 头部信息-->
        <!--页面主要内容-->
        <main class="lyear-layout-content">
            <div class="container-fluid">
                <a class="btn btn-primary" style="float: right" href="/graduation/admin/contentAdd">添加</a>
                <table class="table table-striped table-hover">
                    <tr>
                        <th>序号</th>
                        <th>兴趣内容名称</th>
                        <th>兴趣内容状态</th>
                        <th>兴趣所属阶段</th>
                        <th>兴趣内容链接</th>
                        <th>操作</th>
                    </tr>
                    <#list contents as content>
                        <tr>
                            <td>${content_index+1}</td>
                            <td>${content.name}</td>
                            <td>
                                <#if content.status==1>
                                    已完成
                                    <#else >
                                    未完成
                                </#if>
                            </td>
                            <td>${content.sname}</td>
                            <td>${content.clink}</td>
                            <td>
                                <a href="/graduation/content/delete?id=${content.id}">删除</a> ||
                                <a href="/graduation/content/findById?id=${content.id}">修改</a>
                            </td>
                        </tr>
                    </#list>
                </table>
            </div>
        </main>
        <!--End 页面主要内容-->
    </div>
</div>

<script type="text/javascript" src="../admin/js/jquery.min.js"></script>
<script type="text/javascript" src="../admin/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../admin/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="../admin/js/main.min.js"></script>

</body>
</html>
