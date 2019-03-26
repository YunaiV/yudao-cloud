<template>
    <div style="height:50px;">
    <van-tabbar v-model="active">
        <van-tabbar-item icon="wap-home" to="/home">首页</van-tabbar-item>
        <van-tabbar-item icon="wap-nav" to="/category" >分类</van-tabbar-item>
        <van-tabbar-item icon="cart" to="/cart" >购物车</van-tabbar-item>
        <van-tabbar-item icon="contact" to="/user/index">我的</van-tabbar-item>
    </van-tabbar>
    </div>
</template>

<script>
import { Tabbar, TabbarItem } from "vant";
export default {
    name:'navigate',
    components:{
        [Tabbar.name]: Tabbar,
        [TabbarItem.name]: TabbarItem,
    },
    data() {
        return {
          active: 0,
        };
    },
    created() {
      // TODO 芋艿，可能不太优雅
      let path = this.$route.path;
      switch (path) {
        case '/home':
          this.active = 0;
          break;
        case '/category':
          this.active = 1;
          break;
        case '/cart':
          this.active = 2;
          break;
        case '/user/index':
          this.active = 3;
          break;
      }
    }
}
</script>

