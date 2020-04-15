<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>分类修改</title>
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

                <div class="row">
                    <form class="form-horizontal" action="/category/update" method="post">
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">分类名称</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="name" name="name" value="${category.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="userName" class="col-sm-2 control-label">分类版主</label>
                            <div class="col-sm-10">
                                <select name="username" class="form-control">
                                    <#list userList as user>
                                        <option>${user.username}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">修改分类</button>
                            </div>
                        </div>
                    </form>
                </div>

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
