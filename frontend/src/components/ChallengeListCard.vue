<template>
  <div>
    <!-- 챌린지 리스트 카드 -->
    <b-card
      img-src="https://picsum.photos/600/300/?image=25"
      img-alt="Image"
      img-top
      no-body
      tag="article"
      style="max-width: 30rem;"
      class="mb-2 shadow"
    >
      <div class="challenge-list-card-body">
        <span id="card-title">{{ challenge.challenge_title }}</span>
        <br />
        <img class="card-user-image" src="@/assets/NavBar/anonimous_user.png" />
        <span id="card-user-nick-name">{{ challenge.nick_name }}</span>
        <br />
        <span id="card-day">{{ GetDayList() }}</span>
        <span id="card-period">{{ challenge.period }}일</span>
      </div>
      <hr class="challenge-list-card-hr" />
      <div class="challenge-list-card-footer">
        <span>{{ challenge.people }}명 참여중</span>
      </div>
    </b-card>
  </div>
</template>

<script>
const dayList = ['', '월', '화', '수', '목', '금', '토', '일'];

export default {
  props: {
    challenge: Object,
  },
  methods: {
    // 날짜 숫자 -> 요일로 변경
    GetDayList: function() {
      if (this.challenge.daylist_string != null) {
        let list = this.challenge.daylist_string
          .substring(1, this.challenge.daylist_string.length - 1)
          .split(',');
        let temp = new Array(list.length);
        for (let index = 0; index < list.length; index++) {
          temp[index] = dayList[parseInt(list[index].replace(' ', ''))];
        }
        return temp.join('/');
      }
      return '';
    },
  },
};
</script>
