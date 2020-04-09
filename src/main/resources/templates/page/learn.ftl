<html>
<head>
    <title>学习</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/main.css"/>
    <!-- Scripts -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.min.js"></script>

</head>
<body>
<div class="container-fluid">
    <#include "../common/nav.ftl"/>
    <br>
    <br>
    <br>

    <div class="row">
        <#if currentUser??>
            <div class="col-md-3">
                <table class="table table-bordered table-striped">
                    <tr>
                        <th>所属阶段</th>
                    </tr>
                    <#list stageList as stage>
                        <tr>
                            <td>
                                ${stage.name}
                            </td>
                        </tr>
                    </#list>
                </table>
            </div>
            <div class="col-md-9">
                <table class="table table-bordered table-striped">
                    <tr>
                        <th>当前序号</th>
                        <th>具体内容</th>
                        <th>是否完成</th>
                        <th>视频链接</th>
                        <th>学习笔记</th>
                        <th>相关操作</th>
                    </tr>
                    <#list contentList as content>
                        <tr>
                            <th>${content_index+1}</th>
                            <th>${content.name}</th>
                            <th>${content.status}</th>
                            <th>${content.clink}</th>
                            <th>${content.note}</th>
                            <th>
                                <a href="/content/delete?id=${content.id}">删除</a>
                                <a href="/content/update?id=${content.id}">点击完成</a>
                            </th>
                        </tr>
                    </#list>
                </table>
            </div>
        <#else>
            <h1>您尚未登录！请登录后查看！</h1>
        </#if>

    </div>
    <#include "../common/footer.ftl"/>
</div>
</body>
</html>