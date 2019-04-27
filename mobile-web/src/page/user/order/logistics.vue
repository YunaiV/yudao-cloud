<template>
  <div style="background: #f7f7f7;">
    <headerNav title="订单追踪"/>

    <van-cell-group>
      <van-cell title="订单编号" value="18081609422771742"/>
    </van-cell-group>
    <van-tabs>
      <van-tab v-for="(item, index) in logistics" :key="index" :title="'包裹' + (index + 1)">
        <van-cell-group>
          <van-cell title="快递方式" :value="item.logisticsText"/>
          <van-cell title="快递单号" :value="item.logisticsNo"/>
        </van-cell-group>
        <van-steps direction="vertical" :active="0" active-color="#f60" style="margin-top: 15px;">
          <van-step v-for="(detail, detailIndex) in item.details" :key="detailIndex">
            <h3>{{detail.logisticsInformation}}</h3>
            <p>{{detail.logisticsTimeText}}</p>
          </van-step>
        </van-steps>
      </van-tab>
    </van-tabs>

  </div>
</template>

<script>
  import { getOrderLogisticsInfo } from '../../../api/order';

  export default {
    data() {
      return {
        orderId: '',
        orderNo: '',
        logistics: [],
      }
    },
    mounted() {
      const { id } = this.$route.params;
      getOrderLogisticsInfo({
        orderId: id,
      }).then(res => {
        const { orderId, orderNo, logistics } = res;
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.logistics = logistics;
      })
    },
  }
</script>

<style>

</style>
