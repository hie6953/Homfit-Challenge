<template>
  <div>
    <!-- <calendar-Modal
      :modalShow="modalShow"
      @modalClose="modalShow = false"
      class="btn btn-primary"
    >클릭</calendar-Modal> -->
    <CalendarModal
      :minYear="minYear"
      :maxYear="maxYear"
    />
    <div class="row">
      <b-icon icon="arrow-left-short" class="btn btn-primary col-1" scale="1" @click="monthMinus"></b-icon>
      <div class="col-2 px-auto">{{ year }}년 
        <span v-if="month > 9">{{ month }}</span>
        <span v-else>0{{ month }}</span>월
      </div>
      <b-icon icon="arrow-right-short" class="btn btn-primary col-1" scale="1" @click="monthPlus"></b-icon>
    </div>
  </div>
</template>

<script>
import CalendarModal from "./CalendarModal"
export default {
  
  components: {
    CalendarModal,
  },
  data() {
    return {
      modalShow:true,
      endCreated:true,
      month: 1,
      year: 2021,
      // 현재 날짜 기준 이전달
      maxMonth: 1,
      maxYear: 2021,
      // 가입일 기준
      minMonth: 12,
      minYear: 2020,
    }
  },
  created() {
    const date = new Date();
    this.date *= 1000
    this.maxYear = date.getFullYear()
    // getMonth()가 현재 월보다 1작은거 가져옴
    this.maxMonth = date.getMonth()
    if (this.maxMonth === 0) {
      this.maxMonth = 12
    }
  },
  methods: {
    monthMinus() {
      if (this.month > 1) {
        this.month--
      } else {
        this.year--
        this.month = 12
      }
      if (this.year === this.minYear && this.month < this.minMonth || this.year < this.minYear) {
        this.year = this.minYear
        this.month = this.minMonth
        alert("통계가 존재하지 않습니다.")
      }
    },
    monthPlus() {
      if (this.month < 12) {
        this.month++
      } else {
        this.year++
        this.month = 1
      }
      if (this.year === this.maxYear && this.month > this.maxMonth || this.year > this.maxYear) {
        this.year = this.maxYear
        this.month = this.maxMonth
        alert("통계가 존재하지 않습니다.")
      }
    },
  },
}
</script>

<style>

</style>