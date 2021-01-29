<template>
  <ul class="list">
    <li class="list__item" v-for="todoItem in todoItems" v-bind:key="todoItem">
      <input type="checkbox" v-bind:id="todoItem.item" />
      <label v-bind:for="todoItem.item" class="list__label">
        <p class="list__text">{{ todoItem }}</p>
      </label>
    </li>
  </ul>
</template>

<script>
export default {
  data() {
    return {
      todoItems: [],
    };
  },
  created() {
    if (localStorage.length > 0) {
      for (let i = 0; i < localStorage.length; i++) {
        if (localStorage.key(i) !== 'loglevel:webpack-dev-server') {
          this.todoItems.push(localStorage.getItem(localStorage.key(i)));
        }
      }
    }
  },
};
</script>
