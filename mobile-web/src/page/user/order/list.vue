<template>
  <div>
    <headerNav title="我的订单"/>
    <van-tabs v-model="active" @change="onTabChange">
      <van-tab title="全部"></van-tab>
      <van-tab title="待付款"></van-tab>
      <van-tab title="待收货"></van-tab>
      <van-tab title="已完成"></van-tab>
      <van-tab title="已取消"></van-tab>
    </van-tabs>

    <div v-for="(item,index) in list" :key="index">
      <van-cell-group class="order-item">
        <van-panel :title="'订单：'+item.ordercode" :status="['', '代付款', '待发货', '待收货', '已完成', '已关闭'][item.state]">
          <div slot="header">
            <van-cell class="title" :title="'订单：'+item.ordercode" :value="item.state"
                      :to="'/user/order/info/'+item.orderid"/>
          </div>
          <div>
            <router-link :to="'/user/order/info/'+item.orderid">
              <div v-if="item.products.length==1" v-for="(product,i) in item.products" :key="i">
                <product-card :product='product'/>
              </div>
              <div v-if="item.products.length > 0" class="more">
                <div class="item" v-for="(product,i) in item.products" :key="i">
                  <div class="products-item">
                    <img :src="product.imageURL"/>
                    <span class="product-title">{{product.title}}</span>
                    <!--<span class="product-title">￥{{product.price / 100}} x{{product.quantity}}</span>-->
                  </div>
                </div>
              </div>
            </router-link>
          </div>
          <div slot="footer" class="footer">
            <span class="total">总价：{{item.payAmount / 100}} 元</span>
            <router-link :to="'/user/order/logistics/'+item.orderid">
              <van-button v-if="[3,4,5].indexOf(item.status) != -1" size="small">查看物流</van-button>
            </router-link>
            <van-button v-if="item.status === 3 " size="small" v-on:click="clickConfirmReceiving(item)">确认收货</van-button>
            <van-button v-if="item.status === 1 " size="small" type="danger" @click="goPay(item.orderid)">
                    支付
            </van-button>
          </div>
        </van-panel>
      </van-cell-group>
    </div>
  </div>
</template>

<script>

  import {getOrderPage, confirmReceiving} from '../../../api/order';

  export default {
    components: {},
    data() {
      return {
        active: 0,
        list: [],
        row: {},
      }
    },
    methods: {
      goPay(itemId) {
        this.$router.push('/pay?appId=POd4RC6a&orderId=' + itemId + '&returnUrl=' + encodeURI('/user/order/info/' + itemId));
      },

      onTabChange(index) {
        // status 和 tab index 对应的关系
        const statusArray = [null, 1, 3, 4, 5];
        // if (index === 0) {
        //   // 全部
        // } else if (index === 1) {
        //   // 待付款 1
        // } else if (index === 2) {
        //   // 待收货 3
        // } else if (index === 3) {
        //   // 已完成 4
        // } else if (index === 4) {
        //   // 取消 5
        // }


        this.queryOrderPage({
          status: statusArray[index],
        })
      },

      queryOrderPage(params) {
        this.queryParams = params;
        const statusArray = ['', '待付款', '待发货', '待收货', '已完成', '已关闭']
        getOrderPage({
          pageNo: 0,
          pageSize: 10,
          ...params,
        }).then(data => {
          const {orders} = data;
          const list = orders.map(order => {
            const {orderItems} = order;
            const products = orderItems.map(order => {

              return {
                imageURL: order.skuImage,
                title: order.skuName,
                price: order.price,
                quantity: order.quantity,
              };
            });

            return {
              orderid: order.id,
              ordercode: order.orderNo,
              state: `${statusArray[order.status]}`,
              status: order.status,
              products,
              payAmount: order.payAmount,
            };
          });

          this.list = list;
          // console.log('list', list)
        });
      },
      clickConfirmReceiving({ orderid }) {
        confirmReceiving(orderid).then(res => {
          this.queryOrderPage(this.queryParams)
        })
      },
    },
    mounted() {
      this.queryOrderPage()
    },
  }
</script>

<style lang="less">

  .order-item {
    margin-bottom: 10px;
    font-size: 12px;

    .title {
      border-bottom: 1px solid #e5e5e5;

      .van-cell__title {
        flex: 2;
      }

      .van-cell__value {
        color: red;
      }
    }


    .van-panel__footer {
      text-align: right;
      border-bottom: 1px solid #e5e5e5;
    }

    .footer {
      display: block;
      height: 30px;
    }

    .van-button {
      margin-left: 5px;
    }

    .total {
      position: absolute;
      top: 17px;
      left: 15px;
      font-size: 13px;
    }

    .more {
      overflow-x: scroll;
      white-space: nowrap;
      -webkit-overflow-scrolling: touch;
      margin: 5px 0 5px 15px;

      .item {
        width: 90px;
        height: 90px;
        margin-right: 10px;
        display: inline-block;

        img {
          width: 100%;
        }
      }
    }



    .products-item {
      /*display: flex;*/
      /*flex-direction: row;*/

      .product-title {
        justify-items: center;
        margin: 10px;
      }
    }
  }
</style>
