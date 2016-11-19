<%-- 
    Document   : create_post
    Created on : Nov 19, 2016, 5:13:24 PM
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
            <label class="label">Name</label>
            <p class="control">
              <input class="input" type="text" placeholder="Text input">
            </p>
            <label class="label">Username</label>
            <p class="control has-icon has-icon-right">
              <input class="input is-success" type="text" placeholder="Text input" value="bulma">
              <i class="fa fa-check"></i>
              <span class="help is-success">This username is available</span>
            </p>
            <label class="label">Email</label>
            <p class="control has-icon has-icon-right">
              <input class="input is-danger" type="text" placeholder="Email input" value="hello@">
              <i class="fa fa-warning"></i>
              <span class="help is-danger">This email is invalid</span>
            </p>
            <label class="label">Subject</label>
            <p class="control">
              <span class="select">
                <select>
                  <option>Select dropdown</option>
                  <option>With options</option>
                </select>
              </span>
            </p>
            <label class="label">Message</label>
            <p class="control">
              <textarea class="textarea" placeholder="Textarea"></textarea>
            </p>
            <p class="control">
              <label class="checkbox">
                <input type="checkbox">
                Remember me
              </label>
            </p>
            <p class="control">
              <label class="radio">
                <input type="radio" name="question">
                Yes
              </label>
              <label class="radio">
                <input type="radio" name="question">
                No
              </label>
            </p>
            <p class="control">
              <button class="button is-primary is-fullwidth">Submit</button>
              <button class="button is-link is-fullwidth">Cancel</button>
            </p>
          </div>
        </div>
      </div>
    </section>
    
    <jsp:include page="../layouts/script_included.jsp"/>
    
  </body>
</html>
