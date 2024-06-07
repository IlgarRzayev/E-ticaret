<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
</head>
<body>
    <h2>Add Product</h2>
    <form action="add-product" method="post" enctype="multipart/form-data">
        <label for="name">Product Name:</label>
        <input type="text" id="name" name="name" required><br>
        <label for="category">Category:</label>
        <input type="text" id="category" name="category" required><br>
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" min="0" value="0" required><br>
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" step="0.01" min="0" value="0" required><br>
        <label for="image">Image:</label>
        <input type="file" id="image" name="image" required><br>
        <button type="submit">Add Product</button>
    </form>
</body>
</html>
