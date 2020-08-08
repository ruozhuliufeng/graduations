<html>
<head>
    <title>个人中心修改</title>
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
    <div style="text-align: center;"><h1>个人信息修改</h1></div>
    <form class="form-horizontal" action="/graduation/user/mupdate" method="post">
        <input type="hidden" name="id" value="${currentUser.id}"/>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">用户名称</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputEmail3" name="username"
                       value="${currentUser.username}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">用户邮箱</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="inputPassword3" name="email" value="${currentUser.email}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">用户性别</label>
            <div class="col-sm-10">
                <select class="form-control" name="sex">
                    <option>男</option>
                    <option>女</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">用户兴趣</label>
            <div class="col-sm-4">
                <select class="form-control" name="hname">
                    <#list hobbyList as hobby>
                        <option>${hobby.name}</option>
                    </#list>
                </select>
            </div>
            <div class="col-sm-6">
                <#if recommendHobby??>
                    我们为您推荐的兴趣是：${recommendHobby.name}
                <#else>
                    若您暂时不知道选择哪一个兴趣，阅读博客，我们会为您推荐相关兴趣
                </#if>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">修改</button>
            </div>
        </div>
    </form>

    <#include "../common/footer.ftl"/>
</div>
</body>
</html>
