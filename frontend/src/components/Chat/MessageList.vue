<template>
  <b-list-group class="chat-box col-12 col-md-8 mx-auto">
      <message v-for="(msg, index) in msgs" :key="index" :msg="msg"></message>
  </b-list-group>
</template>

<script>
import Message from "./Message.vue";

export default {
  components: { Message },
  name: "MessageList",
  props: ["msgs"],
};
</script>