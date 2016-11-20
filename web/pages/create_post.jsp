<%--
    Document   : create_post
    Created on : Nov 19, 2016, 5:13:24 PM
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
    <script>
        var tab1 = document.getElementById("tab1");
        var tab2 = document.getElementById("tab2");
        tab1.setAttribute("class","");
        tab2.setAttribute("class","is-active");
    </script>
    <section class="section">
      <div class="container">
        <div class="columns">
          <div class="column is-half is-offset-one-quarter">
             <form action="CreatePost" method="Post">
                 <p style="color: red; font-size: 16px;">${message}</p>
                <label class="label">Name</label>
                <p class="control">
                  <input class="input" type="text" placeholder="Text input" name="postName" required>
                </p>
                <label class="label">Username</label>
                <p class="control has-icon has-icon-right">
                    <input class="input is-success" type="text" placeholder="Text input" value="${sessionScope.loggedInUser.email}" disabled="true">
                  <i class="fa fa-check"></i>
                  <span class="help is-success">This username is available</span>
                </p>
                <label class="label">Tag</label>
                <p class="control">
                  <span class="select is-fullwidth">
                      <select name="tag" class="is-fullwidth" id="tags" multiple>
                         <c:forEach items="${tag}" var="t" varStatus="vs">
                              <option value="${t.tagId}">${t.tagName}</option>
                          </c:forEach>
                    </select>
                  </span>
                    <label class="label">Latitude</label>
                        <input name="lat" type="text" value="0" >
                    <label class="label">Longtitude</label>
                        <input name="lon" type="text" value="0" >
                 
                </p>
                <label class="label">Message</label>
                <p class="control">
                  <textarea class="textarea" placeholder="Textarea" name="postDescription" required></textarea>
                </p>
                <p class="control">
                  <label class="checkbox">
                    <input type="checkbox">
                    Remember me
                  </label>
                </p>
                <p class="control">
                  <label class="radio">
                    <input type="radio" name="question" value="yes">
                    Yes
                  </label>
                  <label class="radio">
                    <input type="radio" name="question" value="no">
                    No
                  </label>
                </p>
                <label class="label">Map</label>
                <div class="content">
                  <div id="map" data-lat="13.6525855" data-lng="100.4936103"></div>
                </div>

                <input type="hidden" name="map_lat" id="map_lat">
                <input type="hidden" name="map_lng" id="map_lng">

                <p class="control">
                  <button class="button is-primary is-fullwidth">Submit</button>
                  <button class="button is-link is-fullwidth">Cancel</button>
                </p>
             </form>
          </div>
        </div>
      </div>
    </section>

    <jsp:include page="../layouts/script_included.jsp"/>
    <script type="text/javascript" src="../assets/js/add.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDiS9UZeqzWSIFgKVE_ooOllVLPT02dcEU&callback=initMap"
    async defer></script>

  </body>
</html>
