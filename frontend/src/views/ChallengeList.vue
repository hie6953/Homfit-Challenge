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
          value="total"
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
          value="yoga"
          v-model="category"
          id="category-yoga"
        />
        <label class="for-checkbox-tools" for="category-yoga"> 요가 </label
        ><!--
						--><input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="pilates"
          v-model="category"
          id="category-pilates"
        />
        <label class="for-checkbox-tools" for="category-pilates">
          필라테스 </label
        ><!--
						--><input class="checkbox-tools" type="radio" name="tools" id="category-food" />
        <label class="for-checkbox-tools" for="category-food"> 식단 </label
        ><!--
						--><input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="aerobic"
          v-model="category"
          id="category-aerobic"
        />
        <label class="for-checkbox-tools" for="category-aerobic">
          유산소운동
        </label>

        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="dance"
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
          value="stretching"
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
          value="strength"
          v-model="category"
          id="category-strength"
        />
        <label class="for-checkbox-tools" for="category-strength">
          근력운동
        </label>
        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="kids"
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
          value="boxing"
          v-model="category"
          id="category-boxing"
        />
        <label class="for-checkbox-tools" for="category-boxing">
          복싱
        </label>
      </div>
    </div>
    <hr id="category-hr-bottom" />
    <div class="mx-auto col-8">
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
          class="col-md-6 col-lg-4 col-xl-3 challenge-list-card"
          :challenge="challenge"
        ></challenge-list-card>
      </div>
    </div>
  </div>
</template>

<script>
import ChallengeListCard from '../components/ChallengeListCard.vue';
import '@/assets/css/challengelist.css';

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
      category: 'total',
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
};
</script>
