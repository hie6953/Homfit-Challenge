<template>
  <div>
    챌린지 시작일 : <input type="date" v-model="start_date" /> 챌린지 종료일 :
    <input type="date" v-model="end_date" />

    <b-button @click="PrevPage()">Previous</b-button>
    <b-button @click="NextPage()">Next</b-button>
  </div>
</template>

<script>
export default {
  props: {
    props_start_date: String,
    props_end_date: String,
  },
  data() {
    return {
      start_date: '',
      end_date: '',
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
