<template>
    <NoticeBar
    :text="data.value"
    :background="data.background"
    :color="data.color"
    />
</template>

<script>
import { NoticeBar } from "vant";

export default {
    name:'notice',
    components:{
        NoticeBar
    },
    props:{
        data:Object
    },
    computed:{

    }
}
</script>

<style>

</style>

