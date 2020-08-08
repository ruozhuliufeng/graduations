<html>
<head>
    <title>个人中心</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../css/main.css"/>
    <!-- Scripts -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.min.js"></script>

</head>
<body>
<div class="container-fluid">
    <#include "../common/nav.ftl"/>
    <br>
    <br>
    <br>
    <!-- 个人信息 -->
    <div style="text-align: center;"><h1>个人信息</h1></div>
    <a class="btn btn-primary" style="float: right" href="/graduation/user/centerUpdate?id=${currentUser.id}">修改</a>
    <table class="table table-hover">
        <tr>
            <td>用户名</td>
            <td>${currentUser.username}</td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td>${currentUser.email}</td>
        </tr>
        <tr>
            <td>性别</td>
            <td>${currentUser.sex}</td>
        </tr>
        <tr>
            <td>兴趣</td>
            <td>
                <#if currentUser.hname??>
                    ${currentUser.hname}
                    <#else >
                    <a href="/graduation/user/centerUpdate" class="btn btn-primary">未选择兴趣，请去个人中心修改</a>
                </#if>

            </td>
        </tr>
<#--        <tr>-->
<#--            <td>阶段</td>-->
<#--            <td>${currentUser.sname}</td>-->
<#--        </tr>-->
    </table>
    <#include "../common/footer.ftl"/>
</div>

</body>
</html>
