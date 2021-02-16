<template>
  <div>
    <b-tabs
      lazy
      v-model="tab"
      content-class="mt-3 col-12 col-md-8 mx-auto"
      class="statistics-menu-bar"
    >
      <b-tab
        :class="{ active: tab == 0 }"
        title="월별 통계"
        @click="UpdateData(1)"
        active
      >
        <div class="statistics-background">
          <Calendar @selectedMonth="UpdateMonth" />
        </div>
        <div class="statistics-tip col-12 flex-wrap">
          <b-icon icon="info-circle" scale="1"></b-icon>
          <p>통계는 챌린지 완료일 기준이며, 인증률이 0%일 경우 데이터를 제공하지않습니다.</p>
        </div>
        <p class="statistics-title">월별 챌린지 인증률</p>
        <MonthGraph :monthList="statisticsData1.monthList" />
        <AverageRate
          :current="statisticsData1.current_average_rate"
          :previous="statisticsData1.previous_average_rate"
        />
      </b-tab>
      <b-tab
        :class="{ active: tab == 1 }"
        title="참여 통계"
        @click="UpdateData(2)"
      >
        <div>

          <div class="mx-auto col-12 col-md-6">
            <CategoryDonut
              :update="update2"
              :labels="categoryLabel"
              :dataList="categoryPercent"
            />
          </div>
          <div class="mx-auto col-12 col-md-6">
            <BodyDonut :update="update2" :labels="bodyLabel" :dataList="bodyPercent" />
          </div>
        </div>
      </b-tab>
      <b-tab
        :class="{ active: tab == 2 }"
        title="연령별/성별"
        @click="UpdateData(3)"
      >
        <!-- <CategoryPrefer
          :update="update3"
          :sex="sex"
          :age="statisticsData3.age"
          :people="statisticsData3.people"
          :labels="categoryLabel"
          :favorite="favoriteCategoryPercent"
        />
        <BodyPrefer
          :update="update3"
          :sex="sex"
          :age="statisticsData3.age"
          :people="statisticsData3.people"
          :labels="bodyLabel"
          :favorite="favoriteBodyPercent"
        /> -->
        <div class="mx-auto col-12 col-md-6">
        <CategoryPrefer
          :update="update3"
          :sex="sex"
          :age="statisticsData3.age"
          :people="statisticsData3.people"
          :labels="bothLabel"
          :bfavorite="favoriteBodyPercent"
          :cfavorite="favoriteCategoryPercent"
        />
        </div>
      </b-tab>
    </b-tabs>
  </div>
</template>

<script>
import "../assets/css/StatisticsPage/statistics.css";
import Calendar from "@/components/StatisticsPage/Calendar.vue";
import MonthGraph from "../components/StatisticsPage/MonthGraph.vue";
import AverageRate from "../components/StatisticsPage/AverageRate.vue";
import CategoryDonut from "../components/StatisticsPage/CategoryDonut.vue";
import BodyDonut from "../components/StatisticsPage/BodyDonut.vue";
// import BodyPrefer from "../components/StatisticsPage/BodyPrefer.vue";
import CategoryPrefer from "../components/StatisticsPage/CategoryPrefer.vue";
import { mapGetters } from "vuex";
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  components: {
    Calendar,
    MonthGraph,
    AverageRate,
    CategoryDonut,
    BodyDonut,
    // BodyPrefer,
    CategoryPrefer,
  },
  data() {
    return {
      tab: 0,
      idxCategory: 1,
      idxBody: 2,
      sex: "남성",
      age: 20,
      selectedMonth: 0,
      bodyPercent: [0, 0, 0, 0, 0, 0, 0, 0, 0],
      favoriteBodyPercent: [0, 0, 0, 0, 0, 0, 0, 0, 0],
      categoryPercent: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      favoriteCategoryPercent: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      bothLabel: [
        "요가",
        "필라테스/전신",
        "유산소/상체",
        "댄스/하체",
        "스트레칭/가슴",
        "근력/팔",
        "키즈/복부",
        "복싱/엉덩이",
        "식단/다리",
        "기타",
      ],
      bodyLabel: [
        "전신",
        "상체",
        "하체",
        "가슴",
        "팔",
        "복부",
        "엉덩이",
        "다리",
        "기타",
      ],
      categoryLabel: [
        "요가",
        "필라테스",
        "유산소",
        "댄스",
        "스트레칭",
        "근력",
        "키즈",
        "복싱",
        "식단",
        "기타",
      ],
      statisticsData1: {
        monthList: [],
        current_average_rate: 0,
        previous_average_rate: 0,
      },
      statisticsData2: {
        bodyList: [1, 0, 1, 0, 1, 0, 0, 0, 0, 0],
        categoryList: [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
      },
      statisticsData3: {
        favoriteBodyList: [1, 0, 1, 0, 1, 0, 0, 0, 0, 0],
        favoriteFitList: [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
        sex: "w",
        age: 20,
        people: 53,
      },
      update2:false,
      update3:false,
    };
  },
  computed: {
    ...mapGetters(["getUserUid"]),
  },
  watch: {
    selectedMonth() {
      this.UpdateData(1);
    },
  },
  
  methods: {
    UpdateMonth(selectedMonth) {
      this.selectedMonth = selectedMonth;
    },
    UpdateData(value) {
      axios
        .get(`${SERVER_URL}/challenge/figures/${value}/${this.selectedMonth}`, {
          params: {
            uid: this.getUserUid,
          },
        })
        .then((data) => {
          switch (value) {
            case 1:
              this.statisticsData1= data.data;
              break;
            case 2:
              this.statisticsData2= data.data;
              break;
              case 3:
              this.statisticsData3= data.data;
              console.log(this.statisticsData3)
              
              break;
          }
          this.update2 = !this.update2;
          if (value == 2) {
            this.UpdateSecondTab();
          }
          this.update3 = !this.update3;
          if (value == 3) {
            this.UpdateThirdTab();
            this.UpdateSex();
          }
          console.log(data);
        })
        .catch(() => {
          alert("챌린지 목록을 불러오지 못했습니다.");
        });

      
    },
    UpdateSecondTab() {
      const reducer = (accumulator, currentValue) => accumulator + currentValue;
      const totalBody = this.statisticsData2.bodyList.reduce(reducer);
      const totalCategory = this.statisticsData2.categoryList.reduce(reducer);
      for (let i = 1; i <= 10; i++) {
        this.categoryPercent[i-1] = (
          (100 * this.statisticsData2.categoryList[i]) /
          totalCategory
        ).toFixed(0);
        if (i < 10) {
          this.bodyPercent[i-1] = (
            (100 * this.statisticsData2.bodyList[i]) /
            totalBody
          ).toFixed(0);
        }
      }
      console.log(this.statisticsData2.bodyList)
      console.log(this.bodyPercent)
    },
    UpdateThirdTab() {
      for (let i = 1; i <= 10; i++) {
        this.favoriteCategoryPercent[i-1] = (
          (100 * this.statisticsData3.favoriteFitList[i]) /
          this.statisticsData3.people
        ).toFixed(0);
        if (i < 10) {
          this.favoriteBodyPercent[i-1] = (
            (100 * this.statisticsData3.favoriteBodyList[i]) /
            this.statisticsData3.people
          ).toFixed(0);
        }
      }
      console.log(this.favoriteBodyPercent)
      console.log(this.favoriteCategoryPercent)
    },
    UpdateSex() {
      if (this.statisticsData3.sex === "m") {
        this.sex = "남성";
      } else {
        this.sex = "여성";
      }
    },
  },
};
</script>

<style></style>
