<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f0f0f0;
            padding-top: 50px;
        }
        .container {
            margin-top: 50px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            border-radius: 8px;
        }
        .product {
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 15px;
            text-align: center;
            margin-bottom: 20px;
            background-color: #f9f9f9;
        }
        .product img {
            max-width: 100%;
            height: auto;
            border-radius: 5px;
        }
        .product h3 {
            font-size: 1.5em;
            margin-bottom: 10px;
        }
        .actions {
            margin-top: 10px;
        }
        .actions .btn {
            margin: 5px;
        }
    </style>
    <%-- <%@include file="homepage.jsp"%> --%>
</head>
<body>
    <c:choose>
    <c:when test="${empty orders}">
        <p>Siparişiniz yok</p>
    </c:when>
    <c:otherwise>
        <div class="row">
            <c:forEach items="${orders}" var="order">
                <c:set var="product" value="${products.get(order.productId)}" />
                <c:if test="${not empty product}">
                    <div class="col-md-4">
                        <div class="product">
                            <h3>${product.name}</h3>
                            <img src="${product.imageUrl}" alt="${product.name}">
                            <p>${product.price} TL</p>
                            <div class="actions">
                                <a href="deleteorder?orderId=${order.orderId}" class="btn btn-danger">Siparişi İptal Et</a>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
