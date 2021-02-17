<template>
  <div class="col-12 col-md-8 mx-auto">
    <div class="certificationphoto-container">
      <!-- 인증사진 업로드 안내메시지 -->
      <div class="cert-upload-info">
        <span class="cert-big">인증 사진을 업로드해주세요.</span>
        <br />
        <span class="cert-small"
          >사진과 글로 챌린지 후기를 자세히 작성해주세요.</span
        >
      </div>
      <!-- 업로드 -->
      <!-- :state="Boolean(form.file)" -->
      <b-form-group>
        <!-- 이미지 -->
        <b-form-file
          id="photo"
          v-model="form.file"
          placeholder="이미지를 업로드해주세요"
          drop-placeholder="이미지를 업로드해주세요"
          required
          accept="image/*"
          :capture="only_cam == 1"
          @change="previewImage"
          class="certification-image-upload"
        ></b-form-file>
        <!-- <div class="certification-image-name">
          파일명: {{ form.file ? form.file.name : '' }}
        </div> -->
        <div class="certification-photo-img" v-if="previewImageData != null">
          <div class="centered">
            <b-img class="cert-upload-image" :src="previewImageData"></b-img>
          </div>
        </div>

        <b-form-textarea
          rows="3"
          v-model="form.content"
          placeholder="예) 땅끄부부 운동 인증샷입니다! 역시 운동하는건 즐거워요 :)"
          class="certification-text-upload"
        ></b-form-textarea>

        <!-- 제출 -->
        <div class="align-center">
          <b-button
            type="submit"
            class="certification-submit"
            variant="warning"
            :disabled="form.file == null"
            aria-disabled="true"
            @click="UploadCertification"
            >업로드</b-button
          >
        </div>
      </b-form-group>
    </div>
  </div>
</template>

<script>
import "../assets/css/certificationphoto.css";
import swal from '@/assets/javascript/sweetAlert.js';
import { mapGetters } from "vuex";
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "CertificationPhoto",
  components: {},
  data: function() {
    return {
      form: {
        petName: "",
        file: null,
        //   desc: ''
        content: "",
      },
      show: true,
      previewImageData: null,
      only_cam: 0,
    };
  },
  created() {
    this.only_cam = this.$route.params.only_cam;
  },
  watch:{

  },
  methods: {
    UploadCertification() {
      let frm = new FormData();
      frm.append("imgFile", document.getElementById("photo").files[0]);
      frm.append("maked_uid", this.getUserUid);
      frm.append("challenge_id", this.$route.params.challenge_id);
      frm.append("feed_contents", this.form.content);

      axios
        .post(`${SERVER_URL}/feed/create`, frm, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then(({ data }) => {
          console.log(data);
          if(data == "success"){
            this.$router.push(`/participated/${this.$route.params.challenge_id}`)
          }
        })
        .catch(() => {
          swal.error('오류가 발생했습니다.');
        });
    },

    onSubmit(evt) {
      evt.preventDefault();
    },
    onReset(evt) {
      evt.preventDefault();
      // Reset
      this.form.petName = "";
      this.form.file = "";
      //   this.form.desc = '';
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
    previewImage() {
      var input = event.target;
      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = (e) => {
          this.previewImageData = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
      } else {
        this.previewImageData = null;
      }
    },
  },
  computed: {
    ...mapGetters(["getUserUid"]),
  },
};
</script>
