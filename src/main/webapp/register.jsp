<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>REGISTER/LOGIN PAGE</title>
      <style>
        * {
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background: #f6f5f7;
            flex-direction: column; /* Updated to column for adding header and footer */
        }

        .header {
            font-size: 36px;
            color: #ff416c; /* Matching form colors */
            margin-bottom: 20px;
            text-align: center;
        }

        .container {
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
            position: relative;
            overflow: hidden;
            width: 768px;
            max-width: 100%;
            min-height: 560px;
        }

        .form-container {
            position: absolute;
            top: 0;
            height: 100%;
            transition: all 0.6s ease-in-out;
            width: 50%;
            padding: 50px;
        }

        .sign-in-container {
            left: 0;
            z-index: 2;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        .container.right-panel-active .sign-in-container {
            display: none;
        }

        .sign-up-container {
            left: 0;
            opacity: 0;
            z-index: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        .container.right-panel-active .sign-up-container {
            transform: translateX(100%);
            opacity: 1;
            z-index: 5;
            animation: show 0.6s;
            display: flex;
        }

        @keyframes show {
            0%, 49.99% {
                opacity: 0;
                z-index: 1;
            }
            50%, 100% {
                opacity: 1;
                z-index: 5;
            }
        }

        .overlay-container {
            position: absolute;
            top: 0;
            left: 50%;
            width: 50%;
            height: 100%;
            overflow: hidden;
            transition: transform 0.6s ease-in-out;
            z-index: 100;
        }

        .container.right-panel-active .overlay-container {
            transform: translateX(-100%);
        }

        .overlay {
            background: #ff416c;
            background: -webkit-linear-gradient(to right, #ff4b2b, #ff416c);
            background: linear-gradient(to right, #ff4b2b, #ff416c);
            background-repeat: no-repeat;
            background-size: cover;
            background-position: 0 0;
            color: #FFFFFF;
            position: relative;
            left: -100%;
            height: 100%;
            width: 200%;
            transform: translateX(0);
            transition: transform 0.6s ease-in-out;
        }

        .container.right-panel-active .overlay {
            transform: translateX(50%);
        }

        .overlay-panel {
            position: absolute;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            padding: 0 40px;
            text-align: center;
            top: 0;
            height: 100%;
            width: 50%;
            transform: translateX(0);
            transition: transform 0.6s ease-in-out;
        }

        .overlay-left {
            transform: translateX(-20%);
        }

        .container.right-panel-active .overlay-left {
            transform: translateX(0);
        }

        .overlay-right {
            right: 0;
            transform: translateX(0);
        }

        .container.right-panel-active .overlay-right {
            transform: translateX(20%);
        }

        button {
            border-radius: 20px;
            border: 1px solid #FF4B2B;
            background-color: #FF4B2B;
            color: #FFFFFF;
            font-size: 12px;
            font-weight: bold;
            padding: 12px 45px;
            letter-spacing: 1px;
            text-transform: uppercase;
            transition: transform 80ms ease-in;
        }

        button:active {
            transform: scale(0.95);
        }

        button:focus {
            outline: none;
        }

        button.ghost {
            background-color: transparent;
            border-color: #FFFFFF;
        }

        h2 {
            margin: 0 0 20px;
        }

        form {
            display: flex;
            flex-direction: column;
            width: 100%;
        }

        form label {
            margin: 10px 0 5px;
        }

        form input {
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        form button {
            margin-top: 10px;
        }

        .social-container {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 10px;
        }

        .social-container a {
            border: 1px solid #ddd;
            border-radius: 50%;
            display: inline-flex;
            justify-content: center;
            align-items: center;
            margin: 0 5px;
            height: 40px;
            width: 40px;
        }

        .social-container a img {
            width: 20px;
            height: 20px;
        }

        .forgot-password {
            color: #a9a9a9;
            font-size: 14px;
            text-decoration: none;
            margin-bottom: 20px;
            display: block;
            text-align: center;
        }

        .forgot-password:hover {
            text-decoration: underline;
        }

        .error {
            color: red;
            font-size: 12px;
        }

        .social-text {
            text-align: center;
            margin: 10px 0 20px 0;
            color: #a9a9a9;
        }

        .footer {
            text-align: center;
            margin-top: 20px;
            color: #a9a9a9;
            font-size: 12px; /* Küçük yazı boyutu */
        }

        .footer .secure-shopping {
            color: #008000;
            margin-bottom: 10px;
        }

        .footer img {
            width: 20px;
            height: 20px;
            vertical-align: middle;
            margin-right: 5px;
        }
    </style>
    </head>
<body>
    <div class="header">UNIQUE</div>
    <div class="container" id="container">
        <div class="form-container sign-up-container">
            <form:form method="post" action="${pageContext.request.contextPath}/save" modelAttribute="user">
                <h2></h2>
                <div class="social-container">
                    <a href="${pageContext.request.contextPath}/auth/google"><img src="https://img.icons8.com/color/48/000000/google-logo.png" alt="Google"></a>
                    <a href="${pageContext.request.contextPath}/auth/apple"><img src="https://img.icons8.com/ios-filled/50/000000/mac-os.png" alt="Apple"></a>
                    <a href="${pageContext.request.contextPath}/auth/microsoft"><img src="https://img.icons8.com/color/48/000000/microsoft.png" alt="Microsoft"></a>
                </div>
                <div class="social-text">veya e-mail ile kayıt ol</div>
                <label for="firstName">Ad:</label>
                <form:input path="name" id="firstName" required="true"/>
                <form:errors path="name" cssClass="error"/>
                <label for="lastName">Soyad:</label>
                <form:input path="surname" id="lastName" required="true"/>
                <form:errors path="surname" cssClass="error"/>
                <label for="email">E-mail:</label>
                <form:input path="email" id="email" required="true"/>
                <form:errors path="email" cssClass="error"/>
                <label for="password">Şifre:</label>
                <form:input type="password" path="password" id="password" required="true"/>
                <form:errors path="password" cssClass="error"/>
                <button type="submit">Kayıt Ol</button>
            </form:form>
        </div>
        <div class="form-container sign-in-container">
            <form>
                <h2></h2>
                <div class="social-container">
                    <a href="${pageContext.request.contextPath}/auth/google"><img src="https://img.icons8.com/color/48/000000/google-logo.png" alt="Google"></a>
                    <a href="${pageContext.request.contextPath}/auth/apple"><img src="https://img.icons8.com/ios-filled/50/000000/mac-os.png" alt="Apple"></a>
                    <a href="${pageContext.request.contextPath}/auth/microsoft"><img src="https://img.icons8.com/color/48/000000/microsoft.png" alt="Microsoft"></a>
                </div>
                <div class="social-text">veya e-mail ile giriş yap</div>
                <label for="email">E-mail:</label>
                <input type="email" id="email" required />
                <label for="password">Şifre:</label>
                <input type="password" id="password" required />
                <a href="#" class="forgot-password">Şifremi Unuttum</a>
                <button type="submit">Giriş Yap</button>
            </form>
        </div>
        <div class="overlay-container">
            <div class="overlay">
                <div class="overlay-panel overlay-left">
                    <h2>Tekrar Hoş Geldiniz!</h2>
                    <p>Alışverişe devam etmek için giriş yapın ve fırsatları kaçırmayın.</p>
                    <button class="ghost" id="signIn">Giriş Yap</button>
                </div>
                <div class="overlay-panel overlay-right">
                    <h2>Merhaba, Hoş Geldiniz!</h2>
                    <p>Kayıt olun ve güvenli alışverişin keyfine şimdi varın.</p>
                    <button class="ghost" id="signUp">Kayıt Ol</button>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">
        <div class="secure-shopping">
            <img src="https://img.icons8.com/fluency/48/000000/security-checked.png" alt="Secure">Güvenli Alışveriş
        </div>
        <div>© Ilgar Rzayev - Ayşenur Eşsiz</div>
    </div>
    <script>
        const signUpButton = document.getElementById('signUp');
        const signInButton = document.getElementById('signIn');
        const container = document.getElementById('container');

        signUpButton.addEventListener('click', () => {
            container.classList.add("right-panel-active");
        });

        signInButton.addEventListener('click', () => {
            container.classList.remove("right-panel-active");
        });
    </script>
</body>
</html>
