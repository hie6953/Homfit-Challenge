<template>
  <div>
    <div>
      <vl-range-selector
        :startDate="start_date"
        :endDate="end_date"
        @startDate="(date) => updateStartDate(date)"
        @endDate="(date) => updateEndDate(date)"
      ></vl-range-selector>
    </div>

    <div
      v-if="start_date != null && end_date != null"
      class="date-period align-center"
    >
      <span>{{ this.start_date }}</span>
      <span>~</span>
      <span>{{ this.end_date }}({{ this.period }}일)</span>
    </div>
    <span></span>

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
import vlRangeSelector from './vl-range-selector.vue';
export default {
  components: { vlRangeSelector },
  props: {
    props_start_date: String,
    props_end_date: String,
    props_period: Number,
    props_dayList: Array,
  },
  data() {
    return {
      start_date: '',
      end_date: '',
      period: '',
      dayList: [],
      canGoNext: false,
    };
  },
  created() {
    this.start_date = this.props_start_date;
    this.end_date = this.props_end_date;
    this.period = this.props_period;
    this.dayList = this.props_dayList;
  },

  methods: {
    updateStartDate: function(date) {
      this.start_date = date;
    },
    updateEndDate: function(date) {
      this.end_date = date;
      this.period = Math.ceil(
        (new Date(this.end_date) - new Date(this.start_date)) /
          (1000 * 3600 * 24)
      );
    },
    PrevPage: function() {
      this.$emit('PrevPage', this.start_date, this.end_date);
    },
    NextPage: function() {
      this.$emit('NextPage', this.start_date, this.end_date);
    },
  },
};
</script>
