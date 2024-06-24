<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Orders List - UNIQUE</title>
    <link rel="stylesheet">
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

.user-list {
    background-color: #fff;
    padding: 1rem;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.user-list h2 {
    color: #333;
    margin-bottom: 1rem;
}

.user-table {
    width: 100%;
    border-collapse: collapse;
}

.user-table th, .user-table td {
    border: 1px solid #ccc;
    padding: 8px;
    text-align: left;
}

.user-table th {
    background-color: #FFA500;
    color: #fff;
}

.user-table td {
    background-color: #fff;
}

.user-table a {
    text-decoration: none;
    color: #333;
    padding: 4px 8px;
    border-radius: 4px;
}

.user-table a.edit-link {
    background-color: #4CAF50;
    color: #fff;
}

.user-table a.edit-link:hover {
    background-color: #45a049;
}

.user-table a.delete-link {
    background-color: #f44336;
    color: #fff;
}

.user-table a.delete-link:hover {
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
    <style>
        /* Additional or overridden styles specific to orders.jsp */
        /* Ensure styles match user-list.jsp as closely as possible */
        /* If necessary, update the CSS to maintain consistency */
    </style>
</head>
<body>
    <header class="header">
        <div class="container">
            <h1 class="logo">UNIQUE</h1>
            <nav>
                <ul class="nav-links">
                    <li><a href="add-order.jsp">Add New Order</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <main class="main-content">
        <section class="user-list">
            <div class="container">
                <h2>Orders List</h2>
                <table class="user-table">
                    <thead>
                        <tr>
                            <th>Sipariş No</th>
                            <th>Kullanıcı Bilgileri</th>
                            <th>Ürün No</th>
                            <th>Sipariş Tutarı</th>
                            <th>Ödeme Yöntemi</th>
                            <th>Düzenle</th>
                            <th>Sil</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${list}">
                            <tr>
                                <td>${order.orderId}</td>
                                <td>${order.userId}</td>
                                <td>${order.productId}</td>
                                <td>${order.totalPrice}</td>
                                <td>${order.paymentMethod}</td>
                                <td><a href="editorder?orderId=${order.orderId}" class="edit-link">Edit</a></td>
                                <td><a href="deleteorder?orderId=${order.orderId}" class="delete-link">Delete</a></td>
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
