<template>
  <!-- <b-modal id="select-preference-modal" v-model="modalShow" size="lg" no-close-on-backdrop class="mt-3"> -->
  <!-- <b-modal id="calendar-modal" v-model="modalShow" size="lg" no-close-on-backdrop class="mt-3"> -->
    <div>
      <div class="row">
        <select name="get-year" id="get-year" class="mx-auto">
          <option :value="$index + minYear" v-for="(n, $index) in maxYear-minYear+1" :key="n">
            {{$index + minYear}}
          </option>
        </select>
      </div>
      <div class="row d-flex flex-wrap">
        <div v-for="(num, index) in 12" :key="index" @click="getMonth(num)" class="col-3 month-button"> {{num}}월</div>
      </div>
    </div>
  <!-- </b-modal> -->
</template>

<script>
export default {
  props: {
    // modalShow,
    minYear: Number,
    maxYear: Number,
  },
  methods: {
    getMonth(num) {
      console.log(num)
    }
  }
}
</script>

<style>

</style>