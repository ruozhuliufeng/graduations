<html>
<head>
    <title>个人中心</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/main.css" />
    <!-- Scripts -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.min.js"></script>

</head>
<body>
<div class="container-fluid">
    <#include "/templates/common/nav.ftl"/>
    <br>
    <br>
    <br>
    <!-- 个人信息 -->
    <h1>个人信息</h1>
    <form>
        姓名：<input type="text" name="username" />
    </form>

    <#include "/templates/common/footer.ftl"/>
</div>

</body>
</html>
