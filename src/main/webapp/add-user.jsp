<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add User</title>
</head>
<body>
    <h2>Add User</h2>
    		<a href="viewuser">View Users</a>
            <form action="save" method="post" >
		        <label for="name">Ad:</label>
		        <input type="text" id="name" name="name" required><br>
		        <label for="lastName">Soyisim:</label>
		        <input type="text" id="lastName" name="surname" required><br>
		        <label for="email">Email:</label>
		        <input type="email" id="email" name="email" required><br>
		        <label for="password">Şifre:</label>
		        <input type="password" id="password" name="password"><br>
		        <button type="submit">Müşteri Ekle</button>
		    </form>    
</body>
</html>
