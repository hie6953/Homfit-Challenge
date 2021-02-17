<template>
  <b-modal id="select-preference-modal" v-model="modalShow" size="lg" no-close-on-backdrop class="mt-3">

    <div class=" select-prefer-container">
      <!-- 안내문구 -->
      <div class="prefer-info-text">
        <b-icon icon="exclamation-circle-fill" variant="warning"></b-icon>
        선택하신 운동종류, 운동부위, 요일을 바탕으로 챌린지를 추천해드립니다.
        <hr />
      </div>
      <!-- 관심운동 -->
      <div class="col-12 col-md-9 pb-5 mx-auto prefer-question">
        <div class="prefer-question-text">
          선호하는 운동 종류를 체크해주세요
        </div>
        <div class="prefer-question-choice">
          <input
            class="challenge-bodyList"
            type="checkbox"
            name="kindList"
            :value="1"
            v-model="kindList"
            id="kindList_yoga"
          />
          <label for="kindList_yoga"> <span class="text">요가</span> </label
          ><input
            class="challenge-bodyList"
            type="checkbox"
            name="kindList"
            :value="2"
            v-model="kindList"
            id="kindList_pilates"
          />
          <label for="kindList_pilates">
            <span class="text">필라테스</span> </label
          ><input
            class="challenge-bodyList"
            type="checkbox"
            name="kindList"
            :value="3"
            v-model="kindList"
            id="kindList_aerobic"
          />
          <label for="kindList_aerobic">
            <span class="text">유산소</span> </label
          ><input
            class="challenge-bodyList"
            type="checkbox"
            name="kindList"
            :value="4"
            v-model="kindList"
            id="kindList_dance"
          />
          <label for="kindList_dance"> <span class="text">댄스</span> </label
          ><input
            class="challenge-bodyList"
            type="checkbox"
            name="kindList"
            :value="5"
            v-model="kindList"
            id="kindList_stretching"
          />
          <label for="kindList_stretching">
            <span class="text">스트레칭</span> </label
          ><input
            class="challenge-bodyList"
            type="checkbox"
            name="kindList"
            :value="6"
            v-model="kindList"
            id="kindList_muscular"
          />
          <label for="kindList_muscular"> <span class="text">근력</span> </label
          ><input
            class="challenge-bodyList"
            type="checkbox"
            name="kindList"
            :value="7"
            v-model="kindList"
            id="kindList_kids"
          />
          <label for="kindList_kids"> <span class="text">키즈</span> </label>
          <input
            class="challenge-bodyList"
            type="checkbox"
            name="kindList"
            :value="8"
            v-model="kindList"
            id="kindList_boxing"
          />
          <label for="kindList_boxing"> <span class="text">복싱</span> </label>
          <input
            class="challenge-bodyList"
            type="checkbox"
            name="kindList"
            id="kindList_etc"
            :value="9"
            v-model="kindList"
          />
          <label for="kindList_etc"> <span class="text">기타</span> </label>
        </div>
      </div>

      <!-- 관심부위 -->
      <div class="col-12 col-md-9 pb-5 mx-auto prefer-question">
        <div class="prefer-question-text">
          관심 있는 부위를 체크해주세요
        </div>
        <div class="prefer-question-choice">
          <input
            class="challenge-bodyList"
            type="checkbox"
            name="bodyList"
            :value="1"
            v-model="bodyList"
            id="bodyList_whole"
          />
          <label for="bodyList_whole"> <span class="text">전신</span> </label
          ><input
            class="challenge-bodyList"
            type="checkbox"
            name="bodyList"
            :value="2"
            v-model="bodyList"
            id="bodyList_upper"
          />
          <label for="bodyList_upper"> <span class="text">상체</span> </label
          ><input
            class="challenge-bodyList"
            type="checkbox"
            name="bodyList"
            :value="3"
            v-model="bodyList"
            id="bodyList_lower"
          />
          <label for="bodyList_lower"> <span class="text">하체</span> </label
          ><input
            class="challenge-bodyList"
            type="checkbox"
            name="bodyList"
            :value="4"
            v-model="bodyList"
            id="bodyList_chest"
          />
          <label for="bodyList_chest"> <span class="text">가슴</span> </label
          ><input
            class="challenge-bodyList"
            type="checkbox"
            name="bodyList"
            :value="5"
            v-model="bodyList"
            id="bodyList_arm"
          />
          <label for="bodyList_arm"> <span class="text">팔</span> </label
          ><input
            class="challenge-bodyList"
            type="checkbox"
            name="bodyList"
            :value="6"
            v-model="bodyList"
            id="bodyList_abdominal"
          />
          <label for="bodyList_abdominal">
            <span class="text">복부</span> </label
          ><input
            class="challenge-bodyList"
            type="checkbox"
            name="bodyList"
            :value="7"
            v-model="bodyList"
            id="bodyList_hip"
          />
          <label for="bodyList_hip"> <span class="text">엉덩이</span> </label>
          <input
            class="challenge-bodyList"
            type="checkbox"
            name="bodyList"
            :value="8"
            v-model="bodyList"
            id="bodyList_leg"
          />
          <label for="bodyList_leg"> <span class="text">다리</span> </label>
          <input
            class="challenge-bodyList"
            type="checkbox"
            name="bodyList"
            id="bodyList_etc"
            :value="9"
            v-model="bodyList"
          />
          <label for="bodyList_etc"> <span class="text">기타</span> </label>
        </div>
      </div>

      <!-- 선호요일 -->
      <div class="col-12 col-md-9 pb-5 mx-auto prefer-question">
        <div class="prefer-question-text">
          선호하는 요일을 체크해주세요
        </div>
        <div class="prefer-question-choice">
          <input
            class="challenge-bodyList"
            type="checkbox"
            name="week"
            :value="7"
            v-model="week"
            id="sunday"
          />
          <label for="sunday"> <span class="text">일</span> </label
          ><input
            class="challenge-bodyList"
            type="checkbox"
            name="week"
            :value="1"
            v-model="week"
            id="monday"
          />
          <label for="monday"> <span class="text">월</span> </label
          ><input
            class="challenge-bodyList"
            type="checkbox"
            name="week"
            :value="2"
            v-model="week"
            id="tuesday"
          />
          <label for="tuesday"> <span class="text">화</span> </label
          ><input
            class="challenge-bodyList"
            type="checkbox"
            name="week"
            :value="3"
            v-model="week"
            id="wednesday"
          />
          <label for="wednesday"> <span class="text">수</span> </label
          ><input
            class="challenge-bodyList"
            type="checkbox"
            name="week"
            :value="4"
            v-model="week"
            id="thursday"
          />
          <label for="thursday"> <span class="text">목</span> </label
          ><input
            class="challenge-bodyList"
            type="checkbox"
            name="week"
            :value="5"
            v-model="week"
            id="friday"
          />
          <label for="friday"> <span class="text">금</span> </label
          ><input
            class="challenge-bodyList"
            type="checkbox"
            name="week"
            :value="6"
            v-model="week"
            id="saturday"
          />
          <label for="saturday"> <span class="text">토</span> </label>
        </div>
      </div>

      <!-- 버튼 -->
        <div class="prefer-choice-submit-btn">
          <!-- <input
            type="button"
            class="prefer-choice-btn-priority btn"
            value="선택완료"
            :class="{ disabled: !canGoNext }"
            @click="canGoNext"
            aria-disabled="true"
          /> -->
          <input
            type="button"
            class="prefer-choice-btn-priority btn"
            value="선택완료"
            :disabled="!canGoNext"
            @click="SelectPreferenceCheck"
            aria-disabled="true"
          />
        </div>
    </div>
  </b-modal>
</template>

<script>
import '@/assets/css/selectpreference.css';
import swal from '@/assets/javascript/sweetAlert.js';
import { mapGetters } from 'vuex';
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'SelectPreference',
  data: function() {
    return {
      kindList: [],
      bodyList: [],
      week: [],
      canGoNext:false,
    };
  },
props:{
  modalShow:Boolean,
  fit:Array,
  body:Array,
  day:Array,
},
  watch: {
    kindList: function() {
      this.CanGoNext();
    },
    bodyList: function() {
      this.CanGoNext();
    },
    week: function() {
      this.CanGoNext();
    },
    modalShow:function(){
      this.kindList = this.fit;
      this.bodyList = this.body;
      this.week = this.day;
    }
  },
  methods: {
    CanGoNext: function() {
      // 페이지 이동 가능여부
      if (
        this.kindList.length > 0 &&
        this.bodyList.length > 0 &&
        this.week.length > 0
      ) {
        this.canGoNext = true;
      } else {
        this.canGoNext = false;
      }
    },

    SelectPreferenceCheck() {
      //axios
      axios
        .put(`${SERVER_URL}/user/updateFavorite`, {
          uid: this.getUserUid,
          fit_list: JSON.stringify(this.kindList.sort()),
          body_list: JSON.stringify(this.bodyList.sort()),
          day_list: JSON.stringify(this.week.sort()),
          // fit_list: this.kindList,
          // body_list: this.bodyList,
          // day_list: this.week,
        })
        .then(({ data }) => {
          console.log(JSON.stringify(this.kindList));
          if (data == 'success') {
            swal.success('선호도 정보가 저장되었습니다.');
            this.$emit("modalClose");
          } else {
            swal.error('실패!!!');
          }
        })
        .catch(() => {
          swal.error('오류가 발생했습니다.');
        });
        window.location.reload();
    },
  },

  computed: {
    ...mapGetters(['getUserUid']),
  },
};
</script>
