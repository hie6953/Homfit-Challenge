<template>
  <div class="col-12 col-md-8 feed-container">
    <div id="instafeed">
      <div v-if="isdatathere" class="thereisnomyfeed">
        `업로드한 나의 피드가 존재하지 않습니다.`
      </div>

      <div
        v-else
        class="feedItem"
        v-for="(feed, index) in feedList"
        :key="index"
      >
        <div class="centered">
          <img class="feed-picture" :src="feed.feed_picture" />
          <!-- <img class="feed-picture" src="https://homfitimage.s3.ap-northeast-2.amazonaws.com/d42ee9bafd0856a5a0b6bd481415f399"> -->
        </div>
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
import swal from '@/assets/javascript/sweetAlert.js';
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import { mapGetters } from 'vuex';

export default {
  name: 'MyPageFeed',
  components: {},
  data: function() {
    return {
      feedList: [],
      isdatathere: false,
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
          // console.log(data);
          this.feedList = data;
          if (this.feedList.length == 0) {
            this.isdatathere = true;
          }

          // console.log(this.isdatathere);
        })
        .catch(() => {
          swal.error('오류가 발생했습니다.');
        });
    },
    // handleResize:function(){
    //   // let value = document.getElementsByClassName('feed-picture')[0].clientWidth;
    //   let arr = document.getElementById('feed-picture-0').clientWidth;
    //   for(let i = 0; i<9;++i){
    //     document.getElementById(`feed-picture-${i}`).style.height = arr;
    //   }
    //   console.log(document.getElementById(`feed-picture-0`).clientHeight + " "+document.getElementById('feed-picture-0').clientWidth);
    // },
  },
  // mounted() {
  //   // 화면 너비 측정 이벤트 추가/
  //   window.addEventListener("resize", this.handleResize);
  //   this.handleResize();
  // },
  computed: {
    ...mapGetters(['getUserUid']),
  },
};
</script>
