<template>
  <div class="mt-3">
    <hr id="hr-top" />

    <div class="mx-auto col-12 search-container">
      <!-- 검색바 -->
      <div class="search-bar">
        <form class="row col-8 search-container">
          <b-dropdown
            class="col-2"
            id="search-dropdown"
            variant="outline-dark"
            :text="searchList"
          >
            <b-dropdown-item
              v-for="(value, index) in searchList"
              :key="`${index}_searchValue`"
              @click="searchValue = index"
              >{{ value }}
            </b-dropdown-item>
          </b-dropdown>
          <input
            class="col-10"
            type="text"
            id="search-bar"
            placeholder="검색어를 입력해주세요"
          />
          <!-- <a href="#"
            ><img
              class="search-icon"
              src="http://www.endlessicons.com/wp-content/uploads/2012/12/search-icon.png"
          /></a> -->
          <a href="#"
            ><b-icon
              icon="search"
              variant="secondary"
              class="search-icon"
            ></b-icon
          ></a>
        </form>
      </div>

      <!-- 태그 -->
      <div class="row search-tag">
        <ul class="col-xs-12 mx-auto s-tags">
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
          <b-tab title="리스트">
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
          <b-tab title="피드" active>
            <!-- 피드 -->
            <div class="row">
              <div class="search-feed">
                <!-- <feed
                  v-for="(challenge, index) in feedList"
                  :key="`${index}_challenge`"
                  >
                </feed> -->
                <Feed />
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

export default {
  name: 'Search',
  components: {
    Feed,
    ChallengeListCard,
  },
  data: function() {
    return {
      searchList: ['챌린지명'],
    };
  },
};
</script>
