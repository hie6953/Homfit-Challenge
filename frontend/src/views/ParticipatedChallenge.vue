<template>
  <div>
    <div class="row col-12 col-md-8 participated-menu-bar justify-content-center">
      <div class="col-3 col-sm-2 border-right"><span class="tab-0 tab-cursor active" @click="tabValue(0)">달성률</span></div>
      <div class="col-3 col-sm-2 border-left border-right"><span class="tab-1 tab-cursor" @click="tabValue(1)">인증샷</span></div>
      <div class="col-3 col-sm-2 border-left"><span class="tab-2 tab-cursor" @click="tabValue(2)">대화</span></div>
    </div>
    <div v-if="tab === 0">
      <Progress
        :challenge="certifyInfo.challenge"
        :certifyInfo="certifyInfo"
        :isMobile="isMobile"
      />
    </div>
    <div v-if="tab === 1">
      
    </div>
    <div v-if="tab === 2">
      <ChatRoom 
        :challenge_id="certifyInfo.challenge.challenge_id"
      />
    </div>
    
  </div>
</template>

<script>
import "@/assets/css/ParticipatedChallenge/participatedChallenge.css";
import Progress from "../components/ParticipatedChallenge/Progress"
import ChatRoom from "../components/Chat/ChatRoom"
import { mapGetters } from "vuex";
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "ParticipatedChallenge",
  components: {
    Progress,
    ChatRoom,
  },
  data() {
    return {
      tab: 0,
      isMobile: false,
      checkDateList: ["진행전", "진행중", "완료"],
      fitKind: ["운동", "식단"],
      fitList: [
        "",
        "요가",
        "필라테스",
        "유산소",
        "댄스",
        "스트레칭",
        "근력운동",
        "키즈",
        "복싱",
        "식단",
        "기타",
      ],
      fitCategory: "",
      
      certifyInfo: {
        // challenge: { "challenge_id": 231, "challenge_title": "조싀앤바믜 마성의 링딩동 챌린지", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 4, "check_date": 1, "period": 8, "nick_name": "이건내닉네임이얌", "people": 4, "kind": 0, "daylist_string": "[2, 4]", "dayList": null, "tagList": null, "bodyList": null },
        challenge: {},
        total_cnt: 3,
        today_cnt: 3,
        user_cnt: 1,
        average_rate: 10,
        user_rate: 10,
        imgList: [
          {
            feed_picture: "https://picsum.photos/300/300/?image=25",
            register_date: "2021.03.12 00:00:00",
          },
        ],
      },
    }
  },
  created() {
    this.createInfo()
  },
  computed: {
    ...mapGetters(["getUserUid", "getUserNickName"]),
  },
  mounted() {
    this.handleResize();
    window.addEventListener("resize", this.handleResize);
  },
  methods: {
    handleResize: function() {
      this.isMobile = window.innerWidth <= 480;
    },
    tabValue(num) {
      document.querySelector(`.tab-${this.tab}`).classList.remove("active")
      document.querySelector(`.tab-${num}`).classList.add("active")
      this.tab = num
    },
    createInfo() {
    this.challenge_id = this.$route.params.challenge_id;
    axios
      .get(`${SERVER_URL}/challenge/detailManagement/${this.challenge_id}`, {
        params: {
          uid: this.getUserUid,
        }
      })
      .then(({ data }) => {
        this.certifyInfo = data;
        while (this.certifyInfo.today_cnt > this.certifyInfo.imgList.length) {
          this.certifyInfo.imgList.push({
            feed_picture: "https://picsum.photos/300/300/?image=24",
            register_date: "",
          })
        }
      })
      .catch(() => {
        // alert("챌린지 목록을 불러오지 못했습니다.");
      });
  },
    
  },
}
</script>

<style>

</style>