<template>
  <div class="challenge-list-card-total">
    <!-- 챌린지 리스트 카드 -->
    <b-card
      img-src="https://picsum.photos/600/300/?image=25"
      img-alt="Image"
      img-top
      no-body
      tag="article"
      style="max-width: 30rem;"
      class="mb-2 shadow"
      @click="ChallengeMoreInfo"
    >
      <div class="bookmark-icon bookmark-disabled">
        <b-icon
          id="bookmarkIcon"
          icon="bookmark-fill"
          scale="1.6"
          class="bookmark-btn"
          @click="DeleteBookmarkBtn"
        ></b-icon>
      </div>
      <div class="challenge-list-card-body">
        <div id="list-card-title" ref="list_card_title" :class="{ellipsis:titleOversize}">
          <span ref="list_card_title_content" >{{
            challenge.challenge_title
          }}</span>
        </div>
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

    <!-- <b-card
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
        <span id="card-day">{{ GetDayList() }}</span>
        <span id="card-period">22일</span>
      </div>
      <hr class="challenge-list-card-hr" />
      <div class="challenge-list-card-footer">
        <span>22명 참여중</span>
      </div>
    </b-card> -->
  </div>
</template>

<script>
import "@/assets/css/challengeListCard.css";
const dayList = ["", "월", "화", "수", "목", "금", "토", "일"];

export default {
  props: {
    challenge: Object,
    isfromBookmark: Number,
  },
  data() {
    return {
      titleOversize : false,
    }
  },
  created() {
    if (this.isfromBookmark === 1) {
      const bookmark = document.querySelector('.bookmark-disabled')
      bookmark.classList.remove('bookmark-disabled')
    }
  },
  mounted() {
    let titleDiv = this.$refs.list_card_title.offsetWidth;
    let titleSpan = this.$refs.list_card_title_content.offsetWidth;
    if (titleDiv < titleSpan) {
     this.titleOversize = true;
    }
  },
  methods: {
    // 날짜 숫자 -> 요일로 변경
    GetDayList: function() {
      if (this.challenge.daylist_string != null) {
        let list = this.challenge.daylist_string
          .substring(1, this.challenge.daylist_string.length - 1)
          .split(",");
        let temp = new Array(list.length);
        for (let index = 0; index < list.length; index++) {
          temp[index] = dayList[parseInt(list[index].replace(" ", ""))];
        }
        return temp.join("/");
      }
      return "";
    },
    ChallengeMoreInfo: function() {
      this.$emit("moreInfo", this.challenge.challenge_id);
    },
    DeleteBookmarkBtn() {
      console.log(this.challenge.challenge_id)
      this.$emit("deleteBookmark", this.challenge.challenge_id);
    }
  },
};
</script>
