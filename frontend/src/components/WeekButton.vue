<template>
  <div>
    <ul class="day-checkbox">
      <li>
        <input type="checkbox" v-model="day" id="Sun" :value="7" /><label
          :class="{ disabled: isDisabled(0) }"
          for="Sun"
          >일</label
        >
      </li>
      <li>
        <input type="checkbox" v-model="day" id="Mon" :value="1" /><label
          :class="{ disabled: isDisabled(1) }"
          for="Mon"
          >월</label
        >
      </li>
      <li>
        <input type="checkbox" v-model="day" id="Tue" :value="2" /><label
          :class="{ disabled: isDisabled(2) }"
          for="Tue"
          >화</label
        >
      </li>
      <li>
        <input type="checkbox" v-model="day" id="Wed" :value="3" /><label
          :class="{ disabled: isDisabled(3) }"
          for="Wed"
          >수</label
        >
      </li>
      <li>
        <input type="checkbox" v-model="day" id="Thu" :value="4" /><label
          :class="{ disabled: isDisabled(4) }"
          for="Thu"
          >목</label
        >
      </li>
      <li>
        <input type="checkbox" v-model="day" id="Fri" :value="5" /><label
          :class="{ disabled: isDisabled(5) }"
          for="Fri"
          >금</label
        >
      </li>
      <li>
        <input type="checkbox" v-model="day" id="Sat" :value="6" /><label
          :class="{ disabled: isDisabled(6) }"
          for="Sat"
          >토</label
        >
      </li>
    </ul>
  </div>
</template>

<script>
import '@/assets/css/weekButton.css';

export default {
  props: {
    cantDays: Array,
    props_day: Array,
    props_day_change_toggle: Boolean,
  },
  data() {
    return {
      day: [],
    };
  },
  created() {
    this.day = this.props_day;
  },
  watch: {
    day: function() {
      this.$emit('change', this.day);
    },
    props_day_change_toggle: function() {
      // console.log(this.props_day);
      this.day = this.props_day;
    },
  },
  methods: {
    isDisabled: function(num) {
      if (this.cantDays != undefined) return this.cantDays[num];
      return false;
    },
  },
};
</script>
