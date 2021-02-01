<template>
  <div class="background-creating ">
    <div class="component col-md-8 col-11 mx-auto">
      <div class="creating-progress-bar">
        <ul id="creating-progress-list">
          <li>
            <img src="@/assets/challengeCreating/information.png" />
            <br />
            <span
              class="creating-progress-check-icon"
              :class="{ 'this-page': page == 1 }"
            >
              <b-icon v-if="pageComplete[0] == 0" icon="x"></b-icon>
              <b-icon
                v-if="pageComplete[0] == 1"
                icon="arrow-clockwise"
              ></b-icon>
              <b-icon v-if="pageComplete[0] == 2" icon="check"></b-icon>
            </span>
            <p>소개</p>
          </li>
          <li>
            <img src="@/assets/challengeCreating/calendar.png" />
            <br />
            <span
              class="creating-progress-check-icon"
              :class="{ 'this-page': page == 2 }"
            >
              <b-icon v-if="pageComplete[1] == 0" icon="x"></b-icon>
              <b-icon
                v-if="pageComplete[1] == 1"
                icon="arrow-clockwise"
              ></b-icon>
              <b-icon v-if="pageComplete[1] == 2" icon="check"></b-icon>
            </span>
            <p>기간</p>
          </li>
          <li>
            <img src="@/assets/challengeCreating/certification.png" />
            <br />
            <span
              class="creating-progress-check-icon"
              :class="{ 'this-page': page == 3 }"
            >
              <b-icon v-if="pageComplete[2] == 0" icon="x"></b-icon>
              <b-icon
                v-if="pageComplete[2] == 1"
                icon="arrow-clockwise"
              ></b-icon>
              <b-icon v-if="pageComplete[2] == 2" icon="check"></b-icon>
            </span>
            <p>인증</p>
          </li>
          <li>
            <img src="@/assets/challengeCreating/hashtag.png" />
            <br />
            <span
              class="creating-progress-check-icon"
              :class="{ 'this-page': page == 4 }"
            >
              <b-icon v-if="pageComplete[3] == 0" icon="x"></b-icon>
              <b-icon
                v-if="pageComplete[3] == 1"
                icon="arrow-clockwise"
              ></b-icon>
              <b-icon v-if="pageComplete[3] == 2" icon="check"></b-icon>
            </span>
            <p>태그</p>
          </li>
        </ul>
      </div>

      <div v-if="page == 1">
        <ChallengeMain
          :props_kind="challenge.kind"
          :props_fit_id="challenge.fit_id"
          :props_bodyList="challenge.bodyList"
          :props_challenge_title="challenge.challenge_title"
          :props_challenge_contents="challenge.challenge_contents"
          @NextPage="PageOneNext"
        ></ChallengeMain>
      </div>
      <div v-if="page == 2">
        <ChallengePeriod
          :props_start_date="challenge.start_date"
          :props_end_date="challenge.end_date"
          :props_period="challenge.period"
          :props_dayList="challenge.dayList"
          @PrevPage="PageTwoPrev"
          @NextPage="PageTwoNext"
        ></ChallengePeriod>
      </div>
      <div v-if="page == 3">
        <ChallengeCertification
          :props_day_certify_count="challenge.day_certify_count"
          :props_challenge_certify_contents="
            challenge.challenge_certify_contents
          "
          :props_good_img="challenge.good_img"
          :props_bad_img="challenge.bad_img"
          :props_only_cam="challenge.only_cam"
          @PrevPage="PageThreePrev"
          @NextPage="PageThreeNext"
        ></ChallengeCertification>
      </div>
      <div v-if="page == 4">
        <ChallengeTag
          :props_tagList="challenge.tagList"
          @PrevPage="PageFourPrev"
          @CreateChallenge="CreateChallenge"
        ></ChallengeTag>
      </div>
    </div>
  </div>
</template>

<script>
import ChallengeMain from '@/components/ChallengeCreating/ChallengeMain.vue';
import ChallengePeriod from '@/components/ChallengeCreating/ChallengePeriod.vue';
import ChallengeCertification from '@/components/ChallengeCreating/ChallengeCertification.vue';
import ChallengeTag from '@/components/ChallengeCreating/ChallengeTag.vue';

import '@/assets/css/ChallengeCreating/challengecreating.css';

import { mapGetters } from 'vuex';

import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'ChallengeCreating',
  components: {
    ChallengeMain,
    ChallengePeriod,
    ChallengeCertification,
    ChallengeTag,
  },
  data() {
    return {
      // 페이지
      page: 1,
      pageComplete: [1, 0, 0, 0], //0:안함, 1:진행중, 2:완료
      challenge: {
        kind: 0,
        fit_id: 1,
        bodyList: [],
        challenge_title: '',
        challenge_contents: 'dd',
        challenge_img: '',
        start_date: null,
        end_date: null,
        dayList: [],
        day_certify_count: 0,
        challenge_certify_contents: '',
        good_img: '',
        bad_img: '',
        only_cam: 1,
        tagList: [],
        make_date: '',
        make_uid: '',
        check_date: 0,
        period: 0,
      },
    };
  },

  computed: {
    ...mapGetters(['getUserUid']),
  },

  methods: {
    // 챌린지 개설
    CreateChallenge: function(tagList) {
      this.challenge.tagList = tagList;
      this.challenge.make_date = this.FormatedMakeDate();
      this.challenge.make_uid = this.getUserUid;
      let tempBodyList = new Array(this.challenge.bodyList.length);
      for (let index = 0; index < this.challenge.bodyList.length; index++) {
        tempBodyList[index] = parseInt(this.challenge.bodyList[index]);
      }
      this.challenge.bodyList = tempBodyList.sort();
      let tempDayList = new Array(this.challenge.dayList.length);
      for (let index = 0; index < this.challenge.dayList.length; index++) {
        tempDayList[index] = parseInt(this.challenge.dayList[index]);
      }
      this.challenge.dayList = tempDayList.sort();

      // 챌린지 개설 axios
      console.log(this.challenge);
      axios
        .post(`${SERVER_URL}/challenge`, this.challenge)
        .then(() => {
          alert('챌린지가 생성되었습니다.');
          this.$router.push('/challengelist');
        })
        .catch(() => {
          alert('등록 처리시 에러가 발생했습니다.');
        });
    },

    // 1페이지
    PageOneNext: function(
      kind,
      fit_id,
      bodyList,
      challenge_title,
      challenge_contents
    ) {
      this.challenge.kind = kind;
      this.challenge.fit_id = fit_id;
      this.challenge.bodyList = bodyList;
      this.challenge.challenge_title = challenge_title;
      this.challenge.challenge_contents = challenge_contents;
      this.NextPage();
    },

    // 2페이지
    PageTwoPrev: function(start_date, end_date) {
      this.challenge.start_date = start_date;
      this.challenge.end_date = end_date;

      this.PrevPage();
    },
    PageTwoNext: function(start_date, end_date) {
      this.challenge.start_date = start_date;
      this.challenge.end_date = end_date;
      this.NextPage();
    },

    // 3페이지
    PageThreePrev: function(
      dayList,
      day_certify_count,
      challenge_certify_contents,
      only_cam
    ) {
      this.challenge.dayList = dayList;
      this.challenge.day_certify_count = day_certify_count;
      this.challenge.challenge_certify_contents = challenge_certify_contents;
      this.challenge.only_cam = only_cam;
      this.PrevPage();
    },
    PageThreeNext: function(
      dayList,
      day_certify_count,
      challenge_certify_contents,
      only_cam
    ) {
      this.challenge.dayList = dayList;
      this.challenge.day_certify_count = day_certify_count;
      this.challenge.challenge_certify_contents = challenge_certify_contents;
      this.challenge.only_cam = only_cam;
      this.NextPage();
    },

    // 4페이지
    PageFourPrev: function(tagList) {
      this.challenge.tagList = tagList;
      this.PrevPage();
    },

    // 페이지전환
    NextPage: function() {
      this.pageComplete[this.page] == 2;
      ++this.page;
      this.pageComplete[this.page] == 1;
    },
    PrevPage: function() {
      --this.page;
    },

    // 개설 날짜 형식 구성
    FormatedMakeDate: function() {
      var d = new Date(),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

      if (month.length < 2) month = '0' + month;
      if (day.length < 2) day = '0' + day;

      return [year, month, day].join('-');
    },
  },
};
</script>
