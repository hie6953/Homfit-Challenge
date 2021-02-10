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
import axios from "axios";
import { mapGetters } from "vuex";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "ChatRoom",
  data() {
    return {
      msgs: [],
      connected: false,
      challenge_id: 128,
      stompClient: "",
    };
  },
  created() {
    this.connect();
  },
  components: {
    MessageForm,
    MessageList,
  },
  computed: {
    ...mapGetters(["getUserUid", "getUserNickName"]),
  },
  methods: {
    sendMessage(msg) {
      //   console.log(this.stompClient);
      if (this.stompClient && this.stompClient.connected) {
        this.stompClient.send(
          `/pub/${this.challenge_id}`,
          JSON.stringify(
            {
              uid: this.getUserUid,
              content: msg.trim(),
              challenge_id: this.challenge_id,
              nick_name: this.getUserNickName,
            },
            {}
          )
        );
      }
    },
    connect() {
      this.socket = new SockJS(`${SERVER_URL}/chatting`);
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        (frame) => {
          this.connected = true;
          console.log(frame);
          this.stompClient.subscribe(`/sub/${this.challenge_id}`, (tick) => {
            this.msgs.push(JSON.parse(tick.body));
          });
          this.getChat();
        },
        (error) => {
          console.log(error);
          this.connected = false;
        }
      );
    },
    disconnect() {
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
      this.connected = false;
    },
    getChat() {
      axios
        .get(`${SERVER_URL}/chat/log/${this.challenge_id}`)
        .then(({ data }) => {
          this.msgs = data;
          this.msgs.reverse();
        })
        .catch(() => {
          alert("채팅 기록 불러오기 실패");
        });
    },
    setChat() {
      axios.post();
    },
  },
};
</script>