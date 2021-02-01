<template>
  <div>
    <editor-menu-bar :editor="editor" v-slot="{ commands, isActive }">
      <div>
        <button
          :class="{ 'is-active': isActive.bold() }"
          @click="commands.bold"
        >
          Bold
        </button>
      </div>
    </editor-menu-bar>
    <editor-content :editor="editor" />
  </div>
</template>

<script>
import { Editor, EditorContent, EditorMenuBar } from 'tiptap';
import { Bold } from 'tiptap-extensions';

export default {
  components: { EditorMenuBar, EditorContent },
  data() {
    return {
      editor: new Editor({ extensions: [new Bold()] }),
    };
  },

  mounted() {
    this.editor = new Editor();
  },
  beforeDestroy() {
    this.editor.destroy();
  },
};
</script>
