<template>
  <!-- axios -->
  <div class="gallery-item">
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
  </div>

  <!-- 사진1 -->
  <!-- <div class="col-xs-4 gallery-item">
          <img
            src="https://images.unsplash.com/photo-1426604966848-d7adac402bff?w=500&h=500&fit=crop"
            class="responsive-image"
            @click="ChallengeMoreInfo"
          />
          <div class="gallery-item-info">
            <ul>
              <li class="gallery-item-likes">
                <span class="visually-hidden"
                  >안녕 우리 함께 운동하쟙안녕 우리 함께 운동하쟙안녕 우리 함께
                  운동하쟙</span
                >
              </li>
            </ul>
          </div>
        </div> -->

  <!-- 사진2 -->
  <!-- <div class="col-xs-4 gallery-item">
          <img
            src="https://images.unsplash.com/photo-1502630859934-b3b41d18206c?w=500&h=500&fit=crop"
            class="responsive-image"
          />
        </div> -->
</template>

<script>
import '../assets/css/feed.css';

export default {
  name: 'Feed',
  props: {
    feed: Object,
  },
  data: function() {
    return {};
  },
  methods: {
    ChallengeMoreInfo: function() {
      this.$emit('moreInfo', this.challenge.challenge_id);
    },
  },
};
</script>
