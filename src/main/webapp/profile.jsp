<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Products List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            padding: 20px;
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
            background-color: #f0f0f0;
        }
        .btn {
            padding: 5px 10px;
            font-size: 14px;
            text-decoration: none;
        }
        .btn-edit {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
        }
        .btn-delete {
            background-color: #dc3545;
            color: #fff;
            border: none;
            border-radius: 4px;
        }
        .btn-share {
            background-color: #28a745;
            color: #fff;
            border: none;
            border-radius: 4px;
        }
        .btn:hover {
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Your Products List</h1>
        <table class="table table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>ProductId</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Image</th>
                    <th>Edit</th>
                    <th>Delete</th>
                    <th>Share Product</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${list}">
                    <tr>
                        <td>${product.getProductId()}</td>
                        <td>${product.getName()}</td>
                        <td>${product.getCategory()}</td>
                        <td>${product.getQuantity()}</td>
                        <td>${product.getPrice()}</td>
                        <td>${product.getImageUrl()}</td>
                        <td><a href="editproduct?productId=${product.getProductId()}" class="btn btn-edit">Edit</a></td>
                        <td><a href="deleteproduct?productId=${product.getProductId()}" class="btn btn-delete">Delete</a></td>
                        <td><a href="shareproduct?productId=${product.getProductId()}" class="btn btn-share">Share Product</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="add-product" class="btn btn-primary">Add New Product</a>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
