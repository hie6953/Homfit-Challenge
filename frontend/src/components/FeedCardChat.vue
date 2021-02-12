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
      <div class="feedchat-icons">
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
        <div class="Comment-section">
          <img
            src="@/assets/NavBar/anonimous_user.png"
            class="commenter-image"
            height="32px"
          />
          <div class="feedchat-div-tmp">
            <div class="user">C107</div>
            <div class="comment">하잉</div>
            <div class="comment-time">20 h</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/FeedCard/feedcardchat.css';

export default {
  name: 'FeedCardChat',
  props: {
    feed: Object,
  },
  data: function() {
    return {};
  },
  methods: {},
};
</script>
