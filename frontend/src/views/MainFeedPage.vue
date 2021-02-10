<template>
  <div class="mt-3">
    <!-- 카테고리 -->
    <div class="category-background row mx-auto">
      <div class="category mx-auto">
        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="0"
          v-model="category"
          id="category-total"
          checked
        />
        <label class="for-checkbox-tools" for="category-total">
          전체
        </label>
        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="1"
          v-model="category"
          id="category-yoga"
        />
        <label class="for-checkbox-tools" for="category-yoga"> 요가 </label>
        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="2"
          v-model="category"
          id="category-pilates"
        />
        <label class="for-checkbox-tools" for="category-pilates">
          필라테스 </label
        ><input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="3"
          v-model="category"
          id="category-aerobic"
        />
        <label class="for-checkbox-tools" for="category-aerobic">
          유산소
        </label>

        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="4"
          v-model="category"
          id="category-dance"
        />
        <label class="for-checkbox-tools" for="category-dance">
          댄스
        </label>
        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="5"
          v-model="category"
          id="category-stretching"
        />
        <label class="for-checkbox-tools" for="category-stretching">
          스트레칭
        </label>
        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="6"
          v-model="category"
          id="category-strength"
        />
        <label class="for-checkbox-tools" for="category-strength">
          근력
        </label>
        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="7"
          v-model="category"
          id="category-kids"
        />
        <label class="for-checkbox-tools" for="category-kids">
          키즈
        </label>
        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="8"
          v-model="category"
          id="category-boxing"
        />
        <label class="for-checkbox-tools" for="category-boxing">
          복싱
        </label>
        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="9"
          v-model="category"
          id="category-food"
        />
        <label class="for-checkbox-tools" for="category-food">
          식단
        </label>
        <input
          class="checkbox-tools"
          type="radio"
          name="tools"
          value="10"
          v-model="category"
          id="category-etc"
        />
        <label class="for-checkbox-tools" for="category-etc">
          기타
        </label>
      </div>
    </div>
    <!-- 피드 -->
    <div class="row col-8 mainfeedpage-container">
      <main-feed
        v-for="(feed, index) in feedList"
        class="col-6 col-md-4 col-lg-3 challenge-list-feed"
        :key="`${index}_feed`"
        :feed="feed"
      >
      </main-feed>
      <!-- <MainFeed /> -->
    </div>
  </div>
</template>

<script>
import '../assets/css/mainfeed/mainfeedpage.css';
import MainFeed from '../components/MainFeed.vue';
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'MainFeedPage',
  components: {
    MainFeed,
  },
  data: function() {
    return {
      category: 0,
      feedList: [],
    };
  },
  created() {
    this.GetFeed();
  },
  watch: {
    category: function() {
      if (this.category == 0) {
        this.GetFeed();
      } else {
        this.GetFeedbyCategory();
      }
    },
  },
  methods: {
    GetFeedbyCategory() {
      axios
        .get(`${SERVER_URL}/feed/category`, {
          params: { category: this.category },
        })
        .then(({ data }) => {
          console.log(data);
          this.feedList = data;
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },

    GetFeed() {
      axios
        .get(`${SERVER_URL}/feed/all`)
        .then(({ data }) => {
          console.log(data);
          this.feedList = data;
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },
  },
};
</script>
