<template>
    <div class="mb-3">
        <p class="more-info-title">챌린지 설명</p>
        <p v-html="challenge_contents"></p>
    </div>
</template>

<script>
export default {
    name:'ChallengeContents',
    props:{
        challenge_contents:String,
    }
}
</script>