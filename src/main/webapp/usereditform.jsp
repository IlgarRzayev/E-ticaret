<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
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

        table input[type="text"] {
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
        <h1>Edit User</h1>
        <form:form method="POST" action="/usereditsave">    
            <table>    
                <tr>  
                    <td></td>    
                    <td><form:hidden path="id" /></td>  
                </tr>   
                <tr>    
                    <td>Name :</td>   
                    <td><form:input path="name" /></td>  
                </tr>    
                <tr>    
                    <td>Surname :</td>    
                    <td><form:input path="surname" /></td>  
                </tr>   
                <tr>    
                    <td>Email :</td>    
                    <td><form:input path="email" /></td>  
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
