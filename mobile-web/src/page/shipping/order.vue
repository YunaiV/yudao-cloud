<template>
  <div class="order">
    <headerNav title="确认订单"/>
    <van-cell
      center
      :border="false"
      class="contact-card"
      is-link
      to="/user/address?id=2"
    >
      <template v-if="type === 'add'">
        <strong>选择地址</strong>
      </template>
      <template v-else>
        <strong>{{addressData.name}} {{addressData.mobile}}</strong>
        <div>{{addressData.address}}</div>
      </template>
    </van-cell>
      <div style="height:15px;"></div>



<!--    <div class="card" v-for="(product,i) in products" :key="i">-->
<!--      <product-card :product='product'/>-->
<!--    </div>-->
    <div v-for="(itemGroup, i) in itemGroups" >
        <div class="card" v-for="(item, j) in itemGroup.items" :key="j">
            <product-card :product='convertProduct(item)'/>
        </div>
        <div style="height:15px;"></div>
    </div>



    <div style="height:15px;"></div>
    <van-cell-group>
      <van-field
        label="留言"
        type="textarea"
        placeholder="请输入留言"
        rows="1"
        autosize
      />
    </van-cell-group>
    <div style="height:15px;"></div>
    <van-cell-group class="total">
      <van-cell title="优惠券" is-link value="抵扣¥5.00"/>
    </van-cell-group>

    <div style="height:15px;"></div>
    <van-cell-group class="total">
      <van-cell title="商品总额" :value="fee.originalTotal"/>
      <van-cell title="运费" :value="+ fee.postageTotal"/>
      <van-cell title="折扣" :value="- fee.discountTotal"/>
      <van-cell title="实付金额" :value="fee.presentTotal" style="font-weight: 700;"/>
    </van-cell-group>

    <div style="height:50px;"></div>
    <van-submit-bar
      :price="3050"
      button-text="提交订单"
      label='实付金额：'
      @submit="onSubmit"
    />

  </div>
</template>

<script>

  import {createOrder, getConfirmCreateOrder} from '../../api/order';
  import {GetDefaultAddress} from '../../api/user';
  import orderStore from '../../store/order'

  export default {
    data() {
      return {
        from: 'direct_order', // 目前有两个来源。direct_order：直接下单; card: 购物车下单。
        // 如下两个参数，在直接下单时使用
        skuId: this.$route.query.skuId,
        quantity: this.$route.query.quantity,

        type: "add",
        addressData: {

        },
        itemGroups: [],
        fee: {
          originalTotal: undefined,
          discountTotal: undefined,
          postageTotal: undefined,
          presentTotal: undefined,
        },
        products: [],
      };
    },
    methods: {
      onSubmit() {
        const { skuId, quantity } = this.$route.query;
        const userAddressId = this.addressData.id;
        const remark = '';

        const orderItems = [{
          skuId,
          quantity,
        }];

        createOrder({
          orderItems,
          userAddressId,
          remark,
        }).then(result => {
          if (result) {
            const { orderNo } = result;
            this.$router.push({  //核心语句
              path:`/order/success`,   //跳转的路径
              query:{           //路由传参时push和query搭配使用 ，作用时传递参数
                ...result,
              }
            });
          }
        })
      },
      convertProduct(item) {
        // debugger;
        return {
          ...item.spu,
          quantity: item.buyQuantity,
          price: item.price,
          sku: {
            ...item,
            spu: undefined,
          }
        };
      }
    },
    mounted: function() {
      if (this.$store.state.addressData.name) {
        this.type = 'add1';
      } else {
        this.type = 'add';
      }
      this.addressData = this.$store.state.addressData;

      // 加载商品信息
      if (this.from === 'direct_order') {
        getConfirmCreateOrder(this.skuId, this.quantity).then(data => {
          this.itemGroups = data.itemGroups;
          this.fee = data.fee;
        })
      }

    },
    created() {
      // 加载地址
      GetDefaultAddress().then((result) => {
        if (!this.addressData.name && result) {
          this.type = 'add1';
          this.addressData = result;
        }
      })
      // 处理来源
    },
    store: orderStore,
  };
</script>

<style lang="less">
  .order {
    font-size: 14px;
    background: #f7f7f7;

    .contact-card::before {
      content: "";
      left: 0;
      right: 0;
      bottom: 0;
      height: 2px;
      position: absolute;
      background: -webkit-repeating-linear-gradient(
        135deg,
        #ff6c6c 0,
        #ff6c6c 20%,
        transparent 0,
        transparent 25%,
        #3283fa 0,
        #3283fa 45%,
        transparent 0,
        transparent 50%
      );
      background: repeating-linear-gradient(
        -45deg,
        #ff6c6c 0,
        #ff6c6c 20%,
        transparent 0,
        transparent 25%,
        #3283fa 0,
        #3283fa 45%,
        transparent 0,
        transparent 50%
      );
      background-size: 80px;
    }

    .total {
      .van-cell__value {
        color: red;
      }
    }

    .van-submit-bar__bar {
      border-top: 1px solid #f7f7f7;
    }

    .additional {
      .van-cell {
        padding: 0 15px;
        font-size: 12px;
      }

      .van-cell__title {
        flex: 11;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
      }

      .van-tag {
        line-height: 12px;
        margin-right: 5px;
      }

      .price {
        color: #e93b3d;
        font-size: 10px;

        span {
          font-size: 16px;
        }
      }
    }
  }
</style>
