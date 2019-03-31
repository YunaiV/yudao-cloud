<template>
<div :style="'background-color:'+((page.BackgroundColor==undefined||page.BackgroundColor=='')?'#fff':page.BackgroundColor)">
    <div :style="'height:'+topheight+'px'" ></div>
    <van-swipe :autoplay="3000" indicator-color="white" height="160">
        <van-swipe-item v-for="(banner, index) in banners" :key="index" >
            <a :href="banner.url">
                <img :src="banner.picUrl" height="100%" width="100%" >
            </a>
        </van-swipe-item>
    </van-swipe>
    <div v-for="(item,index) in page.Sections" :key="index">

        <imageText v-if="item.Code=='ImageText'" :data="item.ParameterDictionary"></imageText>

        <pageLine v-if="item.Code=='Line'"  :data="item.ParameterDictionary" ></pageLine>

        <whitespace v-if="item.Code=='Line'" :data="item.ParameterDictionary"  />

        <pageText v-if="item.Code=='Text'" :data="item.ParameterDictionary" ></pageText>

        <notice v-if="item.Code=='Notice'" :data="item.ParameterDictionary" ></notice>

        <search v-if="item.Code=='Search'" :data="item.ParameterDictionary" v-on:settopheight="settopheight($event)" ></search>

        <pageTitle v-if="item.Code=='Title'" :data="item.ParameterDictionary" ></pageTitle>

        <cube v-if="item.Code=='Cube'" :data="item.ParameterDictionary" ></cube>

        <product v-if="item.Code=='Product'" :data="item" ></product>
    </div>

</div>
</template>
<script>
import "../../assets/style/index.css";
import whitespace from "../../components/page/whitespace.vue";
import pageLine from "../../components/page/line.vue";
import pageText from "../../components/page/text.vue";
import notice from "../../components/page/notice.vue";
import search from "../../components/page/search.vue";
import pageTitle from "../../components/page/title.vue";
import cube from "../../components/page/cube.vue";
import imageAd from "../../components/page/imageAd.vue";
import imageText from "../../components/page/imageText.vue";
import product from "../../components/page/product.vue";
import { GetPage } from "../../api/page.js";
import { getBannerList } from '../../api/promotion.js';

export default {
    name:"page",
    components:{
        whitespace,
        pageLine,
        pageText,
        notice,
        search,
        pageTitle,
        cube,
        [imageAd.name]:imageAd,
        imageText,
        product
    },
    data:function(){
        return{
            topheight:0,
            page:{},
          banners: [], // Banner 列表
        }
    },
    created:function(){
        GetPage().then(response=>{
            this.page=response;
        });
    },
    mounted: function() {
      // 加载 Banner
      let response = getBannerList();
      response.then(data => {
        this.banners = data;
      });
    },
    methods:{
      onBannerClick: function(event, index) {
        debugger;
        console.log(event);
      },
      settopheight:function(value){
        this.topheight=value;
      }
    }
}
</script>
