<template>
  <div class="">
    <div class="mx-auto col-12 col-md-8 search-tmp-container">
      <!-- 검색바 -->
      <div class="row col-12 col-md-10 col-lg-8 search-container">
        <b-dropdown
          class="search-dropdown"
          id="search-dropdown"
          variant="outline-dark"
          :text="searchList[searchValue]"
        >
          <b-dropdown-item
            v-for="(value, index) in searchList"
            :key="`${index}_searchValue`"
            @click="searchValue = index"
            >{{ value }}
          </b-dropdown-item>
        </b-dropdown>

        <div class="search-align-input">
          <input
            class="search-input"
            type="text"
            id="search-bar"
            placeholder="검색어를 입력해주세요"
            v-model="keyword"
            @keyup.enter="getNewData"
          />

          <b-icon
            icon="search"
            variant="secondary"
            class="search-icon"
            @click="getNewData"
          ></b-icon>
        </div>
      </div>

      <!-- 태그 -->
      <div class="row search-tag">
        <ul class="col-12 col-md-10 mx-auto s-tags">
          <li
            v-for="(tag, index) in returnList"
            :key="index"
            class="col-xs-2 s-tag"
            @click="gotoSearchbar(tag)"
          >
            {{ tag.tag_name }}
          </li>

          <!-- <li><a href="#" class="col-xs-2 s-tag">#홈트레이닝</a></li>
          <li><a href="#" class="col-xs-2 s-tag">#홈트레이닝</a></li>
          <li><a href="#" class="col-xs-2 s-tag">#홈트레이닝</a></li>
          <li><a href="#" class="col-xs-2 s-tag">#홈트레이닝</a></li>
          <li><a href="#" class="col-xs-2 s-tag">#홈트레이닝</a></li> -->
        </ul>
      </div>

      <!-- tab bar -->
      <div class="search-result">
        <b-tabs
          align="center"
          class="search-page-tab"
          v-model="tabValue"
          justified
        >
          <b-tab title="리스트" active>
            <div class="search-lists">
              <div class="row list-card">
                <challenge-list-card
                  v-for="(challenge, index) in challengeList"
                  :key="`${index}_challenge`"
                  class="col-6 col-md-4 col-lg-3 challenge-list-card"
                  :challenge="challenge"
                  @moreInfo="ChallengeMoreInfo"
                ></challenge-list-card>
              </div>
            </div>
            <infinite-loading
              ref="InfiniteLoadingChallenge"
              @infinite="getChallengeData"
              spinner="waveDots"
            >
              <div class="infinite-loading-message" slot="no-more">
                <b-button @click="scrollUp"
                  >마지막입니다 <b-icon icon="arrow-up-circle"></b-icon
                ></b-button>
              </div>
              <div
                v-if="challengeAllList != null"
                class="infinite-loading-message"
                slot="no-results"
              >
                결과가 없습니다 :(
              </div>
              <div v-else class="infinite-loading-message" slot="no-results">
                검색어를 입력해주세요.
              </div>
              <div class="infinite-loading-message" slot="error">
                불러오지 못했습니다.
              </div>
            </infinite-loading>
          </b-tab>
          <b-tab title="피드">
            <!-- 피드 -->
            <div class="search-lists">
              <div class="row search-feed">
                <feed
                  v-for="(feed, index) in feedList"
                  class="col-12 col-lg-4 challenge-list-feed"
                  :key="`${index}_feed`"
                  :feed="feed"
                  @feedMoreInfo="FeedMoreInfo(feed)"
                >
                </feed>
                <!-- <Feed /> -->
              </div>
            </div>
            <infinite-loading
              ref="InfiniteLoadingFeed"
              @infinite="getFeedData"
              spinner="waveDots"
            >
              <div class="infinite-loading-message" slot="no-more">
                <b-button @click="scrollUp"
                  >마지막입니다 <b-icon icon="arrow-up-circle"></b-icon
                ></b-button>
              </div>
              <div
                v-if="challengeAllList != null"
                class="infinite-loading-message"
                slot="no-results"
              >
                결과가 없습니다 :(
              </div>
              <div v-else class="infinite-loading-message" slot="no-results">
                검색어를 입력해주세요.
              </div>
              <div class="infinite-loading-message" slot="error">
                불러오지 못했습니다.
              </div>
            </infinite-loading>
          </b-tab>
        </b-tabs>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/search.scss';
import Feed from '../components/Feed.vue';
import ChallengeListCard from '../components/ChallengeListCard.vue';
import InfiniteLoading from 'vue-infinite-loading';
import swal from '@/assets/javascript/sweetAlert.js';
import '@/assets/css/infiniteloading.css';

import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'Search',
  components: {
    Feed,
    ChallengeListCard,
    InfiniteLoading,
  },
  data: function() {
    return {
      keyword: '',
      searchList: ['제목', '태그'],
      searchValue: 0,
      tabValue: 0,
      challengeAllList: null,
      challengeList: [],
      feedAllList: null,
      feedList: [],
      challengePage: 1,
      feedPage: 1,
      returnList: [],
    };
  },
  watch: {
    tabValue: function() {
      if (this == 0) {
        //챌린지
        this.challengePage = 1;
        if (this.$refs.InfiniteLoadingChallenge) {
          this.$refs.InfiniteLoadingChallenge.stateChanger.reset();
        }
      } else {
        //피드
        this.feedPage = 1;
        if (this.$refs.InfiniteLoadingFeed) {
          this.$refs.InfiniteLoadingFeed.stateChanger.reset();
        }
      }
    },
  },
  created() {
    axios
      .get(`${SERVER_URL}/challenge/popularTag`)
      .then(({ data }) => {
        console.log(data);
        this.returnList = data;
      })
      .catch(() => {
        swal.error('오류가 발생했습니다.');
      });
  },

  methods: {
    gotoSearchbar(tag) {
      // console.log(tag.tag_name);
      this.keyword = tag.tag_name;
    },
    FeedMoreInfo: function(feed) {
      // this.$store.commit("SETTMPFEED", this.feed);
      this.$router.push(`/feedcardlist/${feed.challenge_id}/${feed.feed_id}`);
    },
    ChallengeMoreInfo: function(challenge_id) {
      this.$router.push(`/challenge-more-info/${challenge_id}`);
    },
    getNewData: function() {
      console.log('들어옴');
      this.challengePage = 1;
      this.feedPage = 1;
      this.challengeAllList = null;
      this.challengeList = [];
      this.feedAllList = null;
      this.feedList = [];
      if (this.$refs.InfiniteLoadingChallenge) {
        this.$refs.InfiniteLoadingChallenge.stateChanger.reset();
      }
      if (this.$refs.InfiniteLoadingFeed) {
        this.$refs.InfiniteLoadingFeed.stateChanger.reset();
      }
      // this.getAllData();
    },
    async ChallengeListSearch() {
      // console.log('hihi');
      await axios
        .get(`${SERVER_URL}/challenge/search`, {
          params: { keyword: this.keyword, kind: this.searchValue },
        })
        .then(({ data }) => {
          // console.log(data);
          this.challengeAllList = data;
        })
        .catch(() => {
          swal.error('오류가 발생했습니다.');
        });
    },
    async FeedListSearch() {
      await axios
        .get(`${SERVER_URL}/feed/search`, {
          params: { keyword: this.keyword, kind: this.searchValue },
        })
        .then(({ data }) => {
          // console.log(data);
          this.feedAllList = data;
        })
        .catch(() => {
          swal.error('오류가 발생했습니다.');
        });
    },

    async getChallengeData($state) {
      if (this.keyword.length == 0) {
        console.log('결과가 없습니다');
        $state.complete();
        return;
      }
      if (this.challengeAllList == null) {
        console.log('가져오기');
        await this.ChallengeListSearch();
      }
      console.log(this.challengeAllList);
      let getArray = this.challengeAllList.slice(
        (this.challengePage - 1) * 10,
        this.challengePage * 10
      );
      console.log(getArray);
      setTimeout(() => {
        if (getArray.length > 0) {
          this.challengeList = this.challengeList.concat(getArray);
          ++this.challengePage;
          $state.loaded();
        } else {
          $state.complete();
        }
      }, 500);
    },
    async getFeedData($state) {
      if (this.keyword.length == 0) {
        $state.complete();
        return;
      }
      if (this.feedAllList == null) {
        await this.FeedListSearch();
      }
      let getArray = this.feedAllList.slice(
        (this.feedPage - 1) * 10,
        this.feedPage * 10
      );
      setTimeout(() => {
        if (getArray.length > 0) {
          this.feedList = this.feedList.concat(getArray);
          ++this.feedPage;
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
