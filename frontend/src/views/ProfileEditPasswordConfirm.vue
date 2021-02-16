<template>
  <div class="">
    <div class="mx-auto col-11 col-md-8 col-lg-6 password-confirm-container">
      <div class="password-confirm-info">
        <div class="pw-confirm-info">
          <div class="password-confirm-infotext">
            개인 정보 조회를 위해서는 인증이 필요합니다. 비밀번호 입력 후 확인
            버튼을 클릭해주세요.
          </div>
        </div>
        <div class="pw-confirm-input">
          <form class="edit-password">
            <div class="form-group">
              <label class="control-label">비밀번호</label>
              <div class="control-input">
                <input
                  class="form-control"
                  type="password"
                  name="password"
                  id="password"
                  value="password"
                  v-model="password"
                  required
                />
                <span class="error-text" v-if="passwordconfirm"
                  >비밀번호가 일치하지 않습니다.</span
                >
              </div>
            </div>
            <!-- 버튼 -->
            <div class="passchange-confirm-btn">
              <input
                type="button"
                class="passconfirm-btn-priority btn"
                value="확인"
                @click="ConfirmPassword()"
              />
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/profileeditpasswordconfirm.css';
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import { mapGetters } from 'vuex';

export default {
  name: 'ProfileEditPasswordConfirm',
  data: function() {
    return {
      password: '',
      passwordconfirm: false,
    };
  },
  methods: {
    ConfirmPassword: function() {
      axios
        .post(`${SERVER_URL}/user/checkPassword`, {
          password: this.password,
          uid: this.getUserUid,
        })
        .then(({ data }) => {
          console.log(data);
          if (data === true) {
            this.$router.push(`/editprofile`);
          } else this.passwordconfirm = true;
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },
  },
  computed: {
    ...mapGetters(['getUserUid']),
  },
};
</script>
