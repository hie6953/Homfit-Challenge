<template>
  <div>
    <challenge-title
      :challenge_title="challenge.challenge_title"
      challenge_img=""
      :day_certify_count="challenge.day_certify_count"
      :start_date="challenge.start_date"
      :end_date="challenge.end_date"
      :nick_name="challenge.nick_name"
      :period="challenge.period"
      :fit_id="challenge.fit_id"
      :people="challenge.people"
      :kind="challenge.kind"
      :daylist_string="challenge.daylist_string"
      :tagList="challenge.tagList"
      :bodyList="challenge.bodyList"
      :check_date="challenge.check_date"
    ></challenge-title>
    <div id="info-navbar">
      <div class="mx-auto align-center">
      <ul class="info-navbar-ul">
        <li>설명</li>
        <li>인증방법</li>
        <li>역대결과</li>
        <li>후기</li>
      </ul>
      </div>
    </div>
    <div class="row col-11 col-lg-8 mx-auto">
      <div class="col-12 col-lg-8 info-main">
        <challenge-contents
          :challenge_contents="challenge.challenge_contents"
        ></challenge-contents>
        <challenge-certify-contents
          :challenge_certify_contents="challenge.challenge_certify_contents"
          :only_cam="challenge.only_cam"
        ></challenge-certify-contents>
      </div>

      <div v-if="!isMobile" class="col-12 col-lg-4">
        <div class="info-float align-center">
            <b-button id="apply-button"  @click="ChallengeApply">참가하기</b-button>
        </div>
      </div>
    </div>
    <div v-if="isMobile">
      <div class="info-float">
        <div class="row info-float-inside col-12 mx-auto">
          <div class="col-2 align-center my-auto">
            <button class="my-auto" id="bookmark-button">
              <b-icon icon="bookmark" scale="1.6"></b-icon>
            </button>
          </div>
          <div class="col-10 align-center my-auto">
            <b-button id="apply-button" @click="ChallengeApply"
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
import "@/assets/css/ChallengeMoreInfo/challengeMoreInfo.css";

import { mapGetters } from "vuex";

import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "ChallengeMoreInfo",
  components: {
    ChallengeTitle,
    ChallengeContents,
    ChallengeCertifyContents,
  },
  data() {
    return {
      isMobile: false,
      challenge_id: 0,
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
  methods: {
    // 화면 너비에 따른 모바일 여부 판단
    handleResize: function() {
      this.isMobile = window.innerWidth <= 992;
    },
    ChallengeApply: function() {
      console.log(this.getAccessToken);
      if(this.getAccessToken){
      this.user.uid = this.getUserUid;
      axios
        .post(`${SERVER_URL}/challenge/join/${this.challenge_id}`, this.user)
        .then(() => {
          alert("성공적으로 참여되었습니다.");
        })
        .catch(() => {
          alert("오류가 발생했습니다. 다시 한번 시도해주세요.");
        });
      }else{
        this.$router.push(`/login/challengelist`);
      }

    },
  },
  computed: {
    ...mapGetters(["getUserUid","getAccessToken"]),
  },
  mounted() {
    // 화면 너비 측정 이벤트 추가/
    this.handleResize();
    window.addEventListener("resize", this.handleResize);
  },
};
</script>
