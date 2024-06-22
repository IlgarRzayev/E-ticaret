<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> User List</h1>
	<a href="add-user.jsp">Add New User</a>
	<table border="2" width="70%" cellpadding="2">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Surname</th>
			<th>Email</th>
			<th>Edit</th>
			<th>Delete</th>
			
		</tr>
		<c:forEach var="user" items="${list}">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.surname}</td>
				<td>${user.email}</td>
				<td><a href="edituser/${user.id}">Edit</a></td>
				<td><a href="deleteuser/${user.id}">Delete</a></td>
				
			</tr>
		</c:forEach>
	</table>
	<br />
	
</body>
</html>
