<%--
    Document   : index
    Created on : Nov 19, 2016, 4:42:51 PM
    Author     : KS
--%>
<%@page import="java.util.List"%>
<%@page import="model.Post"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="../layouts/head_included.jsp"/>

  </head>
  <body>
    <!-- Navigator Bar -->
    <jsp:include page="../layouts/navbar_included.jsp"/>
    
    <!-- Main Container -->
    <section class="section">
      <div class="container">
        <nav class="level">
          <!-- Left side -->
          <div class="level-left">
            <div class="level-item">
              <p class="subtitle is-5">
                  <% List<Post> posts = (List<Post>)request.getSession().getAttribute("posts");
                        int sizeOfPost = posts.size();
                      %>
                <strong><%= sizeOfPost %></strong> posts
              </p>
            </div>
            <div class="level-item">
              <p class="control has-addons">
                <input class="input" type="text" placeholder="Find a post">
                <button class="button">
                  Search
                </button>
              </p>
            </div>
          </div>

          <!-- Right side -->
          <div class="level-right">
            <p class="level-item"><strong>All</strong></p>
            <p class="level-item"><a>Published</a></p>
            <p class="level-item"><a>Drafts</a></p>
            <p class="level-item"><a>Deleted</a></p>
            <p class="level-item"><a class="button is-success">New</a></p>
          </div>
        </nav>

        <div class="columns is-multiline">
          <!-- Post -->
    <c:forEach items="${posts}" var="p" varStatus="vs">
        <c:if test="${p.status == 0}">
          <div class="column is-3">
            <div class="card">
              <div class="card-image">
                <figure class="image is-3by2">
                  <img src="http://placehold.it/225x225" alt="">
                </figure>
              </div>
              <div class="card-content">
                <div class="media">
                  <div class="media-left">
                    <figure class="image is-32x32">
                      <img src="http://placehold.it/64x64" alt="Image">
                    </figure>
                  </div>
                  <div class="media-content">
                    <p class="title is-5">${p.postName}</p>
                    <p class="subtitle is-6">${p.user.fname} ${p.user.lname}</p>
                  </div>
                </div>

                <div class="content">
                  ${p.postDescription}
                </div>
              </div>
              <footer class="card-footer">
                <a class="card-footer-item" href="Post?post_id=${p.postId}">See more.</a>
                <a class="card-footer-item">Found It!</a>
              </footer>
            </div>
          </div>
          <!-- ./End Post -->
          </c:if>
       </c:forEach>
        </div>
      </div>
    </section>
    
    <jsp:include page="../layouts/script_included.jsp"/>
    
  </body>
</html>
