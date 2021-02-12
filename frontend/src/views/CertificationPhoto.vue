<template>
  <div class="mt-3">
    <div class="row col-12 col-md-8 certificationphoto-container">
      <!-- 인증사진 업로드 안내메시지 -->
      <div class="cert-upload-info">
        <span class="cert-big">인증 사진을 업로드해주세요.</span>
        <br />
        <span class="cert-small"
          >사진과 글로 챌린지 후기를 자세히 작성해주세요.</span
        >
      </div>
      <!-- 업로드 -->
      <b-form-group class="row col-12 col-md-8">
        <!-- 이미지 -->
        <b-form-file
          v-model="form.file"
          :state="Boolean(form.file)"
          placeholder="이미지를 업로드해주세요"
          drop-placeholder="이미지를 업로드해주세요"
          required
          accept=".jpg, .png, .gif, .jpeg"
          @change="previewImage"
          class="certification-image-upload"
        ></b-form-file>
        <div class="certification-image-name">
          파일명: {{ form.file ? form.file.name : '' }}
        </div>
        <div class="">
          <b-img class="cert-upload-image" :src="previewImageData"></b-img>
        </div>

        <!-- 문구 -->
        <b-form-input
          placeholder="예) 땅끄부부 운동 인증샷입니다! 역시 운동하는건 즐거워요 :)"
          class="certification-text-upload"
        >
        </b-form-input>

        <!-- 제출 -->
        <b-button type="submit" class="certification-submit" variant="warning"
          >업로드</b-button
        >
      </b-form-group>
    </div>
  </div>
</template>

<script>
import '../assets/css/certificationphoto.css';

export default {
  name: 'CertificationPhoto',
  components: {},
  data: function() {
    return {
      form: { petName: '', file: '', desc: '' },
      show: true,
      previewImageData: null,
    };
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      alert(JSON.stringify(this.form));
    },
    onReset(evt) {
      evt.preventDefault();
      // Reset
      this.form.petName = '';
      this.form.file = '';
      this.form.desc = '';
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
};
</script>
