<template>
  <div class="term-tag-container">
    <div class="block-box"></div>
    <div class="switch-box">
      <div class="switch-button"><span class="active"></span>
        <button class="switch-button-case left active-case" @click="switchLeft">월</button>
        <button class="switch-button-case right" @click="switchRight">년</button>
      </div>
    </div>
  </div>
</template>

<script>
import "@/assets/css/StatisticsPage/termtab.css"

export default {
  methods: {
    // 왼쪽버튼 클릭
    switchLeft() {
      const switchBtnRight = document.querySelector('.right');
      const switchBtnLeft = document.querySelector('.left');
      const activeSwitch = document.querySelector('.active');
      switchBtnRight.classList.remove('active-case');
      switchBtnLeft.classList.add('active-case');
      activeSwitch.style.left	= '0%';
    },
    // 오른쪽버튼 클릭
    switchRight() {
      const switchBtnRight = document.querySelector('.right');
      const switchBtnLeft = document.querySelector('.left');
      const activeSwitch = document.querySelector('.active');
      switchBtnRight.classList.add('active-case');
      switchBtnLeft.classList.remove('active-case');
      activeSwitch.style.left	= '50%';
    }
  },
}
</script>

<style>

</style>