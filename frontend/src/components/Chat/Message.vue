<template>
  <div>
    <div v-if="msg.uid == getUserUid" class="me px-3">
      <div class="row justify-content-end">
        <div class="my-chat-register-date col-4 col-sm-4 mt-auto mr-2 px-0">
          {{ this.date }} {{ this.time }}
        </div>
        <div class="my-chat-msg col-7 col-sm-4">
          {{msg.content}}
        </div>
      </div>
    </div>
    
    <div v-else class="other">
      <div class="chat-nickname">
        <img v-if="msg.user_img == null" src="@/assets/NavBar/anonimous_user.png" alt="user_img" class="user-img">
        <img v-else :src="msg.user_img" alt="user_img" class="user-img">
        {{msg.nick_name}}
      </div>
      <div class="row px-3">
        <div class="other-chat-msg col-7 col-sm-4">
          {{msg.content}}
        </div>
        <div class="other-chat-register-date col-4 col-sm-4 mt-auto ml-2 px-0">
          {{ this.date }} {{ this.time }}
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
  data() {
    return {
      date: "",
      time: "",
    }
  },
  computed: {
    ...mapGetters(["getUserUid"]),
  },
  mounted() {
    this.updateTime()
  },
  methods: {
    updateTime() {
      if (this.msg.regist_date) {
        this.date = this.msg.regist_date.slice(5,10)
        this.time = this.msg.regist_date.slice(11,16)
      } else {
        const now = new Date();
        this.now *= 1000
        const nowMonth = now.getMonth()+1 > 9 ? (now.getMonth()+1).toString() : "0"+(now.getMonth()+1).toString()
        const nowDate = now.getDate() > 9 ? now.getDate().toString() : "0"+now.getDate().toString()
        const nowHours = now.getHours() > 9 ? now.getHours().toString() : "0"+now.getHours().toString()
        const nowMinutes = now.getMinutes() > 9 ? now.getMinutes().toString() : "0"+now.getMinutes().toString()
        this.date = nowMonth+"-"+nowDate
        this.time = nowHours+":"+nowMinutes
      }
    }
  },
};
</script>

<style>
    
</style>