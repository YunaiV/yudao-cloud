<template>
    <div>
        <page/>
        <navigate />
    </div>
</template>

<script>
import navigate from '../components/footer/navigate.vue'
import page from './page/page.vue'

export default {
    components:{
        page,
        navigate
    }
}
</script>
<style>

</style>


