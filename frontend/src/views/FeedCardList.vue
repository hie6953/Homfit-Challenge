<template>
  <div class="">
    <!-- 피드 -->
    <div class="col-12 col-lg-8 feedcardlist-container">
      <feed-card
        v-for="(feed, index) in feedList"
        class="col-12 col-sm-12 challenge-list-feed"
        :key="`${index}_feed`"
        :feed="feed"
      >
      </feed-card>
      <!-- <FeedCard /> -->
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
import '../assets/css/FeedCard/feedcardlist.css';
import FeedCard from '../components/FeedCard.vue';
import InfiniteLoading from 'vue-infinite-loading';
import swal from '@/assets/javascript/sweetAlert.js';
import '@/assets/css/infiniteloading.css';

import axios from 'axios';
import { mapGetters } from 'vuex';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'FeedCardList',
  components: {
    FeedCard,
    InfiniteLoading,
  },
  data() {
    return {
      page: 1,
      feedAllList: null,
      feedList: [],
    };
  },
  computed: {
    ...mapGetters(['getUserUid']),
  },
  methods: {
    async GetFeed() {
      await axios
        .get(`${SERVER_URL}/feed/all/focus/${this.$route.params.feed_id}`, {
          params: {
            uid: this.getUserUid,
            challenge_id: this.$route.params.challenge_id,
          },
        })
        .then(({ data }) => {
          this.feedAllList = data;
          console.log(data);
        })
        .catch(() => {
          swal.error('오류가 발생했습니다.');
        });
    },

    async getData($state) {
      if (this.feedAllList == null) {
        await this.GetFeed();
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
      }, 500);
    },

    scrollUp: function() {
      window.scrollTo({
        top: 0,
        left: 0,
        behavior: 'smooth',
      });
    },
  },
};
</script>
