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
            @click="getNewCategory"
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
            @click="getNewCategory"
            type="radio"
            name="tools"
            value="1"
            v-model="category"
            id="category-yoga"
          />
          <label class="for-checkbox-tools" for="category-yoga"> 요가 </label>
          <input
            class="checkbox-tools"
            @click="getNewCategory"
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
            @click="getNewCategory"
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
            @click="getNewCategory"
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
            @click="getNewCategory"
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
            @click="getNewCategory"
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
            @click="getNewCategory"
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
            @click="getNewCategory"
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
            @click="getNewCategory"
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
            @click="getNewCategory"
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
      <div class="row">
        <ChallengeListCard
          v-for="(challenge, index) in challengeList"
          :key="`${index}_challengemanage`"
          class="col-6 col-md-4 manage-list-card"
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
    <infinite-loading
      ref="InfiniteLoading"
      @infinite="getData"
      spinner="waveDots"
    >
      <div class="infinite-loading-message" slot="no-more">
        <b-button @click="scrollUp"
          >마지막입니다 <b-icon icon="arrow-up-circle"></b-icon
        ></b-button>
      </div>
      <div class="infinite-loading-message" slot="no-results">
        챌린지가 없습니다.
      </div>
      <div class="infinite-loading-message" slot="error">
        불러오지 못했습니다.
      </div>
    </infinite-loading>
  </div>
</template>

<script>
import "@/assets/css/ChallengeManage/challengeManage.css";
import swal from "@/assets/javascript/sweetAlert.js";
import ChallengeListCard from "../components/ChallengeListCard.vue";
import InfiniteLoading from "vue-infinite-loading";
import "@/assets/css/infiniteloading.css";
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import { mapGetters } from "vuex";
export default {
  components: {
    ChallengeListCard,
    InfiniteLoading,
  },
  data() {
    return {
      isfromChallengeManage: 1,
      cancelActive: 0,
      deleteActive: 0,
      tab: 0,
      category: 0,
      page: 1,
      challengeAllList: null, //모든 데이터
      sortedAllList: [], //정렬된 데이터
      challengeList: [], //페이징 데이터
    };
  },
  computed: {
    ...mapGetters(["getUserUid", "getUserNickName"]),
  },
  methods: {
    ChallengeMoreInfo: function(challenge_id) {
      this.$router.push(`/participated/${challenge_id}`);
    },
    async CancelChallenge(challenge_id) {
      const cancelConfirm = await swal.confirm("참여를 취소하시겠습니까?");
      if (cancelConfirm === false) return false;
      // 취소 버튼 클릭시 리스트[1]에서 삭제
      const itemToFind = this.challengeAllList.find(function(item) {
        return item.challenge_id === challenge_id;
      });
      if (itemToFind.nick_name === this.getUserNickName) {
        swal.error("챌린지 개설자는 참여 취소가 불가능합니다.");
        return false;
      }

      axios
        .delete(`${SERVER_URL}/challenge/join/${challenge_id}`, {
          params: {
            uid: this.getUserUid,
          },
        })
        .then(() => {
          swal.success("참여 취소되었습니다.");
          const idx = this.challengeAllList.indexOf(itemToFind);  //배열에서 해당 챌린지 삭제
          if (idx > -1) this.challengeAllList.splice(idx, 1);
          this.getNewCategory();  //정렬 새로하기
        })
        .catch(() => {
          swal.error("오류가 발생했습니다.");
        });
    },
    async DeleteChallenge(challenge_id) {
      const deleteConfirm = await swal.confirm("챌린지를 삭제하시겠습니까?");
      if (deleteConfirm === false) return false;
      // 삭제 버튼 클릭시 리스트[4]에서 삭제
      const itemToFind = this.challengeAllList.find(function(item) {
        return item.challenge_id === challenge_id;
      });
      if (itemToFind.people > 1) {
        swal.error("챌린지 참여자 존재시 삭제할 수 없습니다.");
        return false;
      }
      if (itemToFind.check_date > 0) {
        swal.error("진행전 챌린지만 삭제 가능합니다.");
        return false;
      }

      axios
        .delete(`${SERVER_URL}/challenge/${challenge_id}`)
        .then(() => {
          swal.success("챌린지가 삭제되었습니다.");
          const idx = this.challengeAllList.indexOf(itemToFind);  //배열에서 해당 챌린지 삭제
          if (idx > -1) this.challengeAllList.splice(idx, 1);
          this.getNewCategory();  //정렬 새로하기
        })
        .catch(() => {
          swal.error("오류가 발생했습니다.");
        });
    },
    tabValue(num) {
      // 탭 누르면 카테고리 0으로 초기화, 해당 탭내용으로 변경
      this.category = 0;
      this.tab = num;
      // console.log(this.cancelActive +" "+this.deleteActive);
      if (num === 1) {
        this.cancelActive = 1;
        this.deleteActive = 0;
      } else if (num === 4) {
        this.cancelActive = 0;
        this.deleteActive = 1;
      } else {
        this.cancelActive = 0;
        this.deleteActive = 0;
      }
      // console.log(this.cancelActive +" "+this.deleteActive);
      this.getNewData();
    },
    updateCategory() {
      // 카테고리에 따른 데이터 정렬
      if (this.category > 0) {
        this.sortedAllList = [];
        for (let i = 0; i < this.challengeAllList.length; i++) {
          if (this.challengeAllList[i].fit_id == this.category) {
            this.sortedAllList.push(this.challengeAllList[i]);
          }
        }
      } else {
        this.sortedAllList = this.challengeAllList;
      }
    },
    async getAllData() {
      await axios
        .get(`${SERVER_URL}/challenge/management/${this.tab}`, {
          params: {
            uid: this.getUserUid,
          },
        })
        .then((data) => {
          this.challengeAllList = data.data;
          this.sortedAllList = data.data;
        })
        .catch(() => {
          swal.error("챌린지 목록을 불러오지 못했습니다.");
        });
    },
    getNewData: function() {
      //새로 데이터 불러오기
      this.page = 1;
      this.challengeAllList = null;
      this.sortedAllList = [];
      this.challengeList = [];
      if (this.$refs.InfiniteLoading) {
        this.$refs.InfiniteLoading.stateChanger.reset();
      }
      // this.getAllData();
    },
    getNewCategory: function() {
      //새로 정렬하기
      this.page = 1;
      this.sortedAllList = [];
      this.challengeList = [];
      if (this.$refs.InfiniteLoading) {
        this.$refs.InfiniteLoading.stateChanger.reset();
      }
    },
    async getData($state) {
      if (this.challengeAllList == null) {
        //새로 데이터 불러오기
        await this.getAllData();
      }
      if (this.challengeList.length == 0) {
        //새로 정렬하기
        await this.updateCategory();
      }
      let getArray = this.sortedAllList.slice(
        (this.page - 1) * 10,
        this.page * 10
      );
      setTimeout(() => {
        if (getArray.length > 0) {
          this.challengeList = this.challengeList.concat(getArray);
          ++this.page;
          $state.loaded();
        } else {
          $state.complete();
        }
      }, 500);
    },
    scrollUp: function() {
      window.scrollTo({
        top: 0,
        left: 0,
        behavior: "smooth",
      });
    },
  },
};
</script>

<style scoped></style>
