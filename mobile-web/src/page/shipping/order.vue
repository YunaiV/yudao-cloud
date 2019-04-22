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


      <!-- 优惠券单元格 -->
      <van-coupon-cell
              :coupons="coupons"
              :chosen-coupon="chosenCoupon"
              @click="showCouponPopup = true"
      />
      <!-- 优惠券列表 -->
      <van-popup v-model="showCouponPopup" position="bottom">
          <van-coupon-list
                  :coupons="coupons"
                  :chosen-coupon="chosenCoupon"
                  :disabled-coupons="disabledCoupons"
                  @change="onCouponChange"
                  @exchange="onCouponExchange"
          />
      </van-popup>

    <div style="height:15px;"></div>
    <van-cell-group class="total">
      <van-cell title="商品总额" :value="fee.buyTotal / 100.0"/>
      <van-cell title="运费" :value="+ fee.postageTotal / 100.0"/>
      <van-cell title="折扣" :value="- fee.discountTotal / 100.0"/>
      <van-cell title="实付金额" :value="fee.presentTotal / 100.0" style="font-weight: 700;"/>
    </van-cell-group>

    <div style="height:50px;"></div>
    <van-submit-bar
      :price="fee.presentTotal"
      button-text="提交订单"
      label='实付金额：'
      @submit="onSubmit"
    />

  </div>
</template>

<script>

  import {
    createOrder,
    getOrderConfirmCreateOrder,
    getCartConfirmCreateOrder,
    createOrderFromCart
  } from '../../api/order';
  import {GetDefaultAddress} from '../../api/user';
  import orderStore from '../../store/order';
  import { Dialog } from 'vant';

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

        // 商品 + 促销相关
        itemGroups: [],
        fee: {
          originalTotal: undefined,
          discountTotal: undefined,
          postageTotal: undefined,
          presentTotal: undefined,
        },
        // products: [], // 应该没用了

        // 优惠劵相关
        showCouponPopup: false,
        coupons: [],
        disabledCoupons: [],
        chosenCoupon: -1,
      };
    },
    methods: {
      onCouponChange(index) {
        // debugger;
        let couponCardId = this.coupons[index].id;
        if (this.from === 'direct_order') {
          getOrderConfirmCreateOrder(this.skuId, this.quantity, couponCardId).then(data => {
            // this.itemGroups = data.itemGroups;
            this.fee = data.fee;
            this.coupons[index].value = data.couponCardDiscountTotal; // 修改优惠劵减免的金额
          })
        } else if (this.from === 'cart') {
          getCartConfirmCreateOrder(couponCardId).then(data => {
            // this.itemGroups = data.itemGroups;
            this.fee = data.fee;
            this.coupons[index].value = data.couponCardDiscountTotal; // 修改优惠劵减免的金额
          })
        }
        this.chosenCoupon = index;
        this.showCouponPopup = false;
      },
      onCouponExchange(a, b, c) {
        Dialog.alert({
          title: '系统提示',
          message: '暂未开发', // TODO 芋艿
        });
      },

      onSubmit() {
        const userAddressId = this.addressData.id;
        if (!userAddressId) {
          Dialog.alert({
            title: '系统提示',
            message: '请选择收获地址',
          });
          return;
        }

        const remark = '';
        const couponCardId = this.chosenCoupon >= 0 ? this.coupons[this.chosenCoupon].id : undefined;

        if (this.from === 'direct_order') {
            const { skuId, quantity } = this.$route.query;
            const orderItems = [{
              skuId,
              quantity,
              couponCardId,
            }];
            createOrder({
              orderItems,
              userAddressId,
              remark,
            }).then(result => {
              if (result) {
                // const { orderNo } = result;
                // this.$router.push({  //核心语句
                //   path:`/order/success`,   //跳转的路径
                //   query:{           //路由传参时push和query搭配使用 ，作用时传递参数
                //     ...result,
                //   }
                // });
                this.$router.push('/pay?appId=POd4RC6a&orderId=' + result.id + '&returnUrl=' + encodeURI('/user/order/info/' + result.id));
              }
            });
        } else if (this.from === 'cart') {
          createOrderFromCart(userAddressId, couponCardId, remark).then(result => {
            if (result) {
              // const { orderNo } = result;
              // this.$router.push({  //核心语句
              //   path:`/order/success`,   //跳转的路径
              //   query:{           //路由传参时push和query搭配使用 ，作用时传递参数
              //     ...result,
              //   }
              // });
              this.$router.push('/pay?appId=POd4RC6a&orderId=' + result.id + '&returnUrl=' + encodeURI('/user/order/info/' + result.id));
            }
          });
        }
      },
      convertProduct(item) {
        return {
          ...item.spu,
          quantity: item.buyQuantity,
          price: item.buyPrice || item.price,
          sku: {
            ...item,
            spu: undefined,
          }
        };
      },
      convertCouponList(cards) {
        let newCards = [];
        for (let i in cards) {
          let card = cards[i];
          newCards.push({
            id: card.id,
            name: card.title,
            condition: '满 ' + card.priceAvailable / 100.0 + ' 元可用',
            startAt: card.validStartTime / 1000,
            endAt: card.validEndTime / 1000,
            // description: '述信息，优惠券可用时展示',
            reason: card.unavailableReason,
            value:	0,
            valueDesc: card.preferentialType === 1 ? card.priceOff / 100 : card.percentOff / 10.0,
            unitDesc: card.preferentialType === 1 ? '元' : '折'
          })
        }
        return newCards;
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
      // debugger;
      if (this.from === 'direct_order') {
        getOrderConfirmCreateOrder(this.skuId, this.quantity).then(data => {
          this.itemGroups = data.itemGroups;
          this.fee = data.fee;
          // 获得优惠劵列表
          this.coupons = this.convertCouponList(data.couponCards.filter(function (element) {
            return element.available;
          }));
          this.disabledCoupons = this.convertCouponList(data.couponCards.filter(function (element) {
            return !element.available;
          }));
        })
      } else if (this.from === 'cart') {
        getCartConfirmCreateOrder().then(data => {
          this.itemGroups = data.itemGroups;
          this.fee = data.fee;
          // 获得优惠劵列表
          this.coupons = this.convertCouponList(data.couponCards.filter(function (element) {
            return element.available;
          }));
          this.disabledCoupons = this.convertCouponList(data.couponCards.filter(function (element) {
            return !element.available;
          }));
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
      if (this.$route.query.from === 'cart') {
        this.from = this.$route.query.from;
      }
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
