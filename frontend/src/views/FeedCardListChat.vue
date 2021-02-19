<template>
  <div class="">
    <!-- 피드 -->
    <div class="row col-12 col-lg-8 feedcardlist-container">
      <!-- <feed-card-chat
        v-for="(feed, index) in feedList"
        class="col-12 challenge-list-feed"
        :key="`${index}_feed`"
        :feed="feed"
      >
      </feed-card-chat> -->
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
