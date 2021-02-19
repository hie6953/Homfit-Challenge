<template>
  <div class="mb-3">
    
    <div class="certify-contents-onlycam">
      <b-icon icon="info-circle" variant="warning"></b-icon>
      <span v-if="only_cam == 1">이 챌린지는 카메라만 사용합니다.</span>
      <span v-else>이 챌린지는 카메라와 갤러리를 사용합니다.</span>
    </div>
    <br />
    <p v-html="challenge_certify_contents"></p>
    <div class="row">
      <div class="col-12 col-md-6 align-center">
        <p class="certify-content-img-title"><span>좋은 인증샷</span></p>
        <img class="certify-content-img" :src="good_img">
      </div>
      <div class="col-12 col-md-6 align-center">
        <p class="certify-content-img-title"><span>나쁜 인증샷</span></p>
        <img class="certify-content-img" :src="bad_img">
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ChallengeCertifyContents",
  props: {
    challenge_certify_contents: String,
    good_img:String,
    bad_img:String,
    only_cam: Number,
  },
};
</script>

<style scoped>
.certify-contents-onlycam span {
  margin-left: 3px;
}

.certify-content-img-title{
  margin-top:10px;
}

.certify-content-img-title span{
  font-weight: 600;
  color: #eac13a;
  border-radius: 10px;
}

.certify-content-img{
  height:180px;
  width:100%;
  object-fit: contain;
}
</style>