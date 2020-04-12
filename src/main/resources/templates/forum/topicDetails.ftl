<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>帖子详情</title>

    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/main.css"/>
    <!-- Scripts -->
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/jquery.min.js"></script>
    <style>
        .bottom_footer {
            position: fixed;
            bottom: 0;
        }
    </style>
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
            <div style="text-align: center;"><h2>博主信息：</h2></div>
            <p class="text-left">博主名称:${blog.userName}<br></p>
            <p class="text-left">博主邮箱:${blog.email}<br></p>
            <p class="text-left">博主级别:<#if blog.type==1>管理员<#else>普通用户</#if></p>

            <#-- 所有评论 -->
            <#if contentList??>
                <table style="margin-top: 200px" class="table table-striped">
                    <tr>
                        <td>评论标题</td>
                        <td>评论内容</td>
                        <td>评论用户</td>
                    </tr>
                    <#list commentList as comment>
                        <tr>
                            <td>${comment.title}</td>
                            <td>${comment.content}</td>
                            <td>${comment.userName}</td>
                        </tr>
                    </#list>
                </table>

            <#else>
                <div style="margin-top: 200px">
                    <h1>还没有人评论博客</h1>
                </div>
            </#if>

        </div>
        <#-- 博客信息 -->
        <div class="col-md-8">
            <div style="width: 100%">
                <#-- 标题、发布时间、修改时间 -->
                <p class="text-left">
                <h3>【主题】:${blog.title}<br></h3></p>
                <p class="text-right">发布时间：${blog.publishTime?date}<br></p>
                <br>
                <p class="text-right">修改时间：${blog.modifyTime?date}<br></p>
            </div>
            <br>
            <div style="width: 100% ">
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
                            <label for="title" class="col-sm-2 control-label">评论标题</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="title" name="title" placeholder="请输入评论标题">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="content" class="col-sm-2 control-label">评论内容</label>
                            <div class="col-sm-10">
                                <textarea name="content" id="content" cols="50" style="height:200px;width: 100%;" placeholder="请输入评论内容">

                                </textarea>
                            </div>
                        </div>
                        <input type="submit" class="col-sm-offset-2 btn btn-primary" value="评论">
                    </form>
                <#else>
                    <h2>尚未登录，无法评论</h2>
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