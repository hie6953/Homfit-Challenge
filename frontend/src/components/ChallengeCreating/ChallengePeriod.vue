<template>
  <div>
    <h4 class="challenge-creating-title">챌린지 기간 선택</h4>
    <div class="challenge-period-information">
      <span
        ><b-icon icon="dot"></b-icon>챌린지 기간은 3일 ~ 30일 범위로 선택할 수
        있습니다.</span
      ><br />
      <span
        ><b-icon icon="dot"></b-icon>챌린지 기간은 수정이 불가능합니다.</span
      >
    </div>
    <div>
      <vl-range-selector
        :startDate="start_date"
        :endDate="end_date"
         class="col-md-12 mx-auto"
        @startDate="(date) => updateStartDate(date)"
        @endDate="(date) => updateEndDate(date)"
      ></vl-range-selector>
    </div>

    <div v-if="start_date != null && end_date != null">
      <div class="date-period align-center">
        <span>{{ this.start_date }}</span>
        <span> ~ </span>
        <span>{{ this.end_date }} ({{ this.period }}일간)</span>
      </div>
      <b-container>
        <b-row class="align-center mb-2">
          <b-col sm="3">
             <span class="require-icon">*</span>
            <span class="vertical-align-middle">챌린지 요일</span>
          </b-col>
          <b-col sm="9" class="challenge-period-daylist">
            <week-button
              :cantDays="cantDays"
              :props_day="dayList"
              @change="(data) => dayChange(data)"
            ></week-button>
          </b-col>
        </b-row>
        <b-row class="align-center">
          <b-col sm="3">
             <span class="require-icon">*</span>
            <span class="vertical-align-middle">하루 챌린지 횟수</span>
          </b-col>
          <b-col sm="9">
            <b-input-group append="회">
              <b-form-input type="number" v-model="day_certify_count" min="1" />
            </b-input-group>
          </b-col>
        </b-row>
      </b-container>
    </div>

    <div class="mt-4">
      <b-button class="prev-page-button" @click="PrevPage()"
        ><b-icon icon="arrow-left-circle-fill" scale="1.5"></b-icon
        ><span>소개</span></b-button
      >
      <b-button
        class="next-page-button"
        :class="{ disabled: !canGoNext }"
        @click="(e) => canGoNext && NextPage()"
        aria-disabled="true"
        ><span>인증</span
        ><b-icon icon="arrow-right-circle-fill" scale="1.5"></b-icon
      ></b-button>
    </div>
  </div>
</template>

<script>
import WeekButton from '../WeekButton.vue';
import vlRangeSelector from './vl-range-selector.vue';
export default {
  components: { vlRangeSelector, WeekButton },
  props: {
    props_start_date: String,
    props_end_date: String,
    props_period: Number,
    props_dayList: Array,
    props_day_certify_count: Number,
  },
  data() {
    return {
      start_date: '',
      end_date: '',
      period: '',
      dayList: [],
      cantDays: [],
      day_certify_count: 0,
      canGoNext: false,
    };
  },
  watch: {
    period: function() {
      this.calculateDays();
      this.CanGoNext();
    },
    dayList: function() {
      this.CanGoNext();
    },
    day_certify_count: function() {
      this.CanGoNext();
    },
  },
  created() {
    this.start_date = this.props_start_date;
    this.end_date = this.props_end_date;
    this.period = this.calculatePeriod();
    this.dayList = this.props_dayList;
    this.day_certify_count = String(this.props_day_certify_count);
  },

  methods: {
    updateStartDate: function(date) {
      this.start_date = date;
    },
    updateEndDate: function(date) {
      this.end_date = date;
      this.period =
        Math.ceil(
          (new Date(this.end_date) - new Date(this.start_date)) /
            (1000 * 3600 * 24)
        ) + 1;
    },
    calculatePeriod: function() {
      if (this.start_date != null && this.end_date != null) {
        return (
          Math.ceil(
            (new Date(this.end_date) - new Date(this.start_date)) /
              (1000 * 3600 * 24)
          ) + 1
        );
      } else {
        return this.props_period;
      }
    },
    // 불가능한 요일 계산
    calculateDays: function() {
      this.cantDays = null;
      if (this.period >= 7)
        this.cantDays = [false, false, false, false, false, false, false];
      else {
        this.cantDays = [true, true, true, true, true, true, true];
        let endDate = new Date(this.end_date);
        for (
          let date = new Date(this.start_date);
          date <= endDate;
          date.setDate(date.getDate() + 1)
        ) {
          let dayNum = new Date(date).getDay();
          this.cantDays[dayNum] = false;
        }
      }
      this.dayList = [];
    },
    dayChange: function(day) {
      this.dayList = day;
    },
    CanGoNext: function() {
      if (
        this.start_date != null &&
        this.end_date != null &&
        this.dayList.length > 0 &&
        this.day_certify_count > 0
      ) {
        this.canGoNext = true;
      } else this.canGoNext = false;
    },
    PrevPage: function() {
      this.$emit(
        'PrevPage',
        this.start_date,
        this.end_date,
        this.period,
        this.dayList,
        parseInt(this.day_certify_count)
      );
    },
    NextPage: function() {
      this.$emit(
        'NextPage',
        this.start_date,
        this.end_date,
        this.period,
        this.dayList,
        parseInt(this.day_certify_count)
      );
    },
  },
};
</script>
