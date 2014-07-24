<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<#include "../common/base-js-css.ftl"> 
		<title>新增用户</title>
	</head>
	<body>
		<div class="container">
			<div class="page-header">
				<h3>用户列表信息</h3>
			</div>
			<form id="saveForm" action="${ctx}/user/list" method="get">
				<table class="table table-bordered table-striped table-hover" style="text-align: center;">
					<div>
						<input type="button" id="addUser" name="addUser" value="添加用户" class="btn btn-primary"></input>
					</div>
					<thead>
						<tr>
							<th>序号</th>
							<th>用户名</th>
							<th>真实姓名</th>
							<th>性别</th>
							<th>密码</th>
							<th>身份证号码</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<#list users?if_exists as user>
							<tr>
								<td>${user_index + 1}</td>
								<td>${user.account?if_exists}</td>
								<td>${user.realName?if_exists}</td>
								<td>${user.sex?if_exists}</td>
								<td>${user.password?if_exists}</td>
								<td>${(user.idCard.cardNum)?if_exists}</td>
								<td>
									<a href="${ctx}/user/update?id=${user.id?if_exists}">修改</a>
									<a href="javaScript:deleteUser('${ctx}/user/delete?id=${user.id?if_exists}');" id="delete">删除</a>
								</td>
							</tr>
						</#list>
					</tbody>
				</table>
			</form>
		</div>
		<script>
			$("#addUser").click(function() {
				location.href = "${ctx}/user/add";
			});
			
			function deleteUser(url) {
				if(confirm("确定删除？")){
					var $form = $("#saveForm");
					$form.attr("method", "post");
					$form.attr("action", url);
					$form.submit();
				}
			}
			
		</script>
	</body>
</html>