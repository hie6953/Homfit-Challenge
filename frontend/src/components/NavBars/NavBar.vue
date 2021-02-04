<template>
  <div>
    <b-navbar class="navbar-main" variant="white">
      <!-- 로고 -->
      <router-link to="/">
        <img id="logo-image" src="@/assets/NavBar/logo.png" />
      </router-link>

      <!-- 알림 -->
      <b-navbar-nav class="ml-auto navbar-right">
        <b-nav-item-dropdown
          id="bellDropdown"
          v-if="getAccessToken"
          right
          class="notice-dropdown my-auto"
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
        <b-nav-item-dropdown
          id="myPageDropdown"
          v-if="getAccessToken"
          right
          class="notice-dropdown"
        >
          <template slot="button-content">
            <button
              id="myPageButton"
              v-if="getAccessToken"
              class=" my-auto"
            >
              <img
                id="userIcon"
                class="circle-user-image"
                src="@/assets/NavBar/anonimous_user.png"
              />
              <b-tooltip target="userIcon" triggers="hover">
                마이페이지
              </b-tooltip>
            </button>
          </template>
          <b-nav-item><router-link id="myPageLink" to="/mypage">마이페이지</router-link></b-nav-item>
          <b-nav-item><button id="logoutButton" @click="LogOut">로그아웃</button></b-nav-item>
        </b-nav-item-dropdown>
        <!-- <router-link
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
        </router-link> -->

        <!-- 로그인 PC -->
        <button
          v-if="!getAccessToken && !isMobile"
          class="login-button"
          @click="Login"
        >
          로그인
        </button>

        <!-- 로그인 Mobile -->
        <button
          v-if="!getAccessToken && isMobile"
          class="login-button-mobile"
          @click="Login"
        >
          <b-icon
            id="login-icon"
            icon="box-arrow-in-down-right"
            scale="1.5"
          ></b-icon>
        </button>

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
    <hr id="navbar-boundary" />
  </div>
</template>

<script>
import NavBarNoticeCard from "@/components/NavBars/NavBarNoticeCard.vue";

import "@/assets/css/NavBar/navbar.css";

import { mapGetters } from "vuex";

export default {
  components: {
    NavBarNoticeCard,
  },

  data() {
    return {
      isMobile: false,
      notices: [
        { type: "ranking", comment: "실버로의 승급을 축하드립니다!" },
        { type: "ToDo", comment: "1시간 요가하기 챌린지 인증하세요." },
      ],
    };
  },
  methods: {
    // 로그인
    Login: function() {
      this.$router.push("/login");
    },
    LogOut:function(){
      alert("로그아웃!");
      this.$store
        .dispatch('LOGOUT')
        .then(() => this.$router.replace('/').catch(() => {}));
      
    },
    // 화면 너비에 따른 모바일 여부 판단
    handleResize: function() {
      this.isMobile = window.innerWidth <= 480;
    },
  },
  computed: {
    ...mapGetters(["getAccessToken"]),
  },
  mounted() {
    // 화면 너비 측정 이벤트 추가/
    this.handleResize();
    window.addEventListener("resize", this.handleResize);
  },
};
</script>
