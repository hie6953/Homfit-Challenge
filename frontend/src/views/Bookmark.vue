<template>
  <div class="col-12 col-md-8 bookmark-container">
    <p class="bookmark-title">챌린지 북마크</p>
    <div class="row list-card">
      <ChallengeListCard
        v-for="(challenge, index) in challengeList"
        :key="`${index}_bookmark`"
        class="col-6 col-md-4 col-lg-3"
        :challenge="challenge"
        :isfromBookmark="isfromBookmark"
        @moreInfo="ChallengeMoreInfo"
        @deleteBookmarkBtn="DeleteBookmark"
      />
    </div>
  </div>
</template>

<script>
const dayList = ['', '월', '화', '수', '목', '금', '토', '일'];
import { mapGetters } from "vuex";
import axios from "axios";
import ChallengeListCard from "../components/ChallengeListCard.vue";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  components: {
    ChallengeListCard,
  },
  data() {
    return {
      isfromBookmark: 1,
      challengeList: [
        // 더미 데이터 (DB 없을 때 확인용)
        // { "challenge_id": 134, "challenge_title": "조싀앤바믜 마성의 링딩동 챌린지", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 4, "check_date": 0, "period": 8, "nick_name": "이건내닉네임이얌", "people": 4, "kind": 0, "daylist_string": "[2, 4]", "dayList": null, "tagList": null, "bodyList": null },
        // { "challenge_id": 132, "challenge_title": "힙으뜸 8분 힙운동 따라하기", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 6, "check_date": 0, "period": 28, "nick_name": "건강이", "people": 3, "kind": 0, "daylist_string": "[3, 5, 7]", "dayList": null, "tagList": null, "bodyList": null },
        // { "challenge_id": 131, "challenge_title": "키쑥쑥 성장운동", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 7, "check_date": 0, "period": 7, "nick_name": "건강이", "people": 3, "kind": 0, "daylist_string": "[2, 4, 6]", "dayList": null, "tagList": null, "bodyList": null },
        // { "challenge_id": 128, "challenge_title": "기초체력 홈트 15분 루틴", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 3, "check_date": 0, "period": 7, "nick_name": "우지니", "people": 3, "kind": 0, "daylist_string": "[1, 2, 3, 4, 5]", "dayList": null, "tagList": null, "bodyList": null },
        // { "challenge_id": 183, "challenge_title": "원푸드 다이어트", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 9, "check_date": 0, "period": 11, "nick_name": "관리자3", "people": 2, "kind": 0, "daylist_string": "[1, 3, 5]", "dayList": null, "tagList": null, "bodyList": null },
        // { "challenge_id": 179, "challenge_title": "sad", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 3, "check_date": 0, "period": 29, "nick_name": "loco", "people": 2, "kind": 0, "daylist_string": "[4]", "dayList": null, "tagList": null, "bodyList": null },
        // { "challenge_id": 178, "challenge_title": "챌린지개설", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 9, "check_date": 0, "period": 3, "nick_name": "닉네임", "people": 2, "kind": 0, "daylist_string": "[4]", "dayList": null, "tagList": null, "bodyList": null },
      ],
    }
  },
  computed: {
    ...mapGetters(["getUserUid"]),
  },
  created() {
    axios
      .get(`${SERVER_URL}/challenge/bookmark/`, {
        params: {
          uid: this.getUserUid
        }
      })
      .then((data) => {
        console.log(data.data)
        this.challengeList = data.data
      })
      .catch(() => {
        alert("챌린지 목록을 불러오지 못했습니다.");
      });
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
      const itemToFind = this.challengeList.find(function(item) {return item.challenge_id === challenge_id})
      const idx = this.challengeList.indexOf(itemToFind)
      if (idx > -1) this.challengeList.splice(idx, 1)
      axios
        .delete(
          `${SERVER_URL}/user/bookmark/${challenge_id}`, {
            params: {
              uid: this.getUserUid
            }
          }
        )
        .then(() => {
          alert("북마크가 해제되었습니다.");
        })
        .catch(() => {
          alert("오류가 발생했습니다.");
        });
    }
  }
}
</script>

<style>
.bookmark-container {
  margin: 25px auto 0;
}
.bookmark-title {
  margin-left: 5px;
  padding-bottom: 5px;
  font-weight: 700;
  font-size: 1.5rem;
  border-bottom: 2px solid #EAC03A;
  display: inline;
}
.list-card {
  clear: both;
  padding-top: 15px;
  display: flex;
}

.challenge-list-card {
  padding: 8px 8px !important;
  transition: transform 200ms ease 100ms;
}

.list-card .challenge-list-card:hover {
  transform: scale(1.2);
  z-index: 2;
}
.bookmark-btn {
  color: #EAC03A;
  position: absolute;
  width: 1em;
  height: 1em;
  z-index: 4;
  left: 22px;
  top: 10px;
}
.bookmark-btn:hover {
  cursor: pointer;
}
</style>