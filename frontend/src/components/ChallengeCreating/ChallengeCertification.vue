<template>
  <div>
    <h4 class="challenge-creating-title">챌린지 인증 방법</h4>

    <!-- 인증 방법 설명 -->
    <b-row class="challenge-creating-row">
      <b-col sm="3" class="align-center">
         <span class="require-icon">*</span>
        <span>인증 방법 설명</span>
      </b-col>
      <b-col sm="9">
        <text-editor
          :props_content="challenge_certify_contents"
          @input="(data) => GetEditorContent(data)"
        ></text-editor>
        <span class="font-size-small">
          <b-icon icon="dot"></b-icon>챌린지 인증 방법을 적어주세요. 자세할수록
          좋습니다!
        </span>
      </b-col>
    </b-row>

    <!-- 인증 예시 등록 -->
    <b-row class="challenge-creating-row">
      <b-col sm="3" class="align-center">
        <span>좋은 인증샷</span>
      </b-col>
      <b-col sm="9">
        <image-uploader
          :props_challenge_img="props_good_img"
          props_default_img="https://homfitimage.s3.ap-northeast-2.amazonaws.com/182165c5919612baffdfcd8091cfe7bc"
          @imageUploaded="GoodImageUploaded"
        ></image-uploader>
      </b-col>
    </b-row>

    <b-row class="challenge-creating-row">
      <b-col sm="3" class="align-center">
        <span>나쁜 인증샷</span>
      </b-col>
      <b-col sm="9">
        <image-uploader
          :props_challenge_img="props_bad_img"
          props_default_img="https://homfitimage.s3.ap-northeast-2.amazonaws.com/14b28a4957875f43d9f3aed789d2d520"
          @imageUploaded="BadImageUploaded"
        ></image-uploader>
      </b-col>
    </b-row>

    <!-- 인증 수단 -->
    <div class="challenge-certification-way align-center">
      <h4 class="challenge-creating-title">인증 수단 선택</h4>
      <div>
        <input type="radio" :value="1" v-model="only_cam" id="OnlyCam" />
        <label for="OnlyCam"><b-icon icon="camera" scale="1.3"></b-icon></label>
        <br />
        <span>카메라만 사용</span>
      </div>
      <div>
        <input type="radio" :value="0" v-model="only_cam" id="NotOnlyCam" />
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
import ImageUploader from "../ImageUploader.vue";
import TextEditor from "./TextEditor.vue";
export default {
  components: {
    TextEditor,
    ImageUploader,
  },
  props: {
    props_challenge_certify_contents: String,
    props_good_img: Object,
    props_bad_img: Object,
    props_only_cam: Number,
  },
  data() {
    return {
      challenge_certify_contents: "",
      good_img: null,
      bad_img: null,
      only_cam: 0,
      canGoNext: false,
    };
  },
  created() {
    this.challenge_certify_contents = this.props_challenge_certify_contents;
    this.only_cam = this.props_only_cam;
  },
  watch: {
    challenge_certify_contents: function() {
      this.CanGoNext();
    },
    only_cam: function() {
      this.CanGoNext();
    },
    good_img: function() {
      this.CanGoNext();
    },
    bad_img: function() {
      this.CanGoNext();
    },
  },

  methods: {
    GoodImageUploaded: function(image) {
      this.good_img = image;
    },
    BadImageUploaded: function(image) {
      this.bad_img = image;
    },
    GetEditorContent: function(data) {
      this.challenge_certify_contents = data;
    },
    CanGoNext: function() {
      if (
        this.challenge_certify_contents.length > 7
      )
        this.canGoNext = true;
      else this.canGoNext = false;
    },
    // 페이지 이동
    PrevPage: function() {
      this.$emit("PrevPage", this.challenge_certify_contents,this.good_img,this.bad_img, this.only_cam);
    },
    NextPage: function() {
      this.$emit("NextPage", this.challenge_certify_contents,this.good_img,this.bad_img, this.only_cam);
    },
  },
};
</script>
