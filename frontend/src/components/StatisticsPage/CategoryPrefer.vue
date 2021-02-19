<template>
  <div>
    <div class="statistics-box row">
      <p class="statistics-title-center">{{ age }}대 {{ sex }}들의 선호 운동</p>
    </div>
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
  watch:{
    update:function(){
      this.createChart();
    }
  },
  methods: {
    createChart() {
      const ctx = document.getElementById("myChart-3")
      new Chart(ctx, {
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
    },
  },
}
</script>

<style>

</style>