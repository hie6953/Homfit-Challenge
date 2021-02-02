<template>
  <div>
    <p id="tag" @click="deleteTag">
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

  methods: {
    deleteTag() {
      this.$emit('delete', this.index);
    },
  },
};
</script>
