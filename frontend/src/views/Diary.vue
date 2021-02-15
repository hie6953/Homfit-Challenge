<template>
  <div class="mx-auto col-12 col-md-8">
    <div class="diary-background">
      <diary-calendar></diary-calendar>
      <hr />
      <div class="diary-content">
        <div class="diary-content-star">
        <div>이 날의 별점을 평가해보세요!</div>
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
            <div>이 날의 챌린지에 대해 기록해보세요!</div>
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
      </div>
    </div>
  </div>
</template>
<script>
import "@/assets/css/Diary/diary.css";
import DiaryCalendar from "../components/DiaryCalendar.vue";

export default {
  components: { DiaryCalendar },
  data() {
    return {
      starList: [
        { icon: "star" },
        { icon: "star" },
        { icon: "star" },
        { icon: "star" },
        { icon: "star" },
      ],
      textDiary:"",
      textAreaFocusOff:true,
    };
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
    },
  },
};
</script>
