<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="admin/css/bootstrap.min.css" />
    <link rel="stylesheet" href="admin/css/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="admin/css/unicorn.login.css" />
    <script src="admin/js/jquery.min.js"></script>
    <script src="admin/js/unicorn.login.js"></script>
</head>
<body>
<div id="loginbox">
    <form id="loginform" class="form-vertical" action="/admin/login" method="post">
        <p>输入用户昵称和密码进入后台.</p>
        <div class="control-group">
            <div class="controls">
                <div class="input-prepend">
                    <span class="add-on"><i class="icon-user"></i></span><input
                            type="text" name="user.nickName" value="${user.nickName }" placeholder="昵称" />
                </div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="input-prepend">
                    <span class="add-on"><i class="icon-lock"></i></span><input
                            type="password" name="user.password" value="${user.password }" placeholder="密码" />
                </div>
            </div>
        </div>
        <div class="form-actions">
				 <span class="pull-right">
				 	<font id="error" style="font-size: 20px;" color="red">${error }</font>
				 	<input type="submit" class="btn btn-inverse" value="进入后台" />
				 </span>
        </div>
    </form>
</div>
</body>
</html>