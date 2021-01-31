<template>
  <div>
    챌린지 시작일 : <input type="date" v-model="start_date" />
    챌린지 종료일 :
    <input type="date" v-model="end_date" />
    <div>
      <vl-range-selector></vl-range-selector>
    </div>

 <div class="mt-4">
    <b-button class="prev-page-button" @click="PrevPage()"><b-icon icon="arrow-left-circle-fill" scale="1.5"></b-icon></b-button>
    <b-button
      class="next-page-button"
      :class="{ disabled: !canGoNext }"
      @click="(e) => canGoNext && NextPage()"
      aria-disabled="true"
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
  },
  data() {
    return {
      start_date: '',
      end_date: '',
      canGoNext:false,
    };
  },
  created() {
    this.start_date = this.props_start_date;
    this.end_date = this.props_end_date;
  },

  methods: {
    PrevPage: function() {
      this.$emit('PrevPage', this.start_date, this.end_date);
    },
    NextPage: function() {
      this.$emit('NextPage', this.start_date, this.end_date);
    },
  },
};
</script>
