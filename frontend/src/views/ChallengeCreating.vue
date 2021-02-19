<template>
  <div class="background-creating col-12 col-md-8  mx-auto">
    <div class="component">
      <div class="creating-progress-bar">
        <ul id="creating-progress-list">
          <li>
            <img
              v-if="pageComplete_1"
              src="@/assets/challengeCreating/information_color.png"
            />
            <img v-else src="@/assets/challengeCreating/information.png" />
            <br />
            <span
              class="creating-progress-check-icon"
              :class="{ 'this-page': page == 1 }"
            >
              <b-icon icon="dot"></b-icon>
            </span>
            <p>소개</p>
          </li>
          <li>
            <img
              v-if="pageComplete_2"
              src="@/assets/challengeCreating/calendar_color.png"
            />
            <img v-else src="@/assets/challengeCreating/calendar.png" />
            <br />
            <span
              class="creating-progress-check-icon"
              :class="{ 'this-page': page == 2 }"
            >
              <b-icon icon="dot"></b-icon>
            </span>
            <p>기간</p>
          </li>
          <li>
            <img
              v-if="pageComplete_3"
              src="@/assets/challengeCreating/certification_color.png"
            />
            <img v-else src="@/assets/challengeCreating/certification.png" />
            <br />
            <span
              class="creating-progress-check-icon"
              :class="{ 'this-page': page == 3 }"
            >
              <b-icon icon="dot"></b-icon>
            </span>
            <p>인증</p>
          </li>
          <li>
            <img
              v-if="pageComplete_4"
              src="@/assets/challengeCreating/hashtag_color.png"
            />
            <img v-else src="@/assets/challengeCreating/hashtag.png" />
            <br />
            <span
              class="creating-progress-check-icon"
              :class="{ 'this-page': page == 4 }"
            >
              <b-icon icon="dot"></b-icon>
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
          :props_challenge_img="challenge.challenge_img"
          :props_challenge_img_url="challenge.challenge_img_url"
          @NextPage="PageOneNext"
        ></ChallengeMain>
      </div>
      <div v-if="page == 2">
        <ChallengePeriod
          :props_start_date="challenge.start_date"
          :props_end_date="challenge.end_date"
          :props_period="challenge.period"
          :props_dayList="challenge.dayList"
          :props_day_certify_count="challenge.day_certify_count"
          @PrevPage="PageTwoPrev"
          @NextPage="PageTwoNext"
        ></ChallengePeriod>
      </div>
      <div v-if="page == 3">
        <ChallengeCertification
          :props_challenge_certify_contents="
            challenge.challenge_certify_contents
          "
          :props_good_img="challenge.good_img"
          :props_bad_img="challenge.bad_img"
          :props_good_img_url="challenge.good_img_url"
          :props_bad_img_url="challenge.bad_img_url"
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

      <div v-if="page == 5">
        <h3 class="align-center">
          "{{ challenge.challenge_title }}"<br />개설이 완료되었습니다.
        </h3>
        <div class="align-center mt-5">
          <router-link
            :to="{
              name: 'ChallengeMoreInfo',
              params: { challenge_id: challenge_id },
            }"
            id="challenge-creating-end-button"
            >챌린지 보러가기</router-link
          >
        </div>
      </div>
    </div>
    <div v-if="isLoading" class="loading">
      <div id="loading-icon">
        <b-icon
          icon="arrow-clockwise"
          animation="spin"
          font-scale="4"
          variant="warning"
        ></b-icon>
        <br />
        <span>챌린지 등록 중...</span>
      </div>
    </div>
  </div>
</template>

<script>
import ChallengeMain from '@/components/ChallengeCreating/ChallengeMain.vue';
import ChallengePeriod from '@/components/ChallengeCreating/ChallengePeriod.vue';
import ChallengeCertification from '@/components/ChallengeCreating/ChallengeCertification.vue';
import ChallengeTag from '@/components/ChallengeCreating/ChallengeTag.vue';
import swal from '@/assets/javascript/sweetAlert.js';
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
      pageComplete_1: false,
      pageComplete_2: false,
      pageComplete_3: false,
      pageComplete_4: false,
      challenge_id: 0,
      isLoading: false,
      challenge_img_url: '',
      good_img_url: '',
      bad_img_url: '',
      challenge: {
        kind: 1,
        fit_id: 1,
        bodyList: [1],
        challenge_title: '',
        challenge_contents: '',
        challenge_img: { file: null },
        start_date: null,
        end_date: null,
        dayList: [],
        day_certify_count: 1,
        challenge_certify_contents: '',
        good_img: { file: null },
        bad_img: { file: null },
        only_cam: 1,
        tagList: [],
        make_date: '',
        make_uid: '',
        check_date: 0,
        period: 0,
      },
    };
  },

  created() {
    // axios시 로딩처리
    axios.interceptors.request.use(
      (config) => {
        this.setLoading(true);
        return config;
      },
      (error) => {
        this.setLoading(false);
        return Promise.reject(error);
      }
    ),
      axios.interceptors.response.use(
        (response) => {
          this.setLoading(false);
          return response;
        },
        (error) => {
          this.setLoading(false);
          return Promise.reject(error);
        }
      );
  },

  computed: {
    ...mapGetters(['getUserUid']),
  },

  methods: {
    // 개설 시 로딩처리
    setLoading: function(value) {
      this.isLoading = value;
    },
    // 챌린지 개설
    CreateChallenge: function(tagList) {
      if (this.kind == 2) {
        //식단 선택의 경우 bodyList=1, fit_id = 9로 처리
        this.bodyList = [1];
        this.fit_id = 9;
      }

      this.challenge.tagList = tagList; //태그리스트 넣기
      this.challenge.make_date = this.FormatedMakeDate(); //개설날짜
      this.challenge.make_uid = this.getUserUid; //사용자 id

      // 이미지 입력 여부
      if (this.challenge.challenge_img == null) {
        this.challenge.challenge_img = '';
      }
      if (this.challenge.good_img == null) {
        this.challenge.good_img = '';
      }
      if (this.challenge.bad_img == null) {
        this.challenge.bad_img = '';
      }

      this.pageComplete_4 = true;

      // // Object To FormData 변환
      var formData = new FormData();
      formData.append('challengeImgFile', this.challenge.challenge_img.file);
      formData.append('goodImgFile', this.challenge.good_img.file);
      formData.append('badImgFile', this.challenge.bad_img.file);
      for (var i in this.challenge) {
        if (i == 'bad_img' || i == 'good_img' || i == 'challenge_img') continue;
        formData.append(i, this.challenge[i]);
      }

      // FormData의 key 확인
      // for (let key of formData.keys()) {
      //   console.log(key + " " + formData.get(key));
      // }

      // // FormData의 value 확인
      // for (let value of formData.values()) {
      //   console.log(value);
      // }

      // 챌린지 개설 axios
      axios
        .post(`${SERVER_URL}/challenge`, formData, {
          headers: { 'Content-Type': 'multipart/form-data' },
        })
        .then((success) => {
          this.page = 5;
          this.challenge_id = String(success.data);
          // console.log(this.challenge_id);
        })
        .catch(() => {
          swal.error('사진은 200MB이하로 올려주세요.');
          // console.log(error);
        });
    },

    // 1페이지
    PageOneNext: function(
      kind,
      fit_id,
      bodyList,
      challenge_title,
      challenge_contents,
      challenge_img,
      challenge_img_url
    ) {
      this.challenge.kind = kind;
      this.challenge.fit_id = fit_id;
      this.challenge.bodyList = bodyList;
      this.challenge.challenge_title = challenge_title;
      this.challenge.challenge_contents = challenge_contents;
      this.challenge.challenge_img = challenge_img;
      this.challenge.challenge_img_url = challenge_img_url;

      this.pageComplete_1 = true;
      this.NextPage();
    },

    // 2페이지
    PageTwoPrev: function(
      start_date,
      end_date,
      period,
      dayList,
      day_certify_count
    ) {
      this.challenge.start_date = start_date;
      this.challenge.end_date = end_date;
      this.challenge.period = period;
      this.challenge.dayList = dayList.sort();
      this.challenge.day_certify_count = day_certify_count;

      this.PrevPage();
    },
    PageTwoNext: function(
      start_date,
      end_date,
      period,
      dayList,
      day_certify_count
    ) {
      this.challenge.start_date = start_date;
      this.challenge.end_date = end_date;
      this.challenge.period = period;
      this.challenge.dayList = dayList.sort();
      this.challenge.day_certify_count = day_certify_count;
      this.pageComplete_2 = true;
      this.NextPage();
    },

    // 3페이지
    PageThreePrev: function(
      challenge_certify_contents,
      good_img,
      bad_img,
      good_img_url,
      bad_img_url,
      only_cam
    ) {
      this.challenge.challenge_certify_contents = challenge_certify_contents;
      this.challenge.only_cam = only_cam;
      this.challenge.good_img = good_img;
      this.challenge.bad_img = bad_img;
      this.challenge.good_img_url = good_img_url;
      this.challenge.bad_img_url = bad_img_url;
      this.PrevPage();
    },
    PageThreeNext: function(
      challenge_certify_contents,
      good_img,
      bad_img,
      good_img_url,
      bad_img_url,
      only_cam
    ) {
      this.challenge.challenge_certify_contents = challenge_certify_contents;
      this.challenge.only_cam = only_cam;
      this.challenge.good_img = good_img;
      this.challenge.bad_img = bad_img;
      this.challenge.good_img_url = good_img_url;
      this.challenge.bad_img_url = bad_img_url;
      this.pageComplete_3 = true;
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
