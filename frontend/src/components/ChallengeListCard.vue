<template>
  <div>
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
        <span id="card-user-nick-name">{{ challenge.make_uid }}</span>
        <br />
        <span id="card-day">{{ GetDayList() }}</span>
        <span id="card-period">{{ challenge.period }}일</span>
      </div>
      <hr class="challenge-list-card-hr" />
      <div class="challenge-list-card-footer">
        <span>20명 참여중</span>
      </div>
    </b-card>
  </div>
</template>

<script>
const dayList = ['', '월', '화', '수', '목', '금', '토', '일'];
// const bodyList = [
//   '',
//   '전신',
//   '상체',
//   '하체',
//   '가슴',
//   '팔',
//   '복부',
//   '엉덩이',
//   '다리',
//   '기타',
// ];
export default {
  props: {
    challenge: Object,
  },
  methods: {
    GetDayList() {
      let list = this.challenge.daylist_string;
      for (
        let index = 0;
        index < this.challnege.daylist_string.length;
        index++
      ) {
        list[index] = dayList[this.challnege.daylist_string[index]];
      }
      return list.join('/');
    },
  },
};
</script>
