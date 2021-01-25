<template>
  <div class="mx-auto col-8">
    <div class="mx-0">
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
          v-model="period"
          id="fruit1"
          name="fruit-1"
          value="Apple"
        />
        <label for="fruit1">1일~1주</label>
        <input
          type="checkbox"
          v-model="period"
          id="fruit2"
          name="fruit-2"
          value="Banana"
        />
        <label for="fruit2">1주~2주</label>
        <input
          type="checkbox"
          v-model="period"
          id="fruit3"
          name="fruit-3"
          value="Cherry"
        />
        <label for="fruit3">2주~3주</label>
        <input
          type="checkbox"
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
    </div>
    <!-- 챌린지 리스트 -->
    <div class="row list-card">
      <challenge-list-card
        v-for="(challenge, index) in challengeList"
        :key="`${index}_challenge`"
        class="col-md-6 col-lg-4 col-xl-3"
        :challenge="challenge"
      ></challenge-list-card>
    </div>
  </div>
</template>

<script>
import ChallengeListCard from '../components/ChallengeListCard.vue';

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
  },
  data() {
    return {
      sortList: ['인기순', '최신순'],
      sortValue: '인기순',
      period: [],
      day: [],
      challengeList: [{ id: 'id' }, { id: 'id' }, { id: 'id' }, { id: 'id' }],
    };
  },
};
</script>

<style>
/* 정렬 */
#sort-dropdown {
  float: left;
}

.dropdown-item:active {
  background-color: #e65c2e !important;
}

/* 필터 */
#filter-dropdown {
  float: right;
}

#filter-dropdown > .dropdown-menu {
  width: 320px;
}

/* 필터 드롭다운 화살표 지우기 */
#filter-dropdown > button::after {
  display: none;
}

h5 {
  margin-left: 5px;
}

/* 필터 - 체크박스 */
input[type='checkbox'] + label {
  display: block;
  margin: 0.2em;
  cursor: pointer;
  padding: 0.2em;
}

input[type='checkbox'] {
  display: none;
}

input[type='checkbox'] + label:before {
  content: '\2714';
  border: 0.2px solid #aaa;
  border-radius: 0.2em;
  display: inline-block;
  width: 1.3em;
  height: 1.3em;
  padding-left: 0.1em;
  padding-bottom: 0.3em;
  margin-right: 0.2em;
  vertical-align: bottom;
  color: transparent;
  transition: 0.2s;
}

input[type='checkbox'] + label:active:before {
  transform: scale(0);
}

input[type='checkbox']:checked + label:before {
  background-color: #e65c2e;
  border-color: #e65c2e;
  color: #fff;
}

input[type='checkbox']:disabled + label:before {
  transform: scale(1);
  border-color: #aaa;
}

input[type='checkbox']:checked:disabled + label:before {
  transform: scale(1);
  background-color: #eac03a;
  border-color: #eac03a;
}

/* 필터 - 요일 */
ul.day-checkbox {
  list-style: none;
  padding: 0 5px;
}
ul.day-checkbox li {
  display: inline;
}
ul.day-checkbox li label {
  display: inline-block;
  background-color: rgba(255, 255, 255, 0.9);
  border: 2px solid rgba(139, 139, 139, 0.3);
  color: #aaa;
  border-radius: 25px;
  margin: 3px 0px;
  user-select: none;
  transition: all 0.2s;
}

ul.day-checkbox li label {
  padding: 8px 12px;
  cursor: pointer;
}

ul.day-checkbox li label::before {
  display: none;
}

ul.day-checkbox li input[type='checkbox']:checked + label {
  border: 2px solid #e2a591;
  background-color: #e65c2e;
  color: #fff;
  transition: all 0.2s;
}

/* 카드 */
.list-card {
  clear: both;
  padding-top: 15px;
}
</style>
