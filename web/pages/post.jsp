<%--
    Document   : post
    Created on : Nov 19, 2016, 5:09:39 PM
    Author     : KS
--%>

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

    <section class="section">
      <div class="container">
        <div class="columns">
          <!-- Post -->
          <div class="column is-3">
            <div class="card">
              <div class="card-image">
                <figure class="image is-3by2">
                  <img src="..${post.image[0].src}" alt="">
                </figure>
              </div>
              <div class="card-content">
                <div class="content">
                  <h2>${post.postName}</h2>
                </div>
                <div class="content">
                    <p id="detail">${post.postDescription}</p>
                </div>
                <div class="media">
                  <div class="media-left">
                    <figure class="image is-32x32">
                      <img src="..${post.user.image.src}" alt="Image">
                    </figure>
                  </div>
                  <div class="media-content">
                    <p class="title is-5">${post.user.fname} ${post.user.lname}</p>
                    <p class="subtitle is-6">${post.user.email}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- ./End Post -->
          <!-- Content -->
          <div class="column">
            <div class="panel">
              <p class="panel-heading">
                Details
              </p>
              <div class="panel-block">
                <div class="content">
                   <p>ชื่อของหาย : ${post.postName}</p>
                   <p>เวลาที่หาย : ${post.lost_time}</p>
                   <p>ละติจูด : ${post.lat}</p>
                   <p>ลองติจูด : ${post.lon}</p>
                   <p>คำอธิบายเพิ่มเติม : ${post.postDescription}</p>
                    <hr>
                  <p>ชื่อ : ${post.user.fname} ${post.user.lname}</p>
                  <p>เพศ : ${post.user.gender == 0 ? 'Female' : 'Male'} </p>
                  <p>เบอร์โทรศัพท์ : ${post.user.tel} </p>
                  <p>คณะ : ${post.user.faculty} </p>
                  <p>ที่อยู่ : ${post.user.address == null ? 'ไม่ได้ระบุที่อยู่ไว้' : post.user.address} </p>
                </div>
              </div>
                 <c:if test="${post.lat != 0.00 && post.lon != 0.00}">
                           <div class="panel-block">
                            <div class="content">
                                 <div id="map" data-lat="${post.lat}" data-lng="${post.lon}"></div>
                            </div>
                          </div>
                 </c:if>
              
              <div class="panel-block">
                  <c:choose>
                      <c:when test="${sessionScope.loggedInUser == null}">
                          <button class="button is-danger is-outlined is-fullwidth is-medium modal-button" data-target="#login">
                              I found it!
                        </button>
                      </c:when>
                      <c:otherwise>
                          <button class="button is-danger is-outlined is-fullwidth is-medium modal-button" data-target="#found-item" ${post.user.userId == sessionScope.loggedInUser.userId ? 'disabled' : ''}>
                                ${(post.user.userId == sessionScope.loggedInUser.userId && post.status == 2) ? 'Closed' : 'Waiting'} 
                           </button>
                      </c:otherwise>
                  </c:choose>
                
              </div>
            </div>
          </div>
          <!-- ./End Content -->
        </div>
      </div>
    </section>

    <jsp:include page="../layouts/script_included.jsp"/>
    <script type="text/javascript" src="../assets/js/map.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDiS9UZeqzWSIFgKVE_ooOllVLPT02dcEU&callback=initMap"
    async defer></script>

    <!-- Modal -->
    <div id="found-item" class="modal">
      <div class="modal-background"></div>
      <div class="modal-card">
        <header class="modal-card-head">
          <p class="modal-card-title">I Found it !</p>
          <button class="delete"></button>
        </header>
        <section class="modal-card-body">
          <form action="PostPending?post_id=${post.postId}" method="post">
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

  </body>
</html>
