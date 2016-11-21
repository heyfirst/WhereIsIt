<%-- 
    Document   : register_new
    Created on : Nov 21, 2016, 1:38:15 AM
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
             <form action="/WhereIsIt/Register" method="POST"><br>
               <label class="label">Image:</label>
               <p class="control">
                 <input type="file" class="input"/>
               </p>
               <label class="label">Email:</label>
               <p class="control">
                 <input type="mail" class="input" placeholder="Email" name="email" required/>
               </p>
               <label class="label">Password:</label>
               <p class="control">
                 <input type="password" class="input" name="password" required/>
               </p>
               <label class="label">Confirm Password:</label>
               <p class="control">
                 <input type="password" class="input" name="cfpassword" required/>
               </p>
               <label class="label">First Name:</label>
               <p class="control">
                 <input type="text" class="input" name="fname"required/>
               </p>
               <label class="label">Last Name:</label>
               <p class="control">
                 <input type="text" class="input" name="lname"required/>
               </p>
               <label class="label">Gender:</label>
               <p class="control">
                  <label class="radio">
                    <input type="radio" name="gender" value="male"required/>
                    Male
                  </label>
                  <label class="radio">
                    <input type="radio" name="gender" value="female"required/>
                    Female
                  </label>
                  <label class="radio">
                    <input type="radio" name="gender" value="donotenter"required/>
                    Do not Enter
                  </label>
               </p>
               <label class="label">Citizen ID:</label>
               <p class="control">
                 <input id="citizen-id" type="text" class="input" name="citizenid" onkeypress="return event.charCode >= 48 && event.charCode <= 57" required/>
               </p>
               <label class="label">Phone number:</label>
               <p class="control">
                 <input id="tel" type="text" class="input" name="tel" onkeypress="return event.charCode >= 48 && event.charCode <= 57" required/>
               </p>
               <label class="label">Faculty:</label>
               <p class="control">
                 <input type="text" class="input" name="faculty"required/>
               </p>
               <label class="label">Address:</label>
               <p class="control">
                 <input type="text" class="input" name="address"required/>
               </p>
               <p>${message}</p>
               <p class="control">
                  <button type="submit" class="button is-primary is-fullwidth">Register</button>
               </p>
            </form>
          </div>
        </div>
      </div>
    </section>
    
    <jsp:include page="../layouts/script_included.jsp"/>
  </body>
</html>
