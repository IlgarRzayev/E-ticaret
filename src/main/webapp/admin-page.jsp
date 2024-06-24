<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel - UNIQUE</title>
    <link rel="stylesheet" >
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
		    padding: 1rem 0;
		    color: #fff;
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
		
		.admin-options {
		    background-color: #fff;
		    padding: 1rem;
		    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
		}
		
		.admin-options h2 {
		    color: #222;
		    margin-bottom: 1rem;
		}
		
		.admin-links {
		    display: flex;
		    justify-content: center;
		}
		
		.admin-link {
		    background-color: #FFA500;
		    color: #fff;
		    padding: 0.5rem 1rem;
		    margin: 0 10px;
		    text-decoration: none;
		}
		
		.admin-link:hover {
		    background-color: #f27000;
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
            <h1 class="logo">UNIQUE Admin Panel</h1>
            <nav>
                <ul class="nav-links">
                    <li><a href="viewuser">Kullanıcılar</a></li>
                    <li><a href="viewproduct">Ürünler</a></li>
                    <li><a href="vieworder">Siparişler</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <main class="main-content">
        <section class="admin-options">
            <div class="container">
                <h2>Yönetim Paneli</h2>
                <div class="admin-links">
                    <a href="viewuser" class="admin-link">Kullanıcılar</a>
                    <a href="viewproduct" class="admin-link">Ürünler</a>
                    <a href="vieworder" class="admin-link">Siparişler</a>
                </div>
            </div>
        </section>
    </main>

    <footer class="footer">
        <div class="container">
            <p>&copy;  2024 UNIQUE. Tüm hakları saklıdır.</p>
        </div>
    </footer>
</body>
</html>
