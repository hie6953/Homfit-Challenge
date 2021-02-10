<template>
    <div class="mb-3">
        <p class="more-info-title">챌린지 인증방법</p>
        <div class="certify-contents-onlycam">
            <b-icon icon="info-circle" variant="warning"></b-icon>
            <span v-if="only_cam == 1">이 챌린지는 카메라만 사용합니다.</span>
            <span v-else>이 챌린지는 카메라와 갤러리를 사용합니다.</span>
        </div>
        <br>
        <p v-html="challenge_certify_contents"></p>
    </div>
</template>

<script>
export default {
    name:'ChallengeCertifyContents',
    props:{
        challenge_certify_contents:String,
        only_cam:Number,
    }
}
</script>