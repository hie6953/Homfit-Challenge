<template>
  <div class="todoList">
    <div class="content">
      <ul class="todos align">
        <li>
          <input type="checkbox" id="todo_1" />
          <label for="todo_1">
            <span class="check"></span>
            땅끄부부와 운동해요
          </label>
          <i class="far fa-trash-alt delete"></i>
        </li>

        <li>
          <input type="checkbox" id="todo_2" />
          <label for="todo_2">
            <span class="check"></span>
            이불 밖은 위험해요
          </label>
          <i class="far fa-trash-alt delete"></i>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import '../assets/css/todolist.css';

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
