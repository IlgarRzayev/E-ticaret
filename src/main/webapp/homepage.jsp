<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UNIQUE Homepage</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        .main-navbar {
            border-bottom: 1px solid #ccc;
        }

        .main-navbar .top-navbar {
            background-color: #FF4B2B;
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
            text-align: center; /* Kategorileri ortalamak için metin hizalaması ekliyoruz */
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

        .search-form {
            display: flex;
            align-items: center;
        }

        .search-form input[type="text"] {
            padding: 8px;
            border-radius: 20px;
            border: none;
            outline: none;
            font-size: 14px;
        }

        .search-form button {
            background-color: transparent;
            border: none;
            cursor: pointer;
            margin-left: 5px;
        }

        .search-form button:hover {
            color: orange;
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

        .dropdown:hover .dropdown-menu {
            display: block;
        }

        .dropdown-menu {
            width: 100%; /* Genişliğini ayarlayın */
            text-align: left; /* Metin hizalamasını sola alın */
        }

        .dropdown-item {
            white-space: normal; /* Metni satır kaydırmaya zorlar */
        }
    </style>
</head>
<body>

    <div class="main-navbar shadow-sm sticky-top">
        <div class="top-navbar">
            <div class="container-fluid">
                <nav class="navbar navbar-expand-lg navbar-dark">
                    <a class="navbar-brand" href="#">UNIQUE</a>
                    <form class="search-form ml-auto">
                        <input type="text" placeholder="Ara...">
                        <button type="submit"><i class="fas fa-search"></i></button>
                    </form>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#topNavbar" aria-controls="topNavbar" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="topNavbar">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item">
                                <a class="nav-links" href="profile"><i class="fas fa-user"></i> Profil</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-links" href="orders"><i class="fas fa-box"></i> Siparişler</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-links" href="cart"><i class="fas fa-shopping-cart"></i> Sepet</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-links" href="checkout"><i class="fas fa-credit-card"></i> Ödeme</a>
                            </li>
                            <%
							if (session.getAttribute("loggedInUser") != null) {
							%>
							<li class="nav-item"><a class="nav-links" href="logout"><i
									class="fas fa-sign-out-alt"></i> Çıkış</a></li>
							<%
							} else {
							%>
							<li class="nav-item">
                                <a class="nav-links" href="register"><i class="fas fa-sign-in-alt"></i> Giriş/Kayıt</a>
                            </li>	<%
								}
								%>
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
                    <ul class="navbar-nav mx-auto">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="electronicsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Elektronik
                            </a>
                            <div class="dropdown-menu" aria-labelledby="electronicsDropdown">
                                <a class="dropdown-item" href="#">TV, Ses ve Görüntü Sistemleri</a>
                                <a class="dropdown-item" href="#">Bilgisayar, Tablet</a>
                                <a class="dropdown-item" href="#">Telefon</a>
                                <a class="dropdown-item" href="#">Beyaz Eşya</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="fashionDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Moda
                            </a>
                            <div class="dropdown-menu" aria-labelledby="fashionDropdown">
                                <a class="dropdown-item" href="#">Kadın</a>
                                <a class="dropdown-item" href="#">Erkek</a>
                                <a class="dropdown-item" href="#">Çocuk</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="homeOfficeDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Ev-Ofis Eşyaları
                            </a>
                            <div class="dropdown-menu" aria-labelledby="homeOfficeDropdown">
                                <a class="dropdown-item" href="#">Mobilya</a>
                                <a class="dropdown-item" href="#">Dekorasyon</a>
                                <a class="dropdown-item" href="#">Ev Tekstili</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="babyToysDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Anne, Bebek, Oyuncak
                            </a>
                            <div class="dropdown-menu" aria-labelledby="babyToysDropdown">
                                <a class="dropdown-item" href="#">Oyuncaklar</a>
                                <a class="dropdown-item" href="#">Bebek Arabaları</a>
                                <a class="dropdown-item" href="#">Emzirme Ürünleri</a>
                                <a class="dropdown-item" href="#">Bebek Bezi & Islak Mendil</a>
                                <a class="dropdown-item" href="#">Bebek Odası & Güvenlik</a>
                                <a class="dropdown-item" href="#">Bebek Bakım & Banyo & Sağlık</a>
                                <a class="dropdown-item" href="#">Giyim</a>
                                <a class="dropdown-item" href="#">Yurt Dışından</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="sportsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Spor, Outdoor
                            </a>
                            <div class="dropdown-menu" aria-labelledby="sportsDropdown">
                                <a class="dropdown-item" href="#">Spor Giyim</a>
                                <a class="dropdown-item" href="#">Spor Ayakkabı</a>
                                <a class="dropdown-item" href="#">Spor Ekipmanları</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="cosmeticsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Kozmetik, Kişisel Bakım
                            </a>
                            <div class="dropdown-menu" aria-labelledby="cosmeticsDropdown">
                                <a class="dropdown-item" href="#">Makyaj</a>
                                <a class="dropdown-item" href="#">Cilt Bakımı</a>
                                <a class="dropdown-item" href="#">Saç Bakımı</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="supermarketDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Süper-Market, PetShop
                            </a>
                            <div class="dropdown-menu" aria-labelledby="supermarketDropdown">
                                <a class="dropdown-item" href="#">Gıda</a>
                                <a class="dropdown-item" href="#">İçecek</a>
                                <a class="dropdown-item" href="#">Ev Temizliği</a>
                                <a class="dropdown-item" href="#">Evcil Hayvan</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="booksDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Kitap, Müzik, Film, Hobi
                            </a>
                            <div class="dropdown-menu" aria-labelledby="booksDropdown">
                                <a class="dropdown-item" href="#">Kitap</a>
                                <a class="dropdown-item" href="#">Müzik</a>
                                <a class="dropdown-item" href="#">Film</a>
                                <a class="dropdown-item" href="#">Hobi</a>
                            </div>
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
