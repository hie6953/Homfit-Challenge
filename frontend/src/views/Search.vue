<template>
  <div class="mt-3">
    <hr id="hr-top" />

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
            @keyup.enter="ChallengeListSearch"
          />

          <b-icon
            icon="search"
            variant="secondary"
            class="search-icon"
            @click="ChallengeListSearch"
          ></b-icon>
        </div>
      </div>

      <!-- 태그 -->
      <div class="row search-tag">
        <ul class="col-12 col-md-10 mx-auto s-tags">
          <li><a href="#" class="col-xs-2 s-tag">#홈트레이닝</a></li>
          <li><a href="#" class="col-xs-2 s-tag">#땅끄부부</a></li>
          <li><a href="#" class="col-xs-2 s-tag">#인기태그</a></li>
          <li><a href="#" class="col-xs-2 s-tag">#인기태그2</a></li>
          <li><a href="#" class="col-xs-2 s-tag">#운동하자</a></li>
        </ul>
      </div>

      <!-- tab bar -->
      <div class="search-result">
        <b-tabs
          content-class="mt-3"
          align="center"
          class="search-page-tab"
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
                >
                </feed>
                <!-- <Feed /> -->
              </div>
            </div>
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
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'Search',
  components: {
    Feed,
    ChallengeListCard,
  },
  data: function() {
    return {
      keyword: '',
      searchList: ['제목', '태그'],
      searchValue: 0,
      challengeList: [],
      feedList: [],
    };
  },
  methods: {
    ChallengeMoreInfo: function(challenge_id) {
      this.$router.push(`/challenge-more-info/${challenge_id}`);
    },
    ChallengeListSearch: function() {
      // console.log('hihi');
      axios
        .get(`${SERVER_URL}/challenge/search`, {
          params: { keyword: this.keyword, kind: this.searchValue },
        })
        .then(({ data }) => {
          // console.log(data);
          this.challengeList = data;
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });

      axios
        .get(`${SERVER_URL}/feed/search`, {
          params: { keyword: this.keyword, kind: this.searchValue },
        })
        .then(({ data }) => {
          // console.log(data);
          this.feedList = data;
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },
  },
};
</script>
