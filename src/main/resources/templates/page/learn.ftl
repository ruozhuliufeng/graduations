<html>
<head>
    <title>学习</title>
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

    <div class="row">
        <#if currentUser??>
            <div class="col-md-3">
                <table class="table table-bordered table-striped">
                    <tr>
                        <th>所属阶段</th>
                    </tr>
                    <#list stageList as stage>
                        <tr>
                            <td>
                                <a href="/hobby/learn?sid=${stage.id}">${stage.name}</a>
                            </td>
                        </tr>
                    </#list>
                </table>
            </div>
            <div class="col-md-9">
            <table class="table table-bordered table-striped">
            <tr>
                <th>当前序号</th>
                <th>具体内容</th>
                <th>是否完成</th>
                <th>视频链接</th>
                <th>学习笔记</th>
                <th>相关操作</th>
            </tr>
        <#elseif contentList??>
            <#list contentList as content>
                <tr>
                    <th>${content_index+1}</th>
                    <th>${content.name}</th>
                    <th>
                        <#if content.status==1>
                            已完成
                        <#else>
                            未完成
                        </#if>
                    </th>
                    <th>${content.clink}</th>
                    <th>
                        <#if content.note??>
                        <#-- 存在博客数据 -->
                        <#--                                    <a href="${content.note}">查看笔记</a>-->
                            笔记已保存在博客页面
                        <#else>
                            <a href="/blog/addTopic?id=${content.id}">填写笔记</a>
                        </#if>
                    </th>
                    <th>
                        <a href="/content/complete?id=${content.id}">点击完成</a>
                    </th>
                </tr>
            </#list>
            <#-- 当前阶段超过你所处的阶段，请认真完成笔记，管理员审核通过后会修改相应阶段 -->
            </table>
            </div>
        <#elseif learnmsg??>
            <div style="text-align: center;"><h1>${learnmsg}</h1></div>
        </#if>
    </div>
    <#include "../common/footer.ftl"/>
</div>
</body>
</html>