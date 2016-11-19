<%-- 
    Document   : sample_page
    Created on : Oct 6, 2016, 3:28:42 PM
    Author     : KS
--%>


<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="util.ConnectionBuilder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="../layouts/head_included.jsp"/>
  </head>
  <body>
    <h1>Where Is It !</h1>
    <%
            Connection con = ConnectionBuilder.getMySqlCond();
            PreparedStatement pstmt = con.prepareStatement("select * from wil_tag");
            ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    int a = rs.getInt(1); 
                    String t = rs.getString(2);
     %>
            <%= a %>  <br> <%= t %> 
       <%         }
%>
    
    <jsp:include page="../layouts/script_included.jsp"/>
  </body>
</html>
