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
    <div class="mainfeedpage-container">
      <MainFeed />
    </div>
  </div>
</template>

<script>
import '../assets/css/mainfeed/mainfeedpage.css';
import MainFeed from '../components/MainFeed.vue';

export default {
  name: 'Feed',
  components: {
    MainFeed,
  },
  props: {
    challenge: Object,
  },
  data: function() {
    return {
      category: 0,
    };
  },
  methods: {
    ChallengeMoreInfo: function() {
      this.$emit('moreInfo', this.challenge.challenge_id);
    },
  },
};
</script>
