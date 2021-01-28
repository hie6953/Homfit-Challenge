<template>
  <div id="app" class="position-relative">
    <header class="sticky-top">
      <NavBar class="navbar-first"></NavBar>
      <NavBarSecond id="navbar-second" class="navbar-second"></NavBarSecond>
    </header>
    <router-view />
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from '@/components/NavBars/NavBar.vue';
import NavBarSecond from '@/components/NavBars/NavBarSecond.vue';

import { mapGetters } from 'vuex';

export default {
  name: 'App',
  components: {
    NavBar,
    NavBarSecond,
  },

  data() {
    return {
      isLogin: false,
      user: {
        email: '',
        password: '',
      },
    };
  },
  computed: {
    ...mapGetters(['getAccessToken', 'getUserEmail']),
  },
  methods: {
    onClickLogout() {
      this.$store
        .dispatch('LOGOUT')
        .then(() => this.$router.replace('/').catch(() => {}));
    },
  },
};

// 스크롤 방향 판단
// 스크롤 올릴 때 NavBarSecondary 보여짐
let prevScrollpos = window.pageYOffset;
window.onscroll = function() {
  var currentScrollPos = window.pageYOffset;
  if (prevScrollpos > currentScrollPos) {
    document.getElementById('navbar-second').style.top = '-1px';
  } else {
    document.getElementById('navbar-second').style.top = '-60px';
  }

  prevScrollpos = currentScrollPos;
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap');
#app {
  font-family: 'Nanum Gothic', sans-serif;
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
</style>
