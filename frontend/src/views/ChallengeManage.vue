<template>
  <div class="manage-container">
    <b-tabs class="manage-menu-bar">
      <b-tab @click="tabValue(0)" title="오늘"></b-tab>
      <b-tab @click="tabValue(1)" title="진행전"></b-tab>
      <b-tab @click="tabValue(2)" title="진행중"></b-tab>
      <b-tab @click="tabValue(3)" title="완료"></b-tab>
      <b-tab @click="tabValue(4)" title="개설"></b-tab>
    </b-tabs>
    <div>
      <div class="manage-category-background row mx-auto">
        <div class="manage-category mx-auto">
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
    <div class="col-12 col-md-8 mx-auto mt-4">
      <div class="row list-card">
        <ChallengeListCard
          v-for="(challenge, index) in items"
          :key="`${index}_challengemanage`"
          class="col-6 col-md-4 challenge-list-card"
          :challenge="challenge"
          :isfromChallengeManage="isfromChallengeManage"
          :cancelActive="cancelActive"
          :deleteActive="deleteActive"
          @moreInfo="ChallengeMoreInfo"
          @cancelChallengeManage="CancelChallenge"
          @deleteChallengeManage="DeleteChallenge"
        />
      </div>
    </div>
  </div>
  
</template>

<script>
import "@/assets/css/ChallengeManage/challengeManage.css"
import ChallengeListCard from "../components/ChallengeListCard.vue";
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import { mapGetters } from "vuex";
export default {
  components: {
    ChallengeListCard,
  },
  data() {
    return {
      isfromChallengeManage: 1,
      cancelActive: 0,
      deleteActive: 0,
      tab: 0,
      category: 0,
      challengeList: [
      ],
      items: [
          // { "challenge_id": 134, "challenge_title": "조싀앤바믜 마성의 링딩동 챌린지", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 4, "check_date": 0, "period": 8, "nick_name": "이건내닉네임이얌", "people": 4, "kind": 0, "daylist_string": "[2, 4]", "dayList": null, "tagList": null, "bodyList": null },
          // { "challenge_id": 183, "challenge_title": "원푸드 다이어트", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 9, "check_date": 0, "period": 11, "nick_name": "관리자3", "people": 2, "kind": 0, "daylist_string": "[1, 3, 5]", "dayList": null, "tagList": null, "bodyList": null },
        ],
      
    }
  },
  computed: {
    ...mapGetters(["getUserUid", "getUserNickName"]),
  },
  watch: {
    category() {
      this.updateCategory()
    }
  },
  mounted() {
    this.UpdateData()
  },
  methods: {
    ChallengeMoreInfo: function(challenge_id) {
      this.$router.push(`/participated/${challenge_id}`);
    },
    CancelChallenge(challenge_id) {
      const cancelConfirm = confirm("참여를 취소하시겠습니까?")
      if (cancelConfirm === false) return false
      // 취소 버튼 클릭시 리스트[1]에서 삭제
      const itemToFind = this.challengeList.find(function(item) {return item.challenge_id === challenge_id})
      if (itemToFind.nick_name === this.getUserNickName) {
        alert("챌린지 개설자는 참여 취소가 불가능합니다.")
        return false
      }
      const idx = this.challengeList.indexOf(itemToFind)
      if (idx > -1) this.challengeList.splice(idx, 1)
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
    DeleteChallenge(challenge_id) {
      const deleteConfirm = confirm("챌린지를 삭제하시겠습니까?")
      if (deleteConfirm === false) return false
      // 삭제 버튼 클릭시 리스트[4]에서 삭제
      const itemToFind = this.challengeList.find(function(item) {return item.challenge_id === challenge_id})
      if (itemToFind.people > 1) {
        alert("챌린지 참여자 존재시 삭제할 수 없습니다.")
        return false
      }
      if (itemToFind.check_date > 0) {
        alert("진행전 챌린지만 삭제 가능합니다.")
        return false
      }
      const idx = this.challengeList.indexOf(itemToFind)
      if (idx > -1) this.challengeList.splice(idx, 1)
    },
    tabValue(num) {
      // 탭 누르면 카테고리 0으로 초기화, 해당 탭내용으로 변경
      this.category = 0
      this.tab = num
      if (num === 1) {
        this.cancelActive = 1
        this.deleteActive = 0
      } else if (num === 4) {
        this.cancelActive = 0
        this.deleteActive = 1
      } else {
        this.cancelActive = 0
        this.deleteActive = 0
      }
      this.items = this.challengeList
      this.UpdateData()
    },
    updateCategory() {
      // 카테고리에 따른 데이터 정렬
      if (this.category > 0) {
        this.items = []
        for (let i = 0; i<this.challengeList.length; i++) {
          if (this.challengeList[i].fit_id == this.category) {
            this.items.push(this.challengeList[i])
          }
        }
      } else {
        this.items = this.challengeList
      }
    },
    UpdateData() {
      axios
      .get(`${SERVER_URL}/challenge/management/${this.tab}`, {
        params: {
          uid: this.getUserUid
        }
      })
      .then((data) => {
        this.challengeList = data.data
        this.items = data.data
      })
      .catch(() => {
        alert("챌린지 목록을 불러오지 못했습니다.");
      });
    }
  }
}
</script>

<style scoped>

</style>
