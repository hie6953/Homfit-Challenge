<template>
  <div class="manage-container">
    <div class="row col-12 col-md-8 manage-menu-bar justify-content-center">
      <div class="col-3 col-sm-2 border-right"><span class="tab-0 tab-cursor active" @click="tabValue(0)">오늘</span></div>
      <div class="col-3 col-sm-2 border-left border-right"><span class="tab-1 tab-cursor" @click="tabValue(1)">참여</span></div>
      <div class="col-3 col-sm-2 border-left border-right"><span class="tab-2 tab-cursor" @click="tabValue(2)">완료</span></div>
      <div class="col-3 col-sm-2 border-left"><span class="tab-3 tab-cursor" @click="tabValue(3)">개설</span></div>
    </div>
    <!-- 카테고리 -->
    <div class="mt-3">
      <div class="category-background row mx-auto">
        <div class="category mx-auto">
          <input
            class="checkbox-tools"
            type="radio"
            name="tools"
            value="0"
            v-model="category"
            id="category-total"
            checked
          />
          <label class="for-checkbox-tools" for="category-total">
            전체
          </label>
          <input
            class="checkbox-tools"
            type="radio"
            name="tools"
            value="1"
            v-model="category"
            id="category-yoga"
          />
          <label class="for-checkbox-tools" for="category-yoga"> 요가 </label>
          <input
            class="checkbox-tools"
            type="radio"
            name="tools"
            value="2"
            v-model="category"
            id="category-pilates"
          />
          <label class="for-checkbox-tools" for="category-pilates">
            필라테스 </label
          ><input
            class="checkbox-tools"
            type="radio"
            name="tools"
            value="3"
            v-model="category"
            id="category-aerobic"
          />
          <label class="for-checkbox-tools" for="category-aerobic">
            유산소
          </label>

          <input
            class="checkbox-tools"
            type="radio"
            name="tools"
            value="4"
            v-model="category"
            id="category-dance"
          />
          <label class="for-checkbox-tools" for="category-dance">
            댄스
          </label>
          <input
            class="checkbox-tools"
            type="radio"
            name="tools"
            value="5"
            v-model="category"
            id="category-stretching"
          />
          <label class="for-checkbox-tools" for="category-stretching">
            스트레칭
          </label>
          <input
            class="checkbox-tools"
            type="radio"
            name="tools"
            value="6"
            v-model="category"
            id="category-strength"
          />
          <label class="for-checkbox-tools" for="category-strength">
            근력
          </label>
          <input
            class="checkbox-tools"
            type="radio"
            name="tools"
            value="7"
            v-model="category"
            id="category-kids"
          />
          <label class="for-checkbox-tools" for="category-kids">
            키즈
          </label>
          <input
            class="checkbox-tools"
            type="radio"
            name="tools"
            value="8"
            v-model="category"
            id="category-boxing"
          />
          <label class="for-checkbox-tools" for="category-boxing">
            복싱
          </label>
          <input
            class="checkbox-tools"
            type="radio"
            name="tools"
            value="9"
            v-model="category"
            id="category-food"
          />
          <label class="for-checkbox-tools" for="category-food">
            식단
          </label>
          <input
            class="checkbox-tools"
            type="radio"
            name="tools"
            value="10"
            v-model="category"
            id="category-etc"
          />
          <label class="for-checkbox-tools" for="category-etc">
            기타
          </label>
        </div>
      </div>
    </div>
    <div class="col-12 col-md-8 mx-auto">
      <div class="row list-card">
        <ChallengeListCard
          v-for="(challenge, index) in items"
          :key="`${index}_challengemanage`"
          class="col-6 col-md-4 col-lg-3 challenge-list-card"
          :challenge="challenge"
          :isfromChallengeManage="isfromChallengeManage"
          :deleteActive="deleteActive"
          @moreInfo="ChallengeMoreInfo"
          @deleteChallengeManage="DeleteChallenge"
        />
      </div>
    </div>
  </div>
  
</template>

<script>
import "@/assets/css/ChallengeManage/challengeManage.css"
import ChallengeListCard from "../components/ChallengeListCard.vue";
// import axios from "axios";
// const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import { mapGetters } from "vuex";
export default {
  components: {
    ChallengeListCard,
  },
  data() {
    return {
      isfromChallengeManage: 1,
      deleteActive: 0,
      tab: 0,
      category: 0,
      challengeList: [
        { "challenge_id": 134, "challenge_title": "조싀앤바믜 마성의 링딩동 챌린지", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 4, "check_date": 0, "period": 8, "nick_name": "이건내닉네임이얌", "people": 4, "kind": 0, "daylist_string": "[2, 4]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 128, "challenge_title": "기초체력 홈트 15분 루틴", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 3, "check_date": 0, "period": 7, "nick_name": "우지니", "people": 3, "kind": 0, "daylist_string": "[1, 2, 3, 4, 5]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 183, "challenge_title": "원푸드 다이어트", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 9, "check_date": 0, "period": 11, "nick_name": "관리자3", "people": 2, "kind": 0, "daylist_string": "[1, 3, 5]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 132, "challenge_title": "힙으뜸 8분 힙운동 따라하기", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 6, "check_date": 0, "period": 28, "nick_name": "건강이", "people": 3, "kind": 0, "daylist_string": "[3, 5, 7]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 179, "challenge_title": "sad", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 3, "check_date": 0, "period": 29, "nick_name": "loco", "people": 2, "kind": 0, "daylist_string": "[4]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 131, "challenge_title": "키쑥쑥 성장운동", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 7, "check_date": 0, "period": 7, "nick_name": "건강이", "people": 3, "kind": 0, "daylist_string": "[2, 4, 6]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 178, "challenge_title": "챌린지개설", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 9, "check_date": 0, "period": 3, "nick_name": "닉네임", "people": 2, "kind": 0, "daylist_string": "[4]", "dayList": null, "tagList": null, "bodyList": null },
      ],
      
      lists: [
        [
          { "challenge_id": 134, "challenge_title": "조싀앤바믜 마성의 링딩동 챌린지", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 4, "check_date": 1, "period": 8, "nick_name": "이건내닉네임이얌", "people": 4, "kind": 0, "daylist_string": "[2, 4]", "dayList": null, "tagList": null, "bodyList": null },
          { "challenge_id": 183, "challenge_title": "원푸드 다이어트", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 9, "check_date": 1, "period": 11, "nick_name": "관리자3", "people": 2, "kind": 0, "daylist_string": "[1, 3, 5]", "dayList": null, "tagList": null, "bodyList": null },
        ],
        [
          { "challenge_id": 128, "challenge_title": "기초체력 홈트 15분 루틴", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 3, "check_date": 0, "period": 7, "nick_name": "우지니", "people": 3, "kind": 0, "daylist_string": "[1, 2, 3, 4, 5]", "dayList": null, "tagList": null, "bodyList": null },
          { "challenge_id": 132, "challenge_title": "힙으뜸 8분 힙운동 따라하기", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 6, "check_date": 0, "period": 28, "nick_name": "건강이", "people": 3, "kind": 0, "daylist_string": "[3, 5, 7]", "dayList": null, "tagList": null, "bodyList": null },
          { "challenge_id": 179, "challenge_title": "sad", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 3, "check_date": 0, "period": 29, "nick_name": "loco", "people": 2, "kind": 0, "daylist_string": "[4]", "dayList": null, "tagList": null, "bodyList": null },
        ],
        [
          { "challenge_id": 131, "challenge_title": "키쑥쑥 성장운동", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 7, "check_date": 2, "period": 7, "nick_name": "건강이", "people": 3, "kind": 0, "daylist_string": "[2, 4, 6]", "dayList": null, "tagList": null, "bodyList": null },
          { "challenge_id": 178, "challenge_title": "챌린지개설", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 9, "check_date": 2, "period": 3, "nick_name": "닉네임", "people": 2, "kind": 0, "daylist_string": "[4]", "dayList": null, "tagList": null, "bodyList": null },
        ],
        [
          { "challenge_id": 183, "challenge_title": "원푸드 다이어트", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 9, "check_date": 1, "period": 11, "nick_name": "관리자3", "people": 2, "kind": 0, "daylist_string": "[1, 3, 5]", "dayList": null, "tagList": null, "bodyList": null },
        ],
      ],
      items: [
          { "challenge_id": 134, "challenge_title": "조싀앤바믜 마성의 링딩동 챌린지", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 4, "check_date": 0, "period": 8, "nick_name": "이건내닉네임이얌", "people": 4, "kind": 0, "daylist_string": "[2, 4]", "dayList": null, "tagList": null, "bodyList": null },
          { "challenge_id": 183, "challenge_title": "원푸드 다이어트", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 9, "check_date": 0, "period": 11, "nick_name": "관리자3", "people": 2, "kind": 0, "daylist_string": "[1, 3, 5]", "dayList": null, "tagList": null, "bodyList": null },
        ],
      
    }
  },
  computed: {
    ...mapGetters(["getUserUid"]),
  },
  watch: {
    category() {
      this.updateCategory()
    }
  },
  // created() {
  //   axios
  //     .get(`${SERVER_URL}/challenge/bookmark/${this.getUserUid}`)
  //     .then((data) => {
  //       this.challengeList = data.data
  //       for (let i = 0; i<challengeList.length; i++) {
  //         if (challengeList[i].check_date === 0) {
  //           this.items[1].push(challengeList[i])
  //         } else if (challengeList[i].check_date === 1) {
  //           this.items[0].push(challengeList[i])
  //         } else {
  //           this.items[2].push(challengeList[i])
  //         }
  //         if (challengeList[i].make_uid === this.getUserUid) {
  //           this.items[3].push(challengeList[i])
  //         }
  //       }
  //     })
  //     .catch(() => {
  //       alert("챌린지 목록을 불러오지 못했습니다.");
  //     });
  // },
  methods: {
    ChallengeMoreInfo: function(challenge_id) {
      this.$router.push(`/challenge-more-info/${challenge_id}`);
    },
    DeleteChallenge(challenge_id) {
      const itemToFind = this.lists[1].find(function(item) {return item.challenge_id === challenge_id})
      const idx = this.lists[1].indexOf(itemToFind)
      if (idx > -1) this.lists[1].splice(idx, 1)
      // axios
      //   .delete(
      //     `${SERVER_URL}/user/bookmark/${this.getUserUid}/${challenge_id}`
      //   )
      //   .then(() => {
      //     alert("북마크가 해제되었습니다.");
      //   })
      //   .catch(() => {
      //     alert("오류가 발생했습니다.");
      //   });
    },
    tabValue(num) {
      this.category = 0
      document.querySelector(`.tab-${this.tab}`).classList.remove("active")
      document.querySelector(`.tab-${num}`).classList.add("active")
      this.tab = num
      if (num === 1) {
        this.deleteActive = 1
      } else {
        this.deleteActive = 0
      }
      this.items = this.lists[num]
    },
    updateCategory() {
      console.log(this.category)
      if (this.category > 0) {
        this.items = []
        for (let i = 0; i<this.lists[this.tab].length; i++) {
          if (this.lists[this.tab][i].fit_id == this.category) {
            this.items.push(this.lists[this.tab][i])
          }
        }
      } else {
        this.items = this.lists[this.tab]
      }
    },
  }
}
</script>

<style>

</style>