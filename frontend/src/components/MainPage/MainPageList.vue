<template>
  <div class="main-page-list-carousel-container">
    <!-- 웹  캐러셀 -->
    <div class="card-carousel-wrapper carousel-web">
      <div class="card-carousel--nav__left" @click="moveCarousel(-1)" :disabled="atHeadOfList"></div>
      <div class="card-carousel">
        <div class="card-carousel--overflow-container">
          <div class="card-carousel-cards" :style="{ transform: 'translateX' + '(' + currentOffset + 'px' + ')'}">
            <div class="card-carousel-card col-4" v-for="item in items" :key="item.id">
              <img src="https://placehold.it/340x340"/>
              <div class="card-carousel--card--footer">
                <p>{{ item.name }}</p>
                <p class="tag" v-for="(tag,index) in item.tag" :class="index &gt; 0 ? 'secondary' : ''" :key="tag.id">{{ tag }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="card-carousel--nav__right" @click="moveCarousel(1)" :disabled="atEndOfList"></div>
    </div>
    <!-- 모바일 캐러셀 -->
    <flickity ref="flickity" :options="flickityOptions" class="carousel-mobile">
      <div class="carousel-cell mx-1" v-for="item in items" :key="item.id">
        <img src="https://placehold.it/340x340"/>
        <p>{{ item.name }}</p>
      </div>
    </flickity>
  </div>
</template>

<script>
import "@/assets/css/mainPageList.css"
import Flickity from 'vue-flickity'
// import ChallengeListCard from '../components/ChallengeListCard.vue'

export default {
  name: 'MainPageList',
  components: {
    Flickity
  },
  // components: { ChallengeListCard },
  props: {
    mainPageList: Array,
  },
  data() {
    return {
      currentOffset: 0,
      windowSize: 3,
      paginationFactor: 370,

      items: this.mainPageList,

      // 모바일 캐러셀
      flickityOptions: {
        initialIndex: 3,
        prevNextButtons: false,
        pageDots: false,
        wrapAround: true
      }
    }
  },
  computed: {
    // 웹 캐러셀 리스트 양 끝에 갔을 때 버튼 비활성화
    atEndOfList() {
      return this.currentOffset <= (this.paginationFactor * -1) * (this.items.length - this.windowSize);
    },
    atHeadOfList() {
      return this.currentOffset === 0;
    },
  },
  methods: {
    // 웹 캐러셀 움직임
    moveCarousel(direction) {
      if (direction === 1 && !this.atEndOfList) {
        this.currentOffset -= this.paginationFactor;
      } else if (direction === -1 && !this.atHeadOfList) {
        this.currentOffset += this.paginationFactor;
      }
    },
  }
}
</script>

<style>

</style>