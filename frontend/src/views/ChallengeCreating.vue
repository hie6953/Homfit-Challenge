<template>
  <div class="background">
    <div class="component col-md-8 col-10 mx-auto">
      <!-- 챌린지 개설 페이지 -->
      <div v-if="page == 1">
        <ChallengeMain
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
          @PrevPage="PageTwoPrev"
          @NextPage="PageTwoNext"
        ></ChallengePeriod>
      </div>
      <div v-if="page == 3">
        <ChallengeCertification
          :props_dayList="challenge.dayList"
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

import '@/assets/css/challengecreating.css';

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

      // 챌린지
      challenge: {
        fit_id: 1,
        bodyList: [],
        challenge_title: '',
        challenge_contents: '',
        challenge_img: '',
        start_date: '',
        end_date: '',
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
      this.challenge.period = Math.ceil(
        (new Date(this.challenge.end_date) -
          new Date(this.challenge.start_date)) /
          (1000 * 3600 * 24)
      );
      // 리스트 내 string -> integer 변환
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
      fit_id,
      bodyList,
      challenge_title,
      challenge_contents
    ) {
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
      ++this.page;
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
