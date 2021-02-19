<template>
  <!-- axios -->
  <div class="main-feed-item" @click="FeedMoreInfo">
    <!-- <img
      src="https://images.unsplash.com/photo-1426604966848-d7adac402bff?w=500&h=500&fit=crop"
      class="responsive-image"
    /> -->
    <div class="mainfeed-center">
      <img :src="feed.feed_picture" class="responsive-image" />
    </div>

    <div class="main-feed-item-info">
      <ul>
        <li class="main-feed-item-likes">
          <span class="visually-hidden">{{ feed.challenge_title }}</span>
        </li>
      </ul>
    </div>
  </div>

  <!-- 사진1 -->
  <!-- <div class="col-xs-4 main-feed-item">
          <img
            src="https://images.unsplash.com/photo-1426604966848-d7adac402bff?w=500&h=500&fit=crop"
            class="responsive-image"
            @click="ChallengeMoreInfo"
          />
          <div class="main-feed-item-info">
            <ul>
              <li class="main-feed-item-likes">
                <span class="visually-hidden"
                  >안녕 우리 함께 운동하쟙안녕 우리 함께 운동하쟙안녕 우리 함께
                  운동하쟙</span
                >
              </li>
            </ul>
          </div>
        </div> -->

  <!-- </div>
    </div>
  </div> -->
</template>

<script>
import '../assets/css/mainfeed/mainfeed.css';

export default {
  name: 'MainFeed',
  props: {
    feed: Object,
  },
  data: function() {
    return {};
  },
  methods: {
    FeedMoreInfo: function() {
      this.$emit("feedMoreInfo");
    },
  },
};
</script>
