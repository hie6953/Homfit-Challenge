<template>
  <div>
    <hr id="navbar-second-boundary" />
    <b-navbar class="navbar-second-menu mobile mx-auto" variant="white">
      <!-- 메뉴 -->
      <b-navbar-nav class="navbar-second-menu-inside">
        <router-link to="/challengelist" class="second-menu-mobile">
          <div class="navbar-second-icon">
            <img src="@/assets/NavBar/list.png" />
            <br />
            <span>리스트</span>
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
            <span>개설</span>
          </div>
        </router-link>
        <router-link to="/challenge-recommend" class="second-menu-mobile">
          <div class="navbar-second-icon">
            <img src="@/assets/NavBar/recommend.png" />
            <br />
            <span>추천</span>
          </div>
        </router-link>
        <router-link to="/challengemanage" class="second-menu-mobile">
          <div class="navbar-second-icon">
            <img src="@/assets/NavBar/my_challenge.png" />
            <br />
            <span>My</span>
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
