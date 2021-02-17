<template>
  <div>
    <message-list :msgs="msgs"></message-list>
    <message-form v-on:sendMessage="sendMessage"></message-form>
  </div>
</template>

<script>
import "@/assets/css/chat.css"
import MessageForm from "@/components/Chat/MessageForm.vue";
import MessageList from "@/components/Chat/MessageList.vue";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import axios from "axios";
import swal from '@/assets/javascript/sweetAlert.js';
import { mapGetters } from "vuex";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "ChatRoom",
  data() {
    return {
      msgs: [],
      connected: false,
      stompClient: "",
      user_img: "",
    };
  },
  props: {
    challenge_id: Number,
  },
  created() {
    // axios
    //   .post(`${SERVER_URL}/user`, this.getUserUid)
    //   .then(({ data }) => {
    //     this.user_img = data.user_img;
    //     console.log(this.user_img)
    //   })
    //   .catch(() => {
    //     alert("유저 정보를 불러오지 못했습니다.");
    //   });
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
      console.log(this.challenge_id)
      axios
        .get(`${SERVER_URL}/chat/log/${this.challenge_id}`)
        .then(({ data }) => {
          this.msgs = data;
          console.log(this.msgs)
          this.msgs.reverse();
        })
        .catch(() => {
          swal.error('채팅 기록 불러오기 실패');
        });
    },
    setChat() {
      axios.post();
    },
  },
};
</script>