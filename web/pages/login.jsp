<%-- 
    Document   : login
    Created on : Oct 24, 2016, 1:04:24 PM
    Author     : pingpongsz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
            <link rel="stylesheet" href="../template/assets/css/bulma.css">
    <link rel="stylesheet" href="../template/assets/css/font-awesome.min.css">
    </head>
    <body>
        <h1>Login Test Page!</h1><br>
        <form action="/WhereIsIt/Login" method="POST">
            Email: <input type="text" name="email" required><br>
            Password: <input type="text" name="password" required><br>
            <input type="submit" value="Login">
        </form>
        <h3>${message}</h3>
    </body>
</html>
