<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>N + 1 selects problem with Hibernate</title>
</head>
<body>
	<h1>N + 1 selects problem with Hibernate</h1>

	<h2>The n+1 selects problem</h2>
	<div>
		<table border="1" style="display: inline-block; text-align: center;">
			<tr>
				<th>&lt;&lt;Table&gt;&gt;</th>
			</tr>
			<tr>
				<td>IMAGE</td>
			</tr>
			<tr>
				<td>ID &lt;&lt;PK&gt;&gt; <br />USERID &lt;&lt;FK&gt;&gt; <br />NAME
					<br />TYPE <br />DATA
				</td>
			</tr>
		</table>
		<pre style="display: inline-block;">                   </pre>
		<table border="1" style="display: inline-block; text-align: center;">
			<tr>
				<th>&lt;&lt;Table&gt;&gt;</th>
			</tr>
			<tr>
				<td>USER</td>
			</tr>
			<tr>
				<td>ID &lt;&lt;PK&gt;&gt; <br />COUNTRYID &lt;&lt;FK&gt;&gt;<br />USERNAME
				</td>
			</tr>
		</table>
		<pre style="display: inline-block;">                   </pre>
		<table border="1" style="display: inline-block; text-align: center;">
			<tr>
				<th>&lt;&lt;Table&gt;&gt;</th>
			</tr>
			<tr>
				<td>COUNTRY</td>
			</tr>
			<tr>
				<td>ID &lt;&lt;PK&gt;&gt; <br />NAME <br />CAPITAL
				</td>
			</tr>
		</table>


	</div>
	
	<pre>
	The Problem:
			List&lt;User&gt; users = em.createQuery("select u from User u").getResultList();
			// select * from USER  ---------- (1)
			for (User user : users) {
			    System.out.println(user.getCountry().getName());
			    // select * from COUNTRY where ID = ? ----------- (2)
			}
		The code has to use one SQL select to load USER entity, then, 
		when it iterates the user to get Country, it need an additional SELECT.
		In the end, you will get one(from query USER) plus N(depend on how many users) SELECT.
		If you know you will access all the country in the users, this is a very inefficient strategy.
		
		Lazily loaded collections also has same problem
			List&lt;User&gt; users = em.createQuery("select u from User u").getResultList();
			// select * from USER
			for (User user : users) {
			    System.out.println(user.getImages().size());
			    // select * from IMAGE where USER_ID = ?
			}
				
	</pre>
	
	<br />
	<a href="${pageContext.servletContext.contextPath}/solution">solution</a>

</body>
</html>