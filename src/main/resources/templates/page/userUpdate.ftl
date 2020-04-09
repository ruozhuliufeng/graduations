<html>
<head>
    <title>个人中心修改</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/main.css"/>
    <!-- Scripts -->
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/jquery.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <#include "../common/nav.ftl"/>
    <br>
    <br>
    <br>
    <!-- 个人信息 -->
    <div style="text-align: center;"><h1>个人信息修改</h1></div>
    <form class="form-horizontal" action="/user/mupdate" method="post">
        <input type="hidden" name="id" value="${currentUser.id}" />
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">用户名称</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputEmail3" name="username" value="${currentUser.username}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">用户邮箱</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="inputPassword3" name="email" value="${currentUser.email}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">用户性格</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputPassword3" name="sex" value="${currentUser.sex}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">用户兴趣</label>
            <div class="col-sm-10">
                <select class="form-control" name="hname">
                    <#list hobbyList as hobby>
                        <option>${hobby.name}</option>
                    </#list>
                </select>
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
