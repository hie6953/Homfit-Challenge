<template>
  <div>
    <h4>챌린지와 관련있는 태그를 입력하세요!</h4>
    <div class="tag-container">
      <!-- <input @keyup.enter="makeTagg()" @keyup.space="makeTagg" /> -->
    </div>

    <b-button class="prev-page-button" @click="PrevPage()">Previous</b-button>
    <b-button class="next-page-button" @click="CreateChallenge()"
      >Create</b-button
    >
  </div>
</template>

<script>
export default {
  props: {
    props_tagList: Array,
  },
  data() {
    return {
      tagList: '',
    };
  },
  created() {
    this.tagList = this.props_tagList;
  },

  methods: {
    PrevPage: function() {
      this.$emit('PrevPage', this.tagList);
    },
    CreateChallenge: function() {
      this.$emit('CreateChallenge', this.tagList);
    },
  },
};

// const tagContainer = document.querySelector('.tag-container');
// const input = document.querySelector('.tag-container input');

// let tags = [];

// function createTag(label) {
//   const div = document.createElement('div');
//   div.setAttribute('class', 'tag');
//   const span = document.createElement('span');
//   span.innerHTML = label;
//   const closeIcon = document.createElement('i');
//   closeIcon.innerHTML = 'close';
//   closeIcon.setAttribute('class', 'material-icons');
//   closeIcon.setAttribute('data-item', label);
//   div.appendChild(span);
//   div.appendChild(closeIcon);
//   return div;
// }

// function clearTags() {
//   document.querySelectorAll('.tag').forEach((tag) => {
//     tag.parentElement.removeChild(tag);
//   });
// }

// function addTags() {
//   clearTags();
//   tags
//     .slice()
//     .reverse()
//     .forEach((tag) => {
//       tagContainer.prepend(createTag(tag));
//     });
// }

// function makeTag() {
//   e.target.value.split(',').forEach((tag) => {
//     tags.push(tag);
//   });
//   addTags();
//   input.value = '';
//   input.focus();
// }
</script>

<style scoped>
.container {
  width: 40%;
  margin: 30px auto;
}
.tag-container {
  border: 2px solid #ccc;
  border-radius: 3px;
  background: #fff;
  display: flex;
  flex-wrap: wrap;
  align-content: flex-start;
  padding: 6px;
  overflow-x: scroll;
}
.tag-container .tag {
  height: 30px;
  margin: 5px;
  padding: 5px 6px;
  border: 1px solid #ccc;
  border-radius: 3px;
  background: #eee;
  display: flex;
  align-items: center;
  color: #333;
  box-shadow: 0 0 4px rgba(0, 0, 0, 0.2), inset 0 1px 1px #fff;
  cursor: default;
}
.tag i {
  font-size: 16px;
  color: #666;
  margin-left: 5px;
}
.tag-container input {
  padding: 5px;
  font-size: 16px;
  border: 0;
  outline: none;
  font-family: 'Rubik';
  color: #333;
  flex: 1;
}
</style>
