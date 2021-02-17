<template>
  <div class="vl-calendar-month">
    <div class="vl-calendar-month__title">{{ year }}년 {{ month + 1 }}월</div>

    <div class="vl-flex">
      <div class="mx-auto">
        <div class="vl-flex">
          <span
            v-for="name in daysNames"
            :key="name"
            class="vl-calendar-month__week-day"
            >{{ name }}</span
          >
        </div>

        <div class="vl-flex vl-flex-wrap">
          <span
            v-for="day in days"
            :key="day"
            @click="$emit('input', getDate(day))"
            class="vl-calendar-month__day"
            :class="calculateClasses(day)"
            :data-day="day"
            :data-month="month"
            :data-year="year"
          >
            {{ day }}
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
const DAYS_SHORTCUTS = ['sun', 'mon', 'tue', 'wed', 'thu', 'fri', 'sat'];
const DAYS_NAMES = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];

export default {
  name: 'VlCalendarMonth',
  props: {
    month: Number,
    year: Number,
    isSelected: Function,
    isDisabled: Function,
    firstDayOfWeek: {
      type: String,
      validator: (v) => DAYS_SHORTCUTS.includes(v),
      default: 'sun',
    },
  },

  computed: {
    days() {
      return createRange(1, countDays(this.month, this.year));
    },

    daysNames() {
      return transpose(DAYS_NAMES, this.daysOffset);
    },

    daysOffset() {
      return DAYS_SHORTCUTS.indexOf(this.firstDayOfWeek);
    },
  },

  methods: {
    getDate(day) {
      return formatDate(day, this.month, this.year);
    },

    calculateClasses(day) {
      const classes = [];
      if (day === 1) {
        let offset =
          (new Date(this.year, this.month, 1).getDay() + 7 - this.daysOffset) %
          7;
        if (offset > 0) {
          classes.push(`vl-calendar-month__day--offset-${offset}`);
        }
      }

      const date = this.getDate(day);
      if (this.isSelected && this.isSelected(date)) {
        classes.push('selected');

        if (!this.isSelected(this.getDate(day - 1))) {
          classes.push('selected--first');
        }

        if (!this.isSelected(this.getDate(day + 1))) {
          classes.push('selected--last');
        }
      }

      // if (new Date(this.year, this.month, day) < new Date()) {
      //   classes.push('disabled');
      // }

      if (this.isDisabled && this.isDisabled(date)) {
        classes.push('disabled');

        if (!this.isDisabled(this.getDate(day - 1))) {
          classes.push('disabled--first');
        }

        if (!this.isDisabled(this.getDate(day + 1))) {
          classes.push('disabled--last');
        }
      }

      return classes;
    },
  },
};

function createRange(start, end) {
  return Array.from({ length: end - start + 1 }, (x, i) => i + start);
}

function transpose(array, offset) {
  const table = [...array];
  for (let i = 0; i < offset; i++) {
    table.push(table.shift());
  }
  return table;
}

function formatDate(day, month, year) {
  const date = new Date(year, month, day);
  return `${date.getFullYear()}-${twoDigits(date.getMonth() + 1)}-${twoDigits(
    date.getDate()
  )}`;
}
function countDays(month, year) {
  return new Date(year, month + 1, 0).getDate();
}
function twoDigits(number) {
  return number > 9 ? '' + number : '0' + number;
}
</script>
