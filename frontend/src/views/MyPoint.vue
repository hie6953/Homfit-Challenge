<template>
  <div class="mt-3">
    <hr id="hr-top" />

    <div class="mx-auto col-8 mypoint-container">
      <div class="mypoint-info">
        <!-- <div class="mypoint-user">{{user.nick_name}}님의 포인트 내역 조회</div> -->
        <div class="mypoint-user">포인트 내역 조회</div>
        <div class="mypoint-total-point">{{ sum }}P</div>
        <!-- <div class="mypoint-total-point">300P</div> -->
      </div>

      <table class="table mypoint-table">
        <template v-if="lists.length != 0">
          <colgroup>
            <col :style="{ width: '15%' }" />
            <col :style="{ width: '50%' }" />
            <col :style="{ width: '10%' }" />
            <col :style="{ width: '25%' }" />
          </colgroup>
          <thead>
            <tr>
              <th scope="col">구분</th>
              <th scope="col">내용</th>
              <th scope="col">적립</th>
              <th scope="col">일자</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(list, index) in lists" :key="index">
              <td>{{ list.title }}</td>
              <td>{{ list.content }}</td>
              <td class="point-text">{{ list.point }}</td>
              <td>{{ getFormatDate(list.point_date) }}</td>
            </tr>
          </tbody>
        </template>

        <tr v-else>
          <td colspan="4">조회된 데이터가 없습니다.</td>
        </tr>
      </table>

      <!-- 임시데이터(후에는 위의 코드로) -->
      <!-- <table class="table mypoint-table">
        <template>
          <colgroup>
            <col :style="{ width: '15%' }" />
            <col :style="{ width: '40%' }" />
            <col :style="{ width: '10%' }" />
            <col :style="{ width: '35%' }" />
          </colgroup>
          <thead>
            <tr>
              <th scope="col">구분</th>
              <th scope="col">내용</th>
              <th scope="col">적립</th>
              <th scope="col">일자</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>임시</td>
              <td>챌린지개설</td>
              <td class="point-text">200</td>
              <td>2021.02.06 21:33:27</td>
            </tr>
            <tr>
              <td>임시</td>
              <td>회원가입</td>
              <td class="point-text">100</td>
              <td>2021.02.03 20:33:27</td>
            </tr>
          </tbody>
        </template>
      </table> -->
    </div>
  </div>
</template>

<script>
import '../assets/css/mypoint.css';
import { mapGetters } from 'vuex';
import moment from 'moment';

import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'MyPoint',
  data: function() {
    return {
      lists: [],
      sum: '',
    };
  },
  created() {
    let uid = this.getUserUid;
    console.log(uid);
    axios
      .post(`${SERVER_URL}/point/inquiry`, { uid })
      .then(({ data }) => {
        if (data.list != null) {
          this.lists = data.list;
        }

        this.sum = data.sum;
        console.log(data);
      })
      .catch(() => {
        alert('에러가 발생했습니다.');
      });
  },
  methods: {
    getFormatDate(point_date) {
      return moment(new Date(point_date)).format('YYYY.MM.DD HH:mm:ss');
    },
  },
  computed: {
    ...mapGetters(['getUserUid']),
  },
};
</script>
