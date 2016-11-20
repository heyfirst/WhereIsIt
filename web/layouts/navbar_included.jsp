<%@page import="model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section class="hero is-primary is-medium">
  <!-- Hero header: will stick at the top -->
  <div class="hero-head">
    <header class="nav">
      <div class="container">
        <div class="nav-left">
          <a class="nav-item" href="/WhereIsIt/pages/">
            <h1>Where Is It</h1>
          </a>
        </div>
        <span class="nav-toggle">
          <span></span>
          <span></span>
          <span></span>
        </span>
        <div class="nav-right nav-menu">
          <a class="nav-item is-active" href="/WhereIsIt/pages/">
            หน้าแรก
          </a>
          <c:choose>
              <c:when test="${sessionScope.loggedInUser != null}">
                <a class="nav-item" href="/WhereIsIt/pages/User">
                  ข้อมูลส่วนตัวของฉัน
                </a>
               </c:when>
              <c:otherwise>
                  <a class="nav-item" >
                  ข้อมูลส่วนตัวของฉัน
                </a>
              </c:otherwise>
            </c:choose>
          <span class="nav-item">
              <c:choose>
                  <c:when test="${sessionScope.loggedInUser == null}">
                    <button class="button is-primary is-inverted modal-button" data-target="#login">
                      <span class="icon">
                        <i class="fa fa-user"></i>
                      </span>
                      <span>Login</span>
                    </button>
                  </c:when>
                  <c:otherwise>
                      <span class="icon">
                        <i class="fa fa-user"></i>
                      </span>
                      <span>${sessionScope.loggedInUser.fname} ${sessionScope.loggedInUser.lname}</span>
                  </c:otherwise>
              </c:choose>
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
          <li class="is-active" id="tab1"><a  href="/WhereIsIt/pages/">รายการของหายในระบบ</a></li>
          <c:choose>
              <c:when test="${sessionScope.loggedInUser != null}">
                    <li id="tab2"><a href="/WhereIsIt/pages/CreatePost">ประกาศหาของหาย</a></li>
               </c:when>
              <c:otherwise>
                  <li id="tab2"><a>ประกาศหาของหาย</a></li>
              </c:otherwise>
            </c:choose>
        </ul>
      </div>
    </nav>
  </div>
</section>