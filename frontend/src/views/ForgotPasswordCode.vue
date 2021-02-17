<template>
  <div class="user-background">
    <div class="user-container  col-sm-10 col-lg-6">
      <form>
        <div class="justtext">
          비밀번호를 재설정하시려면 아래에 인증 코드를 입력하세요.
        </div>

        <input
          placeholder="인증번호를 입력하세요"
          autofocus="autofocus"
          class="sign-in-form__form__input form-control sign-in-form__form__email"
          type="text"
          id="changepwcode"
          v-model="forgotpasscode"
        />

        <div class="sign-in-form__action">
          <!-- <router-link to="/forgot-password-final" class="change_pw_btn"
            >인증</router-link
          > -->
          <input
            type="submit"
            value="인증"
            class="change_pw_btn"
            @click="CheckEmailCode()"
          />
          <router-link to="/forgot-password" class="change_pw_btn_back"
            >취소</router-link
          >
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import '../assets/css/forgotpassword.css';
import swal from '@/assets/javascript/sweetAlert.js';
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  props: {
    email: String,
  },
  data: () => ({
    forgotpasscode: '',
  }),

  methods: {
    CheckEmailCode() {
      axios
        .post(`${SERVER_URL}/email/verifyCode?code=${this.forgotpasscode}`, {
          email: this.email,
        })
        .then(({ data }) => {
          console.log(data);
          if (data != false) {
            swal.success('인증번호 확인');
          } else {
            swal.error('인증번호가 일치하지 않습니다.');
          }
        })
        .catch(() => {
          swal.error('오류가 발생했습니다.');
        });
    },
  },
};
</script>
