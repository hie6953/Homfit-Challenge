<template>
  <div class="">
    <!-- 카테고리 -->
    <div class="category-background row mx-auto">
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
        <label class="for-checkbox-tools" for="category-yoga"> 요가 </label>
        <input
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
    <!-- 피드 -->
    <div class="row col-12 col-lg-8 mainfeedpage-container">
      <main-feed
        v-for="(feed, index) in feedList"
        class="col-6 col-md-4 col-lg-3 challenge-list-feed"
        :key="`${index}_feed`"
        :feed="feed"
        @feedMoreInfo="FeedMoreInfo(feed)"
      >
      </main-feed>
      <!-- <MainFeed /> -->
    </div>
      <infinite-loading
        ref="InfiniteLoading"
        @infinite="getData"
        spinner="waveDots"
      >
        <div class="infinite-loading-message" slot="no-more">
          <b-button @click="scrollUp"
            >마지막입니다 <b-icon icon="arrow-up-circle"></b-icon
          ></b-button>
        </div>
        <div class="infinite-loading-message" slot="no-results">
          결과가 없습니다 :(
        </div>
        <div class="infinite-loading-message" slot="error">
          불러오지 못했습니다.
        </div>
      </infinite-loading>
  </div>
</template>

<script>
import "../assets/css/mainfeed/mainfeedpage.css";
import MainFeed from "../components/MainFeed.vue";
import InfiniteLoading from "vue-infinite-loading";
import FeedDummyData from "@/assets/dummyData/feedDummyData.json";
import "@/assets/css/infiniteloading.css";
import swal from '@/assets/javascript/sweetAlert.js';

import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "MainFeedPage",
  components: {
    MainFeed,
    InfiniteLoading,
  },
  data: function() {
    return {
      page: 1,
      category: 0,
      feedList: [],
      feedAllList: null,
    };
  },
  watch: {
    category: function() {
      this.getNewData();
    },
  },
  methods: {
    FeedMoreInfo: function(feed) {
      // this.$store.commit("SETTMPFEED", this.feed);
      this.$router.push(`/feedcardlist/${feed.challenge_id}/${feed.feed_id}`);
    },
    getNewData: function() {
      this.page = 1;
      this.feedAllList = null;
      this.feedList = [];
      if (this.$refs.InfiniteLoading) {
        this.$refs.InfiniteLoading.stateChanger.reset();
      }
      // this.getAllData();
    },
    async GetFeedbyCategory() {
      await axios
        .get(`${SERVER_URL}/feed/category`, {
          params: { category: this.category },
        })
        .then(({ data }) => {
          // console.log(data);
          this.feedAllList = data;
        })
        .catch(() => {
          swal.error('오류가 발생했습니다.');
          this.feedAllList = FeedDummyData.feedList;
        });
    },

    async GetFeed() {
      await axios
        .get(`${SERVER_URL}/feed/all`)
        .then(({ data }) => {
          // console.log(data);
          this.feedAllList = data;
        })
        .catch(() => {
          swal.error('오류가 발생했습니다.');
          this.feedAllList = FeedDummyData.feedList;
        });
    },

    async getData($state) {
      if (this.feedAllList == null) {
        if (this.category == 0) {
          await this.GetFeed();
        } else {
          await this.GetFeedbyCategory();
        }
      }
      let getArray = this.feedAllList.slice(
        (this.page - 1) * 10,
        this.page * 10
      );
      setTimeout(() => {
        if (getArray.length > 0) {
          this.feedList = this.feedList.concat(getArray);
          ++this.page;
          $state.loaded();
        } else {
          $state.complete();
        }
      }, 100);
    },

    scrollUp: function() {
      window.scrollTo({
        top: 0,
        left: 0,
        behavior: "smooth",
      });
    },
  },
};
</script>
