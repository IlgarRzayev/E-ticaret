<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ödeme</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
    <style>
        /* Global styles */
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
        
        h1, h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #FF4B2B;
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
        
        /* Form styles */
        form {
            max-width: 400px;
            margin: 0 auto;
        }
        
        input[type="radio"] {
            margin-right: 10px;
        }
        
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #FF4B2B;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1rem;
        }
        
        input[type="submit"]:hover {
            background-color: #e8432e;
        }
        
        /* Responsive styles */
        @media (max-width: 768px) {
            .container {
                padding: 10px;
            }
        }
    </style>
    
</head>
<body>
    <div class="container">
        <h1>Ödeme Sayfası</h1>
        <c:choose>
            <c:when test="${not empty payment}">
                <table class="table table-bordered">
                    <tr>
                        <th colspan="5">Toplam Fiyat:</th>
                        <td>${totalPrice}</td>
                    </tr>
                </table>
    
                <h2>Ödeme Yöntemi Seçiniz</h2>
                <form action="<%=request.getContextPath()%>/buy" method="post">
                    <div class="form-group">
                        <input type="radio" id="creditCard" name="paymentMethod" value="Credit Card">
                        <label for="creditCard">Kredi Kartı</label><br>
                    </div>
                    <div class="form-group">
                        <input type="radio" id="paypal" name="paymentMethod" value="PayPal">
                        <label for="paypal">PayPal</label><br>
                    </div>
                    <div class="form-group">
                        <input type="radio" id="bankTransfer" name="paymentMethod" value="Bank Transfer">
                        <label for="bankTransfer">Banka Transferi</label><br><br>
                    </div>
                    <input type="submit" value="Ödemeyi Tamamla" class="btn btn-warning">
                </form>
            </c:when>
            <c:otherwise>
                <p>Sepetinizde ürün bulunmamaktadır. Lütfen ürün ekleyin ve tekrar deneyin.</p>
            </c:otherwise>
        </c:choose>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
