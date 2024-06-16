<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UNIQUE E-Ticaret</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        .main-navbar {
            border-bottom: 1px solid #ccc;
        }

        .main-navbar .top-navbar {
            background-color: #333;
            padding-top: 1px;
            padding-bottom: 1px;
        }

        .main-navbar .top-navbar .navbar-brand {
            padding-left: 1px;
            color: #fff;
            font-size: 24px; /* Navbar başlığı için font boyutunu ayarlıyoruz */
            font-weight: bold;
        }

        .main-navbar .top-navbar .nav-links {
            color: #fff;
            font-size: 16px; /* Linklerin boyutunu artırıyoruz */
            font-weight: 500;
            text-decoration: none;
        }
        .main-navbar .top-navbar .nav-links:hover {
            color: gray;
        }

        .main-navbar .categories-navbar {
            background-color: #ddd;
            padding: 0;
        }

        .main-navbar .categories-navbar .nav-item .nav-link {
            color: black;
            font-size: 15px;
        }

        .main-navbar .categories-navbar .nav-item .nav-link:hover {
            color: orange;
            background-color: #fff;
        }

        .main-navbar .categories-navbar .nav-item,
        .main-navbar .categories-navbar .dropdown-menu {
            margin: 0;
            padding: 0;
        }

        .main-navbar .top-navbar .navbar-nav.ml-auto .nav-item {
            margin-left: 10px; /* Sağ üstteki linkler arasında bir boşluk sağlar */
        }

        .main-navbar .top-navbar .navbar-nav.ml-auto .nav-item .nav-link {
            color: #fff;
            font-size: 18px; /* Linklerin boyutunu artırıyoruz */
            font-weight: 500;
        }

        .main-navbar .top-navbar .navbar-nav.ml-auto .nav-item .nav-link:hover {
            color: orange;
            background-color: transparent;
        }

        @media only screen and (max-width: 600px) {
            .main-navbar .top-navbar .nav-links {
                font-size: 12px;
                padding: 8px 10px;
            }
            .main-navbar .top-navbar .navbar-brand {
                font-size: 18px; /* Küçük ekranlarda logo boyutunu küçültüyoruz */
            }
        }
    </style>
</head>
<body>

    <div class="main-navbar shadow-sm sticky-top">
        <div class="top-navbar">
            <div class="container-fluid">
                <nav class="navbar navbar-expand-lg navbar-dark">
                    <a class="navbar-brand" href="#">UNIQUE</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#topNavbar" aria-controls="topNavbar" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="topNavbar">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item">
                                <a class="nav-links" href="profile.jsp"><i class="fas fa-user"></i> Profil</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-links" href="#"><i class="fas fa-shopping-cart"></i> Sepet</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-links" href="#"><i class="fas fa-credit-card"></i> Ödeme</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-links" href="register"><i class="fas fa-sign-in-alt"></i> Giriş/Kayıt</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
        <nav class="navbar navbar-expand-sm categories-navbar">
            <div class="container-fluid">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#categoriesNav" aria-controls="categoriesNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="categoriesNav">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="#">Elektronik</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Moda</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Ev-Ofis Eşyaları</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Anne, Bebek, Oyuncak</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Spor, Ayakkabı</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Kozmetik, Kişisel Bakım</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Süper-Market, PetShop</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Kitap, Müzik, Film, Hobi</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

