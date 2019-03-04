<template>
    <div class="search-box" :style="boxstyle" >
    <div class="search-box__view" :style="boxviewstyle">
        <div class="search">
            <div :class="'search__filed search__filed--'+data.textalign+' search__filed--'+(data.boxtype=='2'?'circle':'rect')" :style="'background-color:'+(data.boxcolor==undefined?'#ffffff':data.boxcolor)+';'">
                <van-icon name="search" class="icon-search" size="14px" :color="data.color" />
                <div class="cell field" :style="data.textalign=='left'?'':'width: 80px;'">
                    <div class="cell__value cell__value--alone">
                        <div class="field__body">
                            <input type="search" :placeholder="data.keyword==''||data.keyword==undefined?'商品搜索':data.keyword" class="field__control"  />
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</template>

<script>

export default {
    name:'search',
    components:{
    },
    props:{
        data:Object
    },
    created:function(){
        if(this.data.position=='fixed'){
            this.$emit('settopheight', 50);
        }
    },
    computed:{
        boxstyle(){
            if(this.data.position=='fixed'){
                return{
                    position:'fixed',
                    top: 0,
                    zIndex: 10,
                }
            }else{
                return{}
            }
        },
        boxviewstyle(){
            return{
                background:  (this.data.backgroundcolor==undefined?"#f9f9f9":this.data.backgroundcolor),
                paddingRight: '15px',
                top:'0px' ,
            }
        },
    }
}
</script>

<style>

</style>
