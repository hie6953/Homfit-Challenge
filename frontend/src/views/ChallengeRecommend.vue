<template>
  <div>
    <select-preference
    :modalShow="modalShow"
    @modalClose="modalShow = false"
    ></select-preference>

    <div class="recommend-category-background row mx-auto mt-3">
      <div class="recommend-category mx-auto">
        <input
          class="recommend-checkbox-tools"
          type="radio"
          :value="1"
          v-model="recommendCategory"
          id="favorit-category"
          checked
        />
        <label for="favorit-category">
          선호 카테고리
        </label>
        <input
          class="recommend-checkbox-tools"
          type="radio"
          :value="2"
          v-model="recommendCategory"
          id="favorit-bodypart"
        />
        <label for="favorit-bodypart"> 선호 운동 부위 </label>
        <input
          class="recommend-checkbox-tools"
          type="radio"
          :value="3"
          v-model="recommendCategory"
          id="favorit-daylist"
        />
        <label for="favorit-daylist"> 선호 요일 </label
        ><input
          class="recommend-checkbox-tools"
          type="radio"
          :value="4"
          v-model="recommendCategory"
          id="favorit-age-gender"
        />
        <label for="favorit-age-gender">
          나이 & 성별
        </label>
      </div>
    </div>
    <div class="row col-12 col-md-8  mx-auto list-card">
      <challenge-list-card
        v-for="(challenge, index) in challengeList"
        :key="`${index}_challenge`"
        class="col-6 col-md-4 col-xl-3 challenge-list-card"
        :challenge="challenge"
        @moreInfo="ChallengeMoreInfo"
      ></challenge-list-card>
    </div>
    <div class="align-center">
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
          결과가 없습니다 :(
        </div>
        <div class="infinite-loading-message" slot="error">
          불러오지 못했습니다.
        </div>
      </infinite-loading>
    </div>
  </div>
</template>
<script>
import ChallengeListCard from "../components/ChallengeListCard.vue";
import SelectPreference from "../components/SelectPreference.vue";
import InfiniteLoading from "vue-infinite-loading";
import ChallengeListDummyData from "@/assets/dummyData/challengeDummyData.json";
import "@/assets/css/challengerecommend.css";

import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

import { mapGetters } from "vuex";

export default {
  name: "ChallengeRecommend",
  components: { SelectPreference,ChallengeListCard,InfiniteLoading },
  data() {
    return {
      recommendCategory: 1,
      page: 1,
      challengeAllList: null,
      modalShow:false,
      endCreated:false,
      challengeList: [
        { challenge_title: "타이틀" },
        { challenge_title: "타이틀" },
        { challenge_title: "타이틀" },
        { challenge_title: "타이틀" },
        { challenge_title: "타이틀" },
        { challenge_title: "타이틀" },
        { challenge_title: "타이틀" },
        { challenge_title: "타이틀" },
      ],
    };
  },
  watch: {
    recommendCategory: function() {
      this.challengeAllList = null;
    },
  },
  created() {
    axios
        .get(`${SERVER_URL}/user/favorite/isSetting`, {
          params: {
            uid:this.getUserUid,
          },
        })
        .then(({ data }) => {
          this.modalShow = !data;
          this.endCreated = true;
        })
        .catch(() => {
          alert("선호도 정보를 불러오지 못했습니다.");
          this.endCreated = true;
      });
      
  },
  computed: {
    ...mapGetters(["getUserUid"]),
  },
  methods: {
    async getAllData() {
      await axios
        .get(`${SERVER_URL}/challenge/recommend/${this.getUserUid}/${this.recommendCategory}`)
        .then(({ data }) => {
          this.challengeAllList = data;
        })
        .catch(() => {
          alert("챌린지 목록을 불러오지 못했습니다.");
          this.challengeAllList = ChallengeListDummyData.challengeList;
        });
    },

    async getData($state) {
      if(!this.endCreated || this.modalShow)
        return;
      if (this.challengeAllList == null) {
        await this.getAllData();
      }
      let getArray = this.challengeAllList.slice(
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
    ChallengeMoreInfo: function(challenge_id) {
      this.$router.push(`/challenge-more-info/${challenge_id}`);
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
