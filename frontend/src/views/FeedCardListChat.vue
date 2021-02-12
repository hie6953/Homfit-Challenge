<template>
  <div class="mt-3">
    <!-- 피드 -->
    <div class="row col-12 col-lg-8 feedcardlist-container">
      <!-- <feed-card
        v-for="(feed, index) in feedList"
        class="col-6 col-md-4 col-lg-3 challenge-list-feed"
        :key="`${index}_feed`"
        :feed="feed"
      >
      </feed-card> -->
      <FeedCardChat />
    </div>
  </div>
</template>

<script>
import '../assets/css/FeedCard/feedcardlistchat.css';
import FeedCardChat from '../components/FeedCardChat.vue';

export default {
  name: 'FeedCardListChat',
  components: {
    FeedCardChat,
  },
  data: function() {
    return {
      feedList: [],
    };
  },
  methods: {},
};
</script>
