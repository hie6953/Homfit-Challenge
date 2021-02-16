<template>
  <div class="fp-user-background">
    <div class="fp-user-container col-sm-10 col-lg-6">
      <form>
        <h3>비밀번호 재설정</h3>

        <div class="forgot-info">
          비밀번호 재설정 인증번호를 이메일로 안내드리겠습니다.
        </div>

        <!-- 이메일주소 입력 -->
        <div class="input-group email-input">
          <input
            placeholder="이메일 주소를 입력하세요"
            autofocus="autofocus"
            class="form-control"
            type="text"
            id="user_email"
            v-model="email"
          />

          <!-- <input
            type="button"
            value="인증번호 받기"
            class="sign-in-form__form__submit btn btn-priority "
            @click="SendEmailCode()"
          /> -->

          <input
            type="button"
            value="인증번호 받기"
            class="change_pw_btn "
            @click="SendEmailCode()"
          />
        </div>

        <!-- 인증번호 입력 -->
        <div class="input-group email-input">
          <input
            placeholder="인증번호를 입력하세요"
            autofocus="autofocus"
            class="sign-in-form__form__input form-control"
            type="text"
            id="forgotpasswordcode"
            v-model="forgotpasscode"
          />
          <input
            type="button"
            value="   인증 확인   "
            class="change_pw_btn"
            @click="CheckEmailCode()"
          />
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import '../assets/css/forgotpassword.css';
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import Swal from 'sweetalert2';

export default {
  data: () => ({
    // selected: '이메일',
    // contact: null,
    email: '',
    forgotpasscode: '',
  }),

  methods: {
    SendEmailCode() {
      axios
        .post(`${SERVER_URL}/email/verify/password`, {
          email: this.email,
        })
        .then(({ data }) => {
          // console.log(data);
          if (data == 'success') {
            // alert('메일을 발송했습니다.');
            Swal.fire({
              position: 'center',
              icon: 'success',
              title: '이메일 발송 완료!',
              showConfirmButton: false,
              timer: 1500,
            });
            //this.$router.replace('/forgot-password-code');
            this.$store.commit('SETEMAIL', this.email);
          } else {
            // alert('존재하지 않는 이메일입니다.');
            Swal.fire({
              position: 'center',
              icon: 'error',
              title: '존재하지 않는 이메일입니다.',
              showConfirmButton: false,
              timer: 1500,
            });
          }
        })
        .catch(() => {
          // alert('에러가 발생했습니다.');
          Swal.fire({
            position: 'center',
            icon: 'error',
            title: '에러가 발생했습니다.',
            showConfirmButton: false,
            timer: 1500,
          });
        });
    },
    CheckEmailCode() {
      axios
        .post(`${SERVER_URL}/email/verifyCode?code=${this.forgotpasscode}`, {
          email: this.email,
        })
        .then(({ data }) => {
          // console.log(data);
          if (data != false) {
            // alert('인증번호 확인');
            Swal.fire({
              position: 'center',
              icon: 'success',
              title: '이메일인증 성공!',
              showConfirmButton: false,
              timer: 1500,
            });
            this.$router.replace('/forgot-password-final');
          } else {
            // alert('인증번호가 일치하지 않습니다.');
            Swal.fire({
              position: 'center',
              icon: 'error',
              title: '인증에 실패했습니다.',
              showConfirmButton: false,
              timer: 1500,
            });
          }
        })
        .catch(() => {
          // alert('에러가 발생했습니다.');
          Swal.fire({
            position: 'center',
            icon: 'error',
            title: '에러가 발생했습니다.',
            showConfirmButton: false,
            timer: 1500,
          });
        });
    },
  },
};
</script>
