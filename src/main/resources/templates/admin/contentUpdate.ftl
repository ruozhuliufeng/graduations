<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>兴趣内容修改</title>
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
                    <form class="form-horizontal" action="/content/update" method="post">
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">兴趣内容名称</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="name" value="${content.name}" placeholder="兴趣内容名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">兴趣内容阶段</label>
                            <div class="col-sm-10">
                                <select name="sname">
                                    <#list stageList as stage>
                                        <option value="${content.sname}">${stage.name}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="clink" class="col-sm-2 control-label">兴趣内容链接</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="clink" value="${content.clink}" name="clink" placeholder="兴趣内容链接">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="clink" class="col-sm-2 control-label">兴趣内容笔记</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="clink" value="${content.note}" name="note" placeholder="兴趣内容链接">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">修改兴趣内容</button>
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
