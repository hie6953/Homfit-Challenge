<template>
  <div class="col-12 col-md-8 mx-auto">
    <b-tabs content-class="mt-3">
      <b-tab :class="{active:tab == 1}" title="월별 통계" @click="UpdateData()" active>
        <div class="statistics-background">
          <Calendar 
            @selectedMonth="UpdateMonth"
          />
        </div>
        <p class="statistics-tip">
          <b-icon icon="info-circle" scale=1></b-icon>
          통계는 챌린지 완료일 기준이며, 인증률이 0%일 경우 데이터를 제공하지 않습니다.</p>
        <p class="statistics-title">월별 챌린지 인증률</p>
        <MonthGraph
          :monthList="statisticsData.monthList"
        />
        <AverageRate
          :current="statisticsData.current_average_rate"
          :previous="statisticsData.previous_average_rate"
        />
      </b-tab>
      <b-tab :class="{active:tab == 2}" title="참여 통계" value="2" @click="UpdateData()">
        <div class="row">
          <div class="col-3"></div>
          
          <div class="col-6">
            <CategoryDonut
              :labels="categoryLabel"
              :dataList="categoryPercent"
            />
          </div>
          <div class="col-3"></div>
          <div class="col-3"></div>
          <div class="col-6">
            <BodyDonut
              :labels="bodyLabel"
              :dataList="bodyPercent"
            />
          </div>
        </div>
      </b-tab>
      <b-tab :class="{active:tab == 3}" title="연령별/성별" value="3" @click="UpdateData()">
        <BodyPrefer
          :sex="sex"
          :age="age"
          :total="statisticsData.total"
          :per="bodyPreferPercent"
        />
          <!-- :age="statisticsData.age" -->
        <CategoryPrefer
          :sex="sex"
          :age="statisticsData.age"
          :total="statisticsData.total"
          :per="categoryPreferPercent"
        />
      </b-tab>
    </b-tabs>
  </div>
</template>

<script>
import "../assets/css/StatisticsPage/statistics.css"
import Calendar from "@/components/StatisticsPage/Calendar.vue"
import MonthGraph from '../components/StatisticsPage/MonthGraph.vue'
import AverageRate from '../components/StatisticsPage/AverageRate.vue';
import CategoryDonut from '../components/StatisticsPage/CategoryDonut.vue';
import BodyDonut from '../components/StatisticsPage/BodyDonut.vue';
import BodyPrefer from '../components/StatisticsPage/BodyPrefer.vue';
import CategoryPrefer from '../components/StatisticsPage/CategoryPrefer.vue';
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
      tab: 1,
      idxCategory: 1,
      idxBody: 2,
      sex: "남성",
      age: 20,
      selectedMonth: 0,
      bodyPercent: [0,0,0,0,0,0,0,0,0],
      bodyPreferPercent: [0,0,0,0,0,0,0,0,0],
      categoryPercent: [0,0,0,0,0,0,0,0,0,0],
      categoryPreferPercent: [0,0,0,0,0,0,0,0,0,0],
      bodyLabel: ['전신', '상체', '하체', '가슴', '팔', '복부', '엉덩이', '다리', '기타'],
      categoryLabel: ['요가', '필라테스', '유산소', '댄스', '스트레칭', '근력', '키즈', '복싱', '식단', '기타'],
      statisticsData: {
        bodyList: [1,0,1,0,1,0,0,0,0],
        categoryList: [1,1,1,1,1,1,1,1,1,1],
        monthList: [],
        current_average_rate: 0,
        previous_average_rate: 0,
        sex: "w",
        age: 20,
        total: 53,
      },
    }
  },
  computed: {
    ...mapGetters(["getUserUid"]),
  },
  watch: {
    selectedMonth() {
      this.UpdateData()
    }
  },
  mounted() {
    this.UpdatePercent()
    this.UpdateSex()
  },
  methods: {
    UpdateMonth(selectedMonth) {
      this.selectedMonth = selectedMonth
      this.UpdateData()
    },
    UpdateData() {
      axios
      .get(`${SERVER_URL}/challenge/figures/${this.tab}/${this.selectedMonth}`, {
        params: {
          uid: this.getUserUid
        }
      })
      .then((data) => {
        this.statisticsData = data.data
        console.log(data)
      })
      .catch(() => {
        alert("챌린지 목록을 불러오지 못했습니다.");
      });
    },
    UpdatePercent() {
      const reducer = (accumulator, currentValue) => accumulator + currentValue
      const totalBody = this.statisticsData.bodyList.reduce(reducer)
      const totalCategory = this.statisticsData.categoryList.reduce(reducer)
      for (let i = 0; i<10; i++) {
        this.categoryPercent[i] = (100*this.statisticsData.categoryList[i]/totalCategory).toFixed(0)
        if (i<9) {
          this.bodyPercent[i] = (100*this.statisticsData.bodyList[i]/totalBody).toFixed(0)
        }
      }
    },
    UpdateSex() {
      if (this.statisticsData.sex === 'm') {
        this.sex = '남성'
      } else {
        this.sex = '여성'
      }
    }
  },
}
</script>

<style>

</style>