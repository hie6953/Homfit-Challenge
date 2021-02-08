<template>
  <div class="container book-mark-container">
    <div class="row list-card">
      <div class="card-carousel-card col-6 col-md-4" v-for="(challenge, index) in challengeList" :key="index">
        <b-icon
            id="bookmarkIcon"
            icon="bookmark-fill"
            scale="1.6"
            class="bookmark-btn"
             @click="DeleteBookmark(challenge.challenge_id)"
          ></b-icon>
        <b-card
          img-src="https://placehold.it/290x170"
          img-alt="Image"
          img-top
          no-body
          tag="article"
          style="max-width: 30rem;"
          class="mb-2 shadow"
          @click="ChallengeMoreInfo(challenge.challenge_id)"
        >
          <div class="challenge-list-card-body">
            <span id="card-title">{{ challenge.challenge_title }}</span>
            <br />
            <img class="card-user-image" src="@/assets/NavBar/anonimous_user.png" />
            <span id="card-user-nick-name">{{ challenge.nick_name }}</span>
            <br />
            <span id="card-day">{{ GetDayList(challenge) }}</span>
            <span id="card-period">{{ challenge.period }}일</span>
          </div>
          <hr class="challenge-list-card-hr" />
          <div class="challenge-list-card-footer">
            <span>{{ challenge.people }}명 참여중</span>
          </div>
        </b-card>
      </div>
    </div>
  </div>
</template>

<script>
const dayList = ['', '월', '화', '수', '목', '금', '토', '일'];
import { mapGetters } from "vuex";
// import axios from "axios";
// const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  
  data() {
    return {
      challengeList: [
        { "challenge_id": 134, "challenge_title": "조싀앤바믜 마성의 링딩동 챌린지", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 4, "check_date": 0, "period": 8, "nick_name": "이건내닉네임이얌", "people": 4, "kind": 0, "daylist_string": "[2, 4]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 132, "challenge_title": "힙으뜸 8분 힙운동 따라하기", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 6, "check_date": 0, "period": 28, "nick_name": "건강이", "people": 3, "kind": 0, "daylist_string": "[3, 5, 7]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 131, "challenge_title": "키쑥쑥 성장운동", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 7, "check_date": 0, "period": 7, "nick_name": "건강이", "people": 3, "kind": 0, "daylist_string": "[2, 4, 6]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 128, "challenge_title": "기초체력 홈트 15분 루틴", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 3, "check_date": 0, "period": 7, "nick_name": "우지니", "people": 3, "kind": 0, "daylist_string": "[1, 2, 3, 4, 5]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 183, "challenge_title": "원푸드 다이어트", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 9, "check_date": 0, "period": 11, "nick_name": "관리자3", "people": 2, "kind": 0, "daylist_string": "[1, 3, 5]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 179, "challenge_title": "sad", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 3, "check_date": 0, "period": 29, "nick_name": "loco", "people": 2, "kind": 0, "daylist_string": "[4]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 178, "challenge_title": "챌린지개설", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 9, "check_date": 0, "period": 3, "nick_name": "닉네임", "people": 2, "kind": 0, "daylist_string": "[4]", "dayList": null, "tagList": null, "bodyList": null },
      ],
    }
  },
  computed: {
    ...mapGetters(["getUserUid"]),
  },
  created() {

  },
  methods: {
    ChallengeMoreInfo(challenge_id){
      this.$router.push(`/challenge-more-info/${challenge_id}`);
    },
    GetDayList(challenge) {
      if (challenge.daylist_string != null) {
        let list = challenge.daylist_string
          .substring(1, challenge.daylist_string.length - 1)
          .split(',');
        let temp = new Array(list.length);
        for (let index = 0; index < list.length; index++) {
          temp[index] = dayList[parseInt(list[index].replace(' ', ''))];
        }
        return temp.join('/');
      }
      return '';
    },
    DeleteBookmark(challenge_id) {
      console.log(challenge_id)
      console.log(this.getUserUid)
      
      const itemToFind = this.challengeList.find(function(item) {return item.challenge_id === challenge_id})
      const idx = this.challengeList.indexOf(itemToFind)
      if (idx > -1) this.challengeList.splice(idx, 1)
      console.log(this.challengeList)
      // alert("북마크 해제되었습니다.");
      // const params = {
      //   challenge_id: challenge_id,
      //   getUserUid: this.getUserUid,
      // }
      // axios
      //   .delete(`${SERVER_URL}/user/bookmark`, params)
      //   .then(() => {
      //     alert("북마크 해제되었습니다.");
      //   })
      //   .catch(() => {
      //     alert("북마크 해제시 에러가 발생했습니다.");
      //   });
    }
  }
}
</script>

<style>
.book-mark-container {
  margin-top: 25px;
}
.list-card {
  clear: both;
  padding-top: 15px;
  display: flex;
}

.challenge-list-card {
  padding: 8px 8px !important;
  transition: transform 300ms ease 100ms;
}

.list-card .challenge-list-card:hover {
  transform: scale(1.2);
  z-index: 2;
  cursor: pointer;
}
.bookmark-btn {
  position: absolute;
  z-index: 3;
  left: 30px;
  top: 15px;
  color: #EAC03A;
}
</style>