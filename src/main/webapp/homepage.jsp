<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UNIQUE E-Ticaret</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet">
    <style>
        .main-navbar {
            border-bottom: 1px solid #ccc;
             /* Arka plan rengini değiştiriyoruz */
        }

        .main-navbar .top-navbar {
            background-color: #333;
            padding-top: 5px;
            padding-bottom: 5px;
        }

        .main-navbar .top-navbar .navbar-brand {
            padding-left: 10px;
            color: #fff;
            font-size: 24px; /* Navbar başlığı için font boyutunu ayarlıyoruz */
        }

        .main-navbar .top-navbar .nav-links {
            color: #fff;
            font-size: 16px;
            font-weight: 500;
            text-decoration: none;
        }
        .main-navbar .top-navbar .nav-links:hover{
            color: orange;
        }

        .main-navbar .navbar {
            background-color: #ddd;
            padding: 0;
        }

        .main-navbar .navbar .nav-item .nav-link {
            color: black;
            font-size: 15px;
        }

        .main-navbar .navbar .nav-item .nav-link {
            color: black;
            font-size: 15px;
        }

        .main-navbar .navbar .nav-item .nav-link:hover {
            color: orange;
            background-color: #fff;
        }

        .main-navbar .navbar .dropdown-menu .dropdown-item {
            color: orange;
            
        }

        .main-navbar .navbar .dropdown-menu .dropdown-item:hover {
            background-color: transparent;
        }


        .main-navbar .navbar .nav-item,
        .main-navbar .navbar .dropdown-menu {
            margin: 0;
            padding: 0;
        }
        .main-navbar .top-navbar .navbar-nav.ml-auto .nav-item {
            margin-left: 10px; /* Sağ üstteki linkler arasında bir boşluk sağlar */
        }

        .main-navbar .top-navbar .navbar-nav.ml-auto .nav-item .nav-link {
            color: #fff;
            font-size: 16px;
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
}

    </style>
</head>
<body>

    <div class="main-navbar shadow-sm sticky-top">
        <div class="top-navbar">
            <div class="container-fluid">
                <div class="row"> 
                    <div class="col-md-2 my-auto d-none d-sm-none d-md-block d-lg-block">
                        <a class="navbar-brand mr-4" href="#">UNIQUE</a>
                    </div>

                    <div class="col-md-10 my-auto">
                        <div class="col-md-3 my-auto ml-auto">
                            <div class="d-flex justify-content-end">
                                <ul class="nav">
                                    <li class="nav-items">
                                        <a class="nav-links mr-3" href="#"><i class="fas fa-user"></i> Profil</a>
                                    </li>
                                    <li class="nav-items">
                                        <a class="nav-links mr-3" href="#"><i class="fas fa-shopping-cart"></i> Sepet</a>
                                    </li>
                                    <li class="nav-items">
                                        <a class="nav-links" href="#"><i class="fas fa-credit-card"></i> Ödeme</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        
                
                    </div>
                    
                </div>
            </div>
        </div>
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#categoriesNav" aria-controls="categoriesNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="categoriesNav">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item col-2">
                            <a class="nav-link" href="#">Elektronik</a>
                        </li>
                        <hr class="d-md-none">
                        <li class="nav-item col-2">
                            <a class="nav-link" href="#">Moda</a>
                        </li>
                        <hr class="d-md-none">
                        <li class="nav-item col-2">
                            <a class="nav-link" href="#">Ev-Ofis Eşyaları</a>
                        </li>
                        <hr class="d-md-none">
                        <li class="nav-item col-2">
                            <a class="nav-link" href="#">Anne, Bebek, Oyuncak</a>
                        </li>
                        <hr class="d-md-none">
                        <li class="nav-item col-2">
                            <a class="nav-link" href="#">Spor, Ayakkabı</a>
                        </li>
                        <hr class="d-md-none">
                        <li class="nav-item col-2">
                            <a class="nav-link" href="#">Kozmetik, Kişisel Bakım</a>
                        </li>
                        <hr class="d-md-none">
                        <li class="nav-item col-2">
                            <a class="nav-link" href="#">Süper-Market, PetShop</a>
                        </li>
                        <hr class="d-md-none">
                        <li class="nav-item col-2">
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
