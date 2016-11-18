<%-- 
    Document   : post
    Created on : Nov 17, 2016, 2:01:21 AM
    Author     : Huag
--%>
 <link rel="stylesheet" href="../template/assets/css/bulma/css/bulma.css">
  <link rel="stylesheet" href="../template/assets/css/font-awesome.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Where Is It | Lost and Found Website.</title>
  <link rel="stylesheet" href="./assets/css/bulma/css/bulma.css">
  <link rel="stylesheet" href="./assets/css/font-awesome.min.css">
</head>
<body>
  <section class="hero is-primary is-medium">
    <!-- Hero header: will stick at the top -->
    <div class="hero-head">
      <header class="nav">
        <div class="container">
          <div class="nav-left">
            <a class="nav-item">
              <h1>Where Is It</h1>
            </a>
          </div>
          <span class="nav-toggle">
            <span></span>
            <span></span>
            <span></span>
          </span>
          <div class="nav-right nav-menu">
            <a class="nav-item is-active">
              หน้าแรก
            </a>
            <a class="nav-item">
              ของที่หายของฉัน
            </a>
            <a class="nav-item">
              ข้อมูลส่วนตัวของฉัน
            </a>
            <span class="nav-item">
              <a class="button is-primary is-inverted">
                <span class="icon">
                  <i class="fa fa-user"></i>
                </span>
                <span>Login</span>
              </a>
            </span>
          </div>
        </div>
      </header>
    </div>

    <!-- Hero content: will be in the middle -->
    <div class="hero-body">
      <div class="container has-text-centered">
        <h1 class="title">
          Where Is It
        </h1>
        <h2 class="subtitle">
          Lost & Found Application
        </h2>
      </div>
    </div>

    <!-- Hero footer: will stick at the bottom -->
    <div class="hero-foot">
      <nav class="tabs is-boxed is-fullwidth">
        <div class="container">
          <ul>
            <li><a>รายการของหายในระบบ</a></li>
            <li class="is-active"><a>ประกาศหาของหาย</a></li>
          </ul>
        </div>
      </nav>
    </div>
  </section>

    <!-- Main container -->
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
                <h2>${post.postName}</h2>
              </div>
              <div class="content">
                ${post.postDescription}
              </div>
              <div class="media">
                <div class="media-left">
                  <figure class="image is-32x32">
                    <img src="http://placehold.it/64x64" alt="Image">
                  </figure>
                </div>
                <div class="media-content">
                  <p class="title is-5">${post.postName}</p>
                  <p class="subtitle is-6">${post.user.fname} ${post.user.lname}</p>
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
                <p>${post.postDescription}</p>
              </div>
            </div>
            <div class="panel-block">
              <button class="button is-danger is-outlined is-fullwidth is-medium">
                I Found It !
              </button>
            </div>
          </div>
        </div>
        <!-- ./End Content -->
      </div>
    </div>
  </section>
  <footer class="footer">
    <div class="container">
      <div class="content has-text-centered">
        <p>
          <strong>Where Is It</strong> made with <i class="icon fa fa-heart"></i> by KS PP HG JN BZ.
          <br>
          The source code is licensed
          <a href="http://opensource.org/licenses/mit-license.php">MIT</a>. The website content
          is licensed <a href="http://creativecommons.org/licenses/by-nc-sa/4.0/">CC ANS 4.0</a>.
        </p>
        <p>
          <a class="icon" href="https://github.com/jgthms/bulma">
            <i class="fa fa-github"></i>
          </a>
        </p>
      </div>
    </div>
  </footer>
</body>
</html>
