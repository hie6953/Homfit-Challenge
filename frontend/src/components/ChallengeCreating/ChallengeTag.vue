<template>
  <div>
    <h4 class="challenge-creating-title">챌린지 태그 입력</h4>
    <!-- 태그 입력 input -->
    <div>
      <div class="challenge-period-information">
        <span
          ><b-icon icon="dot"></b-icon>태그는 5개까지 입력할 수 있습니다.</span
        ><br />
      </div>
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
        @delete="deleteTag(index)"
      ></Tag>
    </div>

    <!-- 페이지 이동 버튼 -->
    <div class="mt-4">
      <b-button class="prev-page-button" @click="PrevPage()"
        ><b-icon icon="arrow-left-circle-fill" scale="1.5"></b-icon
        ><span>인증</span></b-button
      >
      <b-button class="next-page-button" @click="CreateChallenge()"
        ><span>챌린지 개설</span
        ><b-icon icon="arrow-right-circle-fill" scale="1.5"></b-icon
      ></b-button>
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
  watch: {
    tagList: function() {
      if (this.tagList.length >= 5) {
        document.getElementById('tag-input').readOnly = true;
      } else {
        document.getElementById('tag-input').readOnly = false;
      }
    },
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
