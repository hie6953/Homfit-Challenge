<template>
  <div>
    <b-navbar variant="white">
      <!-- 메뉴 -->
      <b-navbar-nav class="ml-auto mr-auto">
        <router-link to="/challengelist" class="second-menu">
          <span>챌린지 리스트</span>
        </router-link>
        <router-link :to="PageMove('/링크')" class="second-menu">
          <span>챌린지 개설</span>
        </router-link>
        <router-link to="/링크" class="second-menu">
          <span>피드</span>
        </router-link>
        <router-link :to="PageMove('/링크')" class="second-menu">
          <span>추천 챌린지</span>
        </router-link>
      </b-navbar-nav>
    </b-navbar>
  </div>
</template>

<script>
import "@/assets/css/NavBar/navbarsecond.css";
export default {
  data() {
    return {
      login: false,
    };
  },

  methods: {
    // 로그인여부에 따른 이동 페이지 결정
    // 파라미터 : 로그인시 이동할 페이지
    PageMove: function(page) {
      if (this.login) return page;
      else return '/링크';
    },
  },
};
</script>
