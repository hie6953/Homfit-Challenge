<template>
  <div>
    <b-navbar variant="white">
      <!-- 로고 -->
      <b-navbar-brand href="/">
        <img id="logo-image" src="@/assets/NavBar/logo.png" />
      </b-navbar-brand>

      <!-- 알림 -->
      <b-navbar-nav class="ml-auto">
        <b-nav-item-dropdown
          id="bellDropdown"
          v-if="getAccessToken"
          right
          class="notice-dropdown"
        >
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
        <router-link
          to="/링크"
          v-if="getAccessToken"
          class="mt-auto mb-auto main-menu"
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
          to="/mypage"
          v-if="getAccessToken"
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

        <b-button
          v-if="!getAccessToken"
          class="login-button"
          @click="Login"
          variant="outline-dark"
        >
          로그인
        </b-button>

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
import { mapGetters } from 'vuex';

export default {
  components: {
    NavBarNoticeCard,
  },

  data() {
    return {
      notices: [
        { type: 'ranking', comment: '실버로의 승급을 축하드립니다!' },
        { type: 'ToDo', comment: '1시간 요가하기 챌린지 인증하세요.' },
      ],
    };
  },
  methods: {
    Login: function() {
      this.$router.push('/login');
    },
  },
  computed: {
    ...mapGetters(['getAccessToken']),
  },
};
</script>
