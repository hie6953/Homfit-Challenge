<template>
  <div>
    <div class="mb-3 width-100">
      <div class="more-info-header">
        <span class="more-info-total-score">
          <span class="total-score-number">
            <span id="total-score-number-info" class="mr-2">총 평점</span>
            <!-- <span id="total-score-number-value" class="mr-2">{{ totalScore }}</span> -->
          </span>
          <span v-for="num in avg_review" :key="`${num}_totalScore`"
            ><b-icon icon="star-fill" class="color-yellow"></b-icon
          ></span>
          <span v-for="num in 5 - avg_review" :key="`${num}_noTotalScore`"
            ><b-icon icon="star-fill" class="color-gray"></b-icon
          ></span>
          
        </span>
        <button class="review-go-detail" v-b-modal.review-more-modal>
          + 더보기
        </button>
      </div>
      <review
        v-for="(review, index) in reviewList"
        :key="`${index}_reviewList`"
        :review="review"
      ></review>
    </div>
  </div>
</template>

<script>
import Review from "./Review.vue";
export default {
  components: { Review },
  name: "ChallengeReview",
  props: {
    reviewList: Array,
    avg_review:Number,
  },
  methods: {},
};
</script>
