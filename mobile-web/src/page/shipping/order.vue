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
        <strong>张三 138****6520</strong>
        <div>广东省深圳市南山区科技园</div>
      </template>
    </van-cell>
    <div style="height:15px;"></div>
    <div class="card" v-for="(product,i) in products" :key="i">
      <product-card :product='product'/>
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
      <van-cell title="商品总额" value="9.99"/>
      <van-cell title="运费" value="+ 0.00"/>
      <van-cell title="折扣" value="- 5.00"/>
      <van-cell title="实付金额" value="4.99" style="font-weight: 700;"/>
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

  import {createOrder} from '../../api/order';

  export default {
    data() {
      return {
        type: "add1",
        products: [
          {
            imageURL:
              "https://img10.360buyimg.com/mobilecms/s88x88_jfs/t17572/12/840082281/351445/e1828c58/5aab8dbbNedb77d88.jpg",
            title: "良品铺子 肉肉聚汇猪肉脯 猪蹄卤 辣味小吃520g",
            desc: "0.670kg/件，肉肉聚汇520g",
            price: "59.80",
            quantity: 2
          },
          {
            imageURL:
              "https://img10.360buyimg.com/mobilecms/s88x88_jfs/t22720/128/1410375403/319576/8dbd859f/5b5e69b3Nf4f0e9e7.jpg",
            title: "元朗 鸡蛋卷 饼干糕点 中秋礼盒 广东特产680g",
            desc: "1.320kg/件",
            price: "65.80",
            quantity: 1,
            gift: [
              {
                title: "星巴克（Starbucks）星冰乐小熊吊饰星巴克（Starbucks）星冰乐小熊吊饰",
                quantity: 2
              },
              {
                title: "星巴克（Starbucks）星冰乐小熊吊饰星巴克（Starbucks）星冰乐小熊吊饰",
                quantity: 1
              }
            ]
          },
          {
            imageURL:
              "https://img10.360buyimg.com/mobilecms/s88x88_jfs/t17572/12/840082281/351445/e1828c58/5aab8dbbNedb77d88.jpg",
            title: "良品铺子 肉肉聚汇猪肉脯 猪蹄卤 辣味小吃520g",
            desc: "0.670kg/件，肉肉聚汇520g",
            price: "59.80",
            quantity: 2
          },
        ]
      };
    },
    methods: {
      onSubmit() {
        this.$toast("点击按钮");
        const { skuId, quantity } = this.$route.query;
        const userAddressId = 1;
        const remark = '';

        const orderItems = [{
          skuId,
          quantity,
        }];

        createOrder({
          orderItems,
          userAddressId,
          remark,
        })
      },
    },


    activated() {
      //根据key名获取传递回来的参数，data就是map
      this.$on('selectAddress', function (data) {
        //赋值给首页的附近医院数据模型
        console.log(1);
      }.bind(this));
    },
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
