<template>
  <!-- axios -->
  <div class="searchfeed-item" @click="FeedMoreInfo">
    <!-- <img
      src="https://images.unsplash.com/photo-1426604966848-d7adac402bff?w=500&h=500&fit=crop"
      class="responsive-image"
    /> -->
    <div class="searchfeed-center">
      <img :src="feed.feed_picture" class="responsive-image" />
    </div>

    <div class="searchfeed-item-info">
      <ul>
        <li class="searchfeed-item-likes">
          <span class="visually-hidden">{{ feed.challenge_title }}</span>
        </li>
      </ul>
    </div>
  </div>

  <!-- 사진1 -->
  <!-- <div class="col-xs-4 searchfeed-item">
          <img
            src="https://images.unsplash.com/photo-1426604966848-d7adac402bff?w=500&h=500&fit=crop"
            class="responsive-image"
            @click="ChallengeMoreInfo"
          />
          <div class="searchfeed-item-info">
            <ul>
              <li class="searchfeed-item-likes">
                <span class="visually-hidden"
                  >안녕 우리 함께 운동하쟙안녕 우리 함께 운동하쟙안녕 우리 함께
                  운동하쟙</span
                >
              </li>
            </ul>
          </div>
        </div> -->
</template>

<script>
import '../assets/css/feed.css';

export default {
  name: 'Feed',
  props: {
    feed: Object,
  },
  data: function() {
    return {};
  },
  methods: {
    FeedMoreInfo: function() {
      this.$store.commit('SETTMPFEED', this.feed);
      this.$router.push('/feedcardlist');
    },
  },
};
</script>
