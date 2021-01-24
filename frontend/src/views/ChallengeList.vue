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
        <input type="checkbox" id="fruit1" name="fruit-1" value="Apple" />
        <label for="fruit1">1일~1주</label>
        <input type="checkbox" id="fruit2" name="fruit-2" value="Banana" />
        <label for="fruit2">1주~2주</label>
        <input type="checkbox" id="fruit3" name="fruit-3" value="Cherry" />
        <label for="fruit3">2주~3주</label>
        <input type="checkbox" id="fruit4" name="fruit-4" value="Strawberry" />
        <label for="fruit4">3주~</label>

        <b-dropdown-divider></b-dropdown-divider>
        <h5>요일</h5>
        <button id="dayButton">
          월
        </button>
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
  data() {
    return {
      sortList: ['인기순', '최신순'],
      sortValue: '인기순',
      challengeList: [{ id: 'id' }, { id: 'id' }, { id: 'id' }, { id: 'id' }],
    };
  },
};
</script>

<style>
#sort-dropdown {
  float: left;
}

.dropdown-item:active {
  background-color: #e65c2e !important;
}

#filter-dropdown {
  float: right;
}

/* 필터 드롭다운 화살표 지우기 */
#filter-dropdown > button::after {
  display: none;
}

h5 {
  margin-left: 5px;
}

.list-card {
  clear: both;
  padding-top: 15px;
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
  border: 0.1em solid #000;
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
</style>
