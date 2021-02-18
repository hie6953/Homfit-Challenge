<template>
  <div class="mx-auto col-12 col-md-8">
    <div class="diary-background">
      <diary-calendar
        :diaryList="diaryList"
        :prop_change="getData"
        :prop_choiceDate="currentDate"
        @getInfo="getInfo"
        @choiceDate="choiceDate"
      ></diary-calendar>
      <hr />
      <div class="diary-content">
        <div class="diary-content-star">
          <div>이 날의 별점을 평가해보세요!</div>
          <button
            v-for="index in (1, 5)"
            :key="`${index}_starList`"
            class="diary-content-star-button"
            :id="`star-${index}`"
            @click="ClickStar(index)"
          >
            <b-icon :icon="starList[index].icon"></b-icon>
          </button>
        </div>

        <div class="diary-content-write">
          <div>이 날의 챌린지에 대해 기록해보세요!</div>
          <b-form-textarea
            id="diary-textarea"
            v-model="textDiary"
            placeholder="여기에 기록하세요."
            rows="4"
            :class="{ focusoff: textAreaFocusOff }"
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
import swal from "@/assets/javascript/sweetAlert.js";
import { mapGetters } from "vuex";

import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

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
        { icon: "star" },
      ],
      diaryList: [],
      isHave: false,
      starPoint: 0,
      textDiary: "",
      textAreaFocusOff: true,
      currentYear: 0,
      currentMonth: 0,
      currentDate: new Date().getUTCDate(),
      getData: false,
    };
  },
  methods: {
    getInfo: function(year, month) {
      if (month < 10) {
        month = "0" + month;
      }
      axios
        .get(`${SERVER_URL}/diary/search/month`, {
          params: {
            uid: this.getUserUid,
            date: year + "-" + month,
          },
        })
        .then(({ data }) => {
          // console.log(data);
          this.diaryList = data;
          
          this.getData = !this.getData;
        })
        .catch(() => {
          swal.error("기록 목록을 불러오지 못했습니다.");
        });
    },
    choiceDate: function(year, month, date, haveDiary) {
      this.currentYear = year;
      this.currentMonth = month;
      this.currentDate = date;
      this.isHave = haveDiary.have;
      this.textDiary = "";
      this.starPoint = 0;
      this.DisplayStar();
      if (this.isHave) {
        let index = haveDiary.dateIdx;
        this.textDiary = this.diaryList[index].diary_contents;
        this.starPoint = this.diaryList[index].star_point;
        this.DisplayStar();
      }
    },
    Blur: function() {
      console.log('blur');
      this.textAreaFocusOff = true;
      //통신
      if (this.isHave) {
        this.PutConnection();
      } else {
        this.PostConnection();
      }
    },
    Focus: function() {
      this.textAreaFocusOff = false;
    },
    DisplayStar: function() {
      for (let i = 1; i <= this.starPoint; ++i) {
        this.starList[i].icon = "star-fill";
      }
      for (let i = this.starPoint + 1; i <= 5; ++i) {
        this.starList[i].icon = "star";
      }
    },
    ClickStar: function(index) {
      this.DisplayStar(index);
      this.starPoint = index;
      //통신
      if (this.isHave) {
        this.PutConnection();
      } else {
        this.PostConnection();
      }
    },
    PostConnection: function() {
      console.log(this.getUserUid);
      console.log(
        new Date(
          this.currentYear + "-" + this.currentMonth + "-" + this.currentDate
        )
      );
      console.log(this.starPoint);
      console.log(this.textDiary);
      axios
        .post(`${SERVER_URL}/diary/create`, {
          uid: this.getUserUid,
          date: new Date(
            this.currentYear + "-" + this.currentMonth + "-" + this.currentDate
          ),
          star_point: this.starPoint,
          diary_contents: this.textDiary,
        })
        .then(() => {
          this.isHave = true;
          this.getInfo(this.currentYear, this.currentMonth);
        })
        .catch(() => {
          swal.error("기록 목록을 저장하지 못했습니다.");
        });
    },
    PutConnection: function() {
      console.log(this.getUserUid);
      console.log(
        new Date(
          this.currentYear + "-" + this.currentMonth + "-" + this.currentDate
        )
      );
      console.log(this.starPoint);
      console.log(this.textDiary);
      axios
        .put(`${SERVER_URL}/diary/update`, {
          uid: this.getUserUid,
          date: new Date(
            this.currentYear + "-" + this.currentMonth + "-" + this.currentDate
          ),
          star_point: this.starPoint,
          diary_contents: this.textDiary,
        })
        .then(() => {
          this.getInfo(this.currentYear, this.currentMonth);
        })
        .catch(() => {
          swal.error("기록 목록을 수정하지 못했습니다.");
        });
    },
  },
  computed: {
    ...mapGetters(["getUserUid"]),
  },
};
</script>
