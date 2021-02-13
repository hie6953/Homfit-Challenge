<template>
  <div class="col-12 col-md-8 feed-container">
    <div id="instafeed">
      <div class="feedItem" v-for="i in 9" :key="i">
        <img
          src="https://images.unsplash.com/photo-1497445462247-4330a224fdb1?w=500&h=500&fit=crop"
        />
        <div class="imageInfo">
          {{ feed.challenge_title }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/MyPage/mypagefeed.css';
import '../assets/css/MyPage/mypagefeed.scss';

export default {
  data() {
    return {
      feed: [],
    };
  },
};
</script>
