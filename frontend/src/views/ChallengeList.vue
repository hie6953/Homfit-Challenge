<template>
  <div class="mt-3">
    <!-- 카테고리 -->
    <hr id="category-hr-top" />
    <div class="row mx-auto">
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
        <label class="for-checkbox-tools" for="category-yoga"> 요가 </label
        ><!--
						--><input
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
    <hr id="category-hr-bottom" />
    <div class="mx-auto col-10 col-md-8">
      <!-- 정렬 -->
      <b-dropdown id="sort-dropdown" variant="outline-dark" :text="sortValue">
        <b-dropdown-item
          v-for="(value, index) in sortList"
          :key="`${index}_sortValue`"
          @click="sortValue = value"
          >{{ value }}</b-dropdown-item
        >
      </b-dropdown>
      <!-- 필터 -->
      <b-dropdown id="filter-dropdown" right variant="outline-dark">
        <template #button-content>
          필터<b-icon icon="filter"></b-icon>
        </template>
        <h5>기간</h5>
        <input
          type="checkbox"
          class="filter-period"
          v-model="period"
          id="fruit1"
          name="fruit-1"
          value="Apple"
        />
        <label for="fruit1">1일~1주</label>
        <input
          type="checkbox"
          class="filter-period"
          v-model="period"
          id="fruit2"
          name="fruit-2"
          value="Banana"
        />
        <label for="fruit2">1주~2주</label>
        <input
          type="checkbox"
          class="filter-period"
          v-model="period"
          id="fruit3"
          name="fruit-3"
          value="Cherry"
        />
        <label for="fruit3">2주~3주</label>
        <input
          type="checkbox"
          class="filter-period"
          v-model="period"
          id="fruit4"
          name="fruit-4"
          value="Strawberry"
        />
        <label for="fruit4">3주~</label>

        <b-dropdown-divider></b-dropdown-divider>
        <h5>요일</h5>
        <ul class="day-checkbox">
          <li>
            <input type="checkbox" v-model="day" id="Mon" value="1" /><label
              for="Mon"
              >월</label
            >
          </li>
          <li>
            <input type="checkbox" v-model="day" id="Tue" value="2" /><label
              for="Tue"
              >화</label
            >
          </li>
          <li>
            <input type="checkbox" v-model="day" id="Wed" value="3" /><label
              for="Wed"
              >수</label
            >
          </li>
          <li>
            <input type="checkbox" v-model="day" id="Thu" value="4" /><label
              for="Thu"
              >목</label
            >
          </li>
          <li>
            <input type="checkbox" v-model="day" id="Fri" value="5" /><label
              for="Fri"
              >금</label
            >
          </li>
          <li>
            <input type="checkbox" v-model="day" id="Sat" value="6" /><label
              for="Sat"
              >토</label
            >
          </li>
          <li>
            <input type="checkbox" v-model="day" id="Sun" value="7" /><label
              for="Sun"
              >일</label
            >
          </li>
        </ul>
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
import '@/assets/css/challengelist.css';

import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'ChallengeList',
  components: { ChallengeListCard },
  watch: {
    period: function() {
      //필터-기간 바뀔 때
    },
    day: function() {
      //필터-요일 바뀔 때
    },
    category: function() {
      console.log(this.category);
    },
  },
  data() {
    return {
      category: '0',
      sortList: ['인기순', '최신순'],
      sortValue: '인기순',
      period: [],
      day: [],
      challengeList: [
        { id: 'id' },
        { id: 'id' },
        { id: 'id' },
        { id: 'id' },
        { id: 'id' },
        { id: 'id' },
        { id: 'id' },
        { id: 'id' },
        { id: 'id' },
      ],
    };
  },
  created() {
    let category_number = this.$route.params.category_number;
    if (category_number) {
      this.category = category_number;
    }

    axios
      .get(`${SERVER_URL}/challenge/all`)
      .then(({ data }) => {
        this.challengeList = data;
      })
      .catch(() => {
        alert('챌린지 목록을 불러오지 못했습니다.');
      });
  },
};
</script>
