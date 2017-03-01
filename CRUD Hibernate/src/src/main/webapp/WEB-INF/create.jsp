<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create User</title>
</head>
<body>

	<c:if test="${not empty errorMsgs}">
		<font color='red' style="font-family: Tahoma, Verdana, 微軟正黑體;">
			<c:forEach var="errorMsg" items="${errorMsgs}">
				${errorMsg}<br/>
			</c:forEach>
		</font>
	</c:if>
	<form name='f' method="POST">
		<table>
			<tr>
				<td>Name：</td>
				<td><input name="username" /></td>
			</tr>
			<tr>
				<td>Address：</td>
				<td><input name="address" /></td>
			</tr>
		</table>
		<input type="submit" value="Create" />
	</form>

	<a href="${pageContext.servletContext.contextPath}/read?page=0">read</a>

</body>
</html>