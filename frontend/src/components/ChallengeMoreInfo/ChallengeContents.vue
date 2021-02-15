<template>
    <div class="mb-3">
        
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