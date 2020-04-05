<html>
<head>
    <title>学习</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/main.css" />
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
                    </tr>
                </table>
            </div>
        </#if>
        <h1>您尚未登录！请登录后查看！</h1>
    </div>
    <#include "../common/footer.ftl"/>
</div>
</body>
</html>