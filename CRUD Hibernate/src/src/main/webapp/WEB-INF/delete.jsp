<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete Data</title>
</head>
<body>

	<form name='f' method="POST">
		<table>
			<tr>
				<td>Id：</td>
				<td><input name="id" disabled value="${user.id}" /></td>
			</tr>
			<tr>
				<td>Name：</td>
				<td><input name="username" disabled value="${user.username}" /></td>
			</tr>
			<tr>
				<td>Address：</td>
				<td><input name="address" disabled value="${user.address}" /></td>
			</tr>
		</table>
		<input type="submit" value="delete" />
	</form>

	<a href="${pageContext.servletContext.contextPath}/read?page=0">read</a>
</body>
</html>