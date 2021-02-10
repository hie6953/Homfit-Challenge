<template>
  <div>
    <div class="main-container col-12 col-md-8 col-xl-6">
      <MainPageAd class="main-component-margin" />
      <MainPageCategory class="main-component-margin" />
      <div class="main-component-margin">
        <div class="d-flex bd-highlight px-4">
          <div class="p-2 bd-highlight main-list">인기 챌린지</div>
          <router-link :to="{
            name: 'ChallengeListCategory',
            params: { category_number: 0, category_sort: 0 },
          }" class="ml-auto p-2 bd-highlight main-more">
            <div class="main-more">전체보기 ></div>
          </router-link>
        </div>
      </div>
      <MainPageList
        class="main-content-margin"
        :mainPageList="popularChallengeList"
      />
      <div class="main-component-margin">
        <div class="d-flex bd-highlight px-4">
          <div class="p-2 bd-highlight main-list">신규 챌린지</div>
          <router-link :to="{
            name: 'ChallengeListCategory',
            params: { category_number: 0, category_sort: 1 },
          }" class="ml-auto p-2 bd-highlight main-more">
            <div class="main-more">전체보기 ></div>
          </router-link>
        </div>
      </div>
      <MainPageList
        class="main-content-margin"
        :mainPageList="newChallengeList"
      />
    </div>
    <div style="height:100px;">
      <p></p>
      <p></p>
      <p></p>
    </div>
  </div>
</template>

<script>
axios.defaults.paramsSerializer = function(paramObj) {
    const params = new URLSearchParams()
    for (const key in paramObj) {
        params.append(key, paramObj[key])
    }
    return params.toString()
}

import MainPageAd from "../components/MainPage/MainPageAd.vue"
import MainPageCategory from "../components/MainPage/MainPageCategory.vue"
import MainPageList from "../components/MainPage/MainPageList.vue"

import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
// 신규챌린지와 인기챌린지 20개씩 받아오기
// 받아온 리스트 MainPageList로 데이터 보내기
  components: {
    MainPageAd,
    MainPageCategory,
    MainPageList,
  },
  data() {
    return {
      newChallengeList: [],
      popularChallengeList: [],
    }
  },
  created() {
    axios
        .get(`${SERVER_URL}/challenge/all`, {
          params: {
            category: 0, //0:전체, 1~10 카테고리숫자
            sort: 0, //0:인기순,1:최신순
            periodStart: 0, //period최소값(이상) 7
            periodEnd: 0, //period최대값(이하) 30
            day: [], //요일 숫자 배열 [3,4,5]
            page: 1, //페이지 숫자
          },
        })
        .then(({ data }) => {
          this.popularChallengeList = data;
        })
        .catch(() => {
          alert('챌린지 목록을 불러오지 못했습니다.');
        });
    axios
        .get(`${SERVER_URL}/challenge/all`, {
          params: {
            category: 0, //0:전체, 1~10 카테고리숫자
            sort: 1, //0:인기순,1:최신순
            periodStart: 0, //period최소값(이상) 7
            periodEnd: 0, //period최대값(이하) 30
            day: [], //요일 숫자 배열 [3,4,5]
            page: 1, //페이지 숫자
          },
        })
        .then(({ data }) => {
          this.newChallengeList = data;
        })
        .catch(() => {
          alert('챌린지 목록을 불러오지 못했습니다.');
        });
    }
}
</script>

<style scoped>
.main-container {
  margin: 0 auto;
}

.main-component-margin {
  margin-top: 40px;
  padding: 0;
}

.main-content-margin {
  margin-top: 20px;
}

.main-list {
  font-weight: 700;
  font-size: 1.5rem;
}

.main-more:hover {
  text-decoration: none;
}
</style>