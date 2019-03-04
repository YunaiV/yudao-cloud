<template>
<div>
     <headerNav title="我的订单"/>
    <van-tabs v-model="active">
        <van-tab title="全部">
            
            <div v-for="(item,index) in list" :key="index">
                <van-cell-group class="order-item" >
                    <van-panel :title="'订单：'+item.ordercode" :status="item.state"  >
                    <div slot="header">
                        <van-cell class="title" :title="'订单：'+item.ordercode" :value="item.state" :to="'/user/order/info/'+item.orderid"/>
                    </div>
                        <div>
                            <router-link :to="'/user/order/info/'+item.orderid">
                            <div v-if="item.products.length==1" v-for="(product,i) in item.products" :key="i">
                                <product-card :product='product' />
                            </div>
                            <div  v-if="item.products.length>1" class="more" >
                                <div class="item" v-for="(product,i) in item.products" :key="i">
                                    <div >
                                        <img :src="product.imageURL"/>
                                    </div>
                                </div>
                            </div>
                            </router-link>
                        </div>
                        <div slot="footer">
                            <span class="total">总价：￥8154898.89</span>
                            <van-button size="small">确认收货</van-button>
                            <van-button size="small" type="danger">支付</van-button>
                        </div>
                    </van-panel>
                </van-cell-group>
            </div>

        </van-tab>
        <van-tab title="待付款">内容 2</van-tab>
        <van-tab title="待收货">内容 3</van-tab>
        <van-tab title="已完成">内容 4</van-tab>
        <van-tab title="已取消">内容 5</van-tab>
    </van-tabs>
</div>
</template>

<script>
export default {
    components:{
    },
    data(){
        return{
            active:0,
            
            list:[
                {
                    orderid:1,
                    ordercode:'4511248234235',
                    state:'待付款',
                    products:[
                        {
                            imageURL:'https://pop.nosdn.127.net/19e33c9b-6c22-4a4b-96da-1cb7afb32712',
                            title:'BEYOND博洋家纺 床上套件 秋冬保暖纯棉床单被套 双人被罩 磨毛全棉印花床品四件套',
                            price:'499',
                            quantity:2
                        },
                        {
                            imageURL:'https://pop.nosdn.127.net/19e33c9b-6c22-4a4b-96da-1cb7afb32712',
                            title:'BEYOND博洋家纺 床上套件 秋冬保暖纯棉床单被套 双人被罩 磨毛全棉印花床品四件套',
                            price:'499',
                            quantity:1
                        },
                        {
                            imageURL:'https://pop.nosdn.127.net/19e33c9b-6c22-4a4b-96da-1cb7afb32712',
                            title:'BEYOND博洋家纺 床上套件 秋冬保暖纯棉床单被套 双人被罩 磨毛全棉印花床品四件套',
                            price:'499',
                            quantity:2
                        },
                        {
                            imageURL:'https://pop.nosdn.127.net/19e33c9b-6c22-4a4b-96da-1cb7afb32712',
                            title:'BEYOND博洋家纺 床上套件 秋冬保暖纯棉床单被套 双人被罩 磨毛全棉印花床品四件套',
                            price:'499',
                            quantity:2
                        },
                        {
                            imageURL:'https://pop.nosdn.127.net/19e33c9b-6c22-4a4b-96da-1cb7afb32712',
                            title:'BEYOND博洋家纺 床上套件 秋冬保暖纯棉床单被套 双人被罩 磨毛全棉印花床品四件套',
                            price:'499',
                            quantity:2
                        },
                    ]
                },
                {
                    orderid:2,
                    ordercode:'4511248234235',
                    state:'待收货',
                    products:[
                        {
                            imageURL:'https://pop.nosdn.127.net/19e33c9b-6c22-4a4b-96da-1cb7afb32712',
                            title:'BEYOND博洋家纺 床上套件 秋冬保暖纯棉床单被套 双人被罩 磨毛全棉印花床品四件套',
                            price:'499',
                            quantity:2
                        }
                    ]
                },
                {
                    orderid:3,
                    ordercode:'4511248234235',
                    state:'已完成',
                    products:[
                        {
                            imageURL:'https://pop.nosdn.127.net/19e33c9b-6c22-4a4b-96da-1cb7afb32712',
                            title:'BEYOND博洋家纺 床上套件 秋冬保暖纯棉床单被套 双人被罩 磨毛全棉印花床品四件套',
                            price:'499',
                            quantity:2
                        }
                    ]
                },
                {
                    orderid:4,
                    ordercode:'4511248234235',
                    state:'已取消',
                    products:[
                        {
                            imageURL:'https://pop.nosdn.127.net/19e33c9b-6c22-4a4b-96da-1cb7afb32712',
                            title:'BEYOND博洋家纺 床上套件 秋冬保暖纯棉床单被套 双人被罩 磨毛全棉印花床品四件套',
                            price:'499',
                            quantity:2
                        }
                    ]
                },
            ]
        }
    }
}
</script>

<style lang="less">

.order-item{
    margin-bottom: 10px;
    font-size: 12px;
    .title{
        border-bottom: 1px solid #e5e5e5;

        .van-cell__title{
            flex: 2;
        }
        .van-cell__value{
            color: red;
        }
    }

    
    .van-panel__footer {
        text-align: right;
        border-bottom: 1px solid #e5e5e5;
    }
    .van-button {
        margin-left: 5px;
    }
    .total{
    position: absolute;
    top: 17px;
    left: 15px;
    font-size: 13px;
    }
    .more{
        overflow-x: scroll;white-space: nowrap;    -webkit-overflow-scrolling: touch;    margin: 5px 0 5px 15px;
        .item{
            width: 90px; height:90px; margin-right: 10px;display: inline-block;
            img{
                width: 100%;
            }
        }
    }
}
</style>
