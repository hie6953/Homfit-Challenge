<template>
  <div>
    <div class="mb-3 width-100">
      <div class="more-info-header">
        <span class="more-info-title">챌린지 후기</span>
        <span class="ml-3 more-info-total-score">
          <span v-for="num in RoundTotalScore" :key="`${num}_totalScore`"
            ><b-icon icon="star-fill" class="color-yellow"></b-icon
          ></span>
          <span v-for="num in 5 - RoundTotalScore" :key="`${num}_noTotalScore`"
            ><b-icon icon="star-fill" class="color-gray"></b-icon
          ></span>
          <span class="total-score-number ml-2">{{ totalScore }}</span>
        </span>
        <button class="review-go-detail" v-b-modal.review-more-modal>+ 더보기</button>
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

  data() {
    return {
      totalScore: 3,
      modalShow:true,
      reviewList: [
        { nick_name: "안녕" },
        { nick_name: "안녕" },
        { nick_name: "안녕" },
      ],
    };
  },
  computed: {
    RoundTotalScore: function() {
      return Math.round(this.totalScore);
    },
  },
  methods: {
  },
};
</script>
