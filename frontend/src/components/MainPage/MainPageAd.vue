<template>
  <div>
    <b-carousel
      id="carousel-1"
      :interval="4000"
      controls
      indicators
      background="#ababab"
      img-width="1110"
      img-height="600"
      style="text-shadow: 1px 1px 2px #333;"
    >
      <b-carousel-slide>
        <template #img>
          <img
            class="d-block img-fluid"
            width="1110"
            height="600"
            src="@/assets/ad/homfit_ad1.png"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>
      <b-carousel-slide>
        <template #img>
          <img
            class="d-block img-fluid"
            width="1110"
            height="600"
            src="@/assets/ad/ad3.jpg"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>
      <b-carousel-slide>
        <template #img>
          <img
            class="d-block img-fluid"
            width="1110"
            height="600"
            src="@/assets/ad/ad4.jpg"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>
      <b-carousel-slide>
        <template #img>
          <img
            class="d-block img-fluid"
            width="1110"
            height="600"
            src="@/assets/ad/ad1.png"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>
    </b-carousel>
  </div>
</template>


<script>
  export default {
    
  }
</script>