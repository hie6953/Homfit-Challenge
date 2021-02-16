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
              <!-- <div v-if="this.user_img == ''" class="edit-user-photo">
                <img
                  class="profiledeit-user-img"
                  src="@/assets/NavBar/anonimous_user.png"
                />
              </div> -->
              <div class="profileedit-tmp">
                <div v-if="changeuserimg" class="edit-user-photo">
                  <img class="profiledeit-user-img" :src="imgurl" />
                </div>

                <div v-else class="edit-user-photo">
                  <img class="profiledeit-user-img" :src="user_img" />
                </div>
              </div>
              <div class="edit-user-photo-input">
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
import Swal from 'sweetalert2';

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
      imgurl: '',
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

  methods: {
    fileSelect: function(event) {
      var input = event.target;
      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = (e) => {
          this.imgurl = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
        this.changeuserimg = true;
      }
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
              // alert('회원정보가 변경되었습니다.');
              this.giveAlert = true;
              this.$store.commit('SETIMAGE', data.imgurl);
              this.$router.push('/mypage');
              Swal.fire({
                position: 'center',
                icon: 'success',
                title: '회원정보가 변경되었습니다.',
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
              // alert('회원정보가 변경되었습니다.');
              Swal.fire({
                position: 'center',
                icon: 'success',
                title: '회원정보가 변경되었습니다.',
                showConfirmButton: false,
                timer: 1500,
              });
              this.$router.push('/mypage');
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
              // alert('비밀번호가 변경되었습니다.');
              this.$router.push('/mypage');
              Swal.fire({
                position: 'center',
                icon: 'success',
                title: '비밀번호가 변경되었습니다.',
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
      }
    },
  },
  computed: {
    ...mapGetters(['getUserUid']),
  },
};
</script>
