<template>
  <div class="term-tag-container">
    <!-- 임시 -->
    <br>
    <br>
    <br>
    <div class="switch-button"><span class="active"></span>
      <button class="switch-button-case left active-case">월</button>
      <button class="switch-button-case right">년</button>
    </div>
  </div>
</template>

<script>
import "@/assets/css/StatisticsPage/termtag.css"

export default {
  // methods: {

  // },
  // mounted: {
  //   change() {
  //     // const switchButton = document.querySelector('.switch-button');
  //     const switchBtnRight = document.querySelector('.switch-button-case.right');
  //     const switchBtnLeft = document.querySelector('.switch-button-case.left');
  //     const activeSwitch = document.querySelector('.active');

  //     function switchLeft(){
  //       switchBtnRight.classList.remove('active-case');
  //       switchBtnLeft.classList.add('active-case');
  //       activeSwitch.style.left 						= '0%';
  //     }

  //     function switchRight(){
  //       switchBtnRight.classList.add('active-case');
  //       switchBtnLeft.classList.remove('active-case');
  //       activeSwitch.style.left 						= '50%';
  //     }

  //     switchBtnLeft.addEventListener('click', function(){
  //       switchLeft();
  //     }, false);

  //     switchBtnRight.addEventListener('click', function(){
  //       switchRight();
  //     }, false);
  //   }
  // }
}
</script>

<style>

</style>