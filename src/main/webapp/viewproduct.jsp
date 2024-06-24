<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product List - UNIQUE</title>
    <style>
        /* Global styles */
        body, html {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }

        .container {
            width: 80%;
            margin: 0 auto;
        }

        /* Header styles */
        .header {
            background-color: #FFA500;
            color: #fff;
            padding: 1rem 0;
            text-align: center;
        }

        .logo {
            margin: 0;
            font-size: 2rem;
        }

        .nav-links {
            list-style-type: none;
            padding: 0;
        }

        .nav-links li {
            display: inline;
            margin: 0 10px;
        }

        .nav-links a {
            color: #fff;
            text-decoration: none;
        }

        .nav-links a:hover {
            text-decoration: underline;
        }

        /* Main content styles */
        .main-content {
            padding: 2rem 0;
        }

        .product-list {
            background-color: #fff;
            padding: 1rem;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .product-list h2 {
            color: #333;
            margin-bottom: 1rem;
        }

        .product-table {
            width: 100%;
            border-collapse: collapse;
        }

        .product-table th, .product-table td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }

        .product-table th {
            background-color: #FFA500;
            color: #fff;
        }

        .product-table td {
            background-color: #fff;
        }

        .product-table a {
            text-decoration: none;
            color: #333;
            padding: 4px 8px;
            border-radius: 4px;
        }

        .product-table a.edit-link {
            background-color: #4CAF50;
            color: #fff;
        }

        .product-table a.edit-link:hover {
            background-color: #45a049;
        }

        .product-table a.delete-link {
            background-color: #f44336;
            color: #fff;
        }

        .product-table a.delete-link:hover {
            background-color: #da190b;
        }

        /* Footer styles */
        .footer {
            background-color: #000;
            color: #fff;
            text-align: center;
            padding: 1rem 0;
            position: absolute;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>
    <header class="header">
        <div class="container">
            <h1 class="logo">UNIQUE</h1>
            <nav>
                <ul class="nav-links">
                    <li><a href="add-product.jsp">Yeni Ürün Ekle</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <main class="main-content">
        <section class="product-list">
            <div class="container">
                <h2>Ürün Listesi</h2>
                <table class="product-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>İsim</th>
                            <th>Kategori</th>
                            <th>Fiyat</th>
                            <th>Stok Adedi</th>
                            <th>Düzenle</th>
                            <th>Sil</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${list}">
                            <tr>
                                <td>${product.productId}</td>
                                <td>${product.name}</td>
                                <td>${product.category}</td>
                                <td>${product.price}</td>
                                <td>${product.quantity}</td>
                                <td><a href="editproduct?productId=${product.productId}" class="edit-link">Düzenle</a></td>
                                <td><a href="deleteproduct?productId=${product.productId}" class="delete-link">Sil</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
    </main>

    <footer class="footer">
        <div class="container">
            <p>&copy; 2024 UNIQUE. Tüm hakları saklıdır.</p>
        </div>
    </footer>
</body>
</html>
