<%--
    Document   : post
    Created on : Nov 19, 2016, 5:09:39 PM
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
            <div class="card">
              <div class="card-image">
                <figure class="image is-3by2">
                  <img src="http://placehold.it/225x225" alt="">
                </figure>
              </div>
              <div class="card-content">
                <div class="content">
                  <h2>MACBOOK AIR 2015</h2>
                </div>
                <div class="content">
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                  Phasellus nec iaculis mauris.
                </div>
                <div class="media">
                  <div class="media-left">
                    <figure class="image is-32x32">
                      <img src="http://placehold.it/64x64" alt="Image">
                    </figure>
                  </div>
                  <div class="media-content">
                    <p class="title is-5">John Smith</p>
                    <p class="subtitle is-6">@johnsmith</p>
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
                  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sed, nemo officiis perspiciatis voluptas ut, fugit eaque? Nesciunt laboriosam, blanditiis suscipit, minima similique debitis aspernatur exercitationem animi unde expedita ipsa nemo!</p>
                </div>
              </div>
              <div class="panel-block">
                <div class="content">
                  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sed, nemo officiis perspiciatis voluptas ut, fugit eaque? Nesciunt laboriosam, blanditiis suscipit, minima similique debitis aspernatur exercitationem animi unde expedita ipsa nemo!</p>
                </div>
              </div>
              <div class="panel-block">
                <div class="content">
                  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sed, nemo officiis perspiciatis voluptas ut, fugit eaque? Nesciunt laboriosam, blanditiis suscipit, minima similique debitis aspernatur exercitationem animi unde expedita ipsa nemo!</p>
                </div>
              </div>
              <div class="panel-block">
                <button class="button is-danger is-outlined is-fullwidth is-medium modal-button" data-target="#found-item">
                  I Found It !
                </button>
              </div>
            </div>
          </div>
          <!-- ./End Content -->
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
          <div class="content">
            <label class="label">ข้อมูลของที่พบ</label>
            <p class="control">
              <textarea class="input"></textarea>
            </p>
            <label class="label">วันที่เจอ</label>
            <p class="control">
              <input class="input" type="date">
            </p>
            <label class="label">เวลาที่เจอ</label>
            <p class="control">
              <input class="input" type="time">
            </p>
            <hr>
            <label class="label">สถานที่</label>
            <p class="control">
              <input class="input" type="text">
            </p>
            <label class="label">ที่อยู่</label>
            <p class="control">
              <input class="input" type="text">
            </p>
            <a class="button is-success is-medium is-fullwidth">ฉันเจอมันแล้ว</a>
          </div>
        </section>
      </div>
    </div>

  </body>
</html>
