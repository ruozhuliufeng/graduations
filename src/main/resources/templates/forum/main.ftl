<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>学习论坛</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/main.css"/>
    <!-- Scripts -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.min.js"></script>

    <link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
    <link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />

    <script src="bootstrap/js/jQuery.js"></script>
    <script src="bootstrap/js/bootstrap.js" ></script>
</head>
<body>
<div class="container-fluid">
    <#include "../common/nav.ftl"/>
    <br>
    <br>
    <br>
    <table border="0" width="100%" cellspacing="0" cellpadding="0" style="margin-top: 8px;">
        <#list zongList as zone>
            <tr>
                <td>
                    <table style="width: 1200px;" align="center">
                        <tr height="30">
                            <td style="text-indent:5px;" background="images/index/classT.jpg"><b>
                                    <font color="white">■ ${zone.name }</font></b></td>
                        </tr>
                        <tr>
                            <td>
                                <ul class="unstyled inline">
                                    <c:forEach items="${zone.sectionList }" var="section">
                                    <#list
                                        <li style="width: 394px; margin-left: 0px;padding: 0px;">
                                            <div align="center" style="margin-top: 20px;">
                                                <div><a href="/topic/findlist?sectionId=${section.id }"><img
                                                                style="width: 100px;" alt=""
                                                                src="${section.logo }"></a>
                                                </div>
                                                <div style="margin: 10px auto;"><a
                                                            href="/topic/findlist?sectionId=${section.id }"><font
                                                                style="font-size: 30px;font-weight: bold;">${section.name }</font></a>
                                                </div>
                                                <font style="font-size: 12px;">帖子总数：${sectionTopicCount.get(section) }</font>
                                                <font style="font-size: 12px;">精华帖子：${sectionGoodTopicCount.get(section) }</font>
                                                <font style="font-size: 12px;">未回复：${sectionNoReplyTopicCount.get(section) }</font>
                                                <font style="font-size: 12px;">版主：${section.master.nickName }</font>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </#list>

    </table>

    <#include "../common/footer.ftl"/>
</div>
</body>
</html>