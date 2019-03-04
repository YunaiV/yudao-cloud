<template>
<a :href="data.link">
    <div v-if="data.type == '1'" class="cap-title cap-title--normal" :style="'background-color:'+data.backgroundcolor+'; text-align:'+data.textalign+';color:'+data.color">
        <h2 class="cap-title__main">{{data.title}}</h2>
        <p v-if="data.subtitle!=undefined&&data.subtitle!=''" class="cap-title__sub"><span class="cap-title__sub-title">{{data.subtitle}}</span></p>
    </div>
            
    <div v-if="data.type == '2'" class="title" :style="'background:'+data.backgroundcolor+';text-align:'+data.textalign">
        <span :style="data.color==''?'':'border-bottom: 1px solid '+data.color+';color:'+data.color+';'">{{data.title}}</span>
    </div>
    </a>
</template>

<script>
export default {
    name:'page-title',
    props:{
        data:Object
    },
    computed:{
        
    }
}
</script>

<style>

</style>
