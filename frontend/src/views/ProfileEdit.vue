<template>
  <div class="mt-3">
    <hr id="hr-top" />

    <div class="mx-auto col-8 profile-edit-container">
      <div class="row">
        <!-- 이미지 수정 -->
        <!-- <div class="col-md-4">
          <div class="edit-user-photo">
            <img src="@/assets/NavBar/anonimous_user.png" class="user-img" />
            <label class="input-file-button" for="input-file">
              사진선택
            </label>
            <input type="file" id="input-file" class="form-control-photo" />
          </div>
        </div> -->

        <!-- 사용자 정보 수정 -->
        <div class="personal-info">
          <!-- 이미지 -->
          <div class="form-group">
            <div class="edit-user-photo">
              <img class="user-img" src="@/assets/NavBar/anonimous_user.png" />
            </div>
            <div class="edit-user-photo">
              <label class="input-file-button" for="input-file">
                사진선택
              </label>

              <input type="file" id="input-file" class="form-control-photo" />
            </div>
          </div>

          <form class="form-horizontal">
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
                <input class="form-control" type="text" />
              </div>
            </div>
            <div class="form-group">
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
            </div>
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
                  <select id="user_time_zone" class="form-control">
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
            <br />
            <div class="submit-btn">
              <input type="submit" class="btn btn-primary" value="수정" />
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/profileedit.css';

export default {
  name: 'ProfileEdit',
  data: function() {
    return {};
  },
};
</script>
