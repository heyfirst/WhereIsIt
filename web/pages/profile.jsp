<%--
    Document   : profile
    Created on : Nov 20, 2016, 11:09:39 PM
    Author     : KS
--%>

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
            <img src="/" alt="" />
          </div>
          <!-- ./End Post -->
          <!-- Content -->
          <div class="column">

          </div>
          <!-- ./End Content -->
        </div>
      </div>
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
                      <a class="card-footer-item modal-button" data-target="#found-item" onclick="chageFoundFormURL(${p.postId})">Found It!</a>
                    </footer>
                  </div>
                </div>
                <!-- ./End Post -->
              </c:if>
            </c:forEach>
          </c:if>
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
