<%-- 
    Document   : create_post_success
    Created on : Nov 19, 2016, 5:14:49 PM
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
        <div class="column is-half is-offset-one-quarter">
          <div class="content has-text-centered">
            <h1>Post Successful !</h1>
            <p>ตอนนี้ โพสของหายของคุณได้ถูกประกาศบนเว็บไซต์เรียบร้อยแล้ว</p>
            <a class="button is-danger" href="/WhereIsIt/pages/">
              <span class="icon">
                <i class="fa fa-arrow-left"></i>
              </span>
              <span>Back to Home</span>
            </a>
            <a class="button is-success" href="/WhereIsIt/pages/Post?post_id=${post.postId}">
              <span>Go to Post</span>
              <span class="icon">
                <i class="fa fa-arrow-right"></i>
              </span>
            </a>
          </div>
        </div>
      </div>
    </div>
  </section>
    
    <jsp:include page="../layouts/script_included.jsp"/>
    
  </body>
</html>
