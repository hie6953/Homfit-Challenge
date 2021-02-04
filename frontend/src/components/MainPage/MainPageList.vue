<template>
  <div class="main-page-list-carousel-container">
    <!-- 웹  캐러셀 -->
    <div class="card-carousel-wrapper carousel-web">
      <div class="card-carousel--nav__left" @click="moveCarousel(-1)" :disabled="atHeadOfList"></div>
      <div class="card-carousel">
        <div class="card-carousel--overflow-container">
          <div class="card-carousel-cards">
            <!-- :style="{ transform: 'translateX' + '(' + currentOffset + 'px' + ')'}" -->
            <div class="card-carousel-card col-4 mx-1" v-for="item in items" :key="item.id">
              <img src="https://picsum.photos/600/300/?image=25"/>
              <p>{{ item.challenge_title }}</p>
              asdfasdf
            </div>
          </div>
        </div>
      </div>
      <div class="card-carousel--nav__right" @click="moveCarousel(1)" :disabled="atEndOfList"></div>
    </div>
              <!-- <div class="card-carousel--card--footer">
                <br />
                <img 
                  class="card-user-image"
                  src="@/assets/NavBar/anonimous_user.png"
                />
                <br />
              </div> -->
                  <!-- <span id="card-user-nick-name">{{ item.nick_name }}</span>
                  <span id="card-day">{{ GetDayList() }}</span>
                  <span id="card-period">{{ item.period }}일</span> -->
              <!-- 
                <hr class="challenge-list-card-hr" />
                <div class="challenge-list-card-footer">
                  <span>{{ item.people }}명 참여중</span>
                </div> 
              </div>-->
                <!-- <div class="challenge-list-card-body">
                  <span id="card-title">{{ challenge.challenge_title }}</span>
                </div>
                <p class="tag" v-for="(tag,index) in item.tag" :class="index &gt; 0 ? 'secondary' : ''" :key="tag.id">{{ tag }}</p>
                </div> -->
            
    <!-- 모바일 캐러셀 -->
    <flickity ref="flickity" :options="flickityOptions" class="carousel-mobile">
      <!-- <div class="carousel-cell mx-1" v-for="item in items" :key="item.id">
        <ChallengeListCard />
      </div> -->
      <challenge-list-card
        v-for="(challenge, index) in mainPageList"
        :key="`${index}_challenge`"
        class="challenge-list-card carousel-cell"
        :challenge="challenge"
      ></challenge-list-card>
    </flickity>
  </div>
</template>

<script>
import "@/assets/css/mainPageList.css"
import Flickity from 'vue-flickity'
import ChallengeListCard from '../ChallengeListCard.vue'

export default {
  name: 'MainPageList',
  components: {
    Flickity,
    ChallengeListCard
  },
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