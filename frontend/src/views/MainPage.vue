<template>
  <div>
    <div class="container">
      <MainPageAd class="main-component-margin" />
      <MainPageCategory class="main-component-margin" />
    </div>
    <div style="height:100px;">
      <p></p>
      <p></p>
      <p></p>
    </div>
  </div>
</template>

<script>
import MainPageAd from '../components/MainPage/MainPageAd.vue';
import MainPageCategory from '../components/MainPage/MainPageCategory.vue';

export default {
  components: {
    MainPageAd,
    MainPageCategory,
  },
};
</script>

<style scoped>
.main-container {
  padding: 1rem;
}
.main-component-margin {
  margin-top: 40px;
}
</style>
