<template>
  <b-modal id="review-more-modal" size="lg" scrollable>
    <template #modal-header="{ close }">
      <span id="review-more-modal-title" class="my-auto">{{
        challenge_title
      }}</span>
      <b-dropdown
        id="review-more-modal-sort"
        right
        class="my-auto ml-auto"
        :text="sortList[sortValue]"
      >
        <b-dropdown-item
          v-for="(sort, index) in sortList"
          :key="`${index}_sortList`"
          @click="sortValue = index"
          >{{ sort }}</b-dropdown-item
        >
      </b-dropdown>
      <button
        type="button"
        aria-label="Close"
        class="close my-auto ml-0"
        @click="close()"
      >
        ×
      </button>
    </template>
    <review
      v-for="(review, index) in reviewList"
      :key="`${index}_reviewList`"
      class="ml-3"
      :review="review"
    ></review>
    <template #modal-footer>
      <pagination
        class="mx-auto"
        :totalCount="reviewAllList.length"
        @pageChange="getPage"
      ></pagination>
    </template>
  </b-modal>
</template>
<script>
import Pagination from "../Pagination.vue";
import Review from "./Review.vue";

import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  components: {
    Review,
    Pagination,
  },
  props: {
    challenge_title: String,
    challenge_id: String,
  },
  data() {
    return {
      page: 1,
      sortValue: 0,
      sortList: ["최신순", "평점 높은순", "평점 낮은순"],
      reviewAllList: [],
      reviewList: [],
    };
  },
  created() {
    this.getData();
  },
  watch: {
    sortValue: function() {
      this.reviewAllList = [];
      this.getData();
    },
  },
  methods: {
    async getAllData() {
      await axios
        .get(
          `${SERVER_URL}/challenge/review/${this.challenge_id}/${this.sortValue}`
        )
        .then(({ data }) => {
          this.reviewAllList = data;
        })
        .catch(() => {
          alert("후기 목록을 불러오지 못했습니다.");
        });
    },
    async getData() {
      if (this.reviewAllList.length == 0) {
        await this.getAllData();
      }

      this.reviewList = this.reviewAllList.slice(
        (this.page - 1) * 5,
        this.page * 5
      );
    },
    getPage:function(page){
      this.page = page;
      this.reviewList = this.reviewAllList.slice(
        (this.page - 1) * 5,
        this.page * 5
      );
    }
  },
};
</script>
