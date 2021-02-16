<template>
  <div>
    <p class="statistics-title">운동별 선호 챌린지</p>
    <canvas id="myChart-1" width="100%" height="100%"></canvas>
  </div>
</template>

<script>
import Chart from 'chart.js'
export default {
  props: {
    labels: Array,
    dataList: Array,
  },
  methods: {
    createChart() {
      const ctx = document.getElementById("myChart-1")
      const myChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
          // labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
          labels: this.labels,
          datasets: [{
            label: '# of Votes',
            // data: [12, 19, 3, 5, 2, 3,0,0],
            
            data: this.dataList,
            backgroundColor: [
              'rgba(244, 67, 54, 0.2)',
              'rgba(255, 152, 0, 0.2)',
              'rgba(255, 255, 59, 0.2)',
              'rgba(205, 220, 57, 0.2)',
              'rgba(76, 175, 80, 0.2)',
              'rgba(0, 150, 136, 0.2)',
              'rgba(33, 150, 243, 0.2)',
              'rgba(63, 81, 181, 0.2)',
              'rgba(156, 39, 176, 0.2)',
              'rgba(233, 30, 99, 0.2)',
            ],
            borderColor: [
              'rgba(244, 67, 54, 1)',
              'rgba(255, 152, 0, 1)',
              'rgba(255, 255, 59, 1)',
              'rgba(205, 220, 57, 1)',
              'rgba(76, 175, 80, 1)',
              'rgba(0, 150, 136, 1)',
              'rgba(33, 150, 243, 1)',
              'rgba(63, 81, 181, 1)',
              'rgba(156, 39, 176, 1)',
              'rgba(233, 30, 99, 1)',
            ],
            borderWidth: 1
          }]
        },
        options: {}
      })
      console.log(myChart)
    },
  },
  mounted() {
    this.createChart()
  },
}
</script>

<style>

</style>