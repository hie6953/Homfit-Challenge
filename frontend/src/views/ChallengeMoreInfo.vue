<template>
  <div>
    <challenge-title
      :equalNickName="challenge.nick_name === getUserNickName"
      :challenge_title="challenge.challenge_title"
      :challenge_img="challenge.challenge_img"
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
      :user_img="challenge.user_img"
      :people="challenge.people"
      @challengeEdit="ChallengeEdit"
    ></challenge-title>

    <div id="info-navbar">
      <div class="mx-auto align-center">
        <ul class="info-navbar-ul">
          <li @click="moveScroll(1)">설명</li>
          <li @click="moveScroll(2)">인증방법</li>
          <li @click="moveScroll(3)">달성률</li>
          <li v-if="challenge.check_date == 2" @click="moveScroll(4)">후기</li>
        </ul>
      </div>
    </div>

    <div class="row col-12 col-lg-8 mx-auto mt-4">
      <div class="col-12 info-main">
        <p id="challenge-contents" class="more-info-title">챌린지 설명</p>
        <challenge-contents
          :challenge_contents="challenge.challenge_contents"
        ></challenge-contents>

        <p id="challenge-certify-contents" class="more-info-title">
          챌린지 인증방법
        </p>
        <challenge-certify-contents
          :challenge_certify_contents="challenge.challenge_certify_contents"
          :good_img="challenge.good_img"
          :bad_img="challenge.bad_img"
          :only_cam="challenge.only_cam"
        ></challenge-certify-contents>

        <p id="challenge-result" class="more-info-title">챌린지 달성률</p>
        <challenge-result
          :average_rate="challenge.average_rate"
          :isMobile="isMobile"
        ></challenge-result>

        <p
          id="challenge-review"
          class="more-info-title"
          v-if="challenge.check_date == 2"
        >
          챌린지 후기
        </p>
        <challenge-review
          v-if="challenge.check_date == 2 && reviewList.length > 0"
          :reviewList="reviewList"
          :avg_review="avg_review"
        ></challenge-review>
        <div v-else-if="challenge.check_date == 2 && reviewList.length == 0">
          <p>등록된 후기가 없습니다.</p>
        </div>
      </div>
    </div>

    <div v-if="!isMobile">
      <b-button-group
        v-if="getAccessToken"
        id="info-float-button-group"
        class="row info-float align-center"
      >
        <b-button id="bookmark-button" @click="checkBookmark">
          <b-icon v-if="!isBookmarked" icon="bookmark" scale="1.6"></b-icon>
          <b-icon
            v-if="isBookmarked"
            class="bookmark-fill-color"
            icon="bookmark-fill"
            scale="1.6"
          ></b-icon>
        </b-button>
        <b-button
          v-if="isParticipant"
          class="apply-button pc"
          @click="GoChallengeDoing"
          >참여중</b-button
        >
        <b-button
          v-else-if="canParticiapnt"
          class="apply-button pc"
          @click="ChallengeApply"
          >참여하기</b-button
        >
        <b-button v-else class="apply-button pc cantParticipant"
          >참여할 수 없습니다.</b-button
        >
      </b-button-group>
      <div v-else class="info-float align-center col-12">
        <b-button class="apply-button no-login pc mt-2" @click="GoLogin"
          >로그인하기</b-button
        >
      </div>
    </div>
    <div v-if="isMobile">
      <div class="info-float">
        <div v-if="getAccessToken" class="row info-float-inside col-12 mx-auto">
          <div class="col-2 align-center my-auto">
            <button class="my-auto" id="bookmark-button" @click="checkBookmark">
              <b-icon v-if="!isBookmarked" icon="bookmark" scale="1.6"></b-icon>
              <b-icon
                v-if="isBookmarked"
                class="bookmark-fill-color"
                icon="bookmark-fill"
                scale="1.6"
              ></b-icon>
            </button>
          </div>
          <div v-if="isParticipant" class="col-10 align-center my-auto">
            <b-button class="apply-button mobile" @click="GoChallengeDoing"
              >참여중</b-button
            >
          </div>
          <div v-else-if="canParticiapnt" class="col-10 align-center my-auto">
            <b-button class="apply-button mobile" @click="ChallengeApply"
              >참여하기</b-button
            >
          </div>
          <div v-else class="col-10 align-center my-auto">
            <b-button class="apply-button mobile cantParticipant"
              >참여할 수 없습니다.</b-button
            >
          </div>
        </div>

        <div v-else class="col-12 align-center my-auto">
          <b-button class="apply-button mobile mt-2" @click="GoLogin"
            >로그인하기</b-button
          >
        </div>
      </div>
    </div>
    <review-more
      :challenge_title="challenge.challenge_title"
      :challenge_id="challenge_id"
    ></review-more>
  </div>
</template>
<script>
import ChallengeTitle from "@/components/ChallengeMoreInfo/ChallengeTitle.vue";
import ChallengeContents from "@/components/ChallengeMoreInfo/ChallengeContents.vue";
import ChallengeCertifyContents from "@/components/ChallengeMoreInfo/ChallengeCertifyContents.vue";
import ChallengeResult from "@/components/ChallengeMoreInfo/ChallengeResult.vue";
import ChallengeReview from "@/components/ChallengeMoreInfo/ChallengeReview.vue";
import ChallengeListDummyData from "@/assets/dummyData/challengeDummyData.json";
import ReviewMore from "@/components/ChallengeMoreInfo/ReviewMore.vue";
import "@/assets/css/ChallengeMoreInfo/challengeMoreInfo.css";
import swal from '@/assets/javascript/sweetAlert.js';
import { mapGetters } from "vuex";

import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "ChallengeMoreInfo",
  components: {
    ChallengeTitle,
    ChallengeContents,
    ChallengeCertifyContents,
    ChallengeResult,
    ChallengeReview,
    ReviewMore,
  },
  data() {
    return {
      // 모바일여부
      isMobile: false,
      // 화면 스크롤 위치
      challengeContentsLocation: 0,
      challengeCertifyContentsLocation: 0,
      challengeResultLocation: 0,
      challengeReviewLocation: 0,
      scrollPosition: 0,
      // 참여여부
      challenge_id: 0,
      isBookmarked: false,
      isParticipant: false,
      canParticiapnt: false,
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
        average_rate: 0,
        make_date: "",
        nick_name: "",
        make_uid: "",
        check_date: 0,
        period: 0,
      },
      reviewList: [],
      avg_review: 0,
    };
  },
  created() {
    this.challenge_id = this.$route.params.challenge_id;
    //챌린지 상세 정보 조회
    axios
      .get(`${SERVER_URL}/challenge/${this.challenge_id}`)
      .then(({ data }) => {
        
        this.challenge = data.challenge;
        this.reviewList = data.review;
        this.avg_review = data.avg_review;
        this.calculateCanParticipant(); //참여가능여부 측정
        
        console.log(this.challenge);
      })
      .catch(() => {
        swal.error('챌린지 정보를 불러오지 못했습니다.');
        this.challenge = ChallengeListDummyData.challengeList[0];
      });

    //로그인했으면 북마크, 참여정보 조회
    if (this.getAccessToken) {
      axios
        .get(`${SERVER_URL}/challenge/user/${this.challenge_id}`, {
          params: {
            uid: this.getUserUid,
          },
        })
        .then(({ data }) => {
          // console.log(data);
          this.isBookmarked = data.bookmark == "1";
          this.isParticipant = data.participant == "1";
        })
        .catch(() => {
          swal.error('챌린지 관련 회원정보를 가져오지 못했습니다.');
        });
    }
  },

  methods: {
    // 화면 너비에 따른 모바일 여부 판단
    handleResize: function() {
      this.isMobile = window.innerWidth <= 480;
      this.calculateScroll();
    },

    checkBookmark: function() {
      if (!this.isBookmarked) {
        // 북마크 신청
        axios
          .post(`${SERVER_URL}/user/bookmark`, {
            challenge_id: this.challenge_id,
            uid: this.getUserUid,
          })
          .then(() => {
            swal.success('북마크가 등록되었습니다.');
            this.isBookmarked = !this.isBookmarked;
          })
          .catch(() => {
            swal.error('오류가 발생했습니다.');
          });
      } else {
        // 북마크 해제
        axios
          .delete(`${SERVER_URL}/user/bookmark/${this.challenge_id}`, {
            params: {
              uid: this.getUserUid,
            },
          })
          .then(() => {
            swal.success('북마크가 해제되었습니다.');
            this.isBookmarked = !this.isBookmarked;
          })
          .catch(() => {
            swal.error('오류가 발생했습니다.');
          });
      }
    },
    // 스크롤 이동
    moveScroll: function(pos) {
      this.calculateScroll();
      let dest = 0;
      switch (pos) {
        case 1:
          dest = this.challengeContentsLocation;
          break;
        case 2:
          dest = this.challengeCertifyContentsLocation;
          break;
        case 3:
          dest = this.challengeResultLocation;
          break;
        case 4:
          dest = this.challengeReviewLocation;
          break;
      }
      window.scrollTo({ top: dest, behavior: "smooth" });
    },
    // 로그인페이지로 가기
    GoLogin: function() {
      this.$router.push({
        name: "login",
        params: { nextRoute: `challenge-more-info/${this.challenge_id}` },
      });
    },
    // 챌린지 참여중 페이지로 이동
    GoChallengeDoing: function() {
      this.$router.push(`/participated/${this.challenge_id}`);
    },
    // 챌린지 참여
    ChallengeApply: function() {
      if (this.getAccessToken != null) {
        axios
          .post(`${SERVER_URL}/challenge/join/${this.challenge_id}`, {
            uid: this.getUserUid,
          })
          .then(() => {
            swal.success('성공적으로 참여되었습니다.');
            this.isParticipant = true;
          })
          .catch(() => {
            swal.error('오류가 발생했습니다.');
          });
      } else {
        this.$router.push({
          name: "login",
          params: { nextRoute: `challenge-more-info/${this.challenge_id}` },
        });
      }
    },
    // 챌린지 수정페이지로 이동
    ChallengeEdit: function() {
      this.$router.push(`/challenge-edit/${this.challenge_id}`);
    },
    //참여가능여부 판단(진행전이거나, 진행시작날일 때 참여가능)
    calculateCanParticipant: function() {
      // console.log(this.challenge.start_date);
      let startTimeArr = this.challenge.start_date.split("-");
      let limitTime = new Date(
        startTimeArr[0],
        startTimeArr[1]-1,
        startTimeArr[2],
        23,
        59,
        59
      );
      if (
        this.challenge.check_date == 0 ||
        (this.challenge.check_date == 1 && new Date() <= limitTime)
      ) {
        this.canParticiapnt = true;
      }
    },
    // 스크롤위치 계산
    calculateScroll: function() {
      let deviceOffset = 190;
      if (this.isMobile) {
        deviceOffset = 120;
      }

      this.challengeContentsLocation =
        document.getElementById("challenge-contents").getBoundingClientRect()
          .top +
        window.pageYOffset -
        deviceOffset;
      this.challengeCertifyContentsLocation =
        document
          .getElementById("challenge-certify-contents")
          .getBoundingClientRect().top +
        window.pageYOffset -
        deviceOffset;
      this.challengeResultLocation =
        document.getElementById("challenge-result").getBoundingClientRect()
          .top +
        window.pageYOffset -
        deviceOffset;
      if (this.challenge.check_date == 2) {
        this.challengeReviewLocation =
          document.getElementById("challenge-review").getBoundingClientRect()
            .top +
          window.pageYOffset -
          deviceOffset;
      }
    },
  },
  computed: {
    ...mapGetters(["getUserUid", "getAccessToken", "getUserNickName"]),
  },
  mounted() {
    // 화면 너비 측정 이벤트 추가
    window.addEventListener("resize", this.handleResize);
    this.handleResize();
  },

  beforeDestroy() {
    // 화면 너비 측정 이벤트 제거
    window.removeEventListener("resize", this.handleResize);
  },
};
</script>
