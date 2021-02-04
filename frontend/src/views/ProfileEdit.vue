<template>
  <div class="mt-3">
    <hr id="hr-top" />

    <div class="mx-auto col-8 profile-edit-container">
      <b-tabs
        content-class="mt-3"
        align="center"
        class="profileedit-tab"
        justified
      >
        <b-tab title="회원정보수정" active>
          <!-- 사용자 정보 수정 -->
          <div class="personal-info">
            <!-- 이미지 -->
            <div class="form-group">
              <div class="edit-user-photo">
                <img
                  class="user-img"
                  src="@/assets/NavBar/anonimous_user.png"
                />
              </div>
              <div class="edit-user-photo">
                <label class="input-file-button" for="input-file">
                  사진선택
                </label>
                <input type="file" id="input-file" class="form-control-photo" />
              </div>
            </div>

            <form class="edit-personal-info">
              <!-- 텍스트 -->
              <div class="form-group">
                <label class="control-label">이메일</label>
                <div class="control-input">
                  <input
                    class="form-control"
                    type="text"
                    value="ssafy@naver.com"
                    readonly
                  />
                </div>
              </div>
              <div class="form-group">
                <label class="control-label">닉네임</label>
                <div class="control-input">
                  <div class="input-group email-input">
                    <input
                      class="form-control"
                      type="text"
                      name="nick_name"
                      id="nick_name"
                      v-model="nick_name"
                    />
                    <input
                      type="button"
                      value="중복체크"
                      class="phonecode-btn"
                      @click="NicknameCheck()"
                    />
                    <span class="error-text" v-if="nicknamecheck"
                      >이미 동일한 별명이 존재합니다.</span
                    >
                  </div>
                </div>
              </div>
              <!-- <div class="form-group">
                <label class="control-label">휴대폰번호</label>
                <div class="control-input">
                  <div class="input-group email-input">
                    <select id="user_time_zone" class="form-control">
                      <option value="010">010</option>
                      <option value="011">011</option>
                      <option value="012">012</option>
                      <option value="013">013</option>
                    </select>
                    <span class="email-input__separator">-</span>
                    <input class="form-control" type="text" />
                    <span class="email-input__separator">-</span>
                    <input class="form-control" type="text" />
                  </div>
                </div>
              </div> -->
              <div class="form-group">
                <label class="control-label">성별</label>
                <div class="control-input">
                  <input class="form-control" type="text" />
                </div>
              </div>
              <div class="form-group">
                <label class="control-label">연령</label>
                <div class="control-input">
                  <div class="ui-select">
                    <select id="age" class="form-control">
                      <option value="10">10대</option>
                      <option value="20">20대</option>
                      <option value="30">30대</option>
                      <option value="40">40대</option>
                      <option value="50">50대</option>
                    </select>
                  </div>
                </div>
              </div>

              <!-- 버튼 -->
              <br />
              <div class="edit-submit-btn">
                <input
                  type="button"
                  class="edit-btn-priority btn"
                  value="수정"
                />
              </div>
            </form>
          </div>
        </b-tab>

        <!-- 두번째 탭 : 비밀번호 변경 -->
        <b-tab title="비밀번호 변경">
          <div class="personal-info">
            <div class="pw-change-info">
              <div class="password-infotext">
                새로 사용할 비밀번호를 입력해주세요.
              </div>
              <div class="password-change-info">
                비밀번호 변경안내
                <b-icon icon="question-circle" id="changeinfo"></b-icon>
                <b-tooltip
                  target="changeinfo"
                  placement="bottom"
                  variant="light"
                >
                  비밀번호변경안내얌 <br /><br />안전을 위해 비밀번호는 8자 이상
                  입력해주세요.
                </b-tooltip>
              </div>
            </div>
            <div class="pw-change-input">
              <form class="edit-password">
                <div class="form-group">
                  <label class="control-label">새 비밀번호</label>
                  <div class="control-input">
                    <input
                      class="form-control"
                      type="password"
                      name="password"
                      id="password"
                      v-model="password"
                      required
                    />
                    <span class="error-text" v-if="errormsg.password">{{
                      errormsg.password
                    }}</span>
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label">새 비밀번호 확인</label>
                  <div class="control-input">
                    <input
                      class="form-control"
                      type="password"
                      name="passwordcheck"
                      id="passwordcheck"
                      v-model="passwordcheck"
                      required
                    />
                    <span class="error-text" v-if="errormsg.passwordcheck">{{
                      errormsg.passwordcheck
                    }}</span>
                  </div>
                </div>
                <!-- 버튼 -->
                <div class="passchange-submit-btn">
                  <input
                    type="button"
                    class="passchange-btn-priority btn"
                    value="확인"
                  />
                </div>
              </form>
            </div>
          </div>
        </b-tab>
      </b-tabs>
    </div>
  </div>
</template>

<script>
import '../assets/css/profileedit.css';
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'ProfileEdit',
  data: function() {
    return {
      password: '',
      passwordcheck: '',
      errormsg: [],
      nick_name: '',
      nicknamecheck: false,
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

  methods: {
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

    NicknameCheck() {
      axios
        .get(`${SERVER_URL}/user/check/${this.nick_name}`)
        .then(({ data }) => {
          //console.log(data);
          if (data === true) {
            this.nicknamecheck = true;
          } else this.nicknamecheck = false;
          //console.log(this.nicknamecheck);
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },
  },
};
</script>
