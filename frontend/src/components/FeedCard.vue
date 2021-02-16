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
        <img :src="feed.user_img" class="feed-card-user-image" />
        <div class="feed-card-user-name">{{ feed.nick_name }}</div>
        <div class="feed-card-time">
          {{ getFormatDate(feed.register_date) }}<i class="fa fa-globe"></i>
        </div>
      </div>

      <!-- 이미지 -->
      <!-- @/assets/NavBar/anonimous_user.png -->
      <!-- src="http://www.seriouseats.com/recipes/assets_c/2014/09/20140918-jamie-olivers-comfort-food-insanity-burger-david-loftus-thumb-1500xauto-411285.jpg" -->

      <div class="feed-card-img-information">
        <img :src="feed.feed_picture" class="feed-card-image" />
      </div>
      <!-- <div class="thumbnail-wrappper">
        <div class="feed-card-img-information">
          <img
            src="http://www.seriouseats.com/recipes/assets_c/2014/09/20140918-jamie-olivers-comfort-food-insanity-burger-david-loftus-thumb-1500xauto-411285.jpg"
            class="feed-card-image"
          />
        </div>
      </div> -->

      <!-- 챌린지바로가기? or 내용? -->
      <div class="feed-card-information">
        <p>{{ feed.feed_contents }}</p>
      </div>

      <!-- 하단바_좋아요,댓글,바로가기,신고 -->

      <hr class="feed-card-hr" />
      <div class="col-12 feed-icons">
        <div>
          <!-- <a href="#"><span class="feed-card-button-left">좋아요</span></a> -->

          <div class="feedcard-v">
            <!-- 모바일버튼 -->
            <b-button
              v-if="isMobile"
              class="feed-card-button-left"
              @click="FeedCardLike"
            >
              <b-icon
                v-if="feed.user_liked"
                icon="heart-fill"
                variant="warning"
                aria-hidden="true"
              ></b-icon>
              <b-icon
                v-else
                icon="heart"
                variant="warning"
                aria-hidden="true"
              ></b-icon>
            </b-button>

            <!-- 웹버튼 -->
            <b-button
              v-else
              class="feed-card-button-left"
              @click="FeedCardLike"
            >
              <b-icon
                v-if="feed.user_liked"
                icon="heart-fill"
                variant="warning"
                aria-hidden="true"
              ></b-icon>
              <b-icon
                v-else
                icon="heart"
                variant="warning"
                aria-hidden="true"
              ></b-icon>
              좋아요
            </b-button>
            <span class="howmany">{{ feed.like_count }}</span>
          </div>

          <div class="feedcard-v">
            <b-button
              v-if="isMobile"
              class="feed-card-button-left"
              @click="openC"
            >
              <b-icon icon="chat" variant="warning" aria-hidden="true"></b-icon>
            </b-button>

            <b-button v-else class="feed-card-button-left" @click="openC">
              <b-icon icon="chat" variant="warning" aria-hidden="true"></b-icon>
              댓글
            </b-button>
            <!-- <span class="howmany">{{feed.comment_count}}</span> -->
          </div>

          <!-- <div class="feedcard-v"> -->

          <b-button
            v-if="isMobile"
            class="feed-card-button-left"
            @click="movetoChallengeInfo"
          >
            <b-icon
              icon="arrow-right-circle"
              variant="warning"
              aria-hidden="true"
            ></b-icon>
          </b-button>

          <b-button
            v-else
            class="feed-card-button-left"
            @click="movetoChallengeInfo"
          >
            <b-icon
              icon="arrow-right-circle"
              variant="warning"
              aria-hidden="true"
            ></b-icon>
            챌린지
          </b-button>
          <!-- </div> -->

          <b-button
            v-if="isMobile"
            class="feed-card-button-right"
            @click="openModal"
          >
            <b-icon icon="bell" variant="warning" aria-hidden="true"></b-icon>
          </b-button>

          <b-button v-else class="feed-card-button-right" @click="openModal">
            <b-icon icon="bell" variant="warning" aria-hidden="true"></b-icon>
            신고
          </b-button>

          <!-- <button type="button" class="btm_image" id="img_btn">
          <img src="@/assets/NavBar/anonimous_user.png" /> 신고
        </button> -->

          <!-- 컴포넌트 MyModal -->
          <DeclarationModal @close="closeModal" v-if="modal">
            <!-- default 슬롯 콘텐츠 -->

            <div class="declaration-modal">
              <div class="declaration-title">인증샷 신고</div>

              <div class="declaration-info">
                인증샷은 다수에게 신고를 당하면 자동으로 삭제됩니다. <br />
                또한, 스탭이 악의적이라고 판단되는 인증샷의 경우 레드카드가
                발급될 수 있습니다.
              </div>

              <div class="declaration-radio-group">
                <b-form-radio
                  class="declaration-radio"
                  v-model="declarationtype"
                  name="some-radios"
                  value="인증샷 무효 신고"
                  >인증샷 무효 신고
                </b-form-radio>
                <b-form-radio
                  class="declaration-radio"
                  v-model="declarationtype"
                  name="some-radios"
                  value="악성 유저 신고"
                  >악성 유저 신고</b-form-radio
                >
              </div>

              <div class="declaration-contents-align">
                <textarea
                  class="declaration-contents"
                  type="text"
                  placeholder="신고 내용을 입력해주세요. (5자 이상)"
                  v-model="message"
                />
              </div>
            </div>

            <!-- footer 슬롯 콘텐츠 -->
            <template slot="footer">
              <button class="d-modal-btn" @click="doSend">제출</button>
              <button class="d-modal-btn-back" @click="closeModal">취소</button>
            </template>
            <!-- /footer -->
          </DeclarationModal>

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
        <div v-if="openComment">
          <feed-card-chat :feed_id="feed.feed_id"> </feed-card-chat>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/FeedCard/feedcard.css';
import DeclarationModal from './DeclarationModal.vue';
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import { mapGetters } from 'vuex';
import moment from 'moment';
import FeedCardChat from './FeedCardChat.vue';

export default {
  name: 'FeedCard',
  components: {
    DeclarationModal,
    FeedCardChat,
  },
  props: {
    feed: Object,
  },
  data: function() {
    return {
      modal: false,
      message: '',
      declarationtype: '',
      openComment: false,
      isMobile: false,
    };
  },
  mounted() {
    this.onResize();
    window.addEventListener('resize', this.onResize);
  },

  methods: {
    // 모바일
    onResize() {
      this.isMobile = window.innerWidth <= 480;
    },

    // 피드 좋아요 이벤트 함수
    FeedCardLike() {
      // console.log(this.getUserUid);
      // console.log(this.feed.feed_id);
      // console.log(this.feed.user_liked);

      axios
        .put(`${SERVER_URL}/feed/like`, {
          uid: this.getUserUid,
          feed_id: this.feed.feed_id,
          user_liked: this.feed.user_liked, //boolean 값
        })
        .then(({ data }) => {
          console.log(data);
          // this.feedList = data;
          this.feed.user_liked = !this.feed.user_liked;
          this.feed.like_count = data;
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },
    openC() {
      this.openComment = !this.openComment;
    },

    // 신고 모달 관련 함수
    openModal() {
      this.modal = true;
    },
    closeModal() {
      this.modal = false;
    },
    doSend() {
      if (this.message.length >= 5) {
        axios
          .post(`${SERVER_URL}/report`, {
            uid: this.getUserUid,
            feed_id: this.feed.feed_id,
            report_type: this.declarationtype,
            report_content: this.message,
          })
          .then(({ data }) => {
            console.log(data);
            this.closeModal();
            // this.feedList = data;
          })
          .catch(() => {
            alert('에러가 발생했습니다.');
          });
      } else {
        alert('5자 이상 입력해주세요.');
      }

      // if (this.message.length > 5) {
      //   alert(this.message);
      //   this.message = '';
      //   this.closeModal();
      // } else {
      //   alert('5자 이상 입력해주세요.');
      // }
    },

    // 챌린지바로가기이동
    movetoChallengeInfo() {
      this.$router.push(`/challenge-more-info/${this.feed.challenge_id}`);
    },

    getFormatDate(register_date) {
      return moment(new Date(register_date)).format('YYYY.MM.DD HH:mm:ss');
    },
  },
  computed: {
    ...mapGetters(['getUserUid']),
  },
};
</script>
