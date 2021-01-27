<template>
  <div>
    <h4>챌린지와 관련있는 태그를 입력하세요!</h4>
    <div>
      <b-input
        type="text"
        id="tag-input"
        v-model="input"
        @keyup.enter="addTag()"
        @keyup.space="addTag()"
      />
    </div>
    <div class="tag-list">
      <Tag
        v-for="(tag, index) in tagList"
        :key="`${index}_tag`"
        :content="tag"
        :index="index"
        @click.native="deleteTag(index)"
      ></Tag>
    </div>
    <div>
      <b-button class="prev-page-button" @click="PrevPage()">Previous</b-button>
      <b-button class="next-page-button" @click="CreateChallenge()"
        >Create</b-button
      >
    </div>
  </div>
</template>

<script>
import Tag from '@/components/ChallengeCreating/Tag.vue';

export default {
  name: 'ChallengeTag',
  components: {
    Tag,
  },
  props: {
    props_tagList: Array,
  },
  data() {
    return {
      tagList: [],
      input: '',
    };
  },
  created() {
    this.tagList = this.props_tagList;
  },

  methods: {
    addTag: function() {
      this.tagList.unshift(this.input);
      this.input = '';
    },
    deleteTag: function(index) {
      console.log(index);
      this.tagList.splice(index, 1);
    },
    PrevPage: function() {
      this.$emit('PrevPage', this.tagList);
    },
    CreateChallenge: function() {
      this.$emit('CreateChallenge', this.tagList);
    },
  },
};
</script>
