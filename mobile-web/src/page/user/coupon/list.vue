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
                            v-model="loading"
                            :finished="finished"
                            @load="onLoad"
                            >
                        <li  v-for="(item,index) in list" :key="index" :class="'couponitem '+(item.show?'show':'') ">
                            <div class="couponTop">
                                <div class="cpnamount">
                                    <div class="amountWrap">
                                        <div class="amount">
                                                <span class="amountSign" v-if="item.SignPosition=='left'" >{{item.Sign}}</span>
                                                <span class="amountNum">{{item.Coupon}}</span>
                                                <span class="amountSign" v-if="item.SignPosition=='right'" >{{item.Sign}}</span>                   
                                        </div>
                                        <div class="condition">
                                            <span>{{item.Condition}}</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="couponInfoWrap">
                                    <div class="cpninfo">
                                        <div class="detail">
                                        <span class="name">{{item.Name}}</span></div>
                                    </div>
                                    <div class="validity">
                                        <span>{{item.BeginDate}}-{{item.EndDate}}
                                        </span>
                                    </div>
                                    <van-button type="danger" size="mini">立即使用</van-button>
                                </div>
                            </div>
                            <div class="couponMid " v-if="item.Info!=''">
                                <span>详细信息</span>
                                <van-icon name="arrow" class="down" @click="onShowInfo(item.Id,index)" />
                            </div>
                            <div class="info"  v-if="item.Info!=''" >
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
                            v-model="useLoading"
                            :finished="useFinished"
                            @load="onLoadUse"
                            >
                        <li  v-for="(item,index) in useList" :key="index" class="couponitem">
                            <div class="couponTop">
                                <div class="cpnamount">
                                    <div class="amountWrap">
                                        <div class="amount">
                                                <span class="amountSign" v-if="item.SignPosition=='left'" >{{item.Sign}}</span>
                                                <span class="amountNum">{{item.Coupon}}</span>
                                                <span class="amountSign" v-if="item.SignPosition=='right'" >{{item.Sign}}</span>                   
                                        </div>
                                        <div class="condition">
                                            <span>{{item.Condition}}</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="couponInfoWrap">
                                    <div class="cpninfo">
                                        <div class="detail">
                                        <span class="name">{{item.Name}}</span></div>
                                    </div>
                                    <div class="validity">
                                        <span>{{item.BeginDate}}-{{item.EndDate}}
                                        </span>
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
                            v-model="endLoading"
                            :finished="endFinished"
                            @load="onLoadEnd"
                            >
                        <li  v-for="(item,index) in endList" :key="index" class="couponitem">
                            <div class="couponTop">
                                <div class="cpnamount">
                                    <div class="amountWrap">
                                        <div class="amount">
                                                <span class="amountSign" v-if="item.SignPosition=='left'" >{{item.Sign}}</span>
                                                <span class="amountNum">{{item.Coupon}}</span>
                                                <span class="amountSign" v-if="item.SignPosition=='right'" >{{item.Sign}}</span>                   
                                        </div>
                                        <div class="condition">
                                            <span>{{item.Condition}}</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="couponInfoWrap">
                                    <div class="cpninfo">
                                        <div class="detail">
                                        <span class="name">{{item.Name}}</span></div>
                                    </div>
                                    <div class="validity">
                                        <span>{{item.BeginDate}}-{{item.EndDate}}
                                        </span>
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
import { GetCoupon,ExchangeCoupon } from "../../../api/user.js";

export default {
  components: {
  },
  data() {
    return {
            loading:false,
            finished:false,
            list:[],
            page:0,

            
            useLoading:false,
            useFinished:false,
            useList:[],
            usePage:0,

            
            endLoading:false,
            endFinished:false,
            endList:[],
            endPage:0,

            couponCode:'',
            exchangeLoading:false,
    };
  },
  computed: {
  },
  methods: {
        onLoad() {
            this.page++;
            GetCoupon({page:this.page}).then(response=>{
                response.List.forEach(item => {
                    item.show=false;
                    this.list.push(item);
                });
                this.loading = false;
                if(response.TotalPage<=this.page){
                    this.finished = true;
                }
            
            })
        },
        onShowInfo(id,index){
            this.list.forEach((item,itemIndex) => {
                if(index==itemIndex){
                    item.show=!item.show;
                    return;
                }
            });
        },
        onLoadUse() {
            this.usePage++;
            GetCoupon({page:this.usePage}).then(response=>{
                response.List.forEach(item => {
                    this.useList.push(item);
                });
                this.useLoading = false;
                if(response.TotalPage<=this.usePage){
                    this.useFinished = true;
                }
            
            })
        },
        onLoadEnd() {
            this.endPage++;
            GetCoupon({page:this.endPage}).then(response=>{
                response.List.forEach(item => {
                    this.endList.push(item);
                });
                this.endLoading = false;
                if(response.TotalPage<=this.endPage){
                    this.endFinished = true;
                }
            
            })
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
