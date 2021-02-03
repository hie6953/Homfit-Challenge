<template>
  <div class="mt-3">
    <hr id="hr-top" />

    <div class="feed-container">
      <div class="grid">
        <!-- 사진1 -->
        <div class="gallery-item">
          <img
            src="https://images.unsplash.com/photo-1426604966848-d7adac402bff?w=500&h=500&fit=crop"
            class="responsive-image"
          />
          <div class="gallery-item-info">
            <ul>
              <li class="gallery-item-likes">
                <span class="visually-hidden">운동하쟙</span
                ><i class="fas fa-heart" aria-hidden="true"></i> 56
              </li>
            </ul>
          </div>
        </div>

        <!-- 사진2 -->
        <div class="gallery-item">
          <img
            src="https://images.unsplash.com/photo-1502630859934-b3b41d18206c?w=500&h=500&fit=crop"
            class="responsive-image"
          />
        </div>

        <!-- 사진3 -->
        <div class="gallery-item">
          <img
            src="https://images.unsplash.com/photo-1498471731312-b6d2b8280c61?w=500&h=500&fit=crop"
            class="responsive-image"
          />
        </div>

        <!-- 사진4 -->
        <div class="gallery-item">
          <img
            src="https://images.unsplash.com/photo-1515023115689-589c33041d3c?w=500&h=500&fit=crop"
            class="responsive-image"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/feed.css';

export default {
  name: 'Feed',
  data: function() {
    return {};
  },
};
</script>
