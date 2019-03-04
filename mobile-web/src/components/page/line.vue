<template>
    <div :style="divstyle" ><div :style="linestyle"></div></div>
</template>

<script>
export default {
    name: 'page-line',
    props: {
        data: Object,
    },
    computed:{
        divstyle(){
            return{
                height:"30px",
                position:'relative',
                margin:'0px '+this.data.margintype+'px'
            };
        },
        linestyle(){
            return{
                position:'absolute',
                top:'14px',
                width:'100%',
                borderTop:'1px '+(this.data.type==undefined?'solid':this.data.type)+" "+(this.data.color==undefined?'#000':this.data.color),
            };
        }
    }
};
</script>

