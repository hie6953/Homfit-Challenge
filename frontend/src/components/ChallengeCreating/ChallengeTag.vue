<template>
  <div>
    <h4>챌린지와 관련있는 태그를 입력하세요!</h4>
    <!-- 태그 입력 input -->
    <div>
      <b-input
        type="text"
        id="tag-input"
        v-model="input"
        @keyup.enter="addTag()"
        @keyup.space="addTag()"
      />
    </div>
    <!-- 태그 목록 -->
    <div class="tag-list">
      <Tag
        v-for="(tag, index) in tagList"
        :key="`${index}_tag`"
        :content="tag"
        :index="index"
        @click.native="deleteTag(index)"
      ></Tag>
    </div>

    <!-- 페이지 이동 버튼 -->
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
    // 태그 추가
    addTag: function() {
      this.input = this.input.replace(' ', '');
      if (this.input != '') {
        this.tagList.unshift(this.input);
        this.input = '';
      }
    },

    // 태그 삭제
    deleteTag: function(index) {
      this.tagList.splice(index, 1);
    },

    // 페이지 이동
    PrevPage: function() {
      this.$emit('PrevPage', this.tagList);
    },

    // 챌린지 개설
    CreateChallenge: function() {
      this.$emit('CreateChallenge', this.tagList);
    },
  },
};
</script>
