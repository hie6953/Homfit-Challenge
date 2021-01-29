<template>
  <div class="mt-3">
    <hr id="hr-top" />

    <!-- 사용자 프로필 div -->
    <div class="mx-auto col-8 user-profile">
      <div class="user-profile-edit">
        <router-link to="/editprofile" class="justtext">
          <b-icon icon="pencil-square" class="edit-icon"></b-icon>
        </router-link>
      </div>
      <div class="user-profile-container">
        <div class="user-profile-image">
          <img class="user-img" src="@/assets/NavBar/anonimous_user.png" />
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
                <span class="justtext">{{ user.point }}포인트</span>
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
    <div class="mx-auto col-8 user-status-todo">
      <!-- 과제 -->
      <div class="user-challenge-status">
        <h3 class="label-challenge-status">챌린지 현황</h3>
        <div class="move-challenge-list">
          <div class="status-list">
            <div class="status">
              <span>참가중</span>
            </div>
            <div class="status-amount">
              <span>{{ challenge.ing }}</span>
            </div>
          </div>
          <div class="status-list">
            <div class="status">
              <span>완료</span>
            </div>
            <div class="status-amount">
              <span>{{ challenge.ing }}</span>
            </div>
          </div>
          <div class="status-list">
            <div class="status">
              <span>개설</span>
            </div>
            <div class="status-amount">
              <span>{{ challenge.ing }}</span>
            </div>
          </div>
        </div>
      </div>
      <div class="vertical-bar-second"></div>
      <!-- 나의 과제 -->
      <div class="user-todo-list"></div>
    </div>

    <!-- 나의피드 -->
    <!-- 나의눈바디 -->
  </div>
</template>

<script>
import '../assets/css/mypage.css';

export default {
  name: 'Mypage',
  data: function() {
    return {
      user: {
        nick_name: '이건내닉네임이야',
        point: '200',
        grade: 'bronze',
      },
      challenge: {
        ing: '3',
        done: '5',
        create: '1',
      },
    };
  },
  methods() {},
};
</script>
