<template>
<div :style="'background-color:'+((page.BackgroundColor==undefined||page.BackgroundColor=='')?'#fff':page.BackgroundColor)">
    <div :style="'height:'+topheight+'px'" ></div>

    <!-- TODO 搜索框 -->
<!--    <search v-if="item.Code=='Search'" :data="item.ParameterDictionary" v-on:settopheight="settopheight($event)" ></search>-->

    <van-swipe :autoplay="3000" indicator-color="white" height="160">
        <van-swipe-item v-for="(banner, index) in banners" :key="index" >
            <a :href="banner.url">
                <img :src="banner.picUrl" height="100%" width="100%" >
            </a>
        </van-swipe-item>
    </van-swipe>

    <van-row style="text-align: center">
        <van-col span="8">
            <router-link to="/category">
                <van-icon name="http://static.iocoder.cn/icons8-medium-priority-45.png"/>
                <div style="font-size:12px;margin-top: -10px;">分类</div>
            </router-link>
        </van-col>
        <van-col span="8">
            <router-link to="/category">
                <van-icon name="http://static.iocoder.cn/icons8-sun-45.png" />
                <div style="font-size:12px;margin-top: -10px;">热卖</div>
            </router-link>
        </van-col>
        <van-col span="8">
            <router-link to="/category">
                <van-icon name="http://static.iocoder.cn/icons8-new-45.png" />
                <div style="font-size:12px;margin-top: -10px;">新品</div>
            </router-link>
        </van-col>
    </van-row>

    <van-panel title="新品推荐" >
<!--        <product :data="productRecommends['1']" ></product>-->
        <div style="height: 70px;" v-for="(product,i) in productRecommends['1']" :key="i">
            <product-card :product='product' @click="showProduct(product)" />
        </div>
    </van-panel>

    <van-panel title="热卖推荐">
        <div style="height: 70px;" v-for="(product,i) in productRecommends['2']" :key="i">
            <product-card :product='product' @click="showProduct(product)" />
        </div>
    </van-panel>

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
  import {getBannerList, getProductRecommendList} from '../../api/promotion.js';

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
          productRecommends: [], // 推荐商品列表
        }
    },
    created:function(){
        // GetPage().then(response=>{
        //     this.page=response;
        // });
    },
    mounted: function() {
      // 加载 Banner
      {
          let response = getBannerList();
          response.then(data => {
            this.banners = data;
          });
      }
      // 加载 getProductRecommendList
      {
        let response = getProductRecommendList();
        response.then(data => {
          this.productRecommends = data;
        });
      }
    },
    methods:{
      // onBannerClick: function(event, index) {
      //   debugger;
      //   console.log(event);
      // },
      settopheight:function(value){
        this.topheight=value;
      },
      showProduct(product){
        this.$router.push('/product/'+product.id);
      }
    }
}
</script>
