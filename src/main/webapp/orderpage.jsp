<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
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
        <h2>Shopping Cart</h2>
        <form th:action="@{/checkout}" method="post">
            <div class="row">
                <c:forEach items="${products}" var="product">
                    <div class="col-md-4">
                        <div class="product">
                            <h3>${product.name}</h3>
                            <img src="${product.imageUrl}" alt="${product.name}">
                            <p>${product.price} TL</p>
                            <div class="actions">
                                <input type="checkbox" name="selectedProducts" th:value="${product.id}">
                                <label>Seç</label>
                                <a href="deleteFromCart/${product.id}" class="btn btn-danger">Sepetten Sil</a>
                                <a href="checkout/${product.id}" class="btn btn-info">Direkt Ödeme</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <button type="submit" class="btn btn-primary mt-3">Seçilenleri Satın Al</button>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
