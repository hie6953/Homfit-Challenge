<template>
  <div>
    <challenge-title
      :equalNickName="challenge.nick_name === getUserNickName" 
      :challenge_title="challenge.challenge_title"
      challenge_img=""
      :day_certify_count="challenge.day_certify_count"
      :start_date="challenge.start_date"
      :end_date="challenge.end_date"
      :period="challenge.period"
      :fit_id="challenge.fit_id"
      :kind="challenge.kind"
      :daylist_string="challenge.daylist_string"
      :tagList="challenge.tagList"
      :bodyList="challenge.bodyList"
      :check_date="challenge.check_date"
      :nick_name="challenge.nick_name"
      :people="challenge.people"
      @challengeEdit="ChallengeEdit"
    ></challenge-title>



    <div id="info-navbar">
      <div class="mx-auto align-center">
        <ul class="info-navbar-ul">
          <li @click="moveScroll(1)">설명</li>
          <li @click="moveScroll(2)">인증방법</li>
          <li @click="moveScroll(3)">달성률</li>
          <li @click="moveScroll(4)">후기</li>
        </ul>
      </div>
    </div>



    <div class="row col-12 col-lg-8 mx-auto">
      <div class="info-main">
        <challenge-contents
          id="challenge-contents"
          :challenge_contents="challenge.challenge_contents"
        ></challenge-contents>
        
        <challenge-certify-contents
          id="challenge-certify-contents"
          :challenge_certify_contents="challenge.challenge_certify_contents"
          :only_cam="challenge.only_cam"
        ></challenge-certify-contents>
        
        <challenge-result id="challenge-result"></challenge-result>
        
        <challenge-review id="challenge-review"></challenge-review>
        
      </div>

     
    </div>

    <div v-if="!isMobile">
        <div class="row info-float align-center">
          <div class="col-2 my-auto">
            <button class="my-auto" id="bookmark-button" @click="checkBookmark">
              <b-icon v-if="!isBookmarked" icon="bookmark" scale="1.6"></b-icon>
              <b-icon v-if="isBookmarked" icon="bookmark-fill" scale="1.6"></b-icon>
            </button>
          </div>
          <div class="col-10 my-auto">
            <b-button class="apply-button pc"  @click="ChallengeApply">참가하기</b-button>
          </div>
        </div>
      </div>
    <div v-if="isMobile">
      <div class="info-float">
        <div class="row info-float-inside col-12 mx-auto">
          <div class="col-2 align-center my-auto">
            <button class="my-auto" id="bookmark-button" @click="checkBookmark">
              <b-icon v-if="!isBookmarked" icon="bookmark" scale="1.6"></b-icon>
              <b-icon v-if="isBookmarked" icon="bookmark-fill" scale="1.6"></b-icon>
            </button>
          </div>
          <div class="col-10 align-center my-auto">
            <b-button class="apply-button mobile" @click="ChallengeApply"
              >참가하기</b-button
            >
          </div>
        </div>
      </div>
    </div>
    
  </div>
</template>
<script>
import ChallengeTitle from "@/components/ChallengeMoreInfo/ChallengeTitle.vue";
import ChallengeContents from "../components/ChallengeMoreInfo/ChallengeContents.vue";
import ChallengeCertifyContents from "../components/ChallengeMoreInfo/ChallengeCertifyContents.vue";
import ChallengeResult from "../components/ChallengeMoreInfo/ChallengeResult.vue";
import ChallengeReview from "../components/ChallengeMoreInfo/ChallengeReview.vue";
import "@/assets/css/ChallengeMoreInfo/challengeMoreInfo.css";

import { mapGetters } from "vuex";

import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

// const innerWidth = window.innerWidth;
export default {
  name: "ChallengeMoreInfo",
  components: {
    ChallengeTitle,
    ChallengeContents,
    ChallengeCertifyContents,
    ChallengeResult,
    ChallengeReview,
  },
  data() {
    return {
      isMobile: false,
      challengeCertifyContentsLocation: 0,
      challengeResultLocation: 0,
      challengeReviewLocation: 0,
      scrollPosition: 0,
      challenge_id: 0,
      isBookmarked:false,
      isApplied:'',
      challenge: {
        kind: 0,
        fit_id: 1,
        bodyList: [],
        challenge_title: "",
        challenge_contents: "",
        challenge_img: "",
        start_date: null,
        end_date: null,
        dayList: [],
        day_certify_count: 1,
        challenge_certify_contents: "",
        good_img: "",
        bad_img: "",
        only_cam: 1,
        tagList: [],
        make_date: "",
        make_uid: "",
        check_date: 0,
        period: 0,
      },
      user: {
        uid: "",
      },
    };
  },
  created() {
    this.challenge_id = this.$route.params.challenge_id;
    axios
      .get(`${SERVER_URL}/challenge/${this.challenge_id}`)
      .then(({ data }) => {
        this.challenge = data;
        console.log(this.challenge);
      })
      .catch(() => {
        alert("챌린지 목록을 불러오지 못했습니다.");
      });
  },
  watch:{
    isBookmarked:function(){
      if(this.isBookmarked){
        // 북마크 신청
      }else{
        // 북마크 해제
      }
    },
  },
  methods: {
    // 화면 너비에 따른 모바일 여부 판단
    handleResize: function() {
      this.isMobile = window.innerWidth <= 992;
    },
    // 스크롤 위치 판단
    handleScroll: function() {
      let scrollPosition = window.scrollY || document.documentElement.scrollTop;
      if (scrollPosition >= this.challengeReviewLocation) {
        this.scrollPosition = 4;
      } else if (scrollPosition >= this.challengeResultLocation) {
        this.scrollPosition = 3;
      } else if (scrollPosition >= this.challengeCertifyContentsLocation) {
        this.scrollPosition = 2;
      } else {
        this.scrollPosition = 1;
      }
    },

    checkBookmark:function(){
      this.isBookmarked = !this.isBookmarked;
    },
    moveScroll: function(pos) {
      let dest = 0;
      if (pos == 2) {
        dest = this.challengeCertifyContentsLocation;
      } else if (pos == 3) {
        dest = this.challengeResultLocation;
      } else if (pos == 4) {
        dest = this.challengeReviewLocation;
      }
      window.scrollTo({ top: dest, behavior: "smooth" });
    },
    ChallengeApply: function() {
      if (this.getAccessToken != null) {
        this.user.uid = this.getUserUid;
        axios
          .post(`${SERVER_URL}/challenge/join/${this.challenge_id}`, this.user)
          .then(() => {
            alert("성공적으로 참여되었습니다.");
          })
          .catch(() => {
            alert("오류가 발생했습니다. 다시 한번 시도해주세요.");
          });
      } else {
        this.$router.push({name:'login', params:{nextRoute:`challenge-more-info/${this.challenge_id}`}});
      }
    },
    ChallengeEdit:function(){
      this.$router.push(`/challenge-edit/${this.challenge_id}`);
    }
  },
  computed: {
    ...mapGetters(["getUserUid", "getAccessToken","getUserNickName"]),
  },
  mounted() {
    let infoNavbarHeight = 160;
    this.challengeCertifyContentsLocation = document.getElementById(
      "challenge-certify-contents"
    ).getBoundingClientRect().top + window.pageYOffset-infoNavbarHeight;
    this.challengeResultLocation = document.getElementById(
      "challenge-result"
    ).getBoundingClientRect().top + window.pageYOffset-infoNavbarHeight;
    this.challengeReviewLocation = document.getElementById(
      "challenge-review"
    ).getBoundingClientRect().top + window.pageYOffset-infoNavbarHeight;

    // 화면 너비 측정 이벤트 추가/
    this.handleResize();
    window.addEventListener("resize", this.handleResize);
    this.handleScroll();
    window.addEventListener("scroll", this.handleScroll);
  },
};
</script>
