<template>
  <div>
    <b-navbar class="navbar-second-menu" variant="white">
      <!-- 메뉴 -->
      <b-navbar-nav class="ml-auto mr-auto">
        <router-link to="/challengelist" class="second-menu">
          <span>챌린지 리스트</span>
        </router-link>
        <router-link to="/feed" class="second-menu">
          <span>피드</span>
        </router-link>
        <router-link to="/challenge-creating" class="second-menu">
          <span>챌린지 개설</span>
        </router-link>
        <router-link :to="GoChallengeRecommend" class="second-menu">
          <span>추천 챌린지</span>
        </router-link>
        <router-link to="/" class="second-menu">
          <span>나의 챌린지</span>
        </router-link>
      </b-navbar-nav>
    </b-navbar>
    <!-- <hr id="navbar-second-boundary" /> -->
  </div>
</template>

<script>
import "@/assets/css/NavBar/navbarsecond.css";

export default {
  data() {
    return {};
  },

  methods: {
    GoChallengeRecommend:function(){
      
    }
  },
};
</script>
