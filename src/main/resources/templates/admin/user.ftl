<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
		<title>首页 - 个人成长助理平台管理系统</title>
		<link href="../admin/css/bootstrap.min.css" rel="stylesheet">
		<link href="../admin/css/materialdesignicons.min.css" rel="stylesheet">
		<link href="../admin/css/style.min.css" rel="stylesheet">
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
							<table class="table table-bordered table-hover table-striped">
								<tr>
									<th>序号</th>
									<th>用户名称</th>
									<th>用户邮箱</th>
									<th>用户性别</th>
									<th>是否激活</th>
									<th>所选兴趣</th>
									<th>所属阶段</th>
									<th>所属级别</th>
									<th>操作</th>
								</tr>
								<#list userList as user>
									<tr>
										<td>${user_index+1}</td>
										<td>${user.username}</td>
										<td>${user.email}</td>
										<td>${user.sex}</td>
										<td>
											<#if user.status == 1>
												已激活
											<#else>
												未激活
											</#if>

										</td>
										<td>${user.hname}</td>
										<td>${user.sname}</td>
										<td>
											<#if user.type == 1>
												管理员
											<#else>
												普通用户
											</#if>
										</td>
										<td>
											<a href="/graduation/user/delete?id=${user.id}">删除</a> ||
											<a href="/graduation/user/findById?id=${user.id}">修改</a>
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

		<script type="text/javascript" src="../admin/js/jquery.min.js"></script>
		<script type="text/javascript" src="../admin/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="../admin/js/perfect-scrollbar.min.js"></script>
		<script type="text/javascript" src="../admin/js/main.min.js"></script>

	</body>
</html>
