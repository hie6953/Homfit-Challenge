<template>
  <div class="">
    <div class="mx-auto col-11 col-md-8 col-lg-6 profile-edit-container">
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
              <div v-if="this.user_img == ''" class="edit-user-photo">
                <img
                  class="profiledeit-user-img"
                  src="@/assets/NavBar/anonimous_user.png"
                />
              </div>
              <div v-else-if="changeuserimg" class="edit-user-photo">
                <img class="profiledeit-user-img" :src="imgurl" />
              </div>
              <div v-else class="edit-user-photo">
                <img class="profiledeit-user-img" :src="user_img" />
              </div>
              <div class="edit-user-photo">
                <label class="input-file-button" for="input-file">
                  사진선택
                </label>
                <input
                  accept=".jpg, .jpeg, .png"
                  ref="imgurl"
                  @change="fileSelect"
                  type="file"
                  id="input-file"
                  class="form-control-photo"
                />
                <!-- <input type="file" id="input-file" class="form-control-photo" /> -->
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
                    v-model="email"
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
                  </div>
                  <span v-if="isDuplicate">
                    <span class="error-text" v-if="errormsg.nick_name">{{
                      errormsg.nick_name
                    }}</span>
                  </span>
                  <span v-else class="correct-text">
                    <span class="correct-text" v-if="nicknamecheck"
                      >사용 가능한 닉네임입니다.</span
                    >
                  </span>
                </div>
              </div>

              <!-- 버튼 -->
              <br />
              <div class="edit-submit-btn">
                <input
                  type="button"
                  class="edit-btn-priority btn"
                  value="수정"
                  @click="UserProfileEdit()"
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
                    @click="UserPasswordEdit()"
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
import { mapGetters } from 'vuex';
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
const defaultImg =
  'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAScAAAEnCAMAAADchHVzAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyhpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKE1hY2ludG9zaCkiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NDFGODkyNjU2NTFCMTFFNTg1Q0JDM0U2MTgzNTQyMUQiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NDFGODkyNjY2NTFCMTFFNTg1Q0JDM0U2MTgzNTQyMUQiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo0MUY4OTI2MzY1MUIxMUU1ODVDQkMzRTYxODM1NDIxRCIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo0MUY4OTI2NDY1MUIxMUU1ODVDQkMzRTYxODM1NDIxRCIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PnmMEbAAAACxUExURcXF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';

export default {
  name: 'ProfileEdit',
  data: function() {
    return {
      password: '',
      passwordcheck: '',
      errormsg: [],
      nick_name: '',
      email: '',
      nicknamecheck: false, //닉네임 중복체크했나요?
      isDuplicate: false, //닉네임이 중복되나요?
      imgurl: defaultImg,
      user_img: '',
      changeuserimg: false,
      giveAlert: false,
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
  created() {
    let uid = this.getUserUid;
    console.log(uid);
    axios
      .post(`${SERVER_URL}/user`, { uid })
      .then(({ data }) => {
        this.email = data.email;
        this.user_img = data.user_img;
        // console.log(data);
      })
      .catch(() => {
        alert('에러가 발생했습니다.');
      });
  },

  methods: {
    fileSelect() {
      this.imgurl = document.getElementById('input-file').files[0];
      this.changeuserimg = true;
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

    NicknameCheck() {
      axios
        .get(`${SERVER_URL}/user/check/${this.nick_name}`)
        .then(({ data }) => {
          // console.log(data);
          if (data === true) {
            this.isDuplicate = true;
            this.errormsg['nick_name'] = `중복된 닉네임입니다.`;
            this.correctmsg['nick_name'] = ``;
          } else {
            this.isDuplicate = false;
            this.errormsg['nick_name'] = ``;
            //console.log(this.nicknamecheck);
            this.nicknamecheck = true;
          }
        })
        .catch(() => {});
    },

    UserProfileEdit() {
      let photo = document.getElementById('input-file').files[0];

      if (photo != null) {
        const formData = new FormData();
        formData.append('imgFile', photo);
        formData.append('uid', this.getUserUid);

        // for (let key of formData.entries()) {
        //   console.log('${key}');
        // }

        axios
          .put(`${SERVER_URL}/user/updateImg`, formData, {
            headers: {
              'Content-Type': 'multipart/form-data',
            },
          })

          .then(({ data }) => {
            // console.log(data);
            if (data.msg == 'success') {
              alert('회원정보가 변경되었습니다.');
              this.giveAlert = true;
              this.$store.commit('SETIMAGE', data.imgurl);
              this.$router.push('/mypage');
            }
          })
          .catch(() => {
            alert('에러가 발생했습니다.');
          });
      }

      if (
        this.isDuplicate == false &&
        this.nicknamecheck == true &&
        this.nick_name != ''
      ) {
        axios
          .put(`${SERVER_URL}/user/updateDetail`, {
            uid: this.getUserUid,
            nick_name: this.nick_name,
          })

          .then(({ data }) => {
            // console.log(data);
            if (data == 'success' && !this.giveAlert) {
              alert('회원정보가 변경되었습니다.');
              this.$router.push('/mypage');
            }
          })
          .catch(() => {
            alert('에러가 발생했습니다.');
          });
      } else {
        alert('닉네임 중복체크를 해주세요');
      }
    },

    UserPasswordEdit() {
      if (
        this.password == this.passwordcheck &&
        this.password.length > 0 &&
        this.passwordcheck.length > 0
      ) {
        axios
          .put(`${SERVER_URL}/user/updateDetail`, {
            uid: this.getUserUid,
            password: this.password,
          })

          .then(({ data }) => {
            // console.log(data);
            if (data == 'success') {
              alert('비밀번호가 변경되었습니다.');
            }
          })
          .catch(() => {
            alert('에러가 발생했습니다.');
          });
      }
    },
  },
  computed: {
    ...mapGetters(['getUserUid']),
  },
};
</script>
