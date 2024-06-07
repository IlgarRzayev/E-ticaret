<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Your Products List</h1>
	<table border="2" width="70%" cellpadding="2">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Category</th>
			<th>Quantity</th>
			<th>Price</th>
			<th>Image</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach var="emp" items="${list}">
			<tr>
				<td>${product.id}</td>
				<td>${product.name}</td>
				<td>${product.category}</td>
				<td>${product.quantity}</td>
				<td>${product.price}</td>
				<td>${product.imageUrl}</td>
				<td><a href="editproduct/${product.id}">Edit</a></td>
				<td><a href="deleteproduct/${product.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<a href="empform">Add New Employee</a>
</body>
</html>
