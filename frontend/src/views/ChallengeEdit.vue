<template>
  <div class="background-creating">
    <div class="component col-md-8 col-11 mx-auto">
      <!-- 챌린지 명, 챌린지 이미지, 챌린지소개 -->
      <div>
        <h4 class="challenge-creating-title">챌린지 정보</h4>
        <b-row class="challenge-creating-row">
          <b-col sm="3" class="align-center">
            <span class="vertical-align-middle">챌린지 이름</span>
          </b-col>
          <b-col sm="9">
            <b-form-input
              id="challenge_title_input"
              type="text"
              :state="challengeTitleState"
              placeholder="챌린지 이름을 입력하세요(1~20자)"
              v-model="challenge.challenge_title"
            ></b-form-input>
            <b-form-invalid-feedback id="challenge_title_input">
              챌린지 이름을 20자 이내로 입력하세요.
            </b-form-invalid-feedback>
          </b-col>
        </b-row>

        <b-row class="challenge-creating-row">
          <b-col sm="3" class="align-center">
            <span class="vertical-align-middle">챌린지 설명</span>
          </b-col>
          <b-col sm="9">
            <text-editor
              :props_content="challenge.challenge_contents"
              :props_change="getChallenge"
              @input="(data) => GetEditorContent(data)"
            ></text-editor>
          </b-col>
        </b-row>

        <b-row class="challenge-creating-row">
          <b-col sm="3" class="align-center ">
            <span>대표 이미지</span>
          </b-col>
          <b-col sm="9">
            <image-uploader
              :props_default_link="challenge.challenge_img"
              props_default_img="https://homfitimage.s3.ap-northeast-2.amazonaws.com/d42ee9bafd0856a5a0b6bd481415f399"
              :props_change="getChallenge"
              @imageUploaded="ImageUploaded"
            ></image-uploader>
          </b-col>
        </b-row>
      </div>

      <!-- 인증 방법 설명 -->
      <h4 class="challenge-creating-title">챌린지 인증 방법</h4>
      <b-row class="challenge-creating-row">
        <b-col sm="3" class="align-center">
          <span class="require-icon">*</span>
          <span>인증 방법 설명</span>
        </b-col>
        <b-col sm="9">
          <text-editor
            :props_content="challenge.challenge_certify_contents"
            :props_change="getChallenge"
            @input="(data) => GetEditorCertifyContent(data)"
          ></text-editor>
          <span class="font-size-small">
            <b-icon icon="dot"></b-icon>챌린지 인증 방법을 적어주세요.
            자세할수록 좋습니다!
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
            :props_default_link="challenge.good_img"
            props_default_img="https://homfitimage.s3.ap-northeast-2.amazonaws.com/182165c5919612baffdfcd8091cfe7bc"
            :props_change="getChallenge"
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
            :props_default_link="challenge.bad_img"
            props_default_img="https://homfitimage.s3.ap-northeast-2.amazonaws.com/14b28a4957875f43d9f3aed789d2d520"
            :props_change="getChallenge"
            @imageUploaded="BadImageUploaded"
          ></image-uploader>
        </b-col>
      </b-row>

      <!-- 인증 수단 -->
      <div class="challenge-certification-way align-center">
        <h4 class="challenge-creating-title">인증 수단 선택</h4>
        <div>
          <input
            type="radio"
            :value="1"
            v-model="challenge.only_cam"
            id="OnlyCam"
          />
          <label for="OnlyCam"
            ><b-icon icon="camera" scale="1.3"></b-icon
          ></label>
          <br />
          <span>카메라만 사용</span>
        </div>
        <div>
          <input
            type="radio"
            :value="0"
            v-model="challenge.only_cam"
            id="NotOnlyCam"
          />
          <label for="NotOnlyCam"
            ><b-icon icon="camera" scale="1.3"></b-icon> &
            <b-icon icon="card-image" scale="1.3"></b-icon
          ></label>
          <br />
          <span>카메라, 갤러리 사용</span>
        </div>
      </div>

      <div>
        <h4 class="challenge-creating-title">챌린지 태그 입력</h4>
        <!-- 태그 입력 input -->
        <div>
          <div class="challenge-period-information">
            <span
              ><b-icon icon="dot"></b-icon>태그는 5개까지 입력할 수
              있습니다.</span
            ><br />
          </div>
          <b-input
            type="text"
            id="tag-input"
            v-model="input"
            @keyup.enter="addTag()"
            @keyup.space="addTag()"
          />
        </div>
        <!-- 태그 목록 -->
        <div class="tag-list">
          <Tag
            v-for="(tag, index) in challenge.tagList"
            :key="`${index}_tag`"
            :content="tag"
            :index="index"
            class="mx-1 tag-list-tags"
            @delete="deleteTag(index)"
          ></Tag>
        </div>
      </div>

      <div class="align-center mt-5">
        <b-button
          id="edit-button"
          class="col-6 py-2"
          :class="{ disabled: !canEdit }"
          @click="(e) => canEdit && ChallengeEdit()"
          >수정완료</b-button
        >
      </div>
    </div>
  </div>
</template>

<script>
import TextEditor from "@/components/ChallengeCreating/TextEditor.vue";
import Tag from "@/components/ChallengeCreating/Tag.vue";
import ImageUploader from "@/components/ImageUploader.vue";

import "@/assets/css/ChallengeCreating/challengecreating.css";
import "@/assets/css/challengeedit.css";

import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "ChallengeEdit",
  components: {
    TextEditor,
    Tag,
    ImageUploader,
  },
  data() {
    return {
      challenge_id: 0,
      input: "",
      getChallenge: false,
      canEdit: false,
      challenge: {
        kind: 0,
        fit_id: 1,
        bodyList: [],
        challenge_title: "",
        challenge_contents: "",
        challenge_img: "",
        start_date: null,
        end_date: null,
        dayList: [],
        day_certify_count: 1,
        challenge_certify_contents: "",
        good_img: "",
        bad_img: "",
        only_cam: 1,
        tagList: [],
        make_date: "",
        make_uid: "",
        check_date: 0,
        period: 0,
      },
      new_challenge_img: "",
      new_good_img: "",
      new_bad_img: "",
    };
  },
  created() {
    this.challenge_id = this.$route.params.challenge_id;
    this.GetChallnege();
    this.CanEdit();
  },
  watch: {
    challenge: {
      deep: true,
      handler() {
        this.CanEdit();
      },
    },
  },
  methods: {
    GetChallnege() {
      axios
        .get(`${SERVER_URL}/challenge/${this.challenge_id}`)
        .then(({ data }) => {
          this.challenge = data.challenge;
          this.getChallenge = !this.getChallenge;

          this.checkTagListLength();
        })
        .catch(() => {
          alert("챌린지 정보를 불러오지 못했습니다.");
        });
    },
    ImageUploaded: function(image) {
      this.new_challenge_img = image;
    },
    GoodImageUploaded: function(image) {
      this.new_good_img = image;
    },
    BadImageUploaded: function(image) {
      this.new_bad_img = image;
    },
    GetEditorContent: function(data) {
      this.challenge.challenge_contents = data;
    },
    GetEditorCertifyContent: function(data) {
      this.challenge.challenge_certify_contents = data;
    },
    CanEdit: function() {
      if (
        this.challenge.challenge_title.length > 0 &&
        this.challenge.challenge_title.length <= 20 &&
        this.challenge.challenge_contents.length > 7 &&
        this.challenge.challenge_certify_contents.length > 7
      ) {
        this.canEdit = true;
      } else {
        this.canEdit = false;
      }
    },
    // 태그 추가
    addTag: function() {
      this.input = this.input.replace(" ", "");
      if (this.input != "") {
        this.challenge.tagList.unshift(this.input);
        this.input = "";
      }
      this.checkTagListLength();
    },

    // 태그 삭제
    deleteTag: function(index) {
      this.challenge.tagList.splice(index, 1);
      this.checkTagListLength();
    },

    checkTagListLength: function() {
      if (this.challenge.tagList == null) {
        //백엔드와의 통신, tagList가 null일 경우를 대비해서 바꿈
        this.challenge.tagList = [];
      }
      if (this.challenge.tagList.length >= 5) {
        document.getElementById("tag-input").readOnly = true;
      } else {
        document.getElementById("tag-input").readOnly = false;
      }
    },

    ChallengeEdit: function() {
      console.log(this.challenge);
      console.log(this.new_challenge_img); //새로 불러오기 : file
      console.log(this.new_good_img); //삭제 : null
      console.log(this.new_bad_img); //손안댐 :undefined

      // undefined 이거나 null인 경우 : default image
      // file이 있을 경우 : 이미지 변경

      if (this.new_challenge_img == null) {
        this.challenge.challenge_img = "https://homfitimage.s3.ap-northeast-2.amazonaws.com/d42ee9bafd0856a5a0b6bd481415f399";
      }
      if (this.new_good_img == null) {
        this.challenge.good_img = "https://homfitimage.s3.ap-northeast-2.amazonaws.com/182165c5919612baffdfcd8091cfe7bc";
      }
      if (this.new_bad_img == null) {
        this.challenge.bad_img = "https://homfitimage.s3.ap-northeast-2.amazonaws.com/14b28a4957875f43d9f3aed789d2d520";
      }

      // // Object To FormData 변환
      var formData = new FormData();

      for (let i in this.challenge) {
        if (i == "dayList") continue;
        formData.append(i, this.challenge[i]);
      }
      formData.append("challengeImgFile", this.new_challenge_img);
      formData.append("goodImgFile", this.new_good_img);
      formData.append("badImgFile", this.new_bad_img);

      // FormData의 key 확인
      // for (let key of formData.keys()) {
      //   console.log(key+" "+formData.get(key));
      // }

      // // FormData의 value 확인
      // for (let value of formData.values()) {
      //   console.log(value);
      // }

      axios
        .put(`${SERVER_URL}/challenge/${this.challenge_id}`, formData, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then(() => {
          alert("챌린지가 수정되었습니다.");
          this.$router.push(`/challenge-more-info/${this.challenge_id}`);
        })
        .catch((error) => {
          console.log(error);
          alert("등록 처리시 에러가 발생했습니다.");
        });

      // axios
      //   .put(`${SERVER_URL}/challenge/${this.challenge_id}`, this.challenge)
      //   .then(() => {
      //     alert("챌린지가 수정되었습니다.");
      //     this.$router.push(`/challenge-more-info/${this.challenge_id}`);
      //   })
      //   .catch(() => {
      //     alert("등록 처리시 에러가 발생했습니다.");
      //   });
    },
  },
  computed: {
    challengeTitleState() {
      if (this.challenge.challenge_title.length == 0) return null;
      return this.challenge.challenge_title.length <= 20;
    },
  },
};
</script>
