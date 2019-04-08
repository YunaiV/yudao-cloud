<template>
  <div class="order-success">
    <headerNav title="提交成功"/>

    <sapn>TODO 支付成功</sapn>

    <router-link :to="'/user/order'">
      <van-button size="small" type="danger">订单列表</van-button>
    </router-link>
  </div>
</template>

<script>

  import {createOrder} from '../../api/order';
  export default {
    data() {
      return {

      }
    },
    methods: {

    },
    activated() {

    },
  };
</script>

<style lang="less">
  .order-success {
    text-align: center;
  }
</style>
