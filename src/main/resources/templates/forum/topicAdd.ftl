<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>添加博客</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../css/main.css"/>
    <link rel="stylesheet" href="../css/simditor.css"/>
    <!-- Scripts -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/module.js"></script>
    <script src="../js/hotkeys.js"></script>
    <script src="../js/simditor.js"></script>
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
            <table border="0" cellspacing="0" cellpadding="0" style="margin-top: 8px;"
                   align="center">
                <tr>
                    <td class="col-md-4 col-sm-4" valign="top">
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
                    <td class="col-md-8 col-sm-8">
                        <form class="form-horizontal" style="margin-top: 10px;"
                              action="/graduation/blog/add" method="post">
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="title">【主题】</label>
                                <div class="controls col-sm-10">
                                    <input type="text" id="title" name="title" style="width: 800px;">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="section">【分类】</label>
                                <div class="controls col-sm-10">
                                    <select id="section" name="cname" style="width: 400px;">
                                        <option value="0">请选择分类...</option>
                                        <#list categoryList as category>
                                            <option>${category.name}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="text">【内容】</label>
                                <div class="controls col-sm-10">
                                    <textarea name="content" id="text" autofocus>
                                        请输入博客内容
                                    </textarea>
                                </div>
                            </div>
                            <input  name="userId" value="${currentUser.id}" type="hidden"/>
                            <input  name="hid" value="${hid}" type="hidden"/>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <div style="text-align: center;"><button type="submit" class="btn btn-primary">提交博文</button></div>
                                </div>
                            </div>
                        </form>
                    </td>
                </tr>
            </table>
        </div>

        <br>
        <br>
        <script>
            var editor = new Simditor({
                textarea:$('#text')
            })
        </script>
        <#include "../common/footer.ftl"/>
    </div>
</div>

</body>
</html>