<template>
    <div class="review-whole">
        <div class="review-header">
            <div class="review-user-image">
                <img src="@/assets/NavBar/anonimous_user.png" alt="">
            </div>
            <div class="review-user ml-2">
                <div class="review-user-info">
                    <span class="review-user-info-nick-name">{{review.nick_name}}</span>
                    <span class="review-user-info-date ml-2">{{getRegistTime}}</span>
                </div>
                <div class="review-user-score">
                    <span v-for="num in review.star_point" :key="`${num}_reviewScore`"><b-icon icon="star-fill" class="color-black"></b-icon></span>
                    <span v-for="num in (5-review.star_point)" :key="`${num}_noReviewScore`"><b-icon icon="star-fill" class="color-gray"></b-icon></span>
                </div>
            </div>
        </div>
        <div class="review-body">
            <span>{{review.review_contents}}</span>
        </div>
        
    </div>
</template>
<script>
import '@/assets/css/review.css';

export default {
    props:{
        review:Object,
    },
    computed:{
        getRegistTime:function(){
            let time = new Date(this.review.regist_time);
            return time.getFullYear()+"년 "+time.getMonth()+"월 "+time.getDate()+"일 " + time.getHours()+"시 "+time.getMinutes()+"분";
        }
    },
}
</script>