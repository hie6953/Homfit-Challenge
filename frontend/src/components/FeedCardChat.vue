<template>
  <div class="feedchat-border">
    <!-- 댓글목록 -->
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
          <div class="comment">{{ comment.contents }}</div>
          <div class="comment-time">
            {{ comment.comment_regist_date }}
          </div>
        </div>

        <input
          type="button"
          value="삭제"
          @click="DeleteComments(comment.comment_id)"
        />
      </div>
    </div>

    <!-- 댓글작성 -->
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
</template>

<script>
import '../assets/css/FeedCard/feedcardchat.css';
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import { mapGetters } from 'vuex';

export default {
  name: 'FeedCardChat',
  props: {
    feed_id: Number,
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
    DeleteComments(comment_id) {
      axios
        .delete(`${SERVER_URL}/comment/delete/${comment_id}`)
        .then(({ data }) => {
          console.log(data);
          this.LoadComments();
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },
    FeedCommentWrite() {
      axios
        .post(`${SERVER_URL}/comment/create`, {
          uid: this.getUserUid,
          contents: this.contents,
          feed_id: this.feed_id,
        })
        .then(({ data }) => {
          console.log(data);
          this.LoadComments();
          this.contents = '';
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },

    LoadComments() {
      axios
        .get(`${SERVER_URL}/comment/search/${this.feed_id}`)
        .then(({ data }) => {
          console.log(data);
          this.commentList = data.list;
          console.log(this.commentList);
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
