<template>
  <div id="app" class="position-relative">
    <div class="sticky-top">
      <NavBar class="navbar-first"></NavBar>
      <NavBarSecond id="navbar-second" class="navbar-second"></NavBarSecond>
    </div>
    <router-view class="main-view" />
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBars/NavBar.vue";
import NavBarSecond from "@/components/NavBars/NavBarSecond.vue";

import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

import { mapGetters } from "vuex";

export default {
  name: "App",
  components: {
    NavBar,
    NavBarSecond,
  },

  data() {
    return {
      isLogin: false,
      user: {
        email: "",
        password: "",
      },
    };
  },
  created() {
    let item = sessionStorage.getItem("loginInfo");

    if (item != null) {
      axios.defaults.headers.common["access-token"] = JSON.parse(item)[
        "access-token"
      ];
      axios
        .get(`${SERVER_URL}/user/info`)
        .then(() => {
          //토큰이 유효하다면.
          this.$store.commit(
            "LOGIN",
            JSON.parse(sessionStorage.getItem("loginInfo"))
          ); //user없음.
        })
        .catch(() => {
          sessionStorage.removeItem("loginInfo");
          this.$store.dispatch("LOGOUT").then(() => this.$router.replace("/"));
        });
    }
  },
  computed: {
    ...mapGetters(["getAccessToken", "getUserEmail"]),
  },
  methods: {
    onClickLogout() {
      this.$store
        .dispatch("LOGOUT")
        .then(() => this.$router.replace("/").catch(() => {}));
    },
  },
};

// 스크롤 방향 판단
// 스크롤 올릴 때 NavBarSecondary 보여짐
let prevScrollpos = window.pageYOffset;
window.onscroll = function() {
  var currentScrollPos = window.pageYOffset;
  if (prevScrollpos > currentScrollPos) {
    document.getElementById("navbar-second").style.top = "-1px";
  } else {
    document.getElementById("navbar-second").style.top = "-60px";
  }

  prevScrollpos = currentScrollPos;
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap");
#app {
  font-family: "Nanum Gothic", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

/* Navbar 상단고정 위한 relative position설정 */
.position-relative {
  position: relative;
}

/* 상단고정 */
.sticky-top {
  position: fixed;
  top: 0;
  width: 100%;
}

/* NavBar */
.navbar-first {
  position: relative;
  z-index: 2;
}

/* NavbarSecondary*/
.navbar-second {
  position: relative;
  transition: top 0.5s;
  z-index: 1;
}
@media (min-width: 480px) {
  .main-view {
    margin-top: 105px;
  }
}

@media (max-width: 480px) {
  .main-view {
    margin-top: 120px;
  }
}
</style>
