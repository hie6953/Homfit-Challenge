<template>
  <b-modal id="review-more-modal" size="lg" scrollable>
    <template #modal-header="{ close }">
      <span id="review-more-modal-title">{{challenge_title}}</span>
      <button type="button" aria-label="Close" class="close" @click="close()">×</button>
    </template>
      <review
        v-for="(review, index) in reviewList"
        :key="`${index}_reviewList`"
        class="ml-3"
        :review="review"
      ></review>
    <template #modal-footer>
      <pagination class="mx-auto"
        :totalCount="88"
        @pageChange="getData"
      ></pagination>
    </template>
  </b-modal>
</template>
<script>
import Pagination from '../Pagination.vue';
import Review from "./Review.vue";
export default {
  components: {
    Review,
    Pagination,
  },
  props: {
    challenge_title:String,
    challenge_id:Number,
  },
  data() {
    return {
      page:1,
      reviewList: [
        { nick_name: "조아요" },
        { nick_name: "조아요" },
        { nick_name: "조아요" },
        { nick_name: "조아요" },
        { nick_name: "조아요" },
      ],
    };
  },
  methods: {
    getData:function(page){
      //정보 불러오기
      console.log(page);
    }
  },
};
</script>
