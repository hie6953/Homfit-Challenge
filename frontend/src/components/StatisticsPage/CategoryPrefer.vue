<template>
  <div>
    <p class="statistics-title">{{ age }}대 {{ sex }}들의 선호 운동</p>
    <canvas id="myChart-3" width="100%" height="100%"></canvas>
  </div>
</template>

<script>
import Chart from 'chart.js'
export default {
  props: {
    update:Boolean,
    sex: String,
    age: Number,
    people: Number,
    favorite: Array,
    labels: Array,
  },
  data() {
    return {
      noMean: "",
    }
  },
  watch:{
    update:function(){
      this.createChart();
    }
  },
  methods: {
    createChart() {
      const ctx = document.getElementById("myChart-3")
      const myChart = new Chart(ctx, {
        type: 'radar',
        data: {
          labels: this.labels,
          datasets: [{
            label: "운동",
            data: this.favorite,
            backgroundColor: 'rgba(234, 192, 58, 0.2)',
            borderColor: 'rgba(234, 192, 58, 1)',
            pointBackgroundColor: 'rgba(234, 192, 58, 1)',
          }]
        },
        options: {}
      })
      this.noMean = myChart.id
    },
  },
}
</script>

<style>

</style>