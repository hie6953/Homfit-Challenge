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
    // monthList: Array,
  },
  data() {
    return {
      selectedChallengeName: "",
      isMonthList: false,
      monthList: [
        // a = 인증성공률, b = 챌린지이름
        // {a:100, b:"챌린지1"},
        // {a:90, b:"챌린지2"},
        // {a:80, b:"챌린지3"},
        // {a:70, b:"챌린지4"},
        // {a:60, b:"챌린지5"},
        // {a:50, b:"챌린지6"},
        // {a:40, b:"챌린지7"},
        // {a:30, b:"챌린지8"},
        // {a:20, b:"챌린지9"},
        // {a:10, b:"챌린지10"},
      ]
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