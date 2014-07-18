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
					<td>用户名：</td>
					<td><input type="text" name="userName" /></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" name="subbtn" value="提交"/></td>
				</tr>
			</table>
		</form>
	</body>
</html>