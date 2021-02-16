<template>
  <div>
    <p class="statistics-title">이번달 인증률</p>
    <div class="progress mt-2 progress-box">
      <div
        class="progress-bar progress-background"
        id="current-progress"
        role="progressbar"
        style="width: 15%"
        :aria-valuenow="current"
        aria-valuemin="0"
        aria-valuemax="100"
      >{{ this.current }}%</div>
    </div>

    <p class="statistics-title">지난달 인증률</p>
    <div class="progress mt-2 progress-box">
      <div
        class="progress-bar progress-background"
        id="previous-progress"
        role="progressbar"
        style="width: 15%"
        :aria-valuenow="previous"
        aria-valuemin="0"
        aria-valuemax="100"
      >{{ this.previous }}%</div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    current: Number,
    previous: Number,
  },
  mounted() {
    this.ProgressBarWidth()
  },
  watch: {
    current:function(){
      this.ProgressBarWidth();
    }
  },
  methods: {
    ProgressBarWidth() {
      const currentProgress = document.getElementById('current-progress')
      currentProgress.style.width = `${this.current}%`
      const previousProgress = document.getElementById('previous-progress')
      previousProgress.style.width = `${this.previous}%`
    }
  }
}
</script>

<style>

</style>