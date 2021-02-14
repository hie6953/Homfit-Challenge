<template>
  <!-- axios -->
  <!-- <div class="gallery-item">
    <img
      src="https://images.unsplash.com/photo-1426604966848-d7adac402bff?w=500&h=500&fit=crop"
      class="responsive-image"
      @click="ChallengeMoreInfo"
    />
    <div class="gallery-item-info">
      <ul>
        <li class="gallery-item-likes">
          <span class="visually-hidden">{{ feedchat.challenge_title }}</span>
        </li>
      </ul>
    </div>
  </div> -->

  <div class="feedchat-border">
    <div class="feedchat-card">
      <div class="feedchat-card-header">
        <img
          src="@/assets/NavBar/anonimous_user.png"
          class="feedchat-card-user-image"
        />
        <div class="feedchat-card-user-name">이건내이름이얌</div>
        <!-- <div class="feed-delete">
          <b-button class="feed-delete-btn">
            <b-icon icon="trash"></b-icon>
          </b-button>
        </div> -->
        <div class="feedchat-card-time">
          2021년 02월 11일<i class="fa fa-globe"></i>
        </div>
      </div>

      <!-- 이미지 -->
      <div class="feedchat-card-img-information">
        <img
          src="http://www.seriouseats.com/recipes/assets_c/2014/09/20140918-jamie-olivers-comfort-food-insanity-burger-david-loftus-thumb-1500xauto-411285.jpg"
          class="feedchat-card-image"
        />
      </div>

      <!-- 챌린지바로가기? or 내용? -->
      <div class="feedchat-card-information">
        <p>왜 벌써 3시야...</p>
      </div>

      <!-- 하단바_좋아요,댓글,바로가기,신고 -->

      <hr class="feedchat-card-hr" />
      <div class="col-12 feedchat-icons">
        <!-- <a href="#"><span class="feedchat-card-button-left">좋아요</span></a> -->
        <b-button class="feedchat-card-button-left">
          <b-icon icon="heart" variant="warning" aria-hidden="true"></b-icon>
          좋아요
        </b-button>

        <router-link to="/feedcardlist">
          <b-button class="feedchat-card-button-left">
            <b-icon
              icon="chat-fill"
              variant="warning"
              aria-hidden="true"
            ></b-icon>
            댓글
          </b-button>
        </router-link>

        <b-button class="feedchat-card-button-left">
          <b-icon
            icon="arrow-right-circle"
            variant="warning"
            aria-hidden="true"
          ></b-icon>
          바로가기
        </b-button>

        <b-button class="feedchat-card-button-right">
          <b-icon icon="bell" variant="warning" aria-hidden="true"></b-icon>
          신고
        </b-button>
      </div>

      <!-- 댓글쓰기 -->
      <hr class="feedchat-card-hr" />
      <div class="feedchat-card-comments">
        <div
          class="Comment-section"
          v-for="(comment, index) in commentList"
          :key="index"
        >
          <img :src="comment.user_img" class="commenter-image" height="32px" />

          <div class="feedchat-div-tmp">
            <div class="user">{{ comment.nick_name }}</div>
            <div class="comment">{{ comment.comment.contents }}</div>
            <div class="comment-time">
              {{ comment.comment.comment_regist_date }}
            </div>
          </div>
        </div>
      </div>
      <div class="write-comment">
        <input type="text" class="comment-input-box" v-model="contents" />
        <input
          type="button"
          value="작성"
          class="comment-submit-btn"
          @click="FeedCommentWrite"
        />
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/FeedCard/feedcardchat.css';
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import { mapGetters } from 'vuex';

export default {
  name: 'FeedCardChat',
  props: {
    feed: Object,
  },
  data: function() {
    return {
      contents: '',
      commentList: [],
    };
  },
  created() {
    this.LoadComments();
  },
  methods: {
    FeedCommentWrite() {
      axios
        .post(`${SERVER_URL}/comment/create`, {
          uid: this.getUserUid,
          contents: this.contents,
          feed_id: this.feed.feed_id,
        })
        .then(({ data }) => {
          console.log(data);
          this.feedList = data;
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },

    LoadComments() {
      axios
        .get(`${SERVER_URL}/search/${this.feed.feed_id}`)
        .then(({ data }) => {
          console.log(data);
          this.commentList = data.list;
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },
  },
  computed: {
    ...mapGetters(['getUserUid']),
  },
};
</script>
