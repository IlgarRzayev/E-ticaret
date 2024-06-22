<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ödeme</title>
</head>
<body>
    <h1>Ödeme Sayfası</h1>
    <table>
        <tr>
            <th>Ürün Fotoğrafı</th>
            <th>Ürün Adı</th>
            <th>Kategori</th>
            <th>Fiyat</th>
            <th>Adet</th>
            <th>Toplam</th>
        </tr>
        <c:forEach var="cartItem" items="${cartItems}">
            <c:forEach var="product" items="${products}">
                <c:if test="${product.id == cartItem.productId}">
                    <tr>
                        <td><img src="${product.photoUrl}" alt="${product.name}" width="100" height="100"/></td>
                        <td>${product.name}</td>
                        <td>${product.category}</td>
                        <td>${product.price}</td>
                        <td>${cartItem.quantity}</td>
                        <td>${product.price * cartItem.quantity}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </c:forEach>
        <tr>
            <td colspan="5" align="right">Toplam Fiyat:</td>
            <td>${totalPrice}</td>
        </tr>
    </table>

    <h2>Ödeme Yöntemi Seçiniz</h2>
    <form action="pay" method="post">
        <input type="radio" id="creditCard" name="paymentMethod" value="Credit Card">
        <label for="creditCard">Kredi Kartı</label><br>
        <input type="radio" id="paypal" name="paymentMethod" value="PayPal">
        <label for="paypal">PayPal</label><br>
        <input type="radio" id="bankTransfer" name="paymentMethod" value="Bank Transfer">
        <label for="bankTransfer">Banka Transferi</label><br><br>
        <input type="submit" value="Ödemeyi Tamamla">
    </form>
</body>
</html>
