<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.beans.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product - UNIQUE</title>
    <link rel="stylesheet" >
    <style>
        /* Global styles */
        body, html {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        .container {
            width: 80%;
            margin: 0 auto;
        }

        /* Header styles */
        .header {
            background-color: #FF4B2B;
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

        .form-section {
            background-color: #fff;
            padding: 1rem;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .product-form {
            display: flex;
            flex-direction: column;
        }

        .product-form label {
            margin-bottom: 0.5rem;
            color: #333;
        }

        .product-form input[type="text"],
        .product-form input[type="number"] {
            padding: 0.5rem;
            margin-bottom: 1rem;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 1rem;
        }

        .category-select {
            position: relative;
            margin-bottom: 1rem;
        }

        .category-label {
            margin-bottom: 0.5rem;
            color: #333;
        }

        .category-dropdown {
            position: absolute;
            background-color: #fff;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
            width: 100%;
            display: none;
            border: 1px solid #ccc;
            max-height: 200px;
            overflow-y: auto;
        }

        .category-dropdown a {
            display: block;
            padding: 10px;
            text-decoration: none;
            color: #333;
        }

        .category-dropdown a:hover {
            background-color: #f1f1f1;
        }

        .category-select:hover .category-dropdown {
            display: block;
        }

        .product-form button {
            padding: 0.75rem 1.5rem;
            background-color: #FF4B2B;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 1rem;
            cursor: pointer;
        }

        .product-form button:hover {
            background-color: #f27000;
        }

        .image-preview {
            margin-bottom: 1rem;
        }

        .image-preview img {
            max-width: 100%;
            height: auto;
            display: none;
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
                    <li><a href="viewproduct">Ürünleri Görüntüle</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <main class="main-content">
        <section class="form-section">
            <div class="container">
                <h2>Ürün Ekle</h2>
                <form action="add-product" method="post" class="product-form">
                    <label for="userId">Kullanıcı ID:</label>
                    <input type="text" id="userId" name="userId" required><br>

                    <label for="name">Ürün Adı:</label>
                    <input type="text" id="name" name="name" required><br>

                    <div class="category-select">
                        <label for="category" class="category-label">Kategori:</label>
                        <input type="text" id="category" name="category" placeholder="Kategori seçiniz" required>
                        <div class="category-dropdown">
                            <a href="#" onclick="selectCategory('Elektronik')">Elektronik</a>
                            <a href="#" onclick="selectCategory('Moda')">Moda</a>
                            <a href="#" onclick="selectCategory('Ev-Ofis Eşyaları')">Ev-Ofis Eşyaları</a>
                            <a href="#" onclick="selectCategory('Anne, Bebek, Oyuncak')">Anne, Bebek, Oyuncak</a>
                            <a href="#" onclick="selectCategory('Spor, Ayakkabı')">Spor, Ayakkabı</a>
                            <a href="#" onclick="selectCategory('Kozmetik, Kişisel Bakım')">Kozmetik, Kişisel Bakım</a>
                            <a href="#" onclick="selectCategory('Süper-Market, PetShop')">Süper-Market, PetShop</a>
                            <a href="#" onclick="selectCategory('Kitap, Müzik, Film, Hobi')">Kitap, Müzik, Film, Hobi</a>
                            <!-- Ek kategoriler buraya eklenebilir -->
                        </div>
                    </div><br>

                    <label for="quantity">Miktar:</label>
                    <input type="number" id="quantity" name="quantity" min="0" value="0" required><br>

                    <label for="price">Fiyat:</label>
                    <input type="number" id="price" name="price" step="0.01" min="0" value="0" required><br>

                    <button type="submit">Ürün Ekle</button>
                </form>
            </div>
        </section>
    </main>

    <!-- <footer class="footer">
        <div class="container">
            <p>&copy; 2024 UNIQUE. Tüm hakları saklıdır.</p>
        </div>
    </footer> -->

    <script>
        function selectCategory(category) {
            document.getElementById('category').value = category;
        }
    </script>
</body>
</html>
