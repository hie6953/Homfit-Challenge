<template>
  <div class="mx-auto col-12 col-md-8">
    <div class="diary-background">
      <div class="diary-content">
        <div class="diary-content-star">
        <div>이 챌린지의 별점을 평가해보세요!</div>
          <button
            v-for="(star, index) in starList"
            :key="`${index}_starList`"
            class="diary-content-star-button"
            :id="`star-${index}`"
            @click="ClickStar(index)"
          >
            <b-icon :icon="star.icon"></b-icon>
          </button>
        </div>

        <div class="diary-content-write">
            <div>이 챌린지의 후기를 기록해보세요!</div>
          <b-form-textarea
            id="diary-textarea"
            v-model="textDiary"
            placeholder="여기에 기록하세요."
            rows="4"
            :class="{focusoff:textAreaFocusOff}"
            @blur="Blur"
            @focus="Focus"
          ></b-form-textarea>
        </div>
        <div class="row justify-content-end mr-1">
          <div class="review-btn review-complete-btn" @click="ReviewComplete">작성완료</div>
          <div class="review-btn review-cancel-btn" @click="ReviewCancel">작성취소</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import "@/assets/css/Diary/diary.css";
import { mapGetters } from "vuex";
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  data() {
    return {
      starList: [
        { icon: "star" },
        { icon: "star" },
        { icon: "star" },
        { icon: "star" },
        { icon: "star" },
      ],
      star_point: 0,
      textDiary:"",
      textAreaFocusOff:true,
    };
  },
  computed: {
    ...mapGetters(["getUserUid", "getUserNickName"]),
  },
  methods: {
    Blur:function(){
        this.textAreaFocusOff = true;
        //통신
    },
    Focus:function(){
        this.textAreaFocusOff = false;
    },
    ClickStar: function(index) {
      for (let i = 0; i <= index; ++i) {
        this.starList[i].icon = "star-fill";
      }
      for (let i = index + 1; i <= 4; ++i) {
        this.starList[i].icon = "star";
      }
      this.star_point = index+1
      console.log(this.star_point)
    },
    ReviewComplete() {
      axios
        .post(`${SERVER_URL}/challenge/review`, {
            uid: this.getUserUid,
            nick_name: this.getUserNickName,
            star_point: this.star_point,
            review_contents: this.textDiary,
            challenge_id: this.$route.params.challenge_id,
          })
        .then(() => {
          alert("후기가 등록 되었습니다.")
          const challenge_id = this.$route.params.challenge_id;
          this.$router.push(`/participated/${challenge_id}`)
        })
        .catch(() => {
          alert("후기 등록이 실패했습니다.");
        });
    },
    ReviewCancel() {
      const challenge_id = this.$route.params.challenge_id;
      const cancel = confirm("후기 등록을 취소하시겠습니까?")
      if (cancel === false) return false
      this.$router.push(`/participated/${challenge_id}`)
    },
  },
};
</script>
