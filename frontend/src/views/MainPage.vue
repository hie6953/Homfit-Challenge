<template>
  <div>
    <div class="container">
      <MainPageAd class="main-component-margin" />
      <MainPageCategory class="main-component-margin" />
      <div class="main-component-margin">
        <div class="d-flex bd-highlight px-4">
          <div class="p-2 bd-highlight main-list">인기 챌린지</div>
          <router-link to="/challengelist" class="ml-auto p-2 bd-highlight main-more">
            <div class="main-more">전체보기 ></div>
          </router-link>
        </div>
      </div>
      <MainPageList class="main-content-margin" />
    </div>
  </div>
</template>

<script>
import MainPageAd from "../components/MainPage/MainPageAd.vue"
import MainPageCategory from "../components/MainPage/MainPageCategory.vue"
import MainPageList from "../components/MainPage/MainPageList.vue"

export default {

  components: {
    MainPageAd,
    MainPageCategory,
    MainPageList,
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