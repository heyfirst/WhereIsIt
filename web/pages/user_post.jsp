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
          <c:if test="${userPost != null}">
              <p class="subtitle is-5">
                  <% List<Post> posts = (List<Post>)request.getAttribute("userPost");
                        int sizeOfPost = posts.size();
                      %>
                <strong><%= sizeOfPost %></strong> posts
          </c:if>
              </p>
            </div>
            <div class="level-item">
                <form action="/WhereIsIt/pages/User" method="get">
              <p class="control has-addons">
                <input class="input" type="text" placeholder="Find a post" name="searchParam">
                <button class="button" >
                  Search
                </button>
              </p>
                </form>
            </div>
          </div>

          <!-- Right side -->
          <div class="level-right">
              <p class="level-item filter ${type == "all" ? "strong is-disabled" : ' ' }"><a href="User?type=all">All</a></p>
              <p class="level-item filter ${type == "pending" ? "strong is-disabled" : ' ' }"><a href="User?type=pending">Pending</a></p>
              <p class="level-item filter ${type == "closed" ? "strong is-disabled" : ' ' }"><a href="User?type=closed">Closed</a></p>
          </div>
        </nav>

        <div class="columns is-multiline">
          <!-- Post -->
 <c:if test="${userPost != null}">
    <c:forEach items="${userPost}" var="up" varStatus="vs">
          <div class="column is-3 ${up.status == 0 ? '' : up.status == 1 ? 'pending' : up.status == 2 ? 'closed' : ''}">
            <div class="card">
              <div class="card-image">
                <figure class="image is-3by2">
                  <c:choose>
                        <c:when test="${up.image[0].imageId == 0}">
                            <img src="..${up.image[0].src}" alt="">
                        </c:when>
                        <c:otherwise>
                            <img src="..${up.image[0].src}" alt="">
                         </c:otherwise>
                  </c:choose>
                </figure>
              </div>
              <div class="card-content">
                <div class="media">
                  <div class="media-left">
                    <figure class="image is-32x32">
                      <c:choose>
                        <c:when test="${up.user.image.imageId == 0}">
                             <img src="..${up.user.image.src}" alt="">
                        </c:when>
                        <c:otherwise>
                            <img src="..${up.user.image.src}" alt="">
                         </c:otherwise>
                      </c:choose>
                    </figure>
                  </div>
                  <div class="media-content">
                    <p class="title is-5">${up.postName}</p>
                    <p class="subtitle is-6">${up.user.fname} ${up.user.lname}</p>
                  </div>
                </div>

                <div class="content">
                  ${up.postDescription}
                </div>
              </div>
              <footer class="card-footer">
                  <% String type = (String)request.getAttribute("type"); %>
                <c:choose>
                    <c:when test="<%= type.equalsIgnoreCase("all") %>">
                        <c:choose>
                            <c:when test="${up.status == 0 || up.status == 2}">
                                <a class="card-footer-item" href="Post?post_id=${up.postId}">${up.status == 0 ? 'See more' : 'Closed'}</a>
                            </c:when>
                            <c:otherwise>
                                <a class="card-footer-item" href="PostPending?post_id=${up.postId}">Pending</a>
                            </c:otherwise>                    
                        </c:choose>
                    </c:when>
                      <c:when test="<%= type.equalsIgnoreCase("closed") %>"> 
                        <a class="card-footer-item" href="Post?post_id=${up.postId}">Closed</a>
                    </c:when>
                    <c:otherwise> 
                        <!-- type pending -->
                        <a class="card-footer-item" href="PostPending?post_id=${up.postId}">Pending</a>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                     <c:when test="${up.user.userId != sessionScope.loggedInUser.userId}">
                        <a class="card-footer-item modal-button" data-target="#found-item" onclick="chageFoundFormURL(${p.postId})">Found It!</a>
                     </c:when>
                 </c:choose>
              </footer>
            </div>
          </div>
          <!-- ./End Post -->
       </c:forEach>
</c:if>
        </div>
      </div>
    </section>
    
    <jsp:include page="../layouts/script_included.jsp"/>
    
  </body>
</html>
