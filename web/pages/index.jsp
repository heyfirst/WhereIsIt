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
            <% int sizeOfPost = 0; %>
                <c:if test="${posts != null}">
              <p class="subtitle is-5">
                  <% List<Post> posts = (List<Post>)request.getSession().getAttribute("posts");
                         sizeOfPost = posts.size();
                      %>
                </c:if>
                <strong><%= sizeOfPost %></strong> posts
              </p>
                
            </div>
            <div class="level-item">
             <form action="/WhereIsIt/pages/" method="GET">
              <p class="control has-addons">
                <input class="input" type="text" placeholder="Find a post" name="searchParam">
                <button class="button">
                  Search
                </button>
              </p>
             </form>
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
 <c:if test="${posts != null}">
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
                 <c:choose>
                     <c:when test="${p.user.userId != sessionScope.loggedInUser.userId}">
                        <a class="card-footer-item modal-button" data-target="#found-item" onclick="chageFoundFormURL(${p.postId})">Found It!</a>
                     </c:when>
                 </c:choose>
              </footer>
            </div>
          </div>
          <!-- ./End Post -->
          </c:if>
       </c:forEach>
   </c:if>
        </div>
      </div>
    </section>
    
    <jsp:include page="../layouts/script_included.jsp"/>
    
    <!-- Modal -->
    <div id="found-item" class="modal">
      <div class="modal-background"></div>
      <div class="modal-card">
        <header class="modal-card-head">
          <p class="modal-card-title">I Found it !</p>
          <button class="delete"></button>
        </header>
        <section class="modal-card-body">
          <form id="found" action="PostPending?post_id=${post.postId}" method="post">
            <div class="content">
              <label class="label">ข้อมูลของที่พบ</label>
              <p class="control">
                <textarea class="input" name="found_item" required></textarea>
              </p>
              <label class="label">วันที่เจอ</label>
              <p class="control">
                <input class="input" type="date" name="found_date" required>
              </p>
              <label class="label">เวลาที่เจอ</label>
              <p class="control">
                <input class="input" type="time" name="found_time" required>
              </p>
              <hr>
              <label class="label">สถานที่</label>
              <p class="control">
                <input class="input" type="text" name="found_place" required>
              </p>
              <button class="button is-success is-medium is-fullwidth">ฉันเจอมันแล้ว</button>
            </div>
            </form>
        </section>
      </div>
    </div>
            
            <script>
                function chageFoundFormURL(id){
                    var foundForm = document.getElementById("found");
                    foundForm.setAttribute("action","PostPending?post_id="+id);
                }
            </script>
  </body>
</html>
