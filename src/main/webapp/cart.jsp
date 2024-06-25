<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sepet</title>
</head>
<body>
	<h1>Sepetiniz</h1>${cartItems}
	<c:choose>
		<c:when test="${empty cartItems}">
			<p>Sepetinizde ürün bulunmamaktadır.</p>
		</c:when>
		<c:otherwise>
			<table>
				<tr>
					<th>Ürün Adı</th>
					<th>Kategori</th>
					<th>Fiyat</th>
					<th>Adet</th>
					<th>Foto</th>
					<th>Sil</th>
					<th>Öde</th>
				</tr>
				<c:forEach var="cartItem" items="${cartItems}">
                ${product = products.get(cartItem.getProductId())}
                        <c:if
						test="${product.getProductId() == cartItem.getProductId()}">
						<tr>
							<td>${product.getName()}</td>
							<td>${product.getCategory()}</td>
							<td>${product.getPrice()}</td>
							<td>${cartItem.quantity}</td>
							<td><img src="${product.getImageUrl()}"
								alt="${product.getName()}" width="100" height="100" /></td>
							<td><a href="deletecart?cartId=${cartItem.getCartId()}">Sil</a></td>
							<td><a href="pay?cartId=${cartItem.getCartId()}">Öde</a></td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>
