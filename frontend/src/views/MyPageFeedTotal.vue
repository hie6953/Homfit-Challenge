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
  </div>
</template>

<script>
import '../assets/css/FeedCard/feedcardlist.css';
import FeedCard from '../components/FeedCard.vue';
import { mapGetters } from 'vuex';
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'MyPageFeedTotal',
  components: {
    FeedCard,
  },
  computed: {
    ...mapGetters(['getUserUid']),
  },
  created() {
    axios
      .get(`${SERVER_URL}/feed/mypage/allFeed`, {
        params: {
          uid: this.getUserUid,
        },
      })
      .then(({ data }) => {
        this.feedList = data;
        console.log(data);
      })
      .catch(() => {
        alert('에러가 발생했습니다.');
      });
  },
  data: function() {
    return {
      feedList: [],
    };
  },
  methods: {},
};
</script>
