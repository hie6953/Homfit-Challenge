<template>
  <div>
    <b-form-file
      v-model="challenge_img"
      :state="Boolean(challenge_img)"
      accept="image/*"
      :capture="props_only_cam == 1"
      @change="previewImage"
      placeholder="이미지를 올려주세요."
      drop-placeholder="이미지를 내려놓으세요."
    ></b-form-file>
    
    <div class="challenge-main-img" v-if="imageData.length > 0">
      <img class="preview" :src="imageData" />
      <b-button @click="DeleteImage">
        <b-icon icon="x-circle"></b-icon>
      </b-button>
    </div>
  </div>
</template>

<script>
import "@/assets/css/imageuploader.css";
export default {
  props: {
    props_only_cam: {
      type: Number,
      default: 1,
    },
    props_challenge_img:Object,
  },
  data() {
    return {
      challenge_img: null,
      imageData: "",
    };
  },
  created() {
      this.challenge_img = this.props_challenge_img;
  },
  watch:{
      challenge_img:function(){
          this.$emit("imageUploaded",this.challenge_img);
      }
  },
  methods: {
    previewImage: function(event) {
      // Reference to the DOM input element
      var input = event.target;
      // Ensure that you have a file before attempting to read it
      if (input.files && input.files[0]) {
        // create a new FileReader to read this image and convert to base64 format
        var reader = new FileReader();
        // Define a callback function to run, when FileReader finishes its job
        reader.onload = (e) => {
          // Note: arrow function used here, so that "this.imageData" refers to the imageData of Vue component
          // Read image as base64 and set to imageData
          this.imageData = e.target.result;
        };
        // Start the reader job - read file as a data url (base64 format)
        reader.readAsDataURL(input.files[0]);
      }
    },
    DeleteImage: function() {
      this.challenge_img = null;
      this.imageData = "";
    },
  },
};
</script>
