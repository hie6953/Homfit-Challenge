<template>
  <div class="background mx-auto">
    <div class="component">
      <div v-if="page == 1">
        <ChallengeMain
          :props_fit_id="challenge.fit_id"
          :props_body_id="challenge.body_id"
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

// import axios from 'axios';
// const SERVER_URL = process.env.VUE_APP_SERVER_URL;

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
      page: 1,
      challenge: {
        fit_id: 0,
        body_id: [],
        challenge_title: '',
        challenge_contents: '',
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

  methods: {
    CreateChallenge: function(tagList) {
      this.challenge.tagList = tagList;

      //   axios
      //     .post(`${SERVER_URL}/qna/create`, this.challenge)
      //     .then(({ data }) => {
      //       alert('챌린지가 생성되었습니다.' + data);
      //     })
      //     .catch(() => {
      //       alert('등록 처리시 에러가 발생했습니다.');
      //     });
    },

    PageOneNext: function(
      fit_id,
      body_id,
      challenge_title,
      challenge_contents
    ) {
      this.challenge.fit_id = fit_id;
      this.challenge.body_id = body_id;
      this.challenge.challenge_title = challenge_title;
      this.challenge.challenge_contents = challenge_contents;
      this.NextPage();
    },
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
    PageFourPrev: function(tagList) {
      this.challenge.tagList = tagList;
      this.PrevPage();
    },

    NextPage: function() {
      ++this.page;
    },
    PrevPage: function() {
      --this.page;
    },
  },
};
</script>
