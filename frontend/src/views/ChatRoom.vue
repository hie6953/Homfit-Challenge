<template>
  <div>
    <message-list :msgs="msgs"></message-list>
    <message-form v-on:sendMessage="sendMessage"></message-form>
  </div>
</template>

<script>
import MessageForm from "../components/Chat/MessageForm.vue";
import MessageList from "../components/Chat/MessageList.vue";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "ChatRoom",
  data() {
    return {
      msgs: [],
      connected: false,
      challenge_id: '',
    };
  },
  created() {
      this.connect();
  },
  components: {
    MessageForm,
    MessageList,
  },
  methods: {
    sendMessage(msg) {
        if(this.stompClient && this.stompClient.connected){
            this.stompClient.send(`/chat/${this.challenge_id}/receive`, JSON.stringify(msg), {});
        }
    },
    connect() {
        this.socket = new SockJS(`${SERVER_URL}/chat`);
        this.stompClient = Stomp.over(this.socket);
        this.stompClient.connect(
            {},
            frame => {
                this.connected = true;
                console.log(frame);
                this.stompClient.subscribe(`/chat/${this.challenge_id}/send`, tick => {
                    console.log(tick);
                    this.msgs.push(JSON.parse(tick.body).connect);
                });
            },
            error => {
                console.log(error);
                this.connected = false;
            }
        )
    },
    disconnect(){
        if(this.stompClient){
            this.stompClient.disconnect();
        }
        this.connected = false;
    },

  },
};
</script>