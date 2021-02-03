<template>
    <div class="challenge-title-background">
        <div class="row col-11 col-md-8 mx-auto">
        <div class="col-12 col-md-6">

        <img id="challenge-img" src="https://picsum.photos/600/300/?image=25">
        </div>
        <div class="col-12 col-md-6 my-auto">
            <div class="challenge-info">
                <span id="title-check-date">시작전</span>
                <span>카테고리(글씨 더 작게)</span>
                <h2>땅끄부부</h2>
                <span>아이콘+개설자</span>
                <br>
                <span>요일+하루인증횟수+기간+날짜</span>
                <br>
                <span>참여자수</span>
                <br>
                <span>태그</span>
            </div>
        </div>

        </div>
    </div>
</template>
<script>
import '@/assets/css/ChallengeMoreInfo/challengeTitle.css';

// const check_date = ["시작전","진행중","완료"];
export default {
    components:{
    },

}
</script>