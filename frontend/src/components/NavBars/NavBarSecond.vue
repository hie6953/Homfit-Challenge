<template>
  <div>
    <b-navbar variant="white">
      <!-- 메뉴 -->
      <b-navbar-nav class="ml-auto mr-auto">
        <router-link to="/링크" class="second-menu">
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

<style>
/* 기본 메뉴 */
.second-menu {
  font-weight: 700;
  color: black;
  padding-left: 20px;
  padding-right: 20px;
}

/* hover시 메뉴 색 변경 */
.second-menu:hover {
  text-decoration: none;
  color: #e65c2e;
  transition: all 0.3s ease;
}

.second-menu > span {
  position: relative;
}

/*밑줄넣기 부분*/
.second-menu > span::after {
  content: '';
  width: 0%;
  left: 50%;
  height: 1px;
  display: block;
  position: absolute;
  margin-top: 3px;
  border-bottom: 2px solid #e65c2e;
  transition: all 0.3s ease;
}
.second-menu:hover > span::after {
  content: '';
  width: 100%;
  left: 0px;
  height: 1px;
  display: block;
  position: absolute;
  margin-top: 3px;
  border-bottom: 2px solid #e65c2e;
  transition: all 0.3s ease;
}
</style>
