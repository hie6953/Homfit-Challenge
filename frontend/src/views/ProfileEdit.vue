<template>
  <div class="mt-3">
    <hr id="hr-top" />

    <div class="mx-auto col-8 profile-edit-container">
      <div class="row">
        <!-- left column -->
        <div class="col-md-3">
          <div class="text-center">
            <img src="@/assets/NavBar/anonimous_user.png" class="user-img" />

            <input type="file" class="form-control" />
          </div>
        </div>

        <!-- edit form column -->
        <div class="col-md-9 personal-info">
          <form class="form-horizontal" role="form">
            <div class="form-group">
              <label class="col-lg-3 control-label">이메일</label>
              <div class="col-lg-8">
                <input
                  class="form-control"
                  type="text"
                  value="email"
                  readonly
                />
              </div>
            </div>
            <div class="form-group">
              <label class="col-lg-3 control-label">닉네임</label>
              <div class="col-lg-8">
                <input class="form-control" type="text" />
              </div>
            </div>
            <div class="form-group">
              <label class="col-lg-3 control-label">휴대폰번호</label>
              <div class="col-lg-8">
                <input class="form-control" type="text" />
              </div>
            </div>
            <div class="form-group">
              <label class="col-lg-3 control-label">성별</label>
              <div class="col-lg-8">
                <input class="form-control" type="text" />
              </div>
            </div>
            <div class="form-group">
              <label class="col-lg-3 control-label">연령</label>
              <div class="col-lg-8">
                <div class="ui-select">
                  <select id="user_time_zone" class="form-control">
                    <option value="Hawaii">(GMT-10:00) Hawaii</option>
                    <option value="Alaska">(GMT-09:00) Alaska</option>
                    <option value="Pacific Time (US &amp; Canada)"
                      >(GMT-08:00) Pacific Time (US &amp; Canada)</option
                    >
                    <option value="Arizona">(GMT-07:00) Arizona</option>
                    <option value="Mountain Time (US &amp; Canada)"
                      >(GMT-07:00) Mountain Time (US &amp; Canada)</option
                    >
                  </select>
                </div>
              </div>
            </div>
            <div class="form-group">
              <label class="col-md-3 control-label"></label>
              <div class="col-md-8">
                <input
                  type="button"
                  class="btn btn-primary"
                  value="Save Changes"
                />
                <span></span>
                <input type="reset" class="btn btn-default" value="Cancel" />
              </div>
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
