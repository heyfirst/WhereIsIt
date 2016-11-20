<%--
    Document   : script_included
    Created on : Oct 6, 2016, 3:28:13 PM
    Author     : KS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<script src="../assets/js/jquery.min.js"></script>
<script src="../assets/js/cleave.min.js"></script>
<script src="../assets/js/select2.full.min.js"></script>
<script src="../assets/js/superplaceholder.min.js"></script>
<script src="../assets/js/script.js"></script>

<!-- Modal -->
        <div id="login" class="modal">
          <div class="modal-background"></div>
          <div class="modal-card">
            <header class="modal-card-head">
              <p class="modal-card-title" >Login</p>
              <button class="delete"></button>
            </header>
            <section class="modal-card-body">
              <div class="content">
                  <form action="Login" method="post">
                <label class="label">Username</label>
                <p class="control">
                  <input class="input" type="text" placeholder="Username..." name="email" >
                </p>
                <label class="label">Password</label>
                <p class="control">
                  <input class="input" type="password" placeholder="Password..." name="password">
                </p>
                <button type="submit" class="button is-success">Login</button>
                <a class="button is-danger is-outlined" href="/WhereIsIt/pages/Register">Register</a>
               </form>
              </div>
            </section>
          </div>
        </div>
