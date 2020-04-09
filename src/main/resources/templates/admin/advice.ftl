<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
		<title>建议管理</title>
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
							<table>
								<tr>
									<th>序号</th>
									<th>用户名称</th>
									<th>用户邮箱</th>
									<th>用户建议</th>
									<th>操作</th>
								</tr>
								<#list adviceList as advic>
									<tr>
										<td>advice_index</td>
										<td>advice.name</td>
										<td>advice.email</td>
										<td>advice.advice</td>
										<td><a href="#">删除</a></td>
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
