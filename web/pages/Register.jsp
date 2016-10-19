<%-- 
    Document   : Register
    Created on : Oct 18, 2016, 11:00:26 PM
    Author     : Huag
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Register</h1>
     
        <form action="RegisterServlet" method="post"> 
            POST NAME : <input type="text" name="postName"> <br>
            <c:forEach items="${listTag}" var="listTag"  varStatus="count" >
                  <input type="checkbox" name="tag" value="${listTag.tagId}">${listTag.tagName} <br>
            </c:forEach>
              Latitute : <input type="text" name="lat"><br>
              Longitute : <input type="text" name="lon"> <br>
              POST Description : <textarea  name="postDescription"></textarea><br>
              <!--  Assume that the client upload  image of post-->
              Image : <input type="file" name="postImage" multiple ><br>
              <input type="submit" value="add">
        </form>
        
              <h1> Insert Success : <%= request.getAttribute("success")%></h1>
              <ul>
                  <li> postName : <%= request.getAttribute("postName")%> </li>
         
                      <li>Choose tag : <c:forEach items="${tag}" var="tag"  varStatus="count" >
                                                  ${tag}
                                      </c:forEach>
                  <li> description : <%= request.getAttribute("postDescription")%></li>
                  <li> postImage :  <%= request.getAttribute("postImage")%>  </li>

                     

                     <li>check : <%= request.getAttribute("check")%></li>
                     <li>message : <%= request.getAttribute("message")%></li>
                     
              </ul>
       
    </body>
</html>
