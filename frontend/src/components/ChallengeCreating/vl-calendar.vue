<template>
  <div class="vl-calendar">
    <div>

    <span
      class="vl-calendar__arrow float-left"
      @click="moveBack()"
    ><b-icon icon="arrow-left-circle"></b-icon></span>

    <span
      class="vl-calendar__arrow float-right"
      @click="moveForward()"
    ><b-icon icon="arrow-right-circle"></b-icon></span>
    </div>
  <div >
<div>
    <vl-calendar-month
      class="vl-calendar__month col-md-6"
      :month="currentMonthMonth"
      :year="currentMonthYear"
      :is-selected="isSelected"
      :is-disabled="isDisabled"
      :custom-classes="customClasses"
      :show-weeks-number="showWeeksNumber"
      :first-day-of-week="firstDayOfWeek"
      @input="date => $emit('input', date)"
    />

    <vl-calendar-month
      v-if="!singleMonth"
      class="vl-calendar__month col-md-6"
      :month="nextMonthMonth"
      :year="nextMonthYear"
      :is-selected="isSelected"
      :is-disabled="isDisabled"
      :custom-classes="customClasses"
      :show-weeks-number="showWeeksNumber"
      :first-day-of-week="firstDayOfWeek"
      @input="date => $emit('input', date)"
    />
    </div>
  </div>
  </div>
</template>

<script>
import VlCalendarMonth from './vl-calendar-month'
 const DAYS_SHORTCUTS = ['sun', 'mon', 'tue', 'wed', 'thu', 'fri', 'sat']

export default {
  name: 'VlCalendar',
  components: {
    VlCalendarMonth
  },

  props: {
    isSelected: Function,
    isDisabled: Function,
    customClasses: Object,
    showWeeksNumber: Boolean,
    singleMonth: Boolean,
    firstDayOfWeek: {
      type: String,
      validator: v =>  DAYS_SHORTCUTS.includes(v),
      default: 'mon'
    }
  },

  data () {
    const today =  new Date();
    return {
      currentMonthMonth: today.getMonth(),
      currentMonthYear: today.getFullYear()
    }
  },

  computed: {
    nextMonthMonth () {
      return this.currentMonthMonth === 11 ? 0 : this.currentMonthMonth + 1
    },

    nextMonthYear () {
      return this.currentMonthMonth === 11 ? this.currentMonthYear + 1 : this.currentMonthYear
    }
  },

  methods: {
    moveBack () {
      if (this.currentMonthMonth === 0) {
        this.currentMonthMonth = 11
        this.currentMonthYear--
      } else {
        this.currentMonthMonth--
      }
    },

    moveForward () {
      if (this.currentMonthMonth === 11) {
        this.currentMonthMonth = 0
        this.currentMonthYear++
      } else {
        this.currentMonthMonth++
      }
    }
  }
}


</script>
