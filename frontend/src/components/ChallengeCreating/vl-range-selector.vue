<template>
  <vl-calendar
    @input="(date) => emitDate(date)"
    :is-selected="isSelected"
    :is-disabled="calculateDisabled"
    :first-day-of-week="firstDayOfWeek"
    ref="calendar"
  />
</template>

<script>
import VlCalendar from './vl-calendar';
import '@/assets/css/ChallengeCreating/rangeSelector.css';
const DAYS_SHORTCUTS = ['sun', 'mon', 'tue', 'wed', 'thu', 'fri', 'sat'];

export default {
  name: 'VlRangeSelector',
  components: {
    VlCalendar,
  },

  props: {
    startDate: String,
    endDate: String,
    firstDayOfWeek: {
      type: String,
      validator: (v) => DAYS_SHORTCUTS.includes(v),
      default: 'sun',
    },
  },

  data() {
    return {
      maxEndDate: '',
    };
  },

  methods: {
    emitDate(date) {
      if (this.startDate && !this.endDate && date >= this.startDate) {
        this.$emit('endDate', date);
      } else {
        this.$emit('startDate', date);

        if (this.endDate) {
          this.$emit('endDate', null);
        }
      }
      this.$emit('focus');
    },

    isSelected(date) {
      if (!this.startDate && !this.endDate) {
        return false;
      } else if (!this.endDate) {
        return this.startDate === date;
      } else {
        return date >= this.startDate && date <= this.endDate;
      }
    },

    calculateDisabled(date) {
      if (this.startDate && !this.endDate) {
        return date < this.startDate;
      } else return false;
    },
  },
};
</script>
