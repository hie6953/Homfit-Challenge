<template>
  <div>
    <p id="tag">
      {{ content }}
      <b-icon icon="x" variant="white" scale="1.5"></b-icon>
    </p>
  </div>
</template>

<script>
export default {
  props: {
    content: String,
    index: Number,
  },
};
</script>
