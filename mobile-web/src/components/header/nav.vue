<template>
    <van-nav-bar
    :title="title"
    left-text=""
    left-arrow
    @click-left="onBack"
    />
</template>

<script>
import { NavBar  } from 'vant';
export default {
    name:'headerNav',
    components:{
         [NavBar.name]: NavBar,
    },
    props:{
        title:String,
    },
    methods: {
        onBack() {
        history.back();
        },
    }
}
</script>

<style>

</style>
