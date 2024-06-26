<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ürün Detayları</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <%@include file="homepage.jsp"%>
    <style>
        /* Global styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }
        
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            margin-top: 20px;
        }
        
        .product-details {
            text-align: center;
        }
        
        .product-img {
            max-width: 100%;
            height: auto;
            margin: 20px 0;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        
        .quantity-input {
            width: 70px;
            text-align: center;
        }
        
        .actions {
            margin-top: 20px;
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
        <form class="product-details" method="get">
            <h3>${product.name}</h3>
            <img src="${product.imageUrl}" alt="${product.name}" class="product-img">
   			<input type='hidden' name="productId" value="${product.getProductId()}"/>
            <label for="quantity">Miktar:</label>
            <input type="number" id="quantity" name="quantity" value="1" class="quantity-input" min="1" required/>
            
            <p id="totalPrice" class="mt-2">Toplam Fiyat: $${product.price}</p>
            
            <div class="btn-group actions" role="group" aria-label="Ürün İşlemleri">
                <button type='submit' class="btn btn-warning" formaction="addToCart">Sepete Ekle</button> 
            </div>
        </form>
    </div>
    
    <script>
        $(document).ready(function() {
            var price = ${product.price};
            
            // Update total price when quantity changes
            $('#quantity').on('input', function() {
                var quantity = parseInt($(this).val());
                if (isNaN(quantity) || quantity < 1) {
                    quantity = 1;
                    $(this).val(1);
                }
                var totalPrice = price * quantity;
                $('#totalPrice').text('Toplam Fiyat: $' + totalPrice.toFixed(2));
            });
        });
    </script>
</body>
</html>
