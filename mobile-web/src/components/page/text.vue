<template>
    <div :style="style">{{data.value}}</div>
</template>

<script>
export default {
    name:'page-text',
    props:{
        data:Object
    },
    computed:{
        style(){
            return{
                position:'relative',
                padding: '10px',
                fontSize:(this.data.fontsize==undefined?'10':this.data.fontsize)+'px',
                color:(this.data.color==undefined?'#000':this.data.color),
                background:this.data.backgroundcolor,
                textAlign:this.data.textalign,
            }
        }
    }
}
</script>

