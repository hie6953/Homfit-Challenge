<template>
  <form>
    <!-- 챌린지 명, 챌린지 이미지, 챌린지소개 -->
    <div>
      <h4 class="challenge-creating-title">챌린지 정보</h4>
      <b-container>
        <b-row class="challenge-creating-row">
          <b-col sm="3" class="align-center">
            <span class="vertical-align-middle">챌린지 이름</span>
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

        <b-row class="challenge-creating-row">
          <b-col sm="3" class="align-center">
            <span class="vertical-align-middle">챌린지 설명</span>
          </b-col>
          <b-col sm="9">
            <TextEditor
              :props_content="challenge_contents"
              @input="(data) => GetEditorContent(data)"
            ></TextEditor>
          </b-col>
        </b-row>
      </b-container>
    </div>
    <div>
      <div class="align-center mx-auto">
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
      <h4 class="challenge-creating-title">운동종류 선택</h4>
      <div class="challenge-fit align-center">
        <b-button
          :class="{ choice: fit_id == '1', 'not-choice': fit_id != '1' }"
          @click="FitId('1')"
        >
          <img src="@/assets/category/요가.png" />
          <br />
          <span>요가</span>
        </b-button>
        <b-button
          :class="{ choice: fit_id == '2', 'not-choice': fit_id != '2' }"
          @click="FitId('2')"
        >
          <img src="@/assets/category/필라테스.png" />
          <br />
          <span>필라테스</span>
        </b-button>
        <b-button
          :class="{ choice: fit_id == '3', 'not-choice': fit_id != '3' }"
          @click="FitId('3')"
        >
          <img src="@/assets/category/유산소.png" />
          <br />
          <span>유산소</span>
        </b-button>
        <b-button
          :class="{ choice: fit_id == '4', 'not-choice': fit_id != '4' }"
          @click="FitId('4')"
        >
          <img src="@/assets/category/댄스.png" />
          <br />
          <span>댄스</span>
        </b-button>
        <b-button
          :class="{ choice: fit_id == '5', 'not-choice': fit_id != '5' }"
          @click="FitId('5')"
        >
          <img src="@/assets/category/스트레칭.png" />
          <br />
          <span>스트레칭</span>
        </b-button>
        <b-button
          :class="{ choice: fit_id == '6', 'not-choice': fit_id != '6' }"
          @click="FitId('6')"
        >
          <img src="@/assets/category/근력.png" />
          <br />
          <span>근력</span>
        </b-button>
        <b-button
          :class="{ choice: fit_id == '7', 'not-choice': fit_id != '7' }"
          @click="FitId('7')"
        >
          <img src="@/assets/category/키즈.png" />
          <br />
          <span>키즈</span>
        </b-button>
        <b-button
          :class="{ choice: fit_id == '8', 'not-choice': fit_id != '8' }"
          @click="FitId('8')"
        >
          <img src="@/assets/category/복싱.png" />
          <br />
          <span>복싱</span>
        </b-button>
        <b-button
          :class="{ choice: fit_id == '10', 'not-choice': fit_id != '10' }"
          @click="FitId('10')"
        >
          <img src="@/assets/category/기타.png" />
          <br />
          <span>기타</span>
        </b-button>
      </div>

      <div class="align-center col-12 col-md-8 pb-5 mx-auto">
        <h4 class="challenge-creating-title">부위별 선택</h4>
        <input
          class="challenge-bodyList"
          type="checkbox"
          name="bodyList"
          value="1"
          v-model="bodyList"
          id="bodyList_whole"
        />
        <label for="bodyList_whole"> <span class="text">전신</span> </label
        ><input
          class="challenge-bodyList"
          type="checkbox"
          name="bodyList"
          value="2"
          v-model="bodyList"
          id="bodyList_upper"
        />
        <label for="bodyList_upper"> <span class="text">상체</span> </label
        ><input
          class="challenge-bodyList"
          type="checkbox"
          name="bodyList"
          value="3"
          v-model="bodyList"
          id="bodyList_lower"
        />
        <label for="bodyList_lower"> <span class="text">하체</span> </label
        ><input
          class="challenge-bodyList"
          type="checkbox"
          name="bodyList"
          value="4"
          v-model="bodyList"
          id="bodyList_chest"
        />
        <label for="bodyList_chest"> <span class="text">가슴</span> </label
        ><input
          class="challenge-bodyList"
          type="checkbox"
          name="bodyList"
          value="5"
          v-model="bodyList"
          id="bodyList_arm"
        />
        <label for="bodyList_arm"> <span class="text">팔</span> </label
        ><input
          class="challenge-bodyList"
          type="checkbox"
          name="bodyList"
          value="6"
          v-model="bodyList"
          id="bodyList_abdominal"
        />
        <label for="bodyList_abdominal"> <span class="text">복부</span> </label
        ><input
          class="challenge-bodyList"
          type="checkbox"
          name="bodyList"
          value="7"
          v-model="bodyList"
          id="bodyList_hip"
        />
        <label for="bodyList_hip"> <span class="text">엉덩이</span> </label>
        <input
          class="challenge-bodyList"
          type="checkbox"
          name="bodyList"
          value="8"
          v-model="bodyList"
          id="bodyList_leg"
        />
        <label for="bodyList_leg"> <span class="text">다리</span> </label>
        <input
          class="challenge-bodyList"
          type="checkbox"
          name="bodyList"
          id="bodyList_etc"
          value="9"
          v-model="bodyList"
        />
        <label for="bodyList_etc"> <span class="text">기타</span> </label>
      </div>
    </div>

    <div class="mb-4">
      <b-button
        class="next-page-button"
        :class="{ disabled: !canGoNext }"
        @click="(e) => canGoNext && NextPage()"
        aria-disabled="true"
        ><span>기간</span
        ><b-icon icon="arrow-right-circle-fill" scale="1.5"></b-icon
      ></b-button>
    </div>
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
      kind: '0',
      fit_id: '0',
      bodyList: [],
      challenge_title: '',
      challenge_contents: '',
      canGoNext: false,
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
        this.bodyList = [];
        this.fit_id = '9';
      }
    },
    fit_id: function() {
      this.CanGoNext();
    },
    bodyList: function() {
      this.CanGoNext();
    },
    challenge_title: function() {
      this.CanGoNext();
    },
    challenge_contents: function() {
      this.CanGoNext();
    },
  },
  methods: {
    FitId: function(num) {
      this.fit_id = num;
    },
    GetEditorContent: function(data) {
      this.challenge_contents = data;
    },
    CanGoNext: function() {
      if (
        this.challenge_title.length > 0 &&
        this.challenge_title.length <= 20 &&
        this.challenge_contents.length > 7 &&
        ((this.kind != '0' && this.bodyList.length > 0) || this.kind == '2')
      ) {
        this.canGoNext = true;
      } else {
        this.canGoNext = false;
      }
    },
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
