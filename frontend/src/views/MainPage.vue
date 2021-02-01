<template>
  <div>
    <div class="container">
      <MainPageAd class="main-component-margin" />
      <MainPageCategory class="main-component-margin" />
      <div class="main-component-margin">
        <div class="d-flex bd-highlight px-4">
          <div class="p-2 bd-highlight main-list">인기 챌린지</div>
          <router-link :to="{
            name: 'ChallengeListCategory',
            params: { category_number: 3, category_sort: 1 },
          }" class="ml-auto p-2 bd-highlight main-more">
            <div class="main-more">전체보기 ></div>
          </router-link>
        </div>
      </div>
      <MainPageList
        v-if="isWeb === true"
        class="main-content-margin"
        :mainPageList="popularChallengeList"
      />
      <MainPageList2
        v-if="isWeb === false"
        class="main-content-margin"
        :mainPageList="popularChallengeList2"
      />
      <div class="main-component-margin">
        <div class="d-flex bd-highlight px-4">
          <div class="p-2 bd-highlight main-list">신규 챌린지</div>
          <router-link to="/challengelist" class="ml-auto p-2 bd-highlight main-more">
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
import MainPageAd from "../components/MainPage/MainPageAd.vue"
import MainPageCategory from "../components/MainPage/MainPageCategory.vue"
import MainPageList from "../components/MainPage/MainPageList.vue"
import MainPageList2 from "../components/MainPage/MainPageList2.vue"

// import axios from 'axios';
// const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
// 신규챌린지와 인기챌린지 10개씩 받아오기
// 받아온 리스트 MainPageList로 데이터 보내기
  components: {
    MainPageAd,
    MainPageCategory,
    MainPageList,
    MainPageList2,
  },
  data() {
    return {
      isWeb: false,
      newChallengeList: [
        // 예시로 넣어둠
        {name: 'Kin Khao', tag: ["Thai"]},
        {name: 'Jū-Ni', tag: ["Sushi", "Japanese", "$$$$"]},
        {name: 'Delfina', tag: ["Pizza", "Casual"]},
        {name: 'San Tung', tag: ["Chinese", "$$"]},
        {name: 'Anchor Oyster Bar', tag: ["Seafood", "Cioppino"]},
        {name: 'Locanda', tag: ["Italian"]},
        {name: 'Garden Creamery', tag: ["Ice cream"]},
      ],
      popularChallengeList: [
        // 예시로 넣어둠
        {name: 'Kin Khao', tag: ["Thai"]},
        {name: 'Jū-Ni', tag: ["Sushi", "Japanese", "$$$$"]},
        {name: 'Delfina', tag: ["Pizza", "Casual"]},
        {name: 'San Tung', tag: ["Chinese", "$$"]},
        {name: 'Anchor Oyster Bar', tag: ["Seafood", "Cioppino"]},
        {name: 'Locanda', tag: ["Italian"]},
        {name: 'Garden Creamery', tag: ["Ice cream"]},
      ],
      popularChallengeList2: [
        // 예시로 넣어둠
        {name: '2번', tag: ["Thai"]},
        {name: 'Jū-Ni', tag: ["Sushi", "Japanese", "$$$$"]},
        {name: 'Delfina', tag: ["Pizza", "Casual"]},
        {name: 'San Tung', tag: ["Chinese", "$$"]},
        {name: 'Anchor Oyster Bar', tag: ["Seafood", "Cioppino"]},
        {name: 'Locanda', tag: ["Italian"]},
        {name: 'Garden Creamery', tag: ["Ice cream"]},
      ],
    }
  },
  created() {
    // axios
      // .get(`${SERVER_URL}/challenge/all`)
      // .then(({ data }) => {
      //   this.challengeList = data;
      // })
      // .catch(() => {
      //   alert('챌린지 목록을 불러오지 못했습니다.');
      // });
  }
}
</script>

<style scoped>
.main-container {
  padding: 1rem;
}

.main-component-margin {
  margin-top: 40px;
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