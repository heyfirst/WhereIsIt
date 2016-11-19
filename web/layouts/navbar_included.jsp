<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            ข้อมูลส่วนตัวของฉัน
          </a>
          <span class="nav-item">
            <button class="button is-primary is-inverted modal-button" data-target="#login">
              <span class="icon">
                <i class="fa fa-user"></i>
              </span>
              <span>Login</span>
            </button>
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
          <li class="is-active"><a>รายการของหายในระบบ</a></li>
          <li><a>ประกาศหาของหาย</a></li>
        </ul>
      </div>
    </nav>
  </div>
</section>