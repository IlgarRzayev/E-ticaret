<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> Product List</h1>
	<a href="add-product.jsp">Add New User</a>
	<table border="2" width="70%" cellpadding="2">
		<tr>
			<th>Id</th>
			<th>Isim</th>
			<th>Kategori</th>
			<th>Fiyat</th>
			<th>Sayı</th>
			<th>Foto</th>
			<th>Düzenle</th>
			<th>Sil</th>
			
		</tr>
		<c:forEach var="product" items="${list}">
			<tr>
				<td>${product.productId}</td>
				<td>${product.name}</td>
				<td>${product.category}</td>
				<td>${product.price}</td>
				<td>${product.quantity}</td>
				<td>${product.imageUrl}</td>
				<td><a href="editproduct/${product.productId}">Edit</a></td>
				<td><a href="deleteproduct/${product.productId}">Delete</a></td>
				
			</tr>
		</c:forEach>
	</table>
	<br />
	
</body>
</html>
