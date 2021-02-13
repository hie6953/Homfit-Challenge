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
          <span class="visually-hidden">{{ feed.challenge_title }}</span>
        </li>
      </ul>
    </div>
  </div> -->

  <div class="feed-border">
    <div class="feed-card">
      <div class="feed-card-header">
        <img
          src="@/assets/NavBar/anonimous_user.png"
          class="feed-card-user-image"
        />
        <div class="feed-card-user-name">이건내이름이얌</div>
        <div class="feed-card-time">
          2021년 02월 11일<i class="fa fa-globe"></i>
        </div>
      </div>

      <!-- 이미지 -->
      <div class="feed-card-img-information">
        <img
          src="http://www.seriouseats.com/recipes/assets_c/2014/09/20140918-jamie-olivers-comfort-food-insanity-burger-david-loftus-thumb-1500xauto-411285.jpg"
          class="feed-card-image"
        />
      </div>

      <!-- 챌린지바로가기? or 내용? -->
      <div class="feed-card-information">
        <p>왜 벌써 3시야...</p>
      </div>

      <!-- 하단바_좋아요,댓글,바로가기,신고 -->

      <hr class="feed-card-hr" />
      <div class="feed-icons">
        <!-- <a href="#"><span class="feed-card-button-left">좋아요</span></a> -->
        <b-button class="feed-card-button-left">
          <b-icon icon="heart" variant="warning" aria-hidden="true"></b-icon>
          좋아요
        </b-button>

        <router-link to="/feedcardlistchat">
          <b-button class="feed-card-button-left">
            <b-icon icon="chat" variant="warning" aria-hidden="true"></b-icon>
            댓글
          </b-button>
        </router-link>

        <b-button class="feed-card-button-left">
          <b-icon
            icon="arrow-right-circle"
            variant="warning"
            aria-hidden="true"
          ></b-icon>
          바로가기
        </b-button>

        <b-button v-b-modal.modal-scrollable class="feed-card-button-right">
          <b-icon icon="bell" variant="warning" aria-hidden="true"></b-icon>
          신고
        </b-button>

        <!-- <b-button v-b-modal.modal-scrollable class="feed-card-button-right">
          <b-icon icon="bell" variant="warning" aria-hidden="true"></b-icon>
          신고
        </b-button>
        <b-modal id="modal-scrollable" scrollable title="인증샷 신고">
          <div class="declaration-modal">
            <p class="declaration-info">
              인증샷은 다수에게 신고를 당하면 자동으로 삭제됩니다. 또한, 스탭이
              악의적이라고 판단되는 인증샷의 경우 레드카드가 발급될 수 있습니다.
            </p>

            <b-form-radio
              class="declaration-radio"
              v-model="declarationtype"
              name="some-radios"
              value="1"
              >인증샷 무효 신고
            </b-form-radio>
            <b-form-radio
              class="declaration-radio"
              v-model="declarationtype"
              name="some-radios"
              value="2"
              >악성 유저 신고</b-form-radio
            >
          </div>
        </b-modal> -->
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/FeedCard/feedcard.css';

export default {
  name: 'FeedCard',
  props: {
    feed: Object,
  },
  data: function() {
    return {};
  },
  methods: {},
};
</script>
