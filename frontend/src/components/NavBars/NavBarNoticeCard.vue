<template>
  <div>
    <b-card class="mb-2">
      <b-card-text>
        <p id="notice-title">{{notice.type}}</p>
        <span id="notice-comment">{{ notice.comment }}</span>
      </b-card-text>
    </b-card>
  </div>
</template>

<script>
export default {
  props: {
    notice: Object,
  },
};
</script>
