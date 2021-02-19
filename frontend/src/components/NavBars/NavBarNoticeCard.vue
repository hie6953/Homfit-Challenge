<template>
  <div class="nav-bar-notice-card-container" :class="{disabled:notice.alarm_check}">
    <div>
      <div>
        <span id="notice-title">{{ notice.alarm_title }}</span>
        <b-button :disabled="notice.alarm_check" @click="AlarmCheck" class="nav-bar-notice-card-check-button">
          <b-icon icon="check"></b-icon>
          읽음
        </b-button>
      </div>
      <span id="notice-comment">{{ notice.alarm_content }}</span>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    notice: Object,
    index:Number,
  },
  methods: {
    AlarmCheck:function(){
      this.$emit('alarmCheck',this.notice.alarm_id,this.index);
    }
  },
};
</script>
