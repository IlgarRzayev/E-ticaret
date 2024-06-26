<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.beans.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px 0;
        }

        h1 {
            color: #333;
            text-align: center;
        }

        table {
            width: 50%;
            margin: 0 auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        table td, table th {
            padding: 10px;
            text-align: left;
            border: 1px solid #ccc;
        }

        table th {
            background-color: #FFA500;
            color: #fff;
        }

        table input[type="text"], table input[type="number"] {
            padding: 8px;
            width: 100%;
            box-sizing: border-box;
        }

        input[type="submit"] {
            padding: 10px 20px;
            background-color: #FFA500;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #f27000;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Edit Product</h1>
        <form:form method="POST" action="/producteditsave" >    
            <table>    
                <tr>  
                    <td></td>    
                    <td><form:hidden path="productId" /></td>  
                </tr>   
                <tr>    
                    <td>Ürün İsmi :</td>   
                    <td><form:input path="name" /></td>  
                </tr>    
                <tr>    
                    <td>Miktar :</td>    
                    <td><form:input path="quantity" min="0" value="0" /></td>  
                </tr>   
                <tr>    
                    <td>Fiyat :</td>    
                    <td><form:input path="price" type="number" step="0.01" /></td>  
                </tr>   
                <tr>    
                    <td></td>    
                    <td><input type="submit" value="Edit Save" /></td>    
                </tr>    
            </table>    
        </form:form>
    </div>
</body>
</html>
