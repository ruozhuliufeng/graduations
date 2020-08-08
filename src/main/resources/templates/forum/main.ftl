<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>学习论坛</title>
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
    <div class="row">
        <div class="col-md-8">
            <ul class="nav nav-tabs nav-justified">
                <#list categoryList as category>
                    <li role="presentation">
                        <a href="/graduation/blog/?id=${category.id}">${category.name}</a>
                    </li>
                </#list>
            </ul>
            <table class="table table-hover table-striped">
                <tr>
                    <th>博客序号</th>
                    <th>博客作者</th>
                    <th>发布时间</th>
                    <th>修改时间</th>
                    <th>博客标题</th>
                    <th>是否置顶</th>
                    <th>是否精华</th>
                </tr>
                <#if blogList??>
                    <#list blogList as blog>
                        <tr>
                            <td>${blog_index+1}</td>
                            <td>${blog.userName}</td>
                            <td>${blog.publishTime?date}</td>
                            <td>${blog.modifyTime?date}</td>
                            <td><a href="/blog/detail?id=${blog.id}"> ${blog.title}</a></td>
                            <td>
                                <#if blog.top==1>
                                    置顶
                                <#else >
                                    未置顶
                                </#if>
                            </td>
                            <td>
                                <#if blog.good==1>
                                    精华
                                <#else >
                                    未精华
                                </#if>
                            </td>
                        </tr>
                    </#list>
                <#else>
                    <h1>${categorymsg}</h1>
                </#if>

            </table>
        </div>
        <div class="col-md-4">
            <#if currentUser??>
                <div style="height: 20px">
                    <a class="btn btn-primary" style="float: right" href="/graduation/blog/addTopic">新建博文</a>
                </div>
                <div style="margin-top: 20px">
                    <table class="table table-striped table-hover">
                        <tr>
                            <th colspan="2">与您兴趣相同的用户阅读的博客</th>
                        </tr>
                        <tr>
                            <td>序号</td>
                            <td>标题</td>
                        </tr>
                        <#list recommendBlogs as recommendBlog>
                            <#if recommendBlog??>>
                            <tr>
                                <td>${recommendBlog_index+1}</td>
                                <td>
                                    <a href="/blog/detail?id=${recommendBlog.id}">
                                        ${recommendBlog.title}
                                    </a>
                                </td>
                            </tr>
                            </#if>
                            <#else>
                            <tr>
                                <td>1</td>
                                <td>暂未有推荐博客，请多多阅读</td>
                            </tr>
                        </#list>
                    </table>
                </div>
            <#else>
                <div>
                    尚未登录，无法享受个性化服务！
                </div>
            </#if>

        </div>
    </div>

    <#include "../common/footer.ftl"/>

</div>
</body>
</html>