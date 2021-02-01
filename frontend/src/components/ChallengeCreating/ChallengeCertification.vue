<template>
  <div>
    <!-- 인증 방법 설명 -->
    <div>
      <br />
      <h4 class="challenge-creating-title">인증 방법 설명</h4>
      <textarea
        v-model="challenge_certify_contents"
        cols="30"
        rows="10"
      ></textarea>
    </div>

    <!-- 인증 수단 -->
    <div class="challenge-certification-way align-center">
      <h4 class="challenge-creating-title">인증 수단 선택</h4>
      <div>
        <input type="radio" value="1" v-model="only_cam" id="OnlyCam" />
        <label for="OnlyCam"><b-icon icon="camera" scale="1.3"></b-icon></label>
        <br />
        <span>카메라만 사용</span>
      </div>
      <div>
        <input type="radio" value="0" v-model="only_cam" id="NotOnlyCam" />
        <label for="NotOnlyCam"
          ><b-icon icon="camera" scale="1.3"></b-icon> &
          <b-icon icon="card-image" scale="1.3"></b-icon
        ></label>
        <br />
        <span>카메라, 갤러리 사용</span>
      </div>
    </div>

    <!-- 페이지 이동 버튼 -->
    <div class="mt-4">
      <b-button class="prev-page-button" @click="PrevPage()"
        ><b-icon icon="arrow-left-circle-fill" scale="1.5"></b-icon
        ><span>기간</span></b-button
      >
      <b-button
        class="next-page-button"
        :class="{ disabled: !canGoNext }"
        @click="(e) => canGoNext && NextPage()"
        aria-disabled="true"
        ><span>태그</span
        ><b-icon icon="arrow-right-circle-fill" scale="1.5"></b-icon
      ></b-button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    props_challenge_certify_contents: String,
    // props_good_img: Object,
    // props_bad_img: Object,
    props_only_cam: Number,
  },
  data() {
    return {
      challenge_certify_contents: '',
      // good_img: Object,
      // bad_img: Object,
      only_cam: 1,
      canGoNext: false,
    };
  },
  created() {
    this.challenge_certify_contents = this.props_challenge_certify_contents;
    // this.good_img = this.props_good_img;
    // this.bad_img = this.props_bad_img;
    this.only_cam = this.props_only_cam;
  },
  watch: {
    only_cam: function() {
      this.CanGoNext();
    },
  },

  methods: {
    CanGoNext: function() {
      this.canGoNext = true;
    },
    // 페이지 이동
    PrevPage: function() {
      this.$emit('PrevPage', this.challenge_certify_contents, this.only_cam);
    },
    NextPage: function() {
      this.$emit('NextPage', this.challenge_certify_contents, this.only_cam);
    },
  },
};
</script>
