<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="admin/css/bootstrap.css"/>
    <link rel="stylesheet" href="admin/css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" href="admin/css/uniform.css"/>

    <script type="text/javascript" src="bootstrap/js/jquery-1.11.1.js"></script>
    <script src="admin/js/jquery.min.js"></script>
    <script src="admin/js/jquery.ui.custom.js"></script>
    <script src="admin/js/bootstrap.min.js"></script>
    <script src="admin/js/jquery.dataTables.min.js"></script>
    <script src="admin/js/unicorn.tables.js"></script>
    <script type="text/javascript">
        function deleteTopic(topicId) {
            if (confirm("您确定要删除这条数据吗？")) {
                $.post("/topic/udelete", {topicId: topicId}, function (result) {
                    if (result.success) {
                        /* var result=eval('('+result+')'); */
                        alert("数据已成功删除！");
                        location.reload(true);
                    } else {
                        alert("数据删除失败！");
                    }
                }, "json");
            } else {
                return;
            }
        }
    </script>
</head>
<body>
<#include "../common/nav.ftl"/>
<div style="width: 1200px; margin: 0 auto;">
    <h4>版主：${section.master.nickName }</h4>
</div>
<div style="width: 1200px; margin: 0 auto;">
    <div style="margin-bottom: 10px;">
        <a class="" href="/blog/addTopic">
            <img alt="发帖" src="images/post.jpg"></a>
        <div class="pagination alternate pull-right" align="center" style="margin: 0px;">
            <ul class="clearfix">${pageCode }
            </ul>
        </div>
    </div>
    <table border="0" width="100%" cellspacing="0" cellpadding="0" style="margin-top: 8px;">
        <!-- 置顶帖子 -->
        <!-- <tr height="30">
            <td style="text-indent:5;" background="images/index/classT.jpg"><b><font color="white">■ 置顶帖子</font></b></td>
        </tr> -->
        <tr>
            <td>
                <table class="table table-bordered" width="100%" cellspacing="0" cellpadding="0"
                       style="margin-top: 8px;">
                    <tr>
                        <th style="text-align: center;vertical-align: middle; width: 150px;">
                            状态
                        </th>
                        <th style="text-align: center;vertical-align: middle;">
                            帖子标题
                        </th>
                        <th style="text-align: center;vertical-align: middle; width: 100px;">
                            回复数
                        </th>
                        <th style="text-align: center;vertical-align: middle; width: 100px;">
                            发表者
                        </th>
                        <th style="text-align: center;vertical-align: middle; width: 200px;">
                            最后回复
                        </th>
                        <th style="text-align: center;vertical-align: middle; width: 150px;">
                            操作
                        </th>
                    </tr>
                    <#list topicList as topic>

                    </#list>
                    <c:forEach items="${zdTopicList }" var="topic">
                        <tr>
                            <td style="text-align: center;vertical-align:middle;">
                                <c:choose>
                                    <c:when test="${topic.good==0 }">
                                        <span style="color: blue;">【普通】</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span style="color: red;">【精华】</span>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${topic.top==1 }">【置顶】</c:when>
                                    <c:otherwise></c:otherwise>
                                </c:choose>
                            </td>
                            <td style="text-align: center;vertical-align:middle;">
                                <a href="Topic_details.action?topicId=${topic.id }">${topic.title }</a>
                            </td>
                            <td style="text-align: center;vertical-align:middle;">
                                ${topicReplyCount.get(topic) }
                            </td>
                            <td style="text-align: center;vertical-align:middle;">
                                ${topic.user.nickName }
                            </td>
                            <td style="text-align: center;vertical-align:middle; width: 200px;">
                                <strong>${topicLastReply.get(topic).user.nickName }</strong><br>
                                ${topicLastReply.get(topic).publishTime }
                            </td>
                            <td style="text-align: center;vertical-align:middle;">
                                <c:choose>
                                    <c:when test="${currentUser.id==topic.user.id&&currentUser.id!=section.master.id }">
                                        <button class="btn btn-info" data-backdrop="static" onclick="#">修改</button>
                                    </c:when>
                                    <c:when test="${currentUser.id==section.master.id }">
                                        <button class="btn btn-info" type="button" data-backdrop="static"
                                                data-toggle="modal"
                                                data-target="#dlg"
                                                onclick="return modifyTopic(${topic.id },${topic.top },${topic.good })">
                                            修改
                                        </button>
                                        <button class="btn btn-danger" onclick="javascript:deleteTopic(${topic.id })">
                                            删除
                                        </button>
                                    </c:when>
                                    <c:when test="${currentUser.type==2 }">
                                        <button class="btn btn-info" data-backdrop="static" data-toggle="modal"
                                                data-target="#dlg"
                                                onclick="return modifyTopic(${topic.id },${topic.top },${topic.good })">
                                            修改
                                        </button>
                                        <button class="btn btn-danger" onclick="javascript:deleteTopic(${topic.id })">
                                            删除
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        您无权对本贴进行操作
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>

        <!-- 其他帖子 -->
        <!-- <tr height="30">
            <td style="text-indent:5;" background="images/index/classT.jpg"><b><font color="white">■ 其它帖子</font></b></td>
        </tr> -->

        <tr>
            <td>
                <table class="table table-bordered" width="100%" cellspacing="0" cellpadding="0" style="margin-top: 8;">
                    <!-- <tr>
                        <th style="text-align: center;vertical-align: middle; width: 150px;">
                            状态
                        </th>
                        <th style="text-align: center;vertical-align: middle;">
                            帖子标题
                        </th>
                        <th style="text-align: center;vertical-align: middle; width: 100px;">
                            回复数
                        </th>
                        <th style="text-align: center;vertical-align: middle; width: 100px;">
                            发表者
                        </th>
                        <th style="text-align: center;vertical-align: middle; width: 200px;">
                            最后回复
                        </th>
                        <th style="text-align: center;vertical-align: middle; width: 150px;">
                            操作
                        </th>
                    </tr> -->
                    <c:forEach items="${ptTopicList }" var="topic">
                        <tr>
                            <td style="text-align: center;vertical-align:middle;width: 150px;">
                                <c:choose>
                                    <c:when test="${topic.good==0 }">
                                        <span style="color: blue;">【普通】</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span style="color: red;">【精华】</span>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${topic.top==1 }">【置顶】</c:when>
                                    <c:otherwise></c:otherwise>
                                </c:choose>
                            </td>
                            <td style="text-align: center;vertical-align:middle;">
                                <a href="Topic_details.action?topicId=${topic.id}">${topic.title }</a>
                            </td>
                            <td style="text-align: center;vertical-align:middle;width: 100px;">
                                ${topicReplyCount.get(topic) }
                            </td>
                            <td style="text-align: center;vertical-align:middle;width: 100px;">
                                ${topic.user.nickName }
                            </td>
                            <td style="text-align: center;vertical-align:middle;width: 200px;">
                                <strong>${topicLastReply.get(topic).user.nickName }</strong><br>
                                ${topicLastReply.get(topic).publishTime }
                            </td>
                            <td style="text-align: center;vertical-align:middle;width: 150px;">
                                <c:choose>
                                    <c:when test="${currentUser.id==topic.user.id&&currentUser.id!=section.master.id }">
                                        <button class="btn btn-info" data-backdrop="static" onclick="#">修改</button>
                                    </c:when>
                                    <c:when test="${currentUser.id==section.master.id }">
                                        <button class="btn btn-info" data-backdrop="static" data-toggle="modal"
                                                data-target="#dlg"
                                                onclick="return modifyTopic(${topic.id },${topic.top },${topic.good })">
                                            修改
                                        </button>
                                        <button class="btn btn-danger" onclick="javascript:deleteTopic(${topic.id })">
                                            删除
                                        </button>
                                    </c:when>
                                    <c:when test="${currentUser.type==2 }">
                                        <button class="btn btn-info" data-backdrop="static" data-toggle="modal"
                                                data-target="#dlg"
                                                onclick="return modifyTopic(${topic.id },${topic.top },${topic.good })">
                                            修改
                                        </button>
                                        <button class="btn btn-danger" onclick="javascript:deleteTopic(${topic.id })">
                                            删除
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        您无权对本贴进行操作
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>
</div>
<div id="dlg" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
                aria-hidden="true" onclick="return resetValue()">×
        </button>
        <h3 id="myModalLabel">修改主题</h3>
    </div>
    <div class="modal-body">
        <form id="fm" action="#" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>
                        <label class="control-label" for="top">置顶：</label>
                    </td>
                    <td>
                        <select id="topicTop">
                            <option value="0">非置顶</option>
                            <option value="1">置顶</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label class="control-label" for="good">精华：</label>
                    </td>
                    <td>
                        <select id="topicGood">
                            <option value="0">非精华</option>
                            <option value="1">精华</option>
                        </select>
                    </td>
                </tr>
                <%-- <tr>
                    <td>
                        <label class="control-label" for="zone">请选择所属大板块：</label>
                    </td>
                    <td>
                        <select id="section" name="topic.section.id"><option value="">请选择...</option>
                            <c:forEach var="section" items="${section.List }">
                                <option value="${section.id }" ${curSection.id==section.id?'selected':'' }>${section.name }</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr> --%>
            </table>
            <input id="topicId" type="hidden">
        </form>
    </div>
    <div class="modal-footer">
        <font id="error" style="color: red;"></font>
        <button class="btn" data-dismiss="modal" aria-hidden="true"
                onclick="return resetValue()">关闭
        </button>
        <button class="btn btn-primary" onclick="javascript:saveTopic()">保存</button>
    </div>
</div>


<#include "../common/footer.ftl"/>
</body>
</html>