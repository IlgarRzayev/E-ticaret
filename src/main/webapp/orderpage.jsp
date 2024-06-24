<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            margin-top: 50px;
        }
        .product {
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 15px;
            text-align: center;
            margin-bottom: 20px;
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
</head>
<body>
    <div class="container">
        <h2>Orders</h2>
        <c:choose>
            <c:when test="${empty orders}">
                <p>Odemede ürün bulunmamaktadır.</p>
            </c:when>
            <c:otherwise>
                <div class="row">
                    <c:forEach items="${orders}" var="order">
                        <c:forEach items="${products}" var="product">
                            <c:if test="${product.getProductId() == order.getProductId()}">
                                <div class="col-md-4">
                                    <div class="product">
                                        <h3>${product.getName()}</h3>
                                        <img src="${product.getImageUrl()}" alt="${product.getName()}">
                                        <p>${product.getPrice()} TL</p>
                                        <div class="actions">
                                            <a href="viewOrder/${order.getOrderId()}" class="btn btn-info">Siparişi Görüntüle</a>
                                            <a href="cancelOrder/${order.getOrderId()}" class="btn btn-danger">Siparişi İptal Et</a>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
