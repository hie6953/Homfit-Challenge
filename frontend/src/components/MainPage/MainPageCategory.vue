<template>
  <div class="container">
    <div class="category-rows">
      <div class="category-row col-12">
        <router-link to="/challengelist" class="category-box">
          <img src="../../assets/category/요가.png" alt="요가" class="category-circle">
          <p class="category-name">요가</p>
        </router-link>
        <router-link to="/challengelist" class="category-box">
          <img src="../../assets/category/필라테스.png" alt="필라테스" class="category-circle">
          <p class="category-name">필라테스</p>
        </router-link>
        <router-link to="/challengelist" class="category-box">
          <img src="../../assets/category/식단.png" alt="식단" class="category-circle">
          <p class="category-name">식단</p>
        </router-link>
        <router-link to="/challengelist" class="category-box">
          <img src="../../assets/category/유산소.png" alt="유산소" class="category-circle">
          <p class="category-name">유산소</p>
        </router-link>
        <router-link to="/challengelist" class="category-box">
          <img src="../../assets/category/댄스.png" alt="댄스" class="category-circle">
          <p class="category-name">댄스</p>
        </router-link>
      </div>
      <div class="category-row col-12">
        <router-link to="/challengelist" class="category-box">
          <img src="../../assets/category/스트레칭.png" alt="스트레칭" class="category-circle">
          <p class="category-name">스트레칭</p>
        </router-link>
        <router-link to="/challengelist" class="category-box">
          <img src="../../assets/category/근력.png" alt="근력" class="category-circle">
          <p class="category-name">근력</p>
        </router-link>
        <router-link to="/challengelist" class="category-box">
          <img src="../../assets/category/키즈.png" alt="키즈" class="category-circle">
          <p class="category-name">키즈</p>
        </router-link>
        <router-link to="/challengelist" class="category-box">
          <img src="../../assets/category/복싱.png" alt="복싱" class="category-circle">
          <p class="category-name">복싱</p>
        </router-link>
        <router-link to="/challengelist" class="category-box">
          <img src="../../assets/category/기타.png" alt="기타" class="category-circle">
          <p class="category-name">기타</p>
        </router-link>
      </div>
    </div>
  </div>
</template>


<script>
import "@/assets/css/mainPageCategory.css"

export default {
  mounted() {
    // width값이 변화하면 height값을 width값과 같게 변경
    window.addEventListener('resize', function() {
      const categoryBox = document.querySelector('.category-circle')
      categoryBox.style.height = categoryBox.width
    });
  },
}
</script>

<style>

</style>