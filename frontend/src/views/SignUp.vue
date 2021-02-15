<template>
  <div class="user-background">
    <div class="user-container  col-sm-12 col-lg-6">
      <form class="user-sign-up__form" name="signup" @submit.prevent="signup">
        <h3 class="signup_label">회원가입</h3>
        <br />
        <br />

        <div class="user-sign-up-form__form-group">
          <div class="user-sign-up-form-label">이메일</div>
          <div class="user-sign-up-form__form-group__input">
            <div class="input-group email-input">
              <input
                class="form-control"
                type="text"
                placeholder="이메일"
                v-model="emailid"
                name="emailid"
                id="emailid"
                size="1"
                required
              />

              <span class="email-input__separator">@</span>

              <select
                class="form-control"
                name="emaildomain"
                size="1"
                v-model="emaildomain"
                id="emaildomain"
                required
              >
                <option selected="" value="" disabled="">선택해주세요</option>
                <option value="naver.com">naver.com</option>
                <option value="hanmail.net">hanmail.net</option>
                <option value="daum.net">daum.net</option>
                <option value="gmail.com">gmail.com</option>
                <option value="nate.com">nate.com</option
                >>
                <option value="icloud.com">icloud.com</option>
              </select>

              <input
                type="button"
                value="이메일인증"
                class="phonecode-btn"
                @click="EmailCheck()"
              />
              <!-- <input type="button" value="이메일인증" class="phonecode-btn" /> -->
            </div>
            <!-- <span class="error-text" v-if="emailcheck"
              >이미 동일한 이메일이 존재합니다.</span
            > -->
          </div>
        </div>

        <div class="user-sign-up-form__form-group">
          <div class="user-sign-up-form-label">인증번호</div>
          <div class="user-sign-up-form__form-group__input">
            <div class="input-group email-input">
              <input
                type="password"
                placeholder="인증번호"
                v-model="emailcode"
                class="form-control"
                required
              />
              <input
                type="button"
                value="인증번호 확인"
                class="phonecode-btn"
                @click="EmailCodeCheck()"
              />
            </div>
            <!-- <span class="error-text" v-if="emailcheck"
              >인증에 실패했습니다.</span
            > -->
          </div>
        </div>

        <div class="user-sign-up-form__form-group">
          <div class="user-sign-up-form-label">비밀번호</div>
          <div class="user-sign-up-form__form-group__description">
            비밀번호는 8자 이상 입력해주세요.
          </div>
          <div class="user-sign-up-form__form-group__input">
            <input
              type="password"
              placeholder="비밀번호"
              class="form-control"
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

        <div class="user-sign-up-form__form-group">
          <div class="user-sign-up-form-label">비밀번호 확인</div>
          <div class="user-sign-up-form__form-group__input">
            <input
              type="password"
              placeholder="비밀번호 확인"
              class="form-control"
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

        <div class="user-sign-up-form__form-group">
          <div class="user-sign-up-form-label">별명</div>
          <div class="user-sign-up-form__form-group__description">
            다른 유저와 겹치지 않는 별명을 입력해주세요. (2~15자)
          </div>
          <div class="user-sign-up-form__form-group__input">
            <div class="input-group email-input">
              <input
                placeholder="별명 (2~15자)"
                class="form-control"
                name="nick_name"
                id="nick_name"
                v-model="nick_name"
                required
              />

              <input
                type="button"
                value="중복체크"
                class="phonecode-btn"
                @click="NicknameCheck()"
              />
            </div>
            <!-- <span v-if="nicknamecheck">
              <span class="error-text" v-if="errormsg.nick_name">{{
                errormsg.nick_name
              }}</span>
            </span>

            <span v-else>
              <span class="correct-text" v-if="correctmsg.nick_name">{{
                correctmsg.nick_name
              }}</span>
            </span> -->

            <span v-if="nicknamecheck">
              <span class="error-text" v-if="errormsg.nick_name">{{
                errormsg.nick_name
              }}</span>
            </span>

            <span v-else class="correct-text">
              <span class="correct-text" v-if="donicknamecheck"
                >사용 가능한 닉네임입니다.</span
              >
            </span>
          </div>
        </div>

        <div class="user-sign-up-form__form-group">
          <div class="user-sign-up-form-label">연령대</div>
          <div class="user-sign-up-form__form-group__input">
            <!-- <div class="input-group email-input">
              <select class="form-control" name="age" id="age" v-model="age">
                <option value="10대">10대</option>
                <option value="20대">20대</option>
                <option value="30대">30대</option>
                <option value="40대">40대</option>
                <option value="50대 이상">50대 이상</option>
              </select>
            </div> -->
            <b-select
              class="form-control"
              v-model="age"
              :options="ageOptions"
            ></b-select>
          </div>
        </div>

        <div class="user-sign-up-form__form-group">
          <div class="user-sign-up-form-label">성별</div>
          <div class="user-sign-up-form__form-group__input">
            <b-form-radio v-model="sex" name="some-radios" value="f"
              >여자</b-form-radio
            >
            <b-form-radio v-model="sex" name="some-radios" value="m"
              >남자</b-form-radio
            >
          </div>
        </div>
        <input
          type="submit"
          value="회원가입"
          class="sign-up-form__form__submit btn btn-priority"
        />
      </form>

      <div class="hr"></div>

      <div class="sign-in-form__action">
        <router-link to="/login" class="sign-in-form__action"
          >로그인하러가기</router-link
        >
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/signUp.css';
import axios from 'axios';
//import { isPassword } from '../util/usercheck';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'signUp',
  data: function() {
    return {
      emailid: '',
      emaildomain: '',
      password: '',
      phone1: '010',
      phone2: '',
      phone3: '',
      nick_name: '',
      age: '20',
      passwordcheck: '',
      nicknamecheck: false,
      donicknamecheck: false,
      emailcheck: false,
      ageOptions: [
        { text: '10대', value: '10' },
        { text: '20대', value: '20' },
        { text: '30대', value: '30' },
        { text: '40대', value: '40' },
        { text: '50대 이상', value: '50' },
      ],
      sex: 'f',
      errormsg: [],
      correctmsg: [],
      emailcode: '',
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
    NicknameCheck() {
      axios
        .get(`${SERVER_URL}/user/check/${this.nick_name}`)
        .then(({ data }) => {
          console.log(data);
          if (data === true) {
            this.nicknamecheck = true;
            this.errormsg['nick_name'] = `중복된 닉네임입니다.`;
            this.correctmsg['nick_name'] = ``;
          } else {
            this.nicknamecheck = false;
            this.errormsg['nick_name'] = ``;
            this.donicknamecheck = true;
            // this.correctmsg['nick_name'] = `사용 가능한 닉네임입니다.`;
          }
          //console.log(this.nicknamecheck);
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },
    EmailCheck() {
      axios
        .post(`${SERVER_URL}/email/verify`, {
          email: this.emailid + '@' + this.emaildomain,
        })
        .then(({ data }) => {
          console.log(data);
          if (data == 'success') {
            alert('메일을 발송했습니다.');
          } else {
            alert('이미 가입된 이메일 주소입니다.');
          }
          //console.log(this.emailcheck);
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },
    EmailCodeCheck() {
      axios
        .post(`${SERVER_URL}/email/verifyCode?code=${this.emailcode}`, {
          email: this.emailid + '@' + this.emaildomain,
        })
        .then(({ data }) => {
          console.log(data);
          if (data != false) {
            this.emailcheck = true;
            alert('인증번호 확인');
          } else {
            this.emailcheck = false;
            alert('인증에 실패했습니다.');
          }
          //console.log(this.emailcheck);
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
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
    changeint() {
      Number(this.age);
    },
    signup() {
      this.changeint();
      console.log(typeof this.age);
      console.log(typeof this.sex);
      if (this.emailcheck == true && this.nicknamecheck == false) {
        axios
          .post(`${SERVER_URL}/user/signup`, {
            email: this.emailid + '@' + this.emaildomain,
            password: this.password,
            phone_number: this.phone1 + this.phone2 + this.phone3,
            nick_name: this.nick_name,
            age: this.age,
            sex: this.sex,
          })
          .then(({ data }) => {
            let msg = '회원등록 처리시 문제가 발생했습니다.';
            if (data === 'success') {
              msg = '회원등록이 완료되었습니다.';
            }
            console.log(msg);
            //alert(msg);
            this.$router.replace('/login');
          })
          .catch(() => {
            alert('회원등록 처리시 에러가 발생했습니다.');
          });
      } else {
        alert('회원가입에 실패했습니다.');
      }
    },
  },
};
</script>
