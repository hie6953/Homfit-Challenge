<template>
  <div class="user-background">
    <div class="user-container  col-sm-10 col-lg-6">
      <form>
        <h3>비밀번호 변경</h3>
        <input
          id="email"
          class="changepw-padding sign-in-form__form__input form-control sign-in-form__form__email"
          v-model="email"
          readonly
        />

        <input
          placeholder="변경할 비밀번호를 입력해주세요."
          autofocus="autofocus"
          class="changepw-padding sign-in-form__form__input form-control"
          type="password"
          id="password"
          v-model="password"
          required
        />

        <span class="error-text" v-if="errormsg.password">{{
          errormsg.password
        }}</span>

        <input
          placeholder="변경할 비밀번호를 다시 한 번 입력해주세요."
          autofocus="autofocus"
          class="changepw-padding sign-in-form__form__input form-control"
          type="password"
          id="password-confirm"
          v-model="passwordcheck"
          required
          @keyup.enter="UserPasswordEdit()"
        />
        <span class="error-text" v-if="errormsg.passwordcheck">{{
          errormsg.passwordcheck
        }}</span>

        <!-- <button
          class="sign-in-form__form__submit btn btn-priority"
          @submit="UserPasswordEdit()"
        >
          저장
        </button> -->
        <input
          type="button"
          value="확인"
          class="sign-in-form__form__submit btn btn-priority"
          @click="UserPasswordEdit()"
        />
      </form>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import '../assets/css/forgotpassword.css';
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'ForgotPassword',
  data: function() {
    return {
      password: '',
      passwordcheck: '',
      errormsg: [],
      email: '',
    };
  },
  watch: {
    password(value) {
      this.password = value;
      this.checkPassword(value);
    },
    passwordcheck(value) {
      this.passwordcheck = value;
      this.checkPasswordconfirm(value);
    },
  },
  computed: {
    ...mapGetters(['getUserEmail']),
  },
  mounted() {
    this.setEmail();
  },
  methods: {
    UserPasswordEdit() {
      if (
        this.password == this.passwordcheck &&
        this.password.length > 0 &&
        this.passwordcheck.length > 0
      ) {
        axios
          .post(`${SERVER_URL}/user/findPw`, {
            password: this.password,
            email: this.email,
          })

          .then(({ data }) => {
            console.log(data);
            if (data == 'success') {
              alert('비밀번호가 변경되었습니다.');
              this.$router.replace('/login');
            }
          })
          .catch(() => {
            alert('에러가 발생했습니다.');
          });
      }
    },

    setEmail() {
      this.email = this.getUserEmail;
    },
    checkPassword(value) {
      if (value.length < 8) {
        this.errormsg['password'] = `비밀번호를 8자 이상으로 입력해주세요.`;
      } else this.errormsg['password'] = ``;
    },
    checkPasswordconfirm(value) {
      if (value.length >= 0 && this.password != this.passwordcheck) {
        this.errormsg['passwordcheck'] = `비밀번호가 일치하지 않습니다.`;
      } else this.errormsg['passwordcheck'] = ``;
    },
  },
};
</script>
