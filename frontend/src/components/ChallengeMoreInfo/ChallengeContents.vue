<template>
    <div>
        <h2>챌린지 설명</h2>
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