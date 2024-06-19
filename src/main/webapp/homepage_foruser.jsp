<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="homepage.jsp"%>
</head>
<body>
		<style>
			.container {
			    max-width: 1200px;
			    margin: 0 auto;
			    padding: 20px;
			}
			
			.products {
			    display: grid;
			    grid-template-columns: repeat(4, 1fr);
			    grid-gap: 20px;
			}
			
			.product {
			    border: 1px solid #ccc;
			    padding: 10px;
			    text-align: center;
			}
			
			.product img {
			    max-width: 100%;
			    height: auto;
			}
			
			.actions {
			    margin-top: 10px;
			}
			
			
			.actions a {
		    display: inline-block;
		    margin-right: 10px;
		     /* Varsayılan renk: Mavi */
		    text-decoration: none;
		    transition: color 0.3s; /* Renk değişiminde geçiş efekti */
		}
		
		.actions a:hover {
		
		    background-color:gray; /* Hover durumunda farklı bir renk: Koyu mavi */
		}			
		</style>
<body>
    <div class="container">
        <div class="products">
            <c:forEach items="${products}" var="product">
                <div class="product">
                    <h3>${product.name}</h3>
                    <img src="${product.imageUrl}" alt="${product.name}">
                    <div class="actions">
                        <a href="addToCart/${product.id}" class="btn btn-warning">Sepete Ekle</a>
                        <a href="checkout/${product.id}" class="btn btn-dark">Direk Ödeme</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
		
</body>
</html>
