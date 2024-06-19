<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sepet</title>
</head>
<body>
    <h1>Sepetiniz</h1>
    <c:choose>
        <c:when test="${empty cartItems}">
            <p>Sepetinizde ürün bulunmamaktadır.</p>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>Ürün Adı</th>
                    <th>Adet</th>
                </tr>
                <c:forEach var="item" items="${cartItems}">
                    <tr>
                        <td>${item.productId}</td> <!-- Ürün adını almak için gerekli düzenlemeler yapılabilir -->
                        <td>${item.quantity}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>
