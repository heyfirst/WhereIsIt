<%-- 
    Document   : dashboard
    Created on : Oct 8, 2016, 11:26:30 PM
    Author     : Huag
--%>

<%@page import="java.util.List"%>
<%@page import="model.Post"%>
<%@page import="model.Post"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page   contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
<!--        <meta http-equiv="Content-Type" content="text/html; charset="UTF-8">-->
        <title>dash board</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <FORM action="DashBoardServlet" method="post">
            <input type="text" name="searchParam">
            <input type="submit" value="Search">
        </FORM>
        <h1>POST</h1>
        <%= request.getParameter("searchParam")%>
        <c:forEach items="${listPost}" var="post"  varStatus="theCountPost" >
            
            <div style="border: 3px solid black">
                    PostName :  ${post.postName} <br>
                    Tag Name : 
                    <c:forEach items="${listPost[theCountPost.index].tag}" var="tag" varStatus="theCountTag">
                        ${listPost[theCountPost.index].tag[theCountTag.index].tagName}
                    </c:forEach>
                <br>
                postDescript : ${post.postDescription} <br>
        </div>
            </c:forEach>
        
    </body>
</html>
