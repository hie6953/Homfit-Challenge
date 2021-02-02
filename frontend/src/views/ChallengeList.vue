<template>
  <div class="mt-3">
    <!-- 카테고리 -->
    <!-- <hr id="category-hr-top" /> -->
    <div class="category-background row mx-auto">
      <div class="category mx-auto">
        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="0"
          v-model="category"
          id="category-total"
          checked
        />
        <label class="for-checkbox-tools" for="category-total">
          전체
        </label>
        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="1"
          v-model="category"
          id="category-yoga"
        />
        <label class="for-checkbox-tools" for="category-yoga"> 요가 </label>
        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="2"
          v-model="category"
          id="category-pilates"
        />
        <label class="for-checkbox-tools" for="category-pilates">
          필라테스 </label
        ><input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="3"
          v-model="category"
          id="category-aerobic"
        />
        <label class="for-checkbox-tools" for="category-aerobic">
          유산소
        </label>

        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="4"
          v-model="category"
          id="category-dance"
        />
        <label class="for-checkbox-tools" for="category-dance">
          댄스
        </label>
        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="5"
          v-model="category"
          id="category-stretching"
        />
        <label class="for-checkbox-tools" for="category-stretching">
          스트레칭
        </label>
        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="6"
          v-model="category"
          id="category-strength"
        />
        <label class="for-checkbox-tools" for="category-strength">
          근력
        </label>
        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="7"
          v-model="category"
          id="category-kids"
        />
        <label class="for-checkbox-tools" for="category-kids">
          키즈
        </label>
        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="8"
          v-model="category"
          id="category-boxing"
        />
        <label class="for-checkbox-tools" for="category-boxing">
          복싱
        </label>
        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="9"
          v-model="category"
          id="category-food"
        />
        <label class="for-checkbox-tools" for="category-food">
          식단
        </label>
        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="10"
          v-model="category"
          id="category-etc"
        />
        <label class="for-checkbox-tools" for="category-etc">
          기타
        </label>
      </div>
    </div>
    <!-- <hr id="category-hr-bottom" /> -->
    <div class="mx-auto col-11 col-md-8">
      <!-- 정렬 -->
      <b-dropdown
        id="sort-dropdown"
        variant="outline-dark"
        :text="sortList[sortValue]"
      >
        <b-dropdown-item
          v-for="(value, index) in sortList"
          :key="`${index}_sortValue`"
          @click="sortValue = index"
          >{{ value }}</b-dropdown-item
        >
      </b-dropdown>
      <!-- 필터 -->

      <b-dropdown id="filter-dropdown" right variant="outline-dark">
        <template #button-content>
          필터<b-icon icon="filter"></b-icon>
        </template>
        <challenge-list-filter
          :prop_days="day"
          :prop_period="period"
          @change="getFilterData"
        ></challenge-list-filter>
      </b-dropdown>
      <!-- 챌린지 리스트 -->
      <div class="row list-card">
        <challenge-list-card
          v-for="(challenge, index) in challengeList"
          :key="`${index}_challenge`"
          class="col-6 col-md-4 col-lg-3 challenge-list-card"
          :challenge="challenge"
        ></challenge-list-card>
      </div>
    </div>
  </div>
</template>

<script>
import ChallengeListCard from '../components/ChallengeListCard.vue';
import ChallengeListFilter from '../components/ChallengeListFilter.vue';
import '@/assets/css/challengelist.css';

// import axios from 'axios';
// const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'ChallengeList',
  components: { ChallengeListCard, ChallengeListFilter },
  watch: {
    sortValue: function() {
      this.getData();
    },
    category: function() {
      this.getData();
    },
  },
  data() {
    return {
      category: 0,
      sortList: ['인기순', '최신순'],
      sortValue: 0,
      period: [3, 30],
      day: [],
      page: 1,
      challengeList: [],
    };
  },
  created() {
    // 메인페이지 카테고리를 통해 챌린지 리스트로 이동 시 파라미터 받기
    let category_number = this.$route.params.category_number;
    if (category_number) {
      this.category = category_number;
    }

    let category_sort = this.$route.params.category_sort;
    if (category_sort) {
      this.sortValue = category_sort;
    }

    this.getData();
  },
  methods: {
    getData: function() {
      // axios
      //   .get(`${SERVER_URL}/challenge/all`, {
      //     params: {
      //       category: this.category, //0:전체, 1~10 카테고리숫자
      //       sort: this.sortValue, //0:인기순,1:최신순
      //       periodStart: this.period[0], //period최소값(이상) 7
      //       periodEnd: this.period[1], //period최대값(이하) 30
      //       day: this.day, //요일 숫자 배열 [3,4,5]
      //       page: this.page, //페이지 숫자
      //     },
      //   })
      //   .then(({ data }) => {
      //     this.challengeList = data;
      //   })
      //   .catch(() => {
      //     alert('챌린지 목록을 불러오지 못했습니다.');
      //   });
    },
    getFilterData: function(period, day) {
      this.period = period;
      this.day = day;
      this.getData();
    },
    dayChange: function(day) {
      this.day = day;
    },
  },
};
</script>
