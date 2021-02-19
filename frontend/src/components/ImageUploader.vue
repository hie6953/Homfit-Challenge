<template>
  <div>
    <b-form-file
      v-model="challenge_img"
      accept="image/*"
      @change="previewImage"
      placeholder="이미지를 올려주세요."
      drop-placeholder="이미지를 내려놓으세요."
    ></b-form-file>
    
    <div class="challenge-main-img">
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
    props_challenge_img:Object,
    props_challenge_img_url:String,
    props_default_link:String,
    props_default_img:String,
    props_change:Boolean,
  },
  data() {
    return {
      challenge_img: null,
      imageData: "",
    };
  },
  created() {
    if(this.props_challenge_img != null && this.props_challenge_img.file != null){
       this.challenge_img = this.props_challenge_img.file;
        this.imageData = this.props_challenge_img_url;
    }
    else{
      this.challenge_img = null;
      this.imageData = this.props_default_img;
    }
  },
  watch:{
      challenge_img:{
      deep: true,

      handler(){
        this.$emit("imageUploaded",{file:this.challenge_img});
      }
    },
      props_change:function(){
        this.imageData = this.props_default_link;
      }
  },
  methods: {
    previewImage: function(event) {
      var input = event.target;
      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = (e) => {
          this.imageData = e.target.result;
      this.$emit("imageUrlUploaded",this.imageData);
        };
        reader.readAsDataURL(input.files[0]);
      }
    },
    DeleteImage: function() {
      if(this.challenge_img == null){
        this.$emit("imageUploaded",{file:this.challenge_img});
      }
      this.challenge_img =  null;
      this.imageData = this.props_default_img;
    },
  },
};
</script>
