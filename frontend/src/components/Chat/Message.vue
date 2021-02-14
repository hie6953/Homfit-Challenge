<template>
  <div>
    <div v-if="msg.uid == getUserUid" class="me px-3">
      <!-- <div class="chat-nickname">
        {{msg.nick_name}}
        <img src="@/assets/NavBar/anonimous_user.png" alt="user_img" class="user-img">
      </div> -->
      <div class="row justify-content-end">
        <div class="my-chat-register-date col-4 col-sm-4 mt-auto mr-2 pr-0">
          {{msg.register_date}}
          <!-- 오후 4:20 -->
        </div>
        <div class="my-chat-msg col-7 col-sm-4">
          {{msg.content}}
        </div>
      </div>
    </div>
    <div v-else class="other px-3">
      <div class="chat-nickname">
        <img src="@/assets/NavBar/anonimous_user.png" alt="user_img" class="user-img">
        <!-- <img src="msg.user_img" alt="user_img" class="user-img"> -->
        {{msg.nick_name}}
      </div>
      <div class="row">
        <div class="other-chat-msg col-7 col-sm-4">
          {{msg.content}}
        </div>
        <div class="other-chat-register-date col-4 col-sm-4 mt-auto ml-2 pl-0">
          <!-- {{msg.register_date}} -->
          오후 4:19
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "Message",
  props: {
    msg: Object,
  },
  computed: {
    ...mapGetters(["getUserUid"]),
  },
};
</script>

<style>
    
</style>