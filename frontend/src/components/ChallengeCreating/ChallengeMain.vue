<template>
  <form>
    <!-- 챌린지 명, 챌린지 이미지, 챌린지소개 -->
    <div>
      <h4>챌린지 정보</h4>
      <br />
      <b-container>
        <b-row>
          <b-col sm="3">
            <label for="challenge_title_input">챌린지 이름</label>
          </b-col>
          <b-col sm="9">
            <b-form-input
              id="challenge_title_input"
              type="text"
              :state="challengeTitleState"
              placeholder="챌린지 이름을 입력하세요(1~20자)"
              v-model="challenge_title"
            ></b-form-input>
            <b-form-invalid-feedback id="challenge_title_input">
              챌린지 이름을 20자 이내로 입력하세요.
            </b-form-invalid-feedback>
          </b-col>
        </b-row>

        <b-row>
          <b-col sm="3">
            <label for="challenge_contents_texteditor">챌린지 설명</label>
          </b-col>
          <b-col sm="9"><TextEditor></TextEditor> </b-col>
        </b-row>
      </b-container>
    </div>
    <div>
      <div class="challenge_kind_area mx-auto">
        <input
          class="challenge_kind"
          type="radio"
          name="kind"
          value="1"
          id="fit_id_exercise"
          v-model="kind"
        />
        <label for="fit_id_exercise">
          <span data-hover="운동">운동</span>
        </label>
        <input
          class="challenge_kind"
          type="radio"
          name="kind"
          value="2"
          id="fit_id_diet"
          v-model="kind"
        />
        <label for="fit_id_diet">
          <span data-hover="식단">식단</span>
        </label>
      </div>
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

      <div class="col-12 pb-5">
        <input
          class="challenge_bodyList"
          type="checkbox"
          name="bodyList"
          id="booking-1"
        />
        <label for="booking-1"> <span class="text">전신</span> </label
        ><input
          class="challenge_bodyList"
          type="checkbox"
          name="bodyList"
          id="booking-2"
        />
        <label for="booking-2"> <span class="text">상체</span> </label
        ><input
          class="challenge_bodyList"
          type="checkbox"
          name="bodyList"
          id="booking-3"
        />
        <label for="booking-3"> <span class="text">하체</span> </label
        ><input
          class="challenge_bodyList"
          type="checkbox"
          name="bodyList"
          id="booking-4"
        />
        <label for="booking-4"> <span class="text">가슴</span> </label
        ><input
          class="challenge_bodyList"
          type="checkbox"
          name="bodyList"
          id="booking-5"
        />
        <label for="booking-5"> <span class="text">팔</span> </label
        ><input
          class="challenge_bodyList"
          type="checkbox"
          name="bodyList"
          id="booking-6"
        />
        <label for="booking-6"> <span class="text">복부</span> </label
        ><input
          class="challenge_bodyList"
          type="checkbox"
          name="bodyList"
          id="booking-7"
        />
        <label for="booking-7"> <span class="text">엉덩이</span> </label>
        <input
          class="challenge_bodyList"
          type="checkbox"
          name="bodyList"
          id="booking-7"
        />
        <label for="booking-7"> <span class="text">다리</span> </label>
        <input
          class="challenge_bodyList"
          type="checkbox"
          name="bodyList"
          id="booking-7"
        />
        <label for="booking-7"> <span class="text">기타</span> </label>
      </div>
    </div>

    <b-button class="next-page-button" @click="NextPage()">Next</b-button>
  </form>
</template>

<script>
import TextEditor from '@/components/ChallengeCreating/TextEditor.vue';

export default {
  components: {
    TextEditor,
  },

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

  computed: {
    challengeTitleState() {
      if (this.challenge_title.length == 0) return null;
      return this.challenge_title.length <= 20;
    },
  },
};
</script>
