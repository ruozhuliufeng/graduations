<html lang="en" class="hb-loaded">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="csrf-token" content="fwthi2kFGosUpy0roUsNrGHt9BFbH5Sg4lWFt72p">

    <!-- 网站icon，来自于Google开源图标 -->
    <link rel="icon" type="image/png" href="http://source.aicode.cc/favorite.png">
    <title>注册</title>
    <link rel="stylesheet" href="./css/bootstrap-material-design.min.css">
    <style type="text/css">
        html,
        body {
            height: 100%;
        }

        body {
            display: -ms-flexbox;
            display: -webkit-box;
            display: flex;
            -ms-flex-align: center;
            -ms-flex-pack: center;
            -webkit-box-align: center;
            align-items: center;
            -webkit-box-pack: center;
            justify-content: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: 0 auto;
        }

        .form-signin .checkbox {
            font-weight: 400;
        }

        .form-signin .form-control {
            position: relative;
            box-sizing: border-box;
            height: auto;
            padding: 10px;
            font-size: 16px;
        }

        .form-signin .form-control:focus {
            z-index: 2;
        }

        .form-signin input[type="email"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }

        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
    </style>
</head>

<body class="text-center" huaban_collector_injected="true">

<div class="card" style="min-width: 500px;">
    <div class="card-body">

        <form class="form-signin" method="POST" action="/graduation/user/register">

            <h1 class="h3 mb-3 font-weight-normal">注册账号</h1>

<#--           h <input type="hidden" name="_token" value="fwthi2kFGosUpy0roUsNrGHt9BFbH5Sg4lWFt72p">-->

            <div class="text-left form-group bmd-form-group">
                <label for="name" class="bmd-label-floating">用户名称</label>
                <input id="name" type="text" class="form-control" name="username" value="" required="" autofocus="">

            </div>

            <div class="text-left form-group bmd-form-group">
                <label for="email" class="bmd-label-floating">邮箱地址</label>
                <input id="email" type="email" class="form-control" name="email" value="" required="">

            </div>
            <div class="text-left form-group bmd-form-group">
                <label for="sex" class="bmd-label-floating">用户性别</label>
                <select name="sex" id="sex" class="form-control">
                    <option>男</option>
                    <option>女</option>
                </select>
            </div>

            <div class="text-left form-group bmd-form-group">
                <label for="password" class="bmd-label-floating">密码</label>
                <input id="password" type="password" class="form-control" name="password" required="">

            </div>

            <div class="text-left form-group bmd-form-group">
                <label for="password-confirm" class="bmd-label-floating">重复密码</label>
                <input id="password-confirm" type="password" class="form-control" name="password_confirmation"
                       required="">
            </div>

            <button type="submit" class="btn btn-lg btn-primary btn-block btn-raised">
                注册账号
            </button>

            <a class="btn btn-link" href="/graduation/login">
                已有账号，登录
            </a>

            <p class="mt-5 mb-3 text-muted"><a target="_blank" href="/graduation/">成长助理平台</a></p>
        </form>

    </div>
</div>

<script src="./js/jquery.min.js"></script>

<script src="./js/popper.js"></script>
<script src="./js/bootstrap-material-design.js"></script>


<script>
    $(function () {
        $('body').bootstrapMaterialDesign();
    });
</script>
</body>
</html>