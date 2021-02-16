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
          :class="{no_notices:notices.length == 0 && checkedNotices.length == 0}"
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
            <span v-if="notices.length > 0" id="bellIcon-number">{{notices.length}}</span>
            <b-tooltip target="bellIcon" triggers="hover">
              알림
            </b-tooltip>
          </template>
          <div v-if="notices.length == 0 && checkedNotices.length == 0" class="nav-bar-no-notice">
            알림이 없습니다.
          </div>
          <div
            v-else
            v-for="(notice, index) in notices"
            :key="`${index}_notice`"
          >
           <hr v-if="0 != index" class="nav-bar-notice-card-hr">
          <nav-bar-notice-card
            :notice="notice"
            :index="index"
            @alarmCheck="AlarmCheck"
          ></nav-bar-notice-card>
         
          </div>
          <div
            v-for="(notice, index) in checkedNotices"
            :key="`${index}_checkedNotices`"
          >
           <hr v-if="!(notices.length==0 && index == 0)"  class="nav-bar-notice-card-hr">
          <nav-bar-notice-card
            :notice="notice"
            :index="index"
            @alarmCheck="AlarmCheck"
          ></nav-bar-notice-card>
         
          </div>
        </b-nav-item-dropdown>

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
              class=" my-auto mr-1"
            >
              <img id="userIcon" class="circle-user-image" :src="user_img" />
              <b-tooltip target="userIcon" triggers="hover">
                마이페이지
              </b-tooltip>
            </button>
          </template>
          <b-nav-item
            ><router-link class="myPageIconLink" to="/mypage"
              >마이페이지</router-link
            ></b-nav-item
          >
          <b-nav-item
            ><router-link class="myPageIconLink" to="/bookmark"
              >북마크</router-link
            ></b-nav-item
          >
          <b-nav-item
            ><button id="logoutButton" @click="LogOut">
              로그아웃
            </button></b-nav-item
          >
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
        <router-link to="/search" class="my-auto main-menu"
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

import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;


export default {
  components: {
    NavBarNoticeCard,
  },

  data() {
    return {
      isMobile: false,
      notices: [],
      checkedNotices: [],
      // notice 내용 : {alarm_title:'',alarm_content:'',alarm_id:"",alarm_regist_date:"",alarm_check:""}
      user_img: "",
    };
  },
  watch: {
    getUserImg: function() {
      this.user_img = this.getUserImg;
    },
    getAccessToken:function(){
      if(this.getAccessToken != null){
        this.getData();
      }
    }
  },
  methods: {
    // 로그인
    Login: function() {
      this.$router.push("/login");
    },
    LogOut: function() {
      alert("로그아웃!");
      this.$store
        .dispatch("LOGOUT")
        .then(() => this.$router.replace("/").catch(() => {}));
    },
    getData:function(){
      axios
        .get(`${SERVER_URL}/user/alarm`, {
          params: {
            uid: this.getUserUid
          },
        })
        .then(({ data }) => {
          // console.log(data);
          this.notices = data;
          this.checkIdx = this.notices.length;
        })
        .catch(() => {
          alert("알림 목록을 불러오지 못했습니다.");
        });
    },
    AlarmCheck:function(alarm_id,index){
      // put에선 requestbody
      axios
        .put(`${SERVER_URL}/user/alarm`, {
            alarm_id: alarm_id
        })
        .then(() => {
          // alert("갱신");
          this.notices[index].alarm_check = true;
          let pick = this.notices.splice(index,1);
          this.checkedNotices.unshift(pick[0]);
        })
        .catch(() => {
          alert("알림 정보를 갱신하지 못했습니다.");
        });
    },
    // 화면 너비에 따른 모바일 여부 판단
    handleResize: function() {
      this.isMobile = window.innerWidth <= 480;
    },
  },
  computed: {
    ...mapGetters(["getAccessToken", "getUserImg","getUserUid"]),
  },
  mounted() {
    // 화면 너비 측정 이벤트 추가/
    this.handleResize();
    window.addEventListener("resize", this.handleResize);
  },
};
</script>
