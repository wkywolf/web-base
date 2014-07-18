<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>新增用户</title>
	</head>
	<body>
		<form action="/user/add" method="post">
			<table>
				<tr>
					<th>用户名：</th>
					<th>密码：</th>
				</tr>
				<#list users?exists as user>
					<tr>
						<td>${user.userName?if_exists}</td>
						<td>${user.password?if_exists}</td>
					</tr>
				</#list>
			</table>
		</form>
	</body>
</html>