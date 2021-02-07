<template>
  <div class="main-page-list-carousel-container">
    <!-- 웹  캐러셀 -->
    <div class="card-carousel-wrapper carousel-web">
      <div class="card-carousel--nav__left" @click="moveCarousel(-1)" :disabled="atHeadOfList"></div>
      <div class="card-carousel">
        <div class="card-carousel--overflow-container">
          <div class="card-carousel-cards" :style="{ transform: 'translateX' + '(' + currentOffset + 'px' + ')'}">
            <div class="card-carousel-card col-4" v-for="(challenge, index) in mainPageList" :key="index" @click="ChallengeMoreInfo">
              <b-card
                img-src="https://placehold.it/290x170"
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
                  <span id="card-user-nick-name">{{ challenge.nick_name }}</span>
                  <br />
                  <span id="card-day">{{ GetDayList(challenge) }}</span>
                  <span id="card-period">{{ challenge.period }}일</span>
                </div>
                <hr class="challenge-list-card-hr" />
                <div class="challenge-list-card-footer">
                  <span>{{ challenge.people }}명 참여중</span>
                </div>
              </b-card>
            </div>
          </div>
        </div>
      </div>
      <div class="card-carousel--nav__right" @click="moveCarousel(1)" :disabled="atEndOfList"></div>
    </div>
    <!-- 모바일 캐러셀 -->
    <flickity ref="flickity" :options="flickityOptions" class="carousel-mobile">
      <div class="carousel-cell" v-for="(challenge, index) in items" :key="index">
          <b-card
            img-src="https://placehold.it/290x170"
            img-alt="Image"
            img-top
            style="width: 290px;"
            class="mb-2 shadow"
          >
            <div class="challenge-list-card-body">
              <span id="card-title">{{ challenge.challenge_title }}</span>
              <br />
              <img class="card-user-image" src="@/assets/NavBar/anonimous_user.png" />
              <span id="card-user-nick-name">{{ challenge.nick_name }}</span>
              <br />
              <span id="card-day">{{ GetDayList(challenge) }}</span>
              <span id="card-period">{{ challenge.period }}일</span>
            </div>
            <hr class="challenge-list-card-hr" />
            <div class="challenge-list-card-footer d-flex flex-wrap justify-content-between">
              <span class="footer-content col-8 ">{{ challenge.people }}명 참여중</span>
              <button class="btn footer-content col-3" @click="ChallengeMoreInfo(challenge.challenge_id)">more</button>
            </div>
          </b-card>
      </div>
    </flickity>
  </div>
</template>

<script>
const dayList = ['', '월', '화', '수', '목', '금', '토', '일'];

import "@/assets/css/mainPageList.css"
import Flickity from 'vue-flickity'

export default {
  name: 'MainPageList',
  components: {
    Flickity,
  },
  props: {
    mainPageList: Array,
  },
  data() {
    return {
      currentOffset: 0,
      windowSize: 3,
      paginationFactor: 370,
      items: [
        { "challenge_id": 0, "challenge_title": "", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 0, "check_date": 0, "period": 0, "nick_name": "", "people": 0, "kind": 0, "daylist_string": "[]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 0, "challenge_title": "", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 0, "check_date": 0, "period": 0, "nick_name": "", "people": 0, "kind": 0, "daylist_string": "[]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 0, "challenge_title": "", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 0, "check_date": 0, "period": 0, "nick_name": "", "people": 0, "kind": 0, "daylist_string": "[]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 0, "challenge_title": "", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 0, "check_date": 0, "period": 0, "nick_name": "", "people": 0, "kind": 0, "daylist_string": "[]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 0, "challenge_title": "", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 0, "check_date": 0, "period": 0, "nick_name": "", "people": 0, "kind": 0, "daylist_string": "[]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 0, "challenge_title": "", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 0, "check_date": 0, "period": 0, "nick_name": "", "people": 0, "kind": 0, "daylist_string": "[]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 0, "challenge_title": "", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 0, "check_date": 0, "period": 0, "nick_name": "", "people": 0, "kind": 0, "daylist_string": "[]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 0, "challenge_title": "", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 0, "check_date": 0, "period": 0, "nick_name": "", "people": 0, "kind": 0, "daylist_string": "[]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 0, "challenge_title": "", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 0, "check_date": 0, "period": 0, "nick_name": "", "people": 0, "kind": 0, "daylist_string": "[]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 0, "challenge_title": "", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 0, "check_date": 0, "period": 0, "nick_name": "", "people": 0, "kind": 0, "daylist_string": "[]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 0, "challenge_title": "", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 0, "check_date": 0, "period": 0, "nick_name": "", "people": 0, "kind": 0, "daylist_string": "[]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 0, "challenge_title": "", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 0, "check_date": 0, "period": 0, "nick_name": "", "people": 0, "kind": 0, "daylist_string": "[]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 0, "challenge_title": "", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 0, "check_date": 0, "period": 0, "nick_name": "", "people": 0, "kind": 0, "daylist_string": "[]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 0, "challenge_title": "", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 0, "check_date": 0, "period": 0, "nick_name": "", "people": 0, "kind": 0, "daylist_string": "[]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 0, "challenge_title": "", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 0, "check_date": 0, "period": 0, "nick_name": "", "people": 0, "kind": 0, "daylist_string": "[]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 0, "challenge_title": "", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 0, "check_date": 0, "period": 0, "nick_name": "", "people": 0, "kind": 0, "daylist_string": "[]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 0, "challenge_title": "", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 0, "check_date": 0, "period": 0, "nick_name": "", "people": 0, "kind": 0, "daylist_string": "[]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 0, "challenge_title": "", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 0, "check_date": 0, "period": 0, "nick_name": "", "people": 0, "kind": 0, "daylist_string": "[]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 0, "challenge_title": "", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 0, "check_date": 0, "period": 0, "nick_name": "", "people": 0, "kind": 0, "daylist_string": "[]", "dayList": null, "tagList": null, "bodyList": null },
        { "challenge_id": 0, "challenge_title": "", "challenge_contents": null, "challenge_img": "", "challenge_certify_contents": null, "good_img": null, "bad_img": null, "day_certify_count": 0, "only_cam": 0, "start_date": null, "end_date": null, "make_date": null, "make_uid": null, "fit_id": 0, "check_date": 0, "period": 0, "nick_name": "", "people": 0, "kind": 0, "daylist_string": "[]", "dayList": null, "tagList": null, "bodyList": null },
      ],
      // 모바일 캐러셀
      flickityOptions: {
        initialIndex: 3,
        prevNextButtons: false,
        pageDots: false,
        wrapAround: true
      }
    }
  },
  watch: {
    mainPageList() {
      this.updateItems()
    }
  },
  computed: {
    // 웹 캐러셀 리스트 양 끝에 갔을 때 버튼 비활성화
    atEndOfList() {
      return this.currentOffset <= (this.paginationFactor * -1) * (this.mainPageList.length - this.windowSize);
    },
    atHeadOfList() {
      return this.currentOffset === 0;
    },
  },
  methods: {
    // 웹 캐러셀 움직임
    moveCarousel(direction) {
      if (direction === 1 && !this.atEndOfList) {
        this.currentOffset -= this.paginationFactor;
      } else if (direction === -1 && !this.atHeadOfList) {
        this.currentOffset += this.paginationFactor;
      }
    },
    GetDayList: function(challenge) {
      if (challenge.daylist_string != null) {
        let list = challenge.daylist_string
          .substring(1, challenge.daylist_string.length - 1)
          .split(',');
        let temp = new Array(list.length);
        for (let index = 0; index < list.length; index++) {
          temp[index] = dayList[parseInt(list[index].replace(' ', ''))];
        }
        return temp.join('/');
      }
      return '';
    },
    updateItems() {
      this.items = this.mainPageList
    },
    ChallengeMoreInfo:function(challenge_id){
      this.$router.push(`/challenge-more-info/${challenge_id}`);
    },
  }
}
</script>

<style scoped>

</style>