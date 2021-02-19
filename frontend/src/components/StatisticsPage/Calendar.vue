<template>
  <div class="row month-top">
    <b-button @click="monthMinus" class="month-move-button"
      ><b-icon icon="chevron-left"></b-icon
    ></b-button>
    <h2 class="year-month-box">
      {{ year }}년 
      <span v-if="month > 9">{{ month }}</span>
      <span v-else>0{{ month }}</span>월
    </h2>
    <b-button @click="monthPlus" class="month-move-button"
      ><b-icon icon="chevron-right"></b-icon
    ></b-button>
  </div>
</template>

<script>
import swal from '@/assets/javascript/sweetAlert.js';
export default {
  data() {
    return {
      modalShow:true,
      endCreated:true,
      month: new Date().getMonth()+1,
      year: new Date().getFullYear(),
      // 현재 날짜 기준 달
      maxMonth: new Date().getMonth()+1,
      maxYear: new Date().getFullYear(),
      // 가입일 기준이면 좋음... 현재 서비스 2021년 1월부터로 가정
      minMonth: 1,
      minYear: 2021,
    }
  },
  mounted() {
    const date = new Date();
    this.maxYear = date.getFullYear()
    // getMonth()가 현재 월보다 1작은거 가져옴
    this.maxMonth = date.getMonth()+1
    this.$emit("selectedMonth",this.maxMonth)
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
        swal.info('통계가 존재하지 않습니다.');
      } else {
        this.$emit("selectedMonth",this.month)
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
        swal.info('통계가 존재하지 않습니다.');
      } else {
        this.$emit("selectedMonth",this.month)
      }
    },
  },
}
</script>

<style>

</style>