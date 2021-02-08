<template>
  <div class="background-creating">
    <div class="component col-md-8 col-11 mx-auto">
      <!-- 챌린지 명, 챌린지 이미지, 챌린지소개 -->
      <div>
        <h4 class="challenge-creating-title">챌린지 정보</h4>
        <b-container>
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
                @input="(data) => GetEditorContent(data)"
              ></text-editor>
            </b-col>
          </b-row>
        </b-container>
      </div>

      <!-- 인증 방법 설명 -->
      <div>
        <br />
        <h4 class="challenge-creating-title">인증 방법 설명</h4>
        <span class="font-size-small"
          ><b-icon icon="dot"></b-icon>챌린지 인증 방법을 적어주세요. 자세할
          수록 좋습니다!</span
        >
        <text-editor
          :props_content="challenge.challenge_certify_contents"
          @input="(data) => GetEditorCertifyContent(data)"
        ></text-editor>
      </div>

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
            class="mx-1"
            style="display:inline-block;"
            @delete="deleteTag(index)"
          ></Tag>
        </div>
      </div>

    <div class="align-center">
      <b-button >수정완료</b-button>
      </div>
    </div>
  </div>
</template>

<script>
import TextEditor from "@/components/ChallengeCreating/TextEditor.vue";
import Tag from '@/components/ChallengeCreating/Tag.vue';

import "@/assets/css/ChallengeCreating/challengecreating.css";

import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "ChallengeEdit",
  components: {
    TextEditor,
    Tag,
  },
  data() {
    return {
      challenge_id: 0,
      input:'',
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
    };
  },
  async created() {
    this.challenge_id = this.$route.params.challenge_id;
    await axios
      .get(`${SERVER_URL}/challenge/${this.challenge_id}`)
      .then(({ data }) => {
          console.log(data);
          this.challenge = data;
      })
      .catch(() => {
        alert("챌린지 정보를 불러오지 못했습니다.");
      });

    this.checkTagListLength();
  },
  methods: {
    GetEditorContent: function(data) {
      this.challenge.challenge_contents = data;
    },
    GetEditorCertifyContent: function(data) {
      this.challenge.challenge_certify_contents = data;
    },
    // 태그 추가
    addTag: function() {
      this.input = this.input.replace(' ', '');
      if (this.input != '') {
        this.challenge.tagList.unshift(this.input);
        this.input = '';
      }
      this.checkTagListLength();
    },

    // 태그 삭제
    deleteTag: function(index) {
      this.challenge.tagList.splice(index, 1);
      this.checkTagListLength();
    },

    checkTagListLength:function(){
        console.log(this.challenge.tagList.length);
        if (this.challenge.tagList.length >= 5) {
        document.getElementById('tag-input').readOnly = true;
      } else {
        document.getElementById('tag-input').readOnly = false;
      }
    }
  },
  computed: {
    challengeTitleState() {
      if (this.challenge.challenge_title.length == 0) return null;
      return this.challenge.challenge_title.length <= 20;
    },
  },
};
</script>
