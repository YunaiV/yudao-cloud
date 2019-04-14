<template>
  <div style="background: #f7f7f7;">
    <headerNav title="订单详情"/>
    <van-steps :active="active">
      <van-step>提交订单</van-step>
      <van-step>配送中</van-step>
      <van-step>交易完成</van-step>
    </van-steps>
    <van-cell v-if="orderInfo.status >= 2 "
              class="logistics"
              to="/user/order/logistics/1"
              :title="latestLogisticsDetail.logisticsInformation"
              :label="latestLogisticsDetail.logisticsTimeText"
              icon="logistics"
              is-link
    />
    <div style="height:15px;"></div>
    <van-cell-group>
      <van-cell
        center
        :border="false"
      >
        <template>
          <div>{{recipient.name}} {{recipient.mobile}}</div>
          <div>{{recipient.address}}</div>
        </template>
      </van-cell>
    </van-cell-group>

    <div style="height:15px;"></div>
    <div v-for="(product,i) in products" :key="i">
      <product-card :product='product'/>
    </div>
    <div style="height:15px;"></div>
    <van-cell-group>
      <van-cell title="订单编号" :value="orderInfo.orderNo"/>
      <van-cell title="下单时间" :value="orderInfo.createTimeText"/>
      <van-cell title="订单状态" :value="orderInfo.statusText"/>
    </van-cell-group>
    <div style="height:15px;"></div>
    <van-cell-group class="total">
      <van-cell title="商品总额" :value="orderInfo.price"/>
      <van-cell title="运费" :value="'+' + orderInfo.logisticsPrice / 100"/>
      <van-cell title="实付金额" :value="orderInfo.payAmount" style="font-weight: 700;"/>
    </van-cell-group>
    <div class="footer">
      <div class="munu">
        <van-button v-if="orderInfo.status === 3 " size="small">退货</van-button>
        <van-button v-if="orderInfo.status === 3 " size="small" v-on:click="clickConfirmReceiving(orderId)">确认收货</van-button>
        <van-button v-if="orderInfo.status === 1 " size="small" type="danger">支付</van-button>
      </div>
    </div>
  </div>
</template>

<script>

  import moment from 'moment';
  import { getOrderInfo, confirmReceiving } from '../../../api/order';

  export default {
    data() {
      return {
        active: 0,
        orderId: 0,
        orderInfo: {},
        recipient: {},
        latestLogisticsDetail: {},
        products: [
          {
            imageURL: 'https://pop.nosdn.127.net/19e33c9b-6c22-4a4b-96da-1cb7afb32712',
            title: 'BEYOND博洋家纺 床上套件 秋冬保暖纯棉床单被套 双人被罩 磨毛全棉印花床品四件套',
            price: '499',
            quantity: 2
          },
          {
            imageURL: 'https://pop.nosdn.127.net/19e33c9b-6c22-4a4b-96da-1cb7afb32712',
            title: 'BEYOND博洋家纺 床上套件 秋冬保暖纯棉床单被套 双人被罩 磨毛全棉印花床品四件套',
            price: '499',
            quantity: 2
          },
        ]
      }
    },
    methods: {
      clickConfirmReceiving(orderId) {
        confirmReceiving(orderId).then(res => {
          this.queryOrderPage(this.queryParams)
        })
      },
    },
    mounted() {
      const { id } = this.$route.params;
      this.orderId = id;
      getOrderInfo(id).then(res => {
        const { status, recipient, latestLogisticsDetail} = res;
        // 提交订单、配送中、交易成功
        if ([1, 2].indexOf(status) !== -1) {
          this.active = 0
        } else if (status === 3) {
          this.active = 1
        } else if (status >= 4) {
          this.active = 2
        }

        // 收件人信息
        this.recipient = recipient;

        // 订单info
        this.orderInfo = {
          ...res,
          createTimeText: moment(res.createTime).format("YYYY-MM-DD HH:mm"),
        };

        // 最新物流信息
        let logisticsTimeText = '';
        if (latestLogisticsDetail) {
          logisticsTimeText = moment(latestLogisticsDetail.logisticsTime).format("YYYY-MM-DD HH:mm");
        }

        this.latestLogisticsDetail = {
          ...latestLogisticsDetail,
          logisticsTimeText: logisticsTimeText,
        }
      })
    }
  }
</script>

<style lang="less">
  .logistics {
    margin-top: 15px;

    i {
      line-height: 60px;
      font-size: 20px;
    }

    .van-cell__title span {
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      -webkit-line-clamp: 2;
      -webkit-box-orient: vertical;
    }

    .van-cell__label {
      color: #999;
    }
  }

  .total {
    .van-cell__value {
      color: red;
    }
  }

  .footer {
    height: 50px;

    .munu {
      position: fixed;
      height: 49px;
      border-top: 1px solid #e5e5e5;
      bottom: 0;
      background: #fff;
      width: 100%;
      line-height: 24px;
      text-align: right;

      .van-button {
        margin-right: 10px;
      }
    }
  }
</style>
