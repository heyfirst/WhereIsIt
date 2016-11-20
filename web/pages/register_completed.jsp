<%--
    Document   : register_completed
    Created on : Nov 21, 2016, 2:04:03 AM
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
          <div class="column is-half is-offset-one-quarter">
            <div class="content has-text-centered">
              <h1>Register Successful !</h1>
              <p>สมัครสมาชิกเสร็จเรียบร้อย สามารถเช็คข้อมูลส่วนตัวผ่านอีเมลของท่านได้เลยครับ</p>
              <a class="button is-danger" href="/WhereIsIt/pages/">
                <span class="icon">
                  <i class="fa fa-arrow-left"></i>
                </span>
                <span>Back to Home</span>
              </a>
              <a class="button is-success" href="/WhereIsIt/pages/Profile">
                <span>Go to Profile</span>
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
