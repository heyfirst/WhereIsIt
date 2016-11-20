<%-- 
    Document   : post_pending
    Created on : Nov 19, 2016, 5:16:46 PM
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
        <div class="column is-6">
          <div class="card is-fullwidth">
            <div class="card-content">
              <div class="content">
                <h2>FOUNDER</h2>
              </div>
              <div class="media">
                <div class="media-left">
                  <figure class="image is-32x32">
                    <img src="http://placehold.it/64x64" alt="Image">
                  </figure>
                </div>
                <div class="media-content">
                  <p class="title is-5">${founder}</p>
                  <p class="subtitle is-6">@johnsmith</p>
                </div>
              </div>
              <div class="columns">
                <div class="column">
                  <figure class="image is-square">
                    <img src="http://placehold.it/340x225" alt="">
                  </figure>
                </div>
                <div class="column">
                  <figure class="image is-square">
                    <img src="http://placehold.it/340x225" alt="">
                  </figure>
                </div>
                <div class="column">
                  <figure class="image is-square">
                    <img src="http://placehold.it/340x225" alt="">
                  </figure>
                </div>
              </div>
              <div class="content">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                Phasellus nec iaculis mauris.
              </div>
              <hr>
              <div class="content">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                Phasellus nec iaculis mauris.
              </div>
            </div>
            <footer class="card-footer">
              <a class="card-footer-item">Contact</a>
            </footer>
          </div>
        </div>
        <!-- ./End Post -->
        <!-- Post -->
        <div class="column is-6">
          <div class="card is-fullwidth">
            <div class="card-content">
              <div class="content">
                <h2>OWNER</h2>
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
              <div class="columns">
                <div class="column">
                  <figure class="image is-square">
                    <img src="http://placehold.it/340x225" alt="">
                  </figure>
                </div>
                <div class="column">
                  <figure class="image is-square">
                    <img src="http://placehold.it/340x225" alt="">
                  </figure>
                </div>
                <div class="column">
                  <figure class="image is-square">
                    <img src="http://placehold.it/340x225" alt="">
                  </figure>
                </div>
              </div>
              <div class="content">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                Phasellus nec iaculis mauris.
              </div>
              <hr>
              <div class="content">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                Phasellus nec iaculis mauris.
              </div>
            </div>
            <footer class="card-footer">
              <a class="card-footer-item">Contact</a>
            </footer>
          </div>
        </div>
        <!-- ./End Post -->
      </div>
      <div class="columns">
        <div class="column is-4 is-offset-2">
          <button class="button is-danger is-fullwidth  is-medium">Discard</button>
        </div>
        <div class="column is-4">
          <button class="button is-success is-fullwidth  is-medium">Comfirm Post</button>
        </div>
      </div>
    </div>
  </section>
    
    <jsp:include page="../layouts/script_included.jsp"/>
    
  </body>
</html>
