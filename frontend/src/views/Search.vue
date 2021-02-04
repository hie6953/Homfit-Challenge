<template>
  <div class="mt-3">
    <hr id="hr-top" />

    <div class="mx-auto col-8 search-container">
      <!-- 검색바 -->
      <div class="search-bar">
        <form class="search-container">
          <input
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
      <div class="search-tag">
        <ul class="col-10 col-md-10 pb-8 mx-auto s-tags">
          <li><a href="#" class="s-tag">#홈트레이닝</a></li>
          <li><a href="#" class="s-tag">#땅끄부부</a></li>
          <li><a href="#" class="s-tag">#인기태그</a></li>
          <li><a href="#" class="s-tag">#인기태그2</a></li>
          <li><a href="#" class="s-tag">#운동하자</a></li>
        </ul>
      </div>

      <!-- tab bar -->
      <!-- 피드 -->
      <div class="search-feed">
        <Feed />
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/search.scss';
import Feed from '../components/Feed.vue';

export default {
  name: 'Search',
  components: {
    Feed,
  },
  data: function() {
    return {};
  },
};
</script>
