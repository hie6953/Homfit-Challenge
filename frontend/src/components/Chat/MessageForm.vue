<template>
  <div>
    <b-form-textarea
      v-model="msg"
      placeholder="보낼 메세지를 입력하세요"
      rows="1"
      max-rows="3"
      @keyup.13="sendMessageFunc"
    ></b-form-textarea>
  </div>
</template>

<script>
export default {
  name: "MessageForm",
  data() {
    return {
      msg: "",
    };
  },
  methods:{
      sendMessageFunc(){
          if(this.msg.length === 0) return false;
          this.$emit('sendMessage', this.msg);
          this.msg = '';
          return true;
      }
  }
};
</script>