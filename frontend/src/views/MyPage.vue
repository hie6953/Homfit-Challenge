<template>
  <div class="mt-3">
    <hr id="hr-top" />

    <!-- 사용자 프로필 div -->
    <div class="mx-auto col-12 col-md-8 user-profile">
      <div class="user-profile-edit">
        <router-link to="/editprofilepassconfirm" class="justtext">
          <b-icon icon="pencil-square" class="edit-icon"></b-icon>
        </router-link>
      </div>
      <div class="user-profile-container">
        <div v-if="this.user.user_img == ''" class="user-profile-image">
          <img class="user-img" src="@/assets/NavBar/anonimous_user.png" />
        </div>
        <div v-else class="user-profile-image">
          <img class="user-img" :src="this.user.user_img" />
        </div>
        <div class="user-profile-info">
          <div class="user-profile-nickname">
            <span class="nickname-text">{{ user.nick_name }}</span>
          </div>
          <div class="user-profile-grade-point">
            <div class="user-profile-grade">
              <span class="justtext">{{ user.grade }}</span>
            </div>
            <div class="vertical-bar"></div>
            <div class="user-profile-point">
              <router-link to="/mypoint" class="justtext">
                <span class="justtext">{{ sum }}포인트</span>
              </router-link>
            </div>
          </div>
        </div>
      </div>
      <!-- 챌린지 기록, 통계, 뱃지 -->
      <div class="user-profile-link">
        <div class="short-cut">
          <div class="short-cut-item">
            <router-link to="/diary" class="justtext">
              <div class="short-cut-icon">
                <b-icon
                  icon="book-fill"
                  variant="warning"
                  style="width: 22px; height: 22px;"
                ></b-icon>
              </div>
              <div class="short-cut-text">기록</div>
            </router-link>
          </div>
          <div class="short-cut-item">
            <router-link to="/diary" class="justtext">
              <div class="short-cut-icon">
                <b-icon
                  icon="bar-chart-fill"
                  variant="warning"
                  style="width: 22px; height: 22px;"
                ></b-icon>
              </div>
              <div class="short-cut-text">통계</div>
            </router-link>
          </div>
          <div class="short-cut-item">
            <router-link to="/diary" class="justtext">
              <div class="short-cut-icon">
                <b-icon
                  icon="trophy-fill"
                  variant="warning"
                  style="width: 22px; height: 22px;"
                ></b-icon>
              </div>
              <div class="short-cut-text">뱃지</div>
            </router-link>
          </div>
        </div>
      </div>
    </div>

    <!-- 챌린지 현황 -->
    <div class="mx-auto col-12 col-md-8 user-status-todo">
      <!-- 과제 -->
      <div class="col-12 user-challenge-status">
        <h3 class="label-challenge-status">챌린지 현황</h3>
        <div class="col-12 move-challenge-list">
          <div class="status-list-right">
            <div class="status">
              <span>오늘</span>
            </div>
            <router-link to="/challengemanage">
              <div class="status-amount">
                <span>{{ challenge.todayCnt }}</span>
              </div>
            </router-link>
          </div>

          <div class="status-list-both">
            <div class="status">
              <span>참가중</span>
            </div>
            <router-link to="/challengemanage">
              <div class="status-amount">
                <span>{{ ingingCnt }}</span>
              </div>
            </router-link>
          </div>

          <div class="status-list-both">
            <div class="status">
              <span>완료</span>
            </div>
            <router-link to="/challengemanage">
              <div class="status-amount">
                <span>{{ challenge.endCnt }}</span>
              </div>
            </router-link>
          </div>

          <div class="status-list-left">
            <div class="status">
              <span>개설</span>
            </div>
            <router-link to="/challengemanage">
              <div class="status-amount">
                <span>{{ challenge.makeCnt }}</span>
              </div>
            </router-link>
          </div>
        </div>
      </div>
      <!-- <div class="vertical-bar-second"></div> -->
      <!-- 나의 과제 -->
      <!-- <div class="user-todo-list">
        <h3 class="label-today-todo">오늘의 과제</h3>
        <ToDoList />
      </div> -->
    </div>

    <!-- 나의피드 -->
    <div class="mx-auto col-12 col-md-8 myfeeds">
      <div class="mypage-myfeed">
        <h3 class="my-feed-label">나의 피드</h3>
        <div class="my-feed-plus">
          <p class="mb-2">
            <b-icon icon="plus"></b-icon>
            전체보기
          </p>
        </div>
        <div class="grid-feed">
          <MyPageFeed />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import ToDoList from '../components/ToDoList.vue';
import MyPageFeed from '../components/MyPageFeed.vue';
import '../assets/css/MyPage/mypage.css';
import { mapGetters } from 'vuex';

import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'Mypage',
  components: {
    // ToDoList,
    MyPageFeed,
  },
  data: function() {
    return {
      user: {
        nick_name: '',
        point: '',
        grade: '',
        user_img: '',
      },
      challenge: {
        endCnt: '',
        ingCnt: '',
        makeCnt: '',
        preCnt: '',
        todayCnt: '',
      },
      sum: '',
      ingingCnt: '',
    };
  },
  created() {
    let uid = this.getUserUid;
    console.log(uid);
    axios
      .post(
        `${SERVER_URL}/user`,
        { uid }
        // nick_name: this.nick_name,
        // point: this.point,
        // grade: this.grade,
      )
      .then(({ data }) => {
        this.user = data;
        console.log(data);
      })
      .catch(() => {
        alert('에러가 발생했습니다.');
      });

    axios
      .post(`${SERVER_URL}/point/inquiry`, { uid })
      .then(({ data }) => {
        this.sum = data.sum;
      })
      .catch(() => {
        alert('에러가 발생했습니다.');
      });

    axios
      .get(`${SERVER_URL}/challenge/challengeStatus`, {
        params: {
          uid: uid,
        },
      })
      .then(({ data }) => {
        this.challenge = data;
        this.ingingCnt = this.challenge.ingCnt + this.challenge.preCnt;
        console.log(data);
      })
      .catch(() => {
        alert('에러가 발생했습니다.');
      });
  },
  methods: {
    // getUserInfo() {
    //   axios
    //     .post(`${SERVER_URL}/user`, {
    //       uid: getUserUid(),
    //     })
    //     .then(({ data }) => {
    //       //console.log(data);
    //       this.user = data;
    //       //console.log(this.nicknamecheck);
    //     })
    //     .catch(() => {
    //       alert('에러가 발생했습니다.');
    //     });
    // },
  },
  computed: {
    ...mapGetters(['getUserUid']),
  },
};
</script>
