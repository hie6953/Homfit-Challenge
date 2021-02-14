<template>
  <div class="challenge-list-card-total">
    <!-- 챌린지 리스트 카드 -->
    <div class="bookmark-icon bookmark-disabled">
      <b-icon
        id="bookmarkIcon"
        icon="bookmark-fill"
        scale="1.6"
        class="bookmark-btn"
        @click="DeleteBookmarkBtn"
      ></b-icon>
    </div>
    <div class="cancel-icon cancel-disabled btn-sm btn-warning">
      <div
        class="challengeManage-btn"
        @click="CancelChallengeManageBtn"
      >취소</div>
    </div>
    <div class="delete-icon delete-disabled btn-sm btn-warning">
      <div
        class="challengeManage-btn"
        @click="DeleteChallengeManageBtn"
      >삭제</div>
    </div>
    <b-card
      :img-src="image"
      img-alt="Image"
      img-height="150"
      img-top
      no-body
      tag="article"
      style="max-width: 30rem"
      class="mb-2 shadow"
      @click="ChallengeMoreInfo"
    >
      <div class="challenge-list-card-body">
        <div id="list-card-title" ref="list_card_title">
          <span ref="list_card_title_content">{{
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
  </div>
</template>

<script>
import "@/assets/css/challengeListCard.css";
const dayList = ["", "월", "화", "수", "목", "금", "토", "일"];

export default {
  props: {
    challenge: Object,
    isfromBookmark: Number,
    isfromChallengeManage: Number,
    cancelActive: Number,
    deleteActive: Number,
  },
  data() {
    return {
      image: "",
    };
  },
  created() {
    if (this.challenge.challenge_img != "") {
      this.image = this.challenge.challenge_img;
    } else {
      this.image = "https://picsum.photos/600/300/?image=25";
    }
  },
  watch: {
    cancelActive() {
      this.CancelUpdate()
    },
    deleteActive() {
      this.DeleteUpdate();
    },
  },
  mounted() {
    if (this.isfromBookmark === 1) {
      const bookmark = document.querySelector(".bookmark-disabled");
      bookmark.classList.remove("bookmark-disabled");
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
      this.$emit("deleteBookmarkBtn", this.challenge.challenge_id);
    },
    CancelChallengeManageBtn() {
      this.$emit("cancelChallengeManage", this.challenge.challenge_id);
    },
    DeleteChallengeManageBtn() {
      this.$emit("deleteChallengeManage", this.challenge.challenge_id);
    },
    CancelUpdate() {
      // 카테고리가 진행전 일때만 삭제 버튼 활성화
      const challengeManage = document.querySelectorAll('.cancel-icon')
      for (let i = 0; i<challengeManage.length; i++) {
        if (this.cancelActive === 1) {
          challengeManage[i].classList.remove('cancel-disabled')
        } else {
          challengeManage[i].classList.add('cancel-disabled')
        }
      }
    },
    DeleteUpdate() {
      // 카테고리가 진행전 일때만 삭제 버튼 
      const challengeManage = document.querySelectorAll('.delete-icon')
      for (let i = 0; i<challengeManage.length; i++) {
        if (this.deleteActive === 1) {
          challengeManage[i].classList.remove('delete-disabled')
        } else {
          challengeManage[i].classList.add('delete-disabled')
        }
      }
    },
  }
}
</script>
