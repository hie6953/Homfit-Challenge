<template>
  <div>
    <div>
      <input
        type="radio"
        name="fit_category"
        value="1"
        v-model="fit_category"
        id="fit_id_exercise"
      />
      <label for="fit_id_exercise">운동</label>
      <input
        type="radio"
        name="fit_category"
        value="2"
        v-model="fit_category"
        id="fit_id_diet"
        @click="FitIdToDiet"
      />
      <label for="fit_id_diet">식단</label>
    </div>
    <div v-if="fit_category == '1'">
      <h4>운동종류 선택</h4>
      <div>
        <input type="radio" value="1" v-model="fit_id" id="fit_id_yoga" />
        <label for="fit_id_yoga">요가</label>
        <br />
        <input type="radio" value="2" v-model="fit_id" id="fit_id_pilates" />
        <label for="fit_id_pilates">필라테스</label>
        <br />
        <input type="radio" value="3" v-model="fit_id" id="fit_id_aerobic" />
        <label for="fit_id_aerobic">유산소운동</label>
        <br />
        <input type="radio" value="4" v-model="fit_id" id="fit_id_dance" />
        <label for="fit_id_dance">댄스</label>
        <br />
        <input type="radio" value="5" v-model="fit_id" id="fit_id_stretching" />
        <label for="fit_id_stretching">스트레칭</label>
        <br />
        <input type="radio" value="6" v-model="fit_id" id="fit_id_strength" />
        <label for="fit_id_strength">근력운동</label>
        <br />
        <input type="radio" value="7" v-model="fit_id" id="fit_id_kids" />
        <label for="fit_id_kids">키즈</label>
        <br />
        <input type="radio" value="8" v-model="fit_id" id="fit_id_boxing" />
        <label for="fit_id_boxing">복싱</label>
        <br />
        <input type="radio" value="10" v-model="fit_id" id="fit_id_etc" />
        <label for="fit_id_etc">기타</label>
      </div>
      <hr />
      <div>
        <h4>부위별 선택</h4>
        <input type="checkbox" value="1" v-model="body_id" id="body_id_whole" />
        <label for="body_id_whole">전신</label>
        <br />
        <input type="checkbox" value="2" v-model="body_id" id="body_id_upper" />
        <label for="body_id_upper">상체</label>
        <br />
        <input type="checkbox" value="3" v-model="body_id" id="body_id_lower" />
        <label for="body_id_lower">하체</label>
        <br />
        <input type="checkbox" value="4" v-model="body_id" id="body_id_chest" />
        <label for="body_id_chest">가슴</label>
        <br />
        <input type="checkbox" value="5" v-model="body_id" id="body_id_arm" />
        <label for="body_id_arm">팔</label>
        <br />
        <input
          type="checkbox"
          value="6"
          v-model="body_id"
          id="body_id_abdominal"
        />
        <label for="body_id_abdominal">복부</label>
        <br />
        <input type="checkbox" value="7" v-model="body_id" id="body_id_hip" />
        <label for="body_id_hip">엉덩이</label>
        <br />
        <input type="checkbox" value="8" v-model="body_id" id="body_id_leg" />
        <label for="body_id_leg">다리</label>
        <br />
        <input type="checkbox" value="9" v-model="body_id" id="body_id_etc" />
        <label for="body_id_etc">기타</label>
      </div>
    </div>

    <!-- 챌린지 명, 챌린지 이미지, 챌린지소개 -->
    <div>
      <hr />
      챌린지 명 : <input type="text" v-model="challenge_title" />
      <br />
      챌린지 설명
      <textarea v-model="challenge_contents" />
    </div>

    <b-button
      @click="NextPage(fit_id, body_id, challenge_title, challenge_contents)"
      >Next</b-button
    >
  </div>
</template>

<script>
export default {
  props: {
    props_fit_id: Number,
    props_body_id: Array,
    props_challenge_title: String,
    props_challenge_contents: String,
  },
  data() {
    return {
      fit_category: '1',
      fit_id: 0,
      body_id: [],
      challenge_title: '',
      challenge_contents: '',
    };
  },
  created() {
    if (this.props_fit_id == 9) this.fit_category = '2';
    this.fit_id = String(this.props_fit_id);
    this.body_id = this.props_body_id;
    this.challenge_title = this.props_challenge_title;
    this.challenge_contents = this.props_challenge_contents;
  },
  methods: {
    FitIdToDiet: function() {
      this.fit_id = 9;
    },

    NextPage: function() {
      this.$emit(
        'NextPage',
        parseInt(this.fit_id),
        this.body_id,
        this.challenge_title,
        this.challenge_contents
      );
    },
  },
};
</script>
