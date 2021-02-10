<template>
    <div>
        <div v-if="msg.uid == getUserUid" class="me">
            {{msg.nick_name}} - {{msg.content}}
        </div>
        <div v-else class="other">
            {{msg.nick_name}} - {{msg.content}}
        </div>
    </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "Message",
  props: {
    msg: Object,
  },
  computed: {
    ...mapGetters(["getUserUid"]),
  },
};
</script>

<style scoped>
    .me {
        background-color: orange;
        text-align: right;
    }
    .other {
        text-align: left;
    }
</style>