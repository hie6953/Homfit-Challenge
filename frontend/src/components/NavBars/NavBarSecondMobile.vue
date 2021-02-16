<template>
  <div>
    <hr id="navbar-second-boundary" />
    <b-navbar class="navbar-second-menu mobile mx-auto" variant="white">
      <!-- 메뉴 -->
      <b-navbar-nav class="navbar-second-menu-inside">
        <router-link to="/challengelist" class="second-menu-mobile">
          <div class="navbar-second-icon mobile">
            <img src="@/assets/NavBar/list.png" />
            <span>리스트</span>
          </div>
        </router-link>
        <router-link to="/feed" class="second-menu-mobile">
          <div class="navbar-second-icon mobile">
            <img src="@/assets/NavBar/feed.png" />
            <span>피드</span>
          </div>
        </router-link>
        <router-link to="/challenge-creating" class="second-menu-mobile">
          <div class="navbar-second-icon mobile">
            <img src="@/assets/NavBar/create.png" />
            <span>개설</span>
          </div>
        </router-link>
        <router-link to="/challenge-recommend" class="second-menu-mobile">
          <div class="navbar-second-icon mobile">
            <img src="@/assets/NavBar/recommend.png" />
            <span>추천</span>
          </div>
        </router-link>
        <router-link to="/challengemanage" class="second-menu-mobile">
          <div class="navbar-second-icon mobile">
            <img src="@/assets/NavBar/my_challenge.png" />
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
