<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>添加帖子</title>
    <link href="/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="/bootstrap/css/bootstrap-responsive.css" rel="stylesheet"/>
    <#--    <link href="/bootstrap/css/style.css" rel="stylesheet" />-->
    <script src="/bootstrap/js/bootstrap.min.js"></script>
    <script src="/bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" href="/bootstrap/css/emoticon.css"/>
    <script src="/bootstrap/js/jquery-1.11.1.js" type="text/javascript"></script>
    <script src="/bootstrap/js/jquery.emoticons.js" type="text/javascript"></script>
    <script src="/bootstrap/js/ckeditor/ckeditor.js"></script>
    <#--    <script type="text/javascript">-->
    <#--        function checkForm(){-->
    <#--            if('${currentUser.username}'==''){-->
    <#--                alert("请先登陆，再发帖！");-->
    <#--                /* var url="Report_preSave.action?role=0&reportType=1";-->
    <#--                window.open("login.jsp?url="+url); */-->
    <#--                return false;-->
    <#--            }-->
    <#--            if ($("#title").val()===""||$("#title").val()==null) {-->
    <#--                alert("请填写帖子主题！");-->
    <#--                return false;-->
    <#--            }-->
    <#--            if ($("#section").val()===0||$("#section").val()==null) {-->
    <#--                alert("请选择板块！");-->
    <#--                return false;-->
    <#--            }-->
    <#--            if (CKEDITOR.instances.Content.getData().length<10) {-->
    <#--                alert("帖子内容最少10个字符！");-->
    <#--                return false;-->
    <#--            }-->
    <#--        }-->
    <#--    </script>-->
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <#include "../common/nav.ftl"/>
        <br>
        <br>
        <br>
        <br>
        <br>
        <div style="width: 1200px; margin: 0 auto;">
            <table border="0" width="100%" cellspacing="0" cellpadding="0" style="margin-top: 8px;width: 1400px;"
                   align="center">
                <tr height="30" background="/images/index/classT.jpg">
                    <td style="text-indent:5px;">
                        <b><font color="white">■ 发表帖子</font></b>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td style="width: 220px;" valign="top">
                        <table style="margin-top: 10px;margin-left: 20px;" class="">
                            <tr>
                                <td valign="top" width="99%">发帖许可：<br><br>
                                    <ul class="unstyled">
                                        <li>*请不要发表危害祖国的非法信息！</li>
                                        <br><br>
                                        <li>*请不要发表侵犯个人名誉的信息！</li>
                                        <br><br>
                                        <li>*请不要发表不文明内容！</li>
                                    </ul>
                            </tr>
                            <tr height="40">
                                <td align="center">违反以上规则所发生的后果自负！</td>
                            </tr>
                        </table>
                    </td>
                    <td>
                        <form class="form-horizontal" style="margin-top: 10px;"
                              action="/blog/add" method="post" onsubmit="return checkForm()">
                            <div class="control-group">
                                <label class="control-label" for="title">【主题】</label>
                                <div class="controls">
                                    <input type="text" id="title" name="title" style="width: 800px;">
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="section">【板块】</label>
                                <div class="controls">
                                    <select id="section" name="cname" style="width: 400px;">
                                        <option value="0">请选择分类...</option>
                                        <#list categoryList as category>
                                            <option>${category.name}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="Content">【内容】</label>
                                <div class="controls">
							<textarea name="content" id="Content" class="ckeditor"
                                      cols="50" style="height:200px;width: 800px;"></textarea>
                                </div>
                            </div>
                            <input id="user" name="userId" value="${currentUser.id}" type="hidden"/>
                            <div class="control-group">
                                <div class="controls">
                                    <Button class="btn btn-primary " data-dismiss="modal" aria-hidden="true"
                                            type="submit">提交
                                    </Button>
                                    <font id="error"></font>
                                </div>
                            </div>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
        <br>
        <br>
        <br>
        <br>
        <br>

        <#include "../common/footer.ftl"/>
    </div>
</div>

</body>
</html>