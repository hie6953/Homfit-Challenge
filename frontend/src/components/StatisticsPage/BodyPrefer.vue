<template>
  <div>
    <p class="statistics-title">{{ age }}대 {{ sex }}들의 선호 부위</p>
    <div class="row">
      <img v-if="sex === '남성'" src="@/assets/man.png" alt="man" class="col-6">
      <img v-else src="@/assets/woman.png" alt="woman" class="col-6">
      <div class="col-6">
        <div class="progress mt-2 progress-box" v-for="(p,index) in per" :key="index">
          <div
            class="progress-bar progress-background"
            id="current-progress"
            role="progressbar"
            width="p"
            :aria-valuenow="p"
            aria-valuemin="0"
            aria-valuemax="100"
          >{{ p }}%</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    sex: String,
    age: Number,
    total: Number,
    per: Array,
  }
}
</script>

<style>

</style>