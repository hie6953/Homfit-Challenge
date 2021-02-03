<template>
  <div>
    <challenge-title></challenge-title>
    <div id="info-navbar">
      <b-button>설명</b-button>
      <b-button>인증방법</b-button>
      <b-button>역대결과</b-button>
      <b-button>후기</b-button>
    </div>
      <div class="row col-11 col-lg-8 mx-auto">
        <div class="col-12 col-lg-8">
          <br />
          <img src="@/assets/logo.png" alt="" />
          <br />
          <div style="background-color:red;">너비</div>
          <img src="@/assets/logo.png" alt="" /><br />
          <img src="@/assets/logo.png" alt="" /><br />
          <img src="@/assets/logo.png" alt="" /><br />
          <img src="@/assets/logo.png" alt="" /><br />
          <img src="@/assets/logo.png" alt="" />
        </div>

        <div v-if="!isMobile" class="col-12 col-lg-4">
          <div class="info-float">
              <div >

              <div style="background-color:blue;">신청버튼</div>
              </div>
            
          </div>
        </div>
      </div>
    <div v-if="isMobile">
      <div class="info-float">
          <div style="background-color:blue;">신청버튼</div>
        
      </div>
    </div>
  </div>
</template>
<script>
import ChallengeTitle from "@/components/ChallengeMoreInfo/ChallengeTitle.vue";
import "@/assets/css/ChallengeMoreInfo/challengeMoreInfo.css";

export default {
  components: {
    ChallengeTitle,
  },
  data() {
    return {
      isMobile: false,
    };
  },
  methods: {
    // 화면 너비에 따른 모바일 여부 판단
    handleResize: function() {
      this.isMobile = window.innerWidth <= 992;
    },
  },
  mounted() {
    // 화면 너비 측정 이벤트 추가/
    this.handleResize();
    window.addEventListener("resize", this.handleResize);
  },
};
</script>
