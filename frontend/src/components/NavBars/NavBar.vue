<template>
  <div>
    <b-navbar variant="white">
      <!-- 로고 -->
      <b-navbar-brand href="/">
        <img id="logo-image" src="@/assets/NavBar/logo.png" />
      </b-navbar-brand>

      <!-- 알림 -->
      <b-navbar-nav class="ml-auto">
        <b-nav-item-dropdown id="bellDropdown" right class="notice-dropdown">
          <template slot="button-content">
            <b-icon
              id="bellIcon"
              icon="bell"
              scale="1.3"
              variant="dark"
            ></b-icon>
            <b-tooltip target="bellIcon" triggers="hover">
              알림
            </b-tooltip>
          </template>
          <nav-bar-notice-card
            v-for="(notice, index) in notices"
            :key="`${index}_notice`"
            :notice="notice"
          ></nav-bar-notice-card>
        </b-nav-item-dropdown>

        <!-- 북마크 -->
        <router-link :to="PageMove('/링크')" class="mt-auto mb-auto main-menu"
          ><b-icon
            id="bookmarkIcon"
            icon="bookmark"
            scale="1.6"
            variant="dark"
          ></b-icon>
          <b-tooltip target="bookmarkIcon" triggers="hover">
            북마크
          </b-tooltip>
        </router-link>

        <!-- 마이페이지 -->
        <router-link
          :to="PageMove('/login')"
          class=" mt-auto mb-auto main-menu"
        >
          <img
            id="userIcon"
            class="circle-user-image"
            src="@/assets/NavBar/anonimous_user.png"
          />
          <b-tooltip target="userIcon" triggers="hover">
            마이페이지
          </b-tooltip>
        </router-link>

        <!-- 검색 -->
        <router-link to="/링크" class="mt-auto mb-auto main-menu"
          ><b-icon
            id="searchIcon"
            icon="search"
            scale="1.6"
            variant="dark"
          ></b-icon>
          <b-tooltip target="searchIcon" triggers="hover">
            검색
          </b-tooltip>
        </router-link>
      </b-navbar-nav>
    </b-navbar>
  </div>
</template>

<script>
import NavBarNoticeCard from '@/components/NavBars/NavBarNoticeCard.vue';
import '@/assets/css/NavBar/navbar.css';

export default {
  components: {
    NavBarNoticeCard,
  },

  data() {
    return {
      login: true,
      notices: [
        { type: 'ranking', comment: '실버로의 승급을 축하드립니다!' },
        { type: 'ToDo', comment: '1시간 요가하기 챌린지 인증하세요.' },
      ],
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
