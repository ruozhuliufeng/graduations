<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>兴趣内容管理</title>
    <link href="/admin/css/bootstrap.min.css" rel="stylesheet">
    <link href="/admin/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="/admin/css/style.min.css" rel="stylesheet">
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
                <table class="table table-striped table-hover">
                    <tr>
                        <th>序号</th>
                        <th>兴趣名称</th>
                        <th>最高阶段</th>
                    </tr>
                    <#list hobbyList as hobby>
                        <tr>
                            <td>${hobby_index+1}</td>
                            <td>${hobby.name}</td>
                            <td>${hobby.sname}</td>
                            <td>
                                <a href="/hobby/delete?id=${hobby.id}">删除</a> ||
                                <a href="/hobby/hobbyUpdate">修改</a>

                            </td>
                        </tr>
                    </#list>
                </table>
            </div>
        </main>
        <!--End 页面主要内容-->
    </div>
</div>

<script type="text/javascript" src="/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="/admin/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/admin/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="/admin/js/main.min.js"></script>

</body>
</html>
