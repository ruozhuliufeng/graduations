<html>
<head>
    <title>Title</title>
    <script>
        function logout() {
            if (confirm("您确定要退出系统吗？")) {
                window.location.href = "/user/logout";
            }
        }
    </script>
</head>
<body>
<!-- 导航条 -->
<div class="container-fluid">
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
<#--                </button>-->
                <a class="navbar-brand active" href="/graduation/">首页<span
                            class="sr-only">(current)</span></a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="/graduation/hobby/learn">学习进度</a></li>
                    <li><a href="/graduation/blog/">学习论坛</a></li>
                    <#if currentUser??>
                        <li><a href="javascript:logout()">注销</a></li>
                        <li><a href="/graduation/user/center">个人中心</a></li>

                    <#else>
                        <li><a href="/graduation/register">注册</a></li>
                        <li><a href="/graduation/login">登录</a></li>
                    </#if>

                    <li><a href="/graduation/info"> 详细介绍</a></li>

                </ul>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="搜索内容">
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </form>
            </div>
        </div>
    </nav>
</div>
</body>
</html>
