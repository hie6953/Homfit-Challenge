<template>
  <div>
    <div v-if="isMonthList === true">
      <p class="selected-challeng-name" v-if="selectedChallengeName === ''">차트를 클릭해주세요.</p>
      <p class="selected-challeng-name" v-else>{{ selectedChallengeName }}</p>
      <dl class="chart" style="--scale: 100;">
        <MonthGraphElement
          v-for="(month, index) in monthList"
          :key="index"
          :month="month"
          :index="index"
          :monthList="monthList"
          @setChallengeName="SetChallengeName"
        />
      </dl>
    </div>
    <div v-else class="no-month-graph">
      <p>챌린지 통계가 존재하지 않습니다.</p>
    </div>
  </div>
</template>

<script>
import MonthGraphElement from './MonthGraphElement.vue'

export default {
	components: { 
    MonthGraphElement,
  },
  props: {
    monthList: Array,
  },
  data() {
    return {
      selectedChallengeName: "",
      isMonthList: false,
    }
  },
  watch: {
    monthList() {
      if (this.monthList.length != 0) {
        this.isMonthList = true
      } else {
        this.isMonthList = false
      }
    }
  },
  mounted() {
    if (this.monthList.length > 0) {
      this.isMonthList = true
    }
  },
  methods: {
    SetChallengeName(challenge_name) {
      this.selectedChallengeName = challenge_name
    }
  },
}
</script>

<style>

</style>