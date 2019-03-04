<template>
    <div style="height:50px;">
    <van-tabbar >
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
    }
}
</script>

