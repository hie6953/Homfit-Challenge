<template>
  <div>
    <!-- 인증 요일 선택 -->
    <div>
      <h4>인증 요일 선택</h4>
      <input type="checkbox" value="1" v-model="dayList" id="Mon" />
      <label for="Mon">월</label>
      <br />
      <input type="checkbox" value="2" v-model="dayList" id="Tue" />
      <label for="Tue">화</label>
      <br />
      <input type="checkbox" value="3" v-model="dayList" id="Wed" />
      <label for="Wed">수</label>
      <br />
      <input type="checkbox" value="4" v-model="dayList" id="Thu" />
      <label for="Thu">목</label>
      <br />
      <input type="checkbox" value="5" v-model="dayList" id="Fri" />
      <label for="Fri">금</label>
      <br />
      <input type="checkbox" value="6" v-model="dayList" id="Sat" />
      <label for="Sat">토</label>
      <br />
      <input type="checkbox" value="7" v-model="dayList" id="Sun" />
      <label for="Sun">일</label>
    </div>

    <!-- 하루 인증 횟수 -->
    <div>
      <br />
      <h4>하루 인증 횟수</h4>
      <input type="number" v-model="day_certify_count" />회
    </div>

    <!-- 인증 방법 설명 -->
    <div>
      <br />
      <h4>인증 방법 설명</h4>
      <textarea
        v-model="challenge_certify_contents"
        cols="30"
        rows="10"
      ></textarea>
    </div>

    <!-- 인증 수단 -->
    <div>
      <br />
      <h4>인증 수단</h4>
      <input type="radio" value="1" v-model="only_cam" id="OnlyCam" />
      <label for="OnlyCam">카메라만 사용</label>
      <br />
      <input type="radio" value="0" v-model="only_cam" id="NotOnlyCam" />
      <label for="NotOnlyCam">카메라, 갤러리 사용</label>
    </div>

    <!-- 페이지 이동 버튼 -->
    <b-button class="prev-page-button" @click="PrevPage()">Previous</b-button>
    <b-button class="next-page-button" @click="NextPage()">Next</b-button>
  </div>
</template>

<script>
export default {
  props: {
    props_dayList: Array,
    props_day_certify_count: Number,
    props_challenge_certify_contents: String,
    // props_good_img: Object,
    // props_bad_img: Object,
    props_only_cam: Number,
  },
  data() {
    return {
      dayList: [],
      day_certify_count: 0,
      challenge_certify_contents: '',
      // good_img: Object,
      // bad_img: Object,
      only_cam: 1,
    };
  },
  created() {
    this.dayList = this.props_dayList;
    this.day_certify_count = this.props_day_certify_count;
    this.challenge_certify_contents = this.props_challenge_certify_contents;
    // this.good_img = this.props_good_img;
    // this.bad_img = this.props_bad_img;
    this.only_cam = this.props_only_cam;
  },

  methods: {
    // 페이지 이동
    PrevPage: function() {
      this.$emit(
        'PrevPage',
        this.dayList,
        this.day_certify_count,
        this.challenge_certify_contents,
        this.only_cam
      );
    },
    NextPage: function() {
      this.$emit(
        'NextPage',
        this.dayList,
        this.day_certify_count,
        this.challenge_certify_contents,
        this.only_cam
      );
    },
  },
};
</script>
