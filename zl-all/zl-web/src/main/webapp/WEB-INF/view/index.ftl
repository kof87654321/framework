<!DOCTYPE html>
<html>
<head>
<!-- 公共meta信息 -->
<body>
	hello world
	<br/><br/><br/><br/>
	
	<table>
	<tr>
		<td width="100">ID</td>
		<td width="100">昵称</td>
		<td width="100">登录名</td>
	</tr>
	<#list userList as user >
		<tr>
			<td>${user.id}</td>
			<td>${user.nick}</td>
			<td>${user.loginname}</td> 
		</tr>
	</#list>
	</table>
</body>
</html>