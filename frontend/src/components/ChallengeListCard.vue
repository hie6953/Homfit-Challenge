<template>
  <!-- <div>
    <b-card
      title="Card Title"
      img-src="https://picsum.photos/600/300/?image=25"
      img-alt="Image"
      img-top
      tag="article"
      style="max-width: 30rem;"
      class="mb-2 shadow"
    >
      <b-card-text>
        Some quick example text to build on the card title and make up the bulk
        of the card's content.
      </b-card-text>

      <b-button href="#" variant="primary">Go somewhere</b-button>
    </b-card>
  </div> -->

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
        <span id="card-title">땅끄</span>
        <br />
        <img class="card-user-image" src="@/assets/NavBar/anonimous_user.png" />
        <span id="card-user-nick-name">땅끄</span>
        <br />
        <span id="card-day">월/화/수/목/금/토/일</span>
        <span id="card-period">30일</span>
      </div>
      <hr class="challenge-list-card-hr" />
      <div class="challenge-list-card-footer">
        <span>20명 참여중</span>
      </div>
    </b-card>
  </div>
</template>

<script>
// const dayList = ['', '월', '화', '수', '목', '금', '토', '일'];
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
  props: {},
};
</script>
