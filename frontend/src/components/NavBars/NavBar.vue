<template>
  <div>
    <b-navbar variant="danger">
      <!-- 로고 -->
      <b-navbar-brand href="/">HomFit</b-navbar-brand>

      <!-- 메뉴 -->
      <b-navbar-nav class="ml-auto">
        <b-nav-item href="/">
          <b-icon icon="bell" scale="1.2" variant="dark"></b-icon
        ></b-nav-item>
        <b-nav-item href="/"
          ><b-icon icon="bookmark" scale="1.2" variant="dark"></b-icon
        ></b-nav-item>
        <b-nav-item href="/">
          <img
            class="circle-user-image"
            src="@/assets/NavBar/anonimous_user.png"
        /></b-nav-item>
        <b-nav-item href="#"
          ><b-icon icon="search" scale="1.2" variant="dark"></b-icon
        ></b-nav-item>
      </b-navbar-nav>
    </b-navbar>
  </div>
</template>

<style>
/* 원형 사용자 이미지 */
.circle-user-image {
  width: 25px;
  height: 25px;
  border-radius: 50%;
  overflow: hidden;
}
</style>
