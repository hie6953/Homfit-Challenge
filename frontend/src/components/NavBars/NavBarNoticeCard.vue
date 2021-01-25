<template>
  <div>
    <b-card :title="notice.type" class="mb-2">
      <b-card-text>
        {{ notice.comment }}
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
