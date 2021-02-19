<template>
  <div class="textarea-bg">
    <div class="col-0 col-md-2 textarea-mg"></div>
    <div class="col-12 col-md-8 mx-auto row textarea-web">
      <b-form-textarea
        v-model="msg"
        class="col-10 col-sm-11"
        placeholder="보낼 메세지를 입력하세요"
        rows="2"
        max-rows="2"
        style="resize:none;"
        @keyup.13="sendMessageFunc"
      ></b-form-textarea>
      <div class="col-2 col-sm-1 my-auto" @click="sendMessageFunc">
        <img class="send-icon" src="@/assets/sendicon.png" alt="send-icon">
      </div>
    </div>
    <div class="col-0 col-md-2 textarea-mg"></div>
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
      if(this.msg.replace(/\n | /gi, "") <= 1) {
        this.msg = ''
        return false
      };
      this.$emit('sendMessage', this.msg);
      this.msg = '';
      return true;
    }
  }
};
</script>