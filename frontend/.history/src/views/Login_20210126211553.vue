<template>
  <div class="user-background">
    <div class="user-container col-sm-10 col-lg-6">
      <form class="form-signin" role="form">
        <!-- <h2 class="form-signin-heading">HomFit</h2> -->
        <img id="login-logo-image" src="@/assets/NavBar/logo.png" />
        <form
          class="sign-in-form__form"
          id="new_user"
          accept-charset="UTF-8"
          method="post"
        >
          <!--            <b-icon icon="person"></b-icon> -->
          <b-form-input
            placeholder="이메일"
            autofocus="autofocus"
            class="sign-in-form__form__input form-control sign-in-form__form__email"
            type="text"
            id="user_email"
            v-model="user.email"
            required
          />
          <div
            class="sign-in-form__form__input-wrap sign-in-form__form__password"
          >
            <b-form-input
              placeholder="비밀번호"
              autocomplete="off"
              class="sign-in-form__form__input form-control"
              type="password"
              id="user_password"
              v-model="user.password"
              required
              @keypress.enter="login"
            />
          </div>

          <label class="checkbox">
            <input type="checkbox" id="auto_login" v-model="auto_login" />
            로그인유지
          </label>

          <input
            type="hidden"
            name="remember_me"
            id="remember_me"
            value="checked"
          />
          <input type="hidden" name="is_pro" id="is_pro" value="false" />
          <input
            type="submit"
            name="commit"
            value="로그인"
            class="sign-in-form__form__submit btn btn-priority"
            data-disable-with="로그인"
            @click="login"
          />
        </form>

        <div class="hr"></div>

        <div class="sign-in-form__action">
          <router-link to="/forgot-password" class="sign-in-form__action__entry"
            >비밀번호재설정</router-link
          >
          <router-link to="/signup" class="sign-in-form__action__entry"
            >회원가입</router-link
          >
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import '../assets/css/login.css';
import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

export default {
  name: 'Login',
  data: function() {
    return {
      user: {
        email: '',
        password: '',
      },
      message: '',
      auto_login: false,
    };
  },
  created() {
    this.user.email = this.$cookie.get('auto_login');
    if (this.user.email != null) this.auto_login = true;
  },
  computed: {
    nextRoute() {
      return this.$route.params.nextRoute ? this.$route.params.nextRoute : '';
    },
  },
  methods: {
    login: function() {
      //콘솔 확인
      console.log(this.user.email);

      if (this.auto_login) {
        this.$cookie.set('auto_login', this.user.email);
      } else {
        this.$cookie.delete('auto_login');
      }

      // LOGIN
      // 서버와 통신해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store
        .dispatch('LOGIN', this.user)
        .then(() => {
          // console.log(response);
          // this.$router.push('/');
        })
        .catch(({ message }) => (this.msg = message));
    },
  },
};
</script>
