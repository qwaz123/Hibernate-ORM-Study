<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Read Data</title>
</head>
<body>
	<h1>Read</h1>

	<table border="1">
		<c:forEach items="${users}" var="user">
			<tr>
				<td>Id: ${user.id}</td>
				<td>Username: ${user.username}</td>
				<td>Address: ${user.address}</td>
				<td><a href="${pageContext.servletContext.contextPath}/update?id=${user.id}">update</a></td>
				<td><a href="${pageContext.servletContext.contextPath}/delete?id=${user.id}">delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>