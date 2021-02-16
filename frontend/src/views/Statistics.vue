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
        <p class="statistics-tip">
          <b-icon icon="info-circle" scale="1"></b-icon>
          통계는 챌린지 완료일 기준이며, 인증률이 0%일 경우 데이터를 제공하지
          않습니다.
        </p>
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
            :update="update"
              :labels="categoryLabel"
              :dataList="categoryPercent"
            />
          </div>
          <div class="mx-auto col-12 col-md-6">
            <BodyDonut :update="update" :labels="bodyLabel" :dataList="bodyPercent" />
          </div>
        </div>
      </b-tab>
      <b-tab
        :class="{ active: tab == 2 }"
        title="연령별/성별"
        @click="UpdateData(3)"
      >
        <BodyPrefer
          :sex="statisticsData3.sex"
          :age="statisticsData3.age"
          :people="statisticsData3.people"
          :per="bodyPreferPercent"
        />
        <!-- :age="statisticsData.age" -->
        <CategoryPrefer
          :sex="statisticsData3.sex"
          :age="statisticsData3.age"
          :people="statisticsData3.people"
          :per="categoryPreferPercent"
        />
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
import BodyPrefer from "../components/StatisticsPage/BodyPrefer.vue";
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
    BodyPrefer,
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
      bodyPreferPercent: [0, 0, 0, 0, 0, 0, 0, 0, 0],
      categoryPercent: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      categoryPreferPercent: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
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
        bodyList: [1, 0, 1, 0, 1, 0, 0, 0, 0],
        categoryList: [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
      },
      statisticsData3: {
        bodyList: [1, 0, 1, 0, 1, 0, 0, 0, 0],
        categoryList: [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
        sex: "w",
        age: 20,
        people: 53,
      },
      update:false,
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
  // mounted() {
  //   this.UpdatePercent();
  //   this.UpdateSex();
  // },
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
              break;
          }
          this.update = !this.update;
          if (value == 2) {
        this.UpdatePercent();
      }
          console.log(data);
        })
        .catch(() => {
          alert("챌린지 목록을 불러오지 못했습니다.");
        });

      
    },
    UpdatePercent() {
      const reducer = (accumulator, currentValue) => accumulator + currentValue;
      const totalBody = this.statisticsData2.bodyList.reduce(reducer);
      const totalCategory = this.statisticsData2.categoryList.reduce(reducer);
      console.log("+++"+totalCategory+"+++"+this.statisticsData2.categoryList);
      for (let i = 1; i <= 10; i++) {
        this.categoryPercent[i] = (
          (100 * this.statisticsData2.categoryList[i]) /
          totalCategory
        ).toFixed(0);
        if (i < 10) {
          this.bodyPercent[i] = (
            (100 * this.statisticsData2.bodyList[i]) /
            totalBody
          ).toFixed(0);
        }
      }
      console.log(this.categoryPercent);
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
