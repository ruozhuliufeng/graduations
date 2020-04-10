<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>帖子详情</title>

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
    <br>
    <div class="row">
        <#--博主信息-->
        <div class="col-md-4">
            <div style="text-align: center;">博主信息：</div>
            <label>博主名称:</label>${blog.userName}
            <label>博主邮箱:</label>${blog.email}
            <label>博主级别:</label><#if blog.type==1>管理员<#else>普通用户</#if>

        </div>
        <#-- 博客信息 -->
        <div class="col-md-8">
            <div style="width: 100%">
                <#-- 标题、发布时间、修改时间 -->
                【主题】:${blog.title}
                发布时间：${blog.publishTime?date}
                修改时间：${blog.modifyTime?date}
            </div>

            <div style="width: 100%">
                <#-- 博客内容 -->
                ${blog.content}
            </div>
            <div style="width: 100%">
                <#-- 评论 -->
                <#if currentUser??>
                    <form class="form-horizontal" action="/comment/add" method="post">
                        <input type="hidden" name="topicId" value="${blog.id}">
                        <input type="hidden" name="userId" value="${blog.userId}">
                        <div class="form-group">
                            <label for="title">评论标题</label>
                            <input type="text" class="form-control" id="title" placeholder="请输入评论标题">
                        </div>
                        <div class="form-group">
                            <label for="content" class="col-sm-2 control-label">评论内容</label>
                            <div class="col-sm-10">
                                <textarea name="content" cols="5" rows="3">
                                    请输入评论内容
                                </textarea>
                            </div>
                        </div>
                    </form>
                </#if>
            </div>
        </div>
    </div>
    <br>
    <br>
    <br>
    <br>
    <#include "../common/footer.ftl"/>
</div>

</body>
</html>