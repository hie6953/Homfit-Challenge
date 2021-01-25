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
        <span id="card-title">땅끄부부</span>
        <br />
        <img class="card-user-image" src="@/assets/NavBar/anonimous_user.png" />
        <span id="card-user-nick-name">땅끄부부</span>
        <br />
        <span id="card-day">월/수/금</span>
        <span id="card-period">7일</span>
      </div>
      <hr class="challenge-list-card-hr" />
      <div class="challenge-list-card-footer">
        <span>20명 참여중</span>
      </div>
    </b-card>
  </div>
</template>

<script>
export default {
  props: {
    challenge: Object,
  },
};
</script>
