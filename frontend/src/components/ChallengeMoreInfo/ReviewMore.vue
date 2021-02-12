<template>
  <b-modal id="review-more-modal" size="lg" scrollable>
    <template #modal-header="{ close }">
      <span id="review-more-modal-title" class="my-auto">{{challenge_title}}</span>
      <b-dropdown id="review-more-modal-sort" right class="my-auto ml-auto" :text="sortList[sortValue]">
        <b-dropdown-item v-for="(sort,index) in sortList" :key="`${index}_sortList`" @click="sortValue = index">{{sort}}</b-dropdown-item>
      </b-dropdown>
      <button type="button" aria-label="Close" class="close my-auto ml-0" @click="close()">×</button>
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
      sortValue:0,
      sortList:['최신순','평점 높은순','평점 낮은순'],
      reviewList: [
        { nick_name: "조아요" },
        { nick_name: "조아요" },
        { nick_name: "조아요" },
        { nick_name: "조아요" },
        { nick_name: "조아요" },
      ],
    };
  },
  watch:{
    sortValue:function(){
      console.log(this.sortValue);
    },
  },
  methods: {
    getData:function(page){
      //정보 불러오기
      console.log(page);
    }
  },
};
</script>
