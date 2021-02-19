<template>
  <div>
    <h5 class="filter-title">기간</h5>
    <div class="list-filter-period mx-auto">
      <vue-slider v-model="period" :min="3" :max="30"></vue-slider>
    </div>
    <hr />
    <h5 class="filter-title">요일</h5>
    <week-button
      :props_day="day"
      :props_day_change_toggle="dayChangeToggle"
      @change="(data) => dayChange(data)"
    ></week-button>

    <div class="challenge-list-filter">
      <hr />
      <span>
        <b-button @click="defaultChange()" variant="white">
          <b-icon icon="arrow-counterclockwise"></b-icon>
        </b-button>
      </span>
      <span
        class="float-right"
        v-show="
          !(
            this.period.toString() === this.prevPeriod.toString() &&
            this.day.toString() === this.prevDay.toString()
          )
        "
      >
        <b-button-group>
          <b-button @click="backChange()" variant="light">
            <b-icon icon="arrow-left"></b-icon>
          </b-button>
          <b-button
            @click="sendChange()"
            class="challenge-list-filter-send-button"
          >
            <b-icon icon="check" scale="1.4"></b-icon>
          </b-button>
        </b-button-group>
      </span>
    </div>
  </div>
</template>

<script>
import VueSlider from 'vue-slider-component';
import 'vue-slider-component/theme/material.css';

import '@/assets/css/challengeListFilter.css';
import WeekButton from '../components/WeekButton.vue';
export default {
  components: {
    VueSlider,
    WeekButton,
  },
  props: {
    prop_days: Array,
    prop_period: Array,
  },
  data() {
    return {
      period: [3, 30],
      day: [],
      prevPeriod: [3, 30],
      prevDay: [],
      defaultPeriod: [3, 30],
      defaultDay: [],
      dayChangeToggle: false,
      change: false,
    };
  },
  //   watch: {
  //     period: function() {
  //       //   console.log('period' + this.prevPeriod + ' ' + this.period);
  //     },
  //     day: function() {
  //       console.log(this.day.toString());
  //       //   console.log('day' + this.day + ' ' + this.prevDay);
  //     },
  //   },

  methods: {
    checkEqual: function() {
      // console.log(this.day.toString);
      if (
        this.period.toString() === this.prevPeriod.toString() &&
        this.day.toString() === this.prevDay.toString()
      ) {
        return false;
      }
      return true;
    },
    dayChange: function(data) {
      this.day = data;
    },
    sendChange: function() {
      this.prevPeriod = this.period;
      this.prevDay = this.day;
      this.$emit('change', this.period, this.day);
    },
    defaultChange: function() {
      this.period = this.prevPeriod = this.defaultPeriod;
      this.day = this.prevDay = this.defaultDay;
      this.dayChangeToggle = !this.dayChangeToggle;
      this.$emit('change', this.period, this.day);
    },
    backChange: function() {
      this.period = this.prevPeriod;
      this.day = this.prevDay;
      this.dayChangeToggle = !this.dayChangeToggle;
    },
  },
  created() {
    this.period = this.prop_period;
    this.day = this.prop_days;
  },
};
</script>
