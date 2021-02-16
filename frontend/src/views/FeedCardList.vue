<template>
  <div class="">
    <!-- 피드 -->
    <div class="row col-12 col-lg-8 feedcardlist-container">
      <feed-card
        v-for="(feed, index) in feedList"
        class="col-12 challenge-list-feed"
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
import axios from 'axios';
import { mapGetters } from 'vuex';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'FeedCardList',
  components: {
    FeedCard,
  },
  computed: {
    ...mapGetters(['getTmpFeed', 'getUserUid']),
  },
  created() {
    this.tmpfeed = this.getTmpFeed;

    axios
      .get(`${SERVER_URL}/feed/all/focus/${this.tmpfeed.feed_id}`, {
        params: {
          uid: this.getUserUid,
          challenge_id: this.tmpfeed.challenge_id,
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
      tmpfeed: {},
    };
  },
  methods: {},
};
</script>
