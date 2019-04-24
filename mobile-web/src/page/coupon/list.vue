<template>
    <div>
        <headerNav title="我的优惠券"/>
        <van-cell-group>
        <van-field
            center
            clearable
            placeholder="请输入优惠码"

            v-model="couponCode"
        >
            <van-button slot="button" size="small" type="primary" :loading="exchangeLoading" @click="onExchange">兑换</van-button>
        </van-field>
        </van-cell-group>
        <van-tabs >
            <van-tab title="未使用">
                <ul>
                    <van-list
                            v-model="unusedData.loading"
                            :finished="unusedData.finished"
                            @load="onLoad"
                            >
                        <li  v-for="(item,index) in unusedData.list" :key="index" :class="'couponitem '+(item.show?'show':'') ">
                            <div class="couponTop">
                                <div class="cpnamount">
                                    <div class="amountWrap">
                                        <div class="amount" v-if="item.preferentialType === 1">
                                                <span class="amountSign" >￥</span>
                                                <span class="amountNum">{{item.priceOff / 100}}</span>
                                        </div>
                                        <div class="amount" v-else="item.preferentialType === 2">
                                            <span class="amountNum">{{item.percentOff / 10.0}}</span>
                                            <span class="amountSign">折</span>
                                        </div>
                                        <div class="condition">
                                            <span>满 {{item.priceAvailable / 100.0}} 元可用</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="couponInfoWrap">
                                    <div class="cpninfo">
                                        <div class="detail">
                                        <span class="name">{{item.title}}</span></div>
                                    </div>
                                    <div class="validity">
                                        <span>{{item.validStartTime | formatDate('yyyy-MM-dd')}}
                                            ~ {{item.validEndTime | formatDate('yyyy-MM-dd')}}</span>
                                    </div>
                                    <van-button type="danger" size="mini">立即使用</van-button>
                                </div>
                            </div>
<!--                            <div class="couponMid " v-if="item.Info!=''">-->
<!--                                <span>详细信息</span>-->
<!--                                <van-icon name="arrow" class="down" @click="onShowInfo(item.Id,index)" />-->
<!--                            </div>-->
<!--                            <div class="info"  v-if="item.Info!=''" >-->
<!--                                <div class="text">-->
<!--                                    {{item.Info}}-->
<!--                                </div>-->
<!--                            </div>-->
                            <!-- todo 芋艿，后续做优化。指定哪些商品 / 分类可用 -->
                            <div class="info" v-if="item.Info!=''" >
                                <div class="text">
                                    {{item.Info}}
                                </div>
                            </div>
                        </li>
                    </van-list>
                </ul>
            </van-tab>
            <van-tab title="使用记录">
                <ul class="gray" >
                    <van-list
                            v-model="usedData.loading"
                            :finished="usedData.finished"
                            @load="onLoadUse"
                            >
                        <li  v-for="(item,index) in usedData.list" :key="index" class="couponitem">
                            <div class="couponTop">
                                <div class="cpnamount">
                                    <div class="amountWrap">
                                        <div class="amount" v-if="item.preferentialType === 1">
                                            <span class="amountSign" >￥</span>
                                            <span class="amountNum">{{item.priceOff / 100}}</span>
                                        </div>
                                        <div class="amount" v-else="item.preferentialType === 2">
                                            <span class="amountNum">{{item.percentOff / 10.0}}</span>
                                            <span class="amountSign">折</span>
                                        </div>
                                        <div class="condition">
                                            <span>满 {{item.priceAvailable}} 元可用</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="couponInfoWrap">
                                    <div class="cpninfo">
                                        <div class="detail">
                                        <span class="name">{{item.title}}</span></div>
                                    </div>
                                    <div class="validity">
                                        <span>{{item.validStartTime | formatDate('yyyy-MM-dd')}}
                                            ~ {{item.validEndTime | formatDate('yyyy-MM-dd')}}</span>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </van-list>
                </ul>
            </van-tab>
            <van-tab title="已过期">
                <ul class="gray" >

                    <van-list
                            v-model="expireData.loading"
                            :finished="expireData.finished"
                            @load="onLoadEnd"
                            >
                        <li  v-for="(item,index) in expireData.list" :key="index" class="couponitem">
                            <div class="couponTop">
                                <div class="cpnamount">
                                    <div class="amountWrap">
                                        <div class="amount" v-if="item.preferentialType === 1">
                                            <span class="amountSign" >￥</span>
                                            <span class="amountNum">{{item.priceOff / 100}}</span>
                                        </div>
                                        <div class="amount" v-else="item.preferentialType === 2">
                                            <span class="amountNum">{{item.percentOff / 10.0}}</span>
                                            <span class="amountSign">折</span>
                                        </div>
                                        <div class="condition">
                                            <span>满 {{item.priceAvailable}} 元可用</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="couponInfoWrap">
                                    <div class="cpninfo">
                                        <div class="detail">
                                            <span class="name">{{item.title}}</span></div>
                                    </div>
                                    <div class="validity">
                                        <span>{{item.validStartTime | formatDate('yyyy-MM-dd')}}
                                            ~ {{item.validEndTime | formatDate('yyyy-MM-dd')}}</span>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </van-list>
                </ul>
            </van-tab>
        </van-tabs>
    </div>
</template>

<script>
import { ExchangeCoupon } from "../../api/user.js";
import { getCouponPage } from "../../api/promotion.js";

export default {
  components: {
  },
  data() {
    return {
            // loading:false,
            // finished:false,
            // list:[],
            // page:0,
            //
            //
            // useLoading:false,
            // useFinished:false,
            // useList:[],
            // usePage:0,
            //
            //
            // endLoading:false,
            // endFinished:false,
            // endList:[],
            // endPage:0,

            couponCode:'',
            exchangeLoading:false,

      unusedData: {
        page: 0,
        pageSize: 10,
        list: [],
        loading: false,
        finished: false,
      },

      usedData: {
        page: 0,
        pageSize: 10,
        list: [],
        loading: false,
        finished: false,
      },

      expireData: {
        page: 0,
        pageSize: 10,
        list: [],
        loading: false,
        finished: false,
      },
    };
  },
  computed: {
  },
  methods: {
        onLoad() {
            // 进入下一页
            let page = this.unusedData.page + 1;
            getCouponPage(1, page, this.unusedData.pageSize).then(data => {
              // debugger;
              // 设置下页面
              this.unusedData.page = page;
              // 数据保存到 list 中
              this.unusedData.list.push(...data.list);
              // 判断页数
              if (this.unusedData.list.length >= data.total) {
                this.unusedData.finished = true;
              }
              // 标记不在加载中
              this.unusedData.loading = false;
            });

            // GetCoupon({page:this.page}).then(response=>{
            //     response.List.forEach(item => {
            //         item.show=false;
            //         this.list.push(item);
            //     });
            //     this.loading = false;
            //     if(response.TotalPage<=this.page){
            //         this.finished = true;
            //     }
            //
            // })
        },
        // onShowInfo(id,index){
        //     this.list.forEach((item,itemIndex) => {
        //         if(index==itemIndex){
        //             item.show=!item.show;
        //             return;
        //         }
        //     });
        // },
        onLoadUse() {
          // 进入下一页
          let page = this.usedData.page + 1;
          getCouponPage(2, page, this.usedData.pageSize).then(data => {
            // debugger;
            // 设置下页面
            this.usedData.page = page;
            // 数据保存到 list 中
            this.usedData.list.push(...data.list);
            // 判断页数
            if (this.usedData.list.length >= data.total) {
              this.usedData.finished = true;
            }
            // 标记不在加载中
            this.usedData.loading = false;
          });
        },
        onLoadEnd() {
          // 进入下一页
          let page = this.expireData.page + 1;
          getCouponPage(3, page, this.expireData.pageSize).then(data => {
            // debugger;
            // 设置下页面
            this.expireData.page = page;
            // 数据保存到 list 中
            this.expireData.list.push(...data.list);
            // 判断页数
            if (this.expireData.list.length >= data.total) {
              this.expireData.finished = true;
            }
            // 标记不在加载中
            this.expireData.loading = false;
          });
        },
        onExchange(){
            if(this.exchangeLoading){
                return;
            }
            this.exchangeLoading=true;
            ExchangeCoupon(this.couponCode).then(response=>{
                this.$toast('兑换成功');
                this.exchangeLoading=false;
                this.$router.go(0);
            })
        }
  }
};
</script>

<style lang="less">
.couponitem {
        padding: 0 10px;
  margin-top: 10px;
  position: relative;
  font-size: 12px;
  .couponTop {
    background-color: #fcebeb;
    border-left: 1px solid #f3d4d4;
    border-right: 1px solid #f3d4d4;
    border-bottom: 1px dashed  #f3d4d4;
    border-radius: 8px;
    border-top-left-radius: 5px;
    border-top-right-radius: 5px;
    .cpnamount {
      position: relative;
      height: 90px;
      width: 34.08%;
      text-align: center;
      float: left;
      border-right: 1px dashed #f3d4d4;
    }
    .amountWrap {
      width: 100%;
      position: absolute;
      top: 50%;
      -webkit-transform: translate(0, -50%);
      transform: translate(0, -50%);
      .amount {
        font-size: 15px;
        .amountSign {
          margin-right: 3px;
        }
        .amountNum {
          font-size: 39px;
          line-height: 1;
          font-weight: bold;
        }
      }
    }
  }
  .couponInfoWrap {
    position: relative;
    margin-left: 37.465%;
    height: 90px;
    .cpninfo {
      position: absolute;
      top: 26px;
      -webkit-transform: translate(0, -50%);
      transform: translate(0, -50%);
      width: 100%;
      .detail {
        padding-right: 15px;
        word-break: break-all;
        .name {
          font-size: 13px;
          color: #000;
        }
      }
    }
    .validity {
        position: absolute;
        bottom: 16px;
        font-size: 10px;
    }
    button{
        right: 16px;
        position: absolute;
        bottom: 16px;
    }
  }
  .couponMid {
    position: relative;
    height: 31px;
    line-height: 31px;
    padding-left: 16px;
    font-size: 12px;

    background-color: #fcebeb;
    border-left: 1px solid #f3d4d4;
    border-right: 1px solid #f3d4d4;
    border-bottom: 1px solid #f3d4d4;
    border-radius: 8px;
    border-bottom-left-radius: 5px;
    border-bottom-right-radius: 5px;
    i{
        right: 16px;
        position: absolute;
        top: 8px;
         -webkit-transform: rotate(90deg);
        transform: rotate(90deg);
    }
  }

  .info{
      display: none;
      background-color: #fcebeb;
    border-left: 1px solid #f3d4d4;
    border-right: 1px solid #f3d4d4;
    border-bottom: 1px solid #f3d4d4;
    border-bottom-left-radius: 8px;
    border-bottom-right-radius: 8px;
padding: 10px 25px 12px 15px;
    font-size: 12px;
  }
}
  .show{

  .couponMid {
          border-bottom: 1px dashed #f3d4d4;
          border-bottom-left-radius: 0;
    border-bottom-right-radius: 0;
    i{
         -webkit-transform: rotate(-90deg);
        transform: rotate(-90deg);
    }
  }
  .info{
      display: block;
  }
  }
  .gray{
      -webkit-filter: grayscale(100%);filter:progid:DXImageTransform.Microsoft.BasicImage(graysale=1);
  }
</style>
