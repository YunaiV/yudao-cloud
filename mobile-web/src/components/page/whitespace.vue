<template>
    <div :style="style"></div>
</template>

<script>
export default {
    name: 'whitespace',
    props: {
        data: Object,
    },
    computed: {
        style() {
            return {
                height: (this.data.height==undefined?'30':this.data.height)+"px",
            };
        }
    }
};
</script>

