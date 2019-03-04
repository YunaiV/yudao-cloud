<template>
  <div>
    <div class="user-profile">
      <div class="user-profile-avatar">
        <a href="/#/user/info">
          <img :src="data.Avatar" alt="用户头像">
        </a>
      </div>
      <div class="user-profile-username">
        <a href="/#/user/info">
          <span class="m-nick">{{data.UserName}}</span>
        </a>  
      </div>
    </div>

    <van-cell-group class="user-group">
      <van-cell title="我的订单" value="查看全部订单" is-link  to="/user/order"/>
      <van-row class="user-links">
        <router-link  to="/user/order/1">
          <van-col span="6">
            <van-icon name="pending-payment">
              <van-info :info="data.UnPayTotal"  />
            </van-icon>
            <div>待付款</div>
          </van-col>
        </router-link>
        <router-link  to="/user/order/2">
          <van-col span="6">
            <van-icon name="logistics">
              <van-info :info="data.UnRecieveTotal"   />
            </van-icon>
            <div>待发货</div>
          </van-col>
        </router-link>
        <router-link  to="/user/order/2">
          <van-col span="6">
            <van-icon name="point-gift">
            </van-icon>
            <div>已完成</div>
          </van-col>
        </router-link>
        <router-link  to="/user/aftersale">
          <van-col span="6">
            <van-icon name="after-sale" >
              <van-info :info="data.AfterSaleTotal"   />
            </van-icon>
            <div>售后</div>
          </van-col>
        </router-link>
      </van-row>
    </van-cell-group>
    
    <van-cell-group class="user-group">
      <van-cell title="我的服务" />
      <van-row class="user-links">
        <router-link  to="/user/coupon">
          <van-col span="6">
            <van-icon name="coupon" />
            <div>我的优惠券</div>
          </van-col>
        </router-link>
        <router-link  to="/user/favorite">
          <van-col span="6">
            <van-icon name="like-o" />
            <div>我的收藏</div>
          </van-col>
        </router-link>
        <router-link  to="/user/address">
          <van-col span="6" >
            <van-icon name="location"/>
            <div>收货地址</div>
          </van-col>
        </router-link>
      </van-row>
    </van-cell-group>

    <van-cell-group>
      <van-cell title="切换账号" is-link to="/login" />
    </van-cell-group>
    <navigate />
  </div>
</template>

<script>
import { GetUserIndex } from "../../api/user.js";

export default {
  data(){
    return{
      data:{}
    }
  },
  components: {
  },
  created:function(){
      GetUserIndex().then(response=>{
          this.data=response;
      });
  },
};
</script>

<style lang="less">
.user {
  &-profile {
        text-align: center;
        display: block;
    width: 100%;
    height: 141px;
    background-color: #f1f5fa;
    background-repeat: no-repeat;
    background-size: 100% 100%;
    img{
          width: 100%;
    }
    &-avatar{
          padding-top: 23px;
    padding-bottom: 5px;

      img{

      width: 65px;
      height: 65px;
      border-radius: 50%;
      overflow: hidden;
      }
    }
    &-username{
      font-size: 20px;
    }
  }
  &-group {
    margin-bottom: .3rem;
    
    .van-cell__value{
      color: #999;
      font-size: 12px;
    }
  }
  &-links {
    padding: 15px 0;
    font-size: 12px;
    text-align: center;
    background-color: #fff;
    .van-icon {
      position: relative;
      width: 24px;
      font-size: 24px;
    }
  }
}
</style>