<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<#include "../common/base-js-css.ftl"> 
		<title>新增用户</title>
	</head>
	<body>
		<div align="center">
			<h3>新增或更新用户信息</h3>
			<form action="${ctx?if_exists}/user/save" method="post" class="form-horizontal" role="form">
				<input type="hidden" name="id" value="${user.id?if_exists}" />
				<div class="form-group">
					<label for="account" class="col-sm-2 control-label">用户名：</label>
					<div class="col-sm-2">
			      		<input type="text" class="form-control" id="account" name="account" value="${user.account?if_exists}" placeholder="用户名">
			    	</div>
			  	</div>
			  	<div class="form-group">
			    	<label for="password" class="col-sm-2 control-label">密码：</label>
			    	<div class="col-sm-2">
			      		<input type="password" class="form-control" id="password" name="password" value="${user.password?if_exists}" placeholder="密码">
			    	</div>
			  	</div>
			  	<div class="form-group">
					<label for="realName" class="col-sm-2 control-label">真实姓名：</label>
					<div class="col-sm-2">
			      		<input type="text" class="form-control" id="realName" name="realName" value="${user.realName?if_exists}" placeholder="真实姓名">
			    	</div>
			  	</div>
			  	<div class="form-group">
					<label for="sex" class="col-sm-2 control-label">性别：</label>
					<div class="col-sm-2">
			      		<input type="text" class="form-control" id="sex" name="sex" value="${user.sex?if_exists}" placeholder="性别">
			    	</div>
			  	</div>
			  	<div class="form-group">
			    	<div class="col-sm-offset-2 col-sm-2">
			      		<button type="submit" class="btn btn-primary">提交</button>
			      		<a href="javascript:void(0);" id="listUser"  class="btn btn-default">查看列表</a>
			    	</div>
			  	</div>
			</form>
		</div>
		<script>
			$("#listUser").click(function() {
				location.href = "${ctx}/user/list";
			});
		</script>
	</body>
</html>