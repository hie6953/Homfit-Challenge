<template>
    <div>
        <h2>챌린지 인증방법</h2>
        <p v-if="only_cam == 1">카메라만 사용</p>
        <p v-else>카메라, 갤러리 사용</p>
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