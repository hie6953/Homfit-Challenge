<template>
  <div>
    <hr id="navbar-second-boundary" />
    <b-navbar class="navbar-second-menu px-0" variant="white">
      <!-- 메뉴 -->
      <b-navbar-nav class="navbar-second-menu-inside">
        <router-link to="/challengelist" class="second-menu-mobile">
          <div class="navbar-second-icon">
            <img src="@/assets/NavBar/list.png" />
            <br />
            <span>챌린지 리스트</span>
          </div>
        </router-link>
        <router-link to="/feed" class="second-menu-mobile">
          <div class="navbar-second-icon">
            <img src="@/assets/NavBar/feed.png" />
            <br />
            <span>피드</span>
          </div>
        </router-link>
        <router-link to="/challenge-creating" class="second-menu-mobile">
          <div class="navbar-second-icon">
            <img src="@/assets/NavBar/create.png" />
            <br />
            <span>챌린지 개설</span>
          </div>
        </router-link>
        <router-link to="/" class="second-menu-mobile">
          <div class="navbar-second-icon">
            <img src="@/assets/NavBar/recommend.png" />
            <br />
            <span>추천 챌린지</span>
          </div>
        </router-link>
        <router-link to="/" class="second-menu-mobile">
          <div class="navbar-second-icon">
            <img src="@/assets/NavBar/my_challenge.png" />
            <br />
            <span>나의 챌린지</span>
          </div>
        </router-link>
      </b-navbar-nav>
    </b-navbar>
  </div>
</template>

<script>
import "@/assets/css/NavBar/navbarsecond.css";

export default {
  data() {
    return {};
  },

  methods: {},
};
</script>
