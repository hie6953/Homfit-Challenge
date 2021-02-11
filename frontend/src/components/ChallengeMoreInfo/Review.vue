<template>
    <div class="review-whole">
        <div class="review-header">
            <div class="review-user-image">
                <img src="@/assets/NavBar/anonimous_user.png" alt="">
            </div>
            <div class="review-user ml-2">
                <div class="review-user-info">
                    <span class="review-user-info-nick-name">{{review.nick_name}}</span>
                    <span class="review-user-info-date ml-2">21.01.12</span>
                </div>
                <div class="review-user-score">
                    <span v-for="num in score" :key="`${num}_reviewScore`"><b-icon icon="star-fill" class="color-black"></b-icon></span>
                    <span v-for="num in (5-score)" :key="`${num}_noReviewScore`"><b-icon icon="star-fill" class="color-gray"></b-icon></span>
                </div>
            </div>
        </div>
        <div class="review-body">
            <span>최고야 최고야 최고야 최고야 최고야 최고야 최고야 최고야 최고야 최고야 최고야 </span>
        </div>
        
    </div>
</template>
<script>
import '@/assets/css/review.css';

export default {
    props:{
        review:Object,
    },
    data() {
        return {
            score:3,
        }
    },
}
</script>