<%-- 
    Document   : homepage
    Created on : Oct 23, 2016, 10:14:07 PM
    Author     : pingpongsz
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Homepage</h1>
        <h1>Logged in as ${loggedInUser.fname} ${loggedInUser.lname}</h1>
        <h1>Email : ${loggedInUser.email}</h1>
        <h1>${message}</h1>
        
    <c:if test="${loggedInUser != null}">
        <form action="/WhereIsIt/Logout" method="GET">
            <input type="submit" value="Logout">
        </form>
    </c:if>

    </body>
</html>
