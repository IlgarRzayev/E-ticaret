<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.beans.*"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="homepage.jsp"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sepetiniz - UNIQUE</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body, html {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            border-radius: 8px;
            margin-top: 20px;
        }

        h1 {
            text-align: center;
            color: #FF4B2B;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        table, th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #FF4B2B;
            color: #fff;
        }

        .btn {
            text-decoration: none;
            padding: 8px 12px;
            border-radius: 4px;
            color: #fff;
            margin-right: 5px;
        }

        .btn-delete {
            background-color: #dc3545;
        }

        .btn-delete:hover {
            background-color: #c82333;
        }

        .btn-pay {
            background-color: #28a745;
        }

        .btn-pay:hover {
            background-color: #218838;
        }

        img {
            max-width: 100px;
            height: auto;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Sepetiniz</h1>
        <c:choose>
            <c:when test="${empty cartItems}">
                <p>Sepetinizde ürün bulunmamaktadır.</p>
            </c:when>
            <c:otherwise>
                <table class="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>Ürün Adı</th>
                            <th>Kategori</th>
                            <th>Fiyat</th>
                            <th>Adet</th>
                            <th>Foto</th>
                            <th>Sil</th>
                            <th>Öde</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cartItem" items="${cartItems}">
                            ${product = products.get(cartItem.getProductId())}
                            <c:if test="${product.getProductId() == cartItem.getProductId()}">
                                <tr>
                                    <td>${product.getName()}</td>
                                    <td>${product.getCategory()}</td>
                                    <td>${product.getPrice()}</td>
                                    <td>${cartItem.quantity}</td>
                                    <td><img src="${product.getImageUrl()}" alt="${product.getName()}" /></td>
                                    <td><a href="deletecart?cartId=${cartItem.getCartId()}" class="btn btn-delete">Sil</a></td>
                                    <%-- <td><a href="pay?cartId=${cartItem.getCartId()}" class="btn btn-pay">Öde</a></td> --%>
                                    
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
