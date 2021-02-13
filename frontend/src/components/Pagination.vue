<template>
  <div>
    <b-button-group class="pagination-button-group">
      <b-button :class="{ disabled: !canGoFront }" @click="pageMove(0)"
        ><b-icon icon="chevron-double-left"></b-icon
      ></b-button>
      <b-button :class="{ disabled: !canGoFront }" @click="pageMove(1)"
        ><b-icon icon="chevron-left"></b-icon
      ></b-button>
      <b-button
        v-for="(value, index) in pageList"
        :key="`${index}_pageList`"
        :class="{ pageChoice: value==page }"
        class="pagination-button-number"
        @click="page = value"
      >
        {{ value }}
      </b-button>
      <b-button :class="{ disabled: !canGoEnd }" @click="pageMove(2)"
        ><b-icon icon="chevron-right"></b-icon
      ></b-button>
      <b-button :class="{ disabled: !canGoEnd }" @click="pageMove(3)"
        ><b-icon icon="chevron-double-right"></b-icon
      ></b-button>
    </b-button-group>
  </div>
</template>
<script>
import "@/assets/css/pagination.css";

export default {
  props: {
    totalCount: Number,
  },
  data() {
    return {
      page: 1,
      pageList: [],
      onePageCount: 5,
      lastPage: 0,
      canGoFront: false,
      canGoEnd: true,
    };
  },
  created() {
    this.lastPage = Math.ceil(this.totalCount / this.onePageCount);
    this.calculatePageList();
  },
  watch: {
    page: function() {
      this.$emit("pageChange",this.page);
    },
  },
  methods: {
    calculatePageList: function() {
      let index = 0;
      let startNum =
        Math.floor((this.page - 1) / this.onePageCount) * this.onePageCount;
      let endNum = startNum + this.onePageCount;
      this.canGoFront = startNum != 0;

      if (endNum >= this.lastPage) {
        this.canGoEnd = false;
        endNum = this.lastPage;
      } else {
        this.canGoEnd = true;
      }
      this.pageList = [];
      for (let i = startNum; i < endNum; ++i) {
        this.pageList[index++] = i + 1;
      }
    },
    pageMove: function(mode) {
      switch (mode) {
        case 0:
            if(!this.canGoFront) return; 
          this.page = 1;
          break;
        case 1:
            if(!this.canGoFront) return; 
          this.page =
            (Math.ceil(this.page / this.onePageCount) - 1) * this.onePageCount;
          break;
        case 2:
            if(!this.canGoEnd) return; 
          this.page =
            Math.ceil(this.page / this.onePageCount) * this.onePageCount + 1;
          break;
        case 3:
            if(!this.canGoEnd) return; 
          this.page = this.lastPage;
          break;
      }
      this.calculatePageList();
    },
  },
};
</script>
