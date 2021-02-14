<template>
  <div>
    <div class="calendar-top">
      <b-button @click="ClickPrev" class="calendar-move-button"
        ><b-icon icon="chevron-left"></b-icon
      ></b-button>
      <h2 class="calendar-year-month">
        {{ currentYear }}년 {{ currentMonth }}월
      </h2>
      <b-button @click="ClickNext" class="calendar-move-button"
        ><b-icon icon="chevron-right"></b-icon
      ></b-button>
    </div>
    <div>
      <table class="calendar-table">
        <thead>
          <tr>
            <th
              class="calendar-column weekDays"
              v-for="(day, index) in weekDay"
              :key="`${index}_weekDay`"
            >
              {{ day }}
            </th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="(row, index) in currentMonthDateMatrix"
            :key="`${index}_currentMonthDateMatrix`"
          >
            <td
              class="calendar-column"
              v-for="(date, index) in row"
              :key="`${index}_row`"
            >
              <div v-if="date !== ' '">
                <button
                  class="calendar-column-button"
                  :class="{ choiced: date == choiceDate }"
                  @click="DateChoice(date)"
                >
                  {{ date }}
                </button>
                <br />
                <span class="calendar-column-dots">
                  <span>
                    <b-icon
                      v-if="diaryTrue"
                      icon="dot"
                      variant="warning"
                    ></b-icon>
                  </span>
                </span>
              </div>
              <span v-else>{{ date }}</span>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="calendar-dot-info">
      <b-icon icon="dot" variant="warning"></b-icon>
      <span>일기 작성</span>
      </div>
    </div>
  </div>
</template>
<script>
import "@/assets/css/diarycalendar.css";
export default {
  data() {
    return {
      weekDay: ["일", "월", "화", "수", "목", "금", "토"],
      currentYear: new Date().getFullYear(),
      currentMonth: new Date().getMonth() + 1,
      choiceDate: 0,
      currentMonthDateMatrix: [],
      diaryTrue: true,
    };
  },
  created() {
    this.createCalendar();
  },
  watch: {},
  methods: {
    DateChoice: function(date) {
      this.choiceDate = date;
    },

    createCalendar: function() {
      this.currentMonthDateMatrix = [];
      this.choiceDate = 0;
      let date = 1;
      let firstDay = new Date(this.currentYear, this.currentMonth, 1).getDay();
      let lastDate = new Date(this.currentYear, this.currentMonth, 0).getDate();
      let calendarRow = [];
      for (let i = 0; i < firstDay; ++i) {
        calendarRow[i] = " ";
      }

      for (let index = 0; index < 6; ++index) {
        let calendarRow = [];
        for (let i = 0; i < 7; ++i) {
          if ((index == 0 && i < firstDay) || date > lastDate) {
            calendarRow[i] = " ";
          } else {
            calendarRow[i] = date++;
          }
        }
        this.currentMonthDateMatrix[index] = calendarRow;
      }
    },
    ClickNext: function() {
      if (this.currentMonth == 12) {
        ++this.currentYear;
        this.currentMonth = 1;
      } else {
        ++this.currentMonth;
      }
      this.createCalendar();
    },
    ClickPrev: function() {
      if (this.currentMonth == 1) {
        --this.currentYear;
        this.currentMonth = 12;
      } else {
        --this.currentMonth;
      }
      this.createCalendar();
    },
  },
};
</script>
