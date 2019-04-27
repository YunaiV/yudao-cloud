<template>
  <div>
    <headerNav title="服务单详情"/>

    <div v-if="serviceType === 1">
      <van-steps :active="active">
        <van-step>提交申请</van-step>
        <van-step>客服审核</van-step>
        <van-step>客户确认</van-step>
        <van-step>仓库收货</van-step>
        <van-step>完成</van-step>

      </van-steps>

      <van-cell class="logistics"
                :to="`/user/aftersale/track/${lastLogisticsInfo.id}/${serviceNumber}`"
                :title="lastLogisticsDetail.logisticsInformation"
                :label="lastLogisticsDetail.logisticsTimeText" is-link/>
    </div>

    <div v-if="serviceType === 2">
      <van-steps v-if="serviceType === 2" :active="active">
        <van-step>提交申请</van-step>
        <van-step>客服审核</van-step>
        <van-step>客户确认</van-step>
        <van-step>仓库收货</van-step>
        <van-step>完成</van-step>
      </van-steps>
    </div>

    <div style="height:15px;"></div>
    <div v-for="(product,i) in products" :key="i">
      <product-card :product='product'/>
    </div>

    <div style="height:15px;"></div>
    <van-cell-group>
      <van-cell title="服务单号" :value="serviceNumber"/>
      <van-cell title="申请时间" :value="applyTime"/>
      <van-cell title="服务类型" :value="serviceTypeText"/>
      <van-cell title="退款金额" :value="refundPrice / 100"/>
    </van-cell-group>
    <div style="height:15px;"></div>

    <!-- 暂时没有 -->
    <!--<van-cell-group>-->
      <!--<van-cell title="联系人" value="张三"/>-->
      <!--<van-cell title="联系电话" value="138****6514"/>-->
    <!--</van-cell-group>-->
  </div>
</template>

<script>

  import moment from 'moment';
  import { getOrderReturnInfo } from '../../../api/order';

  export default {
    data() {
      return {
        active: 0,
        serviceNumber: -1,
        applyTime: '-1',
        serviceType: '',
        serviceTypeText: '',
        refundPrice: 0,
        lastLogisticsInfo: {},
        lastLogisticsDetail: {},
        products: [
          {
            imageURL: 'https://pop.nosdn.127.net/19e33c9b-6c22-4a4b-96da-1cb7afb32712',
            title: 'BEYOND博洋家纺 床上套件 秋冬保暖纯棉床单被套 双人被罩 磨毛全棉印花床品四件套',
            price: '499',
            quantity: 2
          },
        ]
      }
    },
    mounted() {
      const { orderId } = this.$route.params;
      getOrderReturnInfo(orderId).then(res => {
        console.log('getOrderReturnInfo success', res)
        const { returnInfo, orderItems, lastLogisticsInfo } = res;
        this.lastLogisticsInfo = lastLogisticsInfo
        if (lastLogisticsInfo && lastLogisticsInfo.lastLogisticsDetail) {
          const lastLogisticsDetail = lastLogisticsInfo.lastLogisticsDetail;
          this.lastLogisticsDetail = {
            ...lastLogisticsDetail,
            logisticsTimeText: moment(lastLogisticsDetail.logisticsTime).format("YYYY-MM-DD HH:mm"),
          }
        }
        this.serviceType = returnInfo.serviceType
        this.serviceTypeText = returnInfo.serviceTypeText
        this.serviceNumber = returnInfo.serviceNumber
        this.applyTime = moment(returnInfo.createTime).format("YYYY-MM-DD HH:mm")
        this.refundPrice = returnInfo.refundPrice
        this.products = orderItems.map(item => {
          return {
            ...item,
            title: item.skuName,
            picUrls: [item.skuImage],
            price: item.presentTotal,
          }
        })
      })
    },
  }
</script>

<style>

</style>
