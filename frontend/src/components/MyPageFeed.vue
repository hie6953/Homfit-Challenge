<template>
  <div class="col-12 col-md-8 feed-container">
    <div id="instafeed">
      <div class="feedItem" v-for="(feed, index) in feedList" :key="index">
        <img :src="feed.feed_picture" />
        <!-- <img src="https://images.unsplash.com/photo-1497445462247-4330a224fdb1?w=500&h=500&fit=crop"> -->
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
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import { mapGetters } from 'vuex';

export default {
  name: 'MyPageFeed',
  components: {},
  data: function() {
    return {
      feedList: [],
    };
  },
  created() {
    this.LoadMyPageFeed();
  },
  methods: {
    LoadMyPageFeed() {
      axios
        .get(`${SERVER_URL}/feed/mypage`, {
          params: {
            uid: this.getUserUid,
          },
        })
        .then(({ data }) => {
          console.log(data);
          this.feedList = data;
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },
  },
  computed: {
    ...mapGetters(['getUserUid']),
  },
};
</script>
