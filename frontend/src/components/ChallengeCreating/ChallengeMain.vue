<template>
  <div>
    <div>
      <input
        type="radio"
        name="fit_category"
        value="1"
        v-model="kind"
        id="fit_id_exercise"
      />
      <label for="fit_id_exercise">운동</label>
      <input
        type="radio"
        name="fit_category"
        value="2"
        v-model="kind"
        id="fit_id_diet"
      />
      <label for="fit_id_diet">식단</label>
    </div>
    <div v-if="kind == '1'">
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
        <input
          type="checkbox"
          value="1"
          v-model="bodyList"
          id="bodyList_whole"
        />
        <label for="bodyList_whole">전신</label>
        <br />
        <input
          type="checkbox"
          value="2"
          v-model="bodyList"
          id="bodyList_upper"
        />
        <label for="bodyList_upper">상체</label>
        <br />
        <input
          type="checkbox"
          value="3"
          v-model="bodyList"
          id="bodyList_lower"
        />
        <label for="bodyList_lower">하체</label>
        <br />
        <input
          type="checkbox"
          value="4"
          v-model="bodyList"
          id="bodyList_chest"
        />
        <label for="bodyList_chest">가슴</label>
        <br />
        <input type="checkbox" value="5" v-model="bodyList" id="bodyList_arm" />
        <label for="bodyList_arm">팔</label>
        <br />
        <input
          type="checkbox"
          value="6"
          v-model="bodyList"
          id="bodyList_abdominal"
        />
        <label for="bodyList_abdominal">복부</label>
        <br />
        <input type="checkbox" value="7" v-model="bodyList" id="bodyList_hip" />
        <label for="bodyList_hip">엉덩이</label>
        <br />
        <input type="checkbox" value="8" v-model="bodyList" id="bodyList_leg" />
        <label for="bodyList_leg">다리</label>
        <br />
        <input type="checkbox" value="9" v-model="bodyList" id="bodyList_etc" />
        <label for="bodyList_etc">기타</label>
      </div>
    </div>

    <!-- 챌린지 명, 챌린지 이미지, 챌린지소개 -->
    <div>
      <hr />
      챌린지 명 : <input type="text" v-model="challenge_title" />
      <br />
      챌린지 설명 :
      <textarea v-model="challenge_contents" />
    </div>

    <b-button class="next-page-button" @click="NextPage()">Next</b-button>
  </div>
</template>

<script>
export default {
  props: {
    props_kind: Number,
    props_fit_id: Number,
    props_bodyList: Array,
    props_challenge_title: String,
    props_challenge_contents: String,
  },
  data() {
    return {
      kind: 0,
      fit_id: 0,
      bodyList: [],
      challenge_title: '',
      challenge_contents: '',
    };
  },
  created() {
    this.kind = String(this.props_kind);
    this.fit_id = String(this.props_fit_id);
    this.bodyList = this.props_bodyList;
    this.challenge_title = this.props_challenge_title;
    this.challenge_contents = this.props_challenge_contents;
  },
  watch: {
    kind: function() {
      if (this.kind == '1') {
        this.fit_id = '1';
      } else {
        this.fit_id = '9';
      }
    },
  },
  methods: {
    NextPage: function() {
      this.$emit(
        'NextPage',
        parseInt(this.kind),
        parseInt(this.fit_id),
        this.bodyList,
        this.challenge_title,
        this.challenge_contents
      );
    },
  },
};
</script>
