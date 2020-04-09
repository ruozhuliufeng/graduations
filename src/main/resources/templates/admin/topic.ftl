<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
		<title>帖子管理</title>
		<link href="/admin/css/bootstrap.min.css" rel="stylesheet">
		<link href="/admin/css/materialdesignicons.min.css" rel="stylesheet">
		<link href="/admin/css/style.min.css" rel="stylesheet">
	</head>

	<body>
		<div class="lyear-layout-web">
			<div class="lyear-layout-container">
				<!--左侧导航-->
				<aside class="lyear-layout-sidebar">

					<#include "left.ftl">

				</aside>
				<!--End 左侧导航-->

				<!--头部信息-->
				<header class="lyear-layout-header">

					<#include "header.ftl">

				</header>
				<!--End 头部信息-->

				<!--页面主要内容-->
				<main class="lyear-layout-content">

					<div class="container-fluid">

						<div class="row">
							<table class="table table-striped table-hover">
								<tr>
									<th>序号</th>
									<th>发布时间</th>
									<th>修改时间</th>
									<th>帖子标题</th>
									<th>帖子内容</th>
									<th>用户名称</th>
									<th>所属分类</th>
									<th>是否置顶</th>
									<th>是否精华</th>
									<th>操作</th>
								</tr>
								<#list blogList as blog>
									<tr>
										<td>${blog_index+1}</td>
										<td>${blog.publishTime?date}</td>
										<td>${blog.modifyTime?date}</td>
										<td>${blog.title}</td>
										<td>${blog.content}</td>
										<td>${blog.categoryName}</td>
										<td>${blog.userName}</td>
										<td>${blog.good}</td>
										<td>${blog.top}</td>
										<td>
											<a href="/blog/delete?id=${blog.id}">删除</a> ||
											<a href="/admin/topicUpdate">修改</a>
										</td>
									</tr>
								</#list>
							</table>

						</div>

					</div>

				</main>
				<!--End 页面主要内容-->
			</div>
		</div>

		<script type="text/javascript" src="/admin/js/jquery.min.js"></script>
		<script type="text/javascript" src="/admin/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="/admin/js/perfect-scrollbar.min.js"></script>
		<script type="text/javascript" src="/admin/js/main.min.js"></script>

	</body>
</html>
