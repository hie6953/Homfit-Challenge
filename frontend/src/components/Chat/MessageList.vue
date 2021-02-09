<template>
    <b-list-group>
        <div v-for="(msg, index) in msgs" :key="index">
            <span>{{msg}}</span>
        </div>
    </b-list-group>
</template>

<script>
export default {
    name:'MessageList',
    props:['msgs'],
};
</script>