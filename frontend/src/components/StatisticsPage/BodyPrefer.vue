<template>
  <div>
    <p class="statistics-title">{{ age }}대 {{ sex }}들의 선호 부위</p>
    <canvas id="myChart-4" width="100%" height="100%"></canvas>
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
      const ctx = document.getElementById("myChart-4")
      const myChart = new Chart(ctx, {
        type: 'radar',
        data: {
          labels: this.labels,
          datasets: [{
            label: "부위",
            data: this.favorite,
            backgroundColor: 'rgba(230, 92, 46, 0.5)',
            borderColor: 'rgba(230, 92, 46, 1)',
            pointBackgroundColor: 'rgba(230, 92, 46, 1)',
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