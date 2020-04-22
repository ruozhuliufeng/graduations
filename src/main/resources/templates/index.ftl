<!DOCTYPE>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>首页</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <!-- Scripts -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.min.js"></script>
    <script src="js/skel.min.js"></script>
    <script src="js/util.js"></script>
    <script src="js/main.js"></script>

</head>
<body>
<!-- Header -->
<header id="header">
    <div class="inner">
        <a href="/" class="logo"><strong>成长助理</strong> 平台</a>
        <nav id="nav">
            <a href="/">主页</a>
            <a href="/hobby/learn">学习进度</a>
            <a href="/blog/">学习论坛</a>
            <#if currentUser??>
                <a href="/user/center">个人中心</a>
            <#else>
                <a href="/register">注册</a>
                <a href="/login">登录</a>
            </#if>
        </nav>
        <a href="#navPanel" class="navPanelToggle"><span class="fa fa-bars"></span></a>
    </div>
</header>

<!-- Banner -->
<section id="banner">
    <div class="inner">
        <header>
            <h1>欢迎来到成长助理平台</h1>
        </header>

        <div class="flex ">

            <div>
                <span class="icon fa-car"></span>
                <h3>个性化</h3>
                <p>博客个性化</p>
            </div>

            <div>
                <span class="icon fa-camera"></span>
                <h3>体系化</h3>
                <p>资源体系化</p>
            </div>

            <div>
                <span class="icon fa-bug"></span>
                <h3>便捷化</h3>
                <p>搜索便捷化</p>
            </div>

        </div>

        <footer>
            <a href="/info" class="button">了解更多</a>
        </footer>
    </div>
</section>
<!-- Three -->
<section id="three" class="wrapper align-center">
    <div class="inner">
        <div class="flex flex-2">
            <article>
                <#if currentUser??>
                    <header>
                        <h3>您选择的兴趣是<br/></h3>
                    </header>
<#--                    <#elseif currentUser.hname??>-->
                    <strong>
                        ${currentUser.hname}
                    </strong>
                <#else>
                    <header>
                        <h3>您尚未登录，无法查看相关信息！</h3>
                    </header>
                </#if>


            </article>
            <article>
                <header>
                    <h3>目前可供选择的兴趣有</h3>
                </header>
                <table class="table table-striped table-condensed table-hover table-bordered">
                    <tr>
                        <td><strong>所属分类</strong></td>
                        <td><strong>具体内容</strong></td>
                    </tr>

                    <#list hobbyList as hobby>
                        <tr>
                            <td>${hobby.cname} </td>
                            <td>${hobby.name}</td>
                        </tr>
                    </#list>
                </table>
                <footer>
                    <#if currentUser??>
                        <a href="/user/center" class="button">前往选择</a>
                        <#else >
                        <a href="/login" class="button">登录选择</a>
                    </#if>

                </footer>
            </article>
        </div>
    </div>
</section>

<!-- Footer -->
<footer id="footer">
    <div class="inner">

        <h3>联系我们</h3>

        <form action="/advice/add" method="post">

            <div class="field half first">
                <label for="name">姓名</label>
                <input name="name" id="name" type="text" placeholder="姓名">
            </div>
            <div class="field half">
                <label for="email">邮箱</label>
                <input name="email" id="email" type="email" placeholder="邮箱地址">
            </div>
            <div class="field">
                <label for="message">评论信息</label>
                <textarea name="advice" id="message" rows="6" placeholder="对我们的建议或者相关权限的申请"></textarea>
            </div>
            <ul class="actions">
                <li><input value="提交评论" onclick="javascript:advice()" class="button alt" type="submit"></li>
            </ul>
        </form>

        <div class="copyright" style="font-size: 1.1em;color: #000000;">
            Copyright &copy; 2019-2020.
            <a href="http://www.baidu.com/" target="_blank" title="解惑"><span style="color: #F5E79E;">解惑</span></a> --
            <a href="http://www.csdn.net" target="_blank" title="发现"><span style="color: lemonchiffon;">发现</span>
            </a> --
            <a href="/admin/loginPage" title="后台管理" target="_blank"><span style="color: red;">后台</span>
            </a>
        </div>
    </div>
</footer>
<script>
    function advice() {
        var x = window.prompt("您的建议我们已经收到！已经联系管理员寻找相关资源，请输入邮箱，更新后第一时间通知您！")
    }
</script>
</body>
</html>