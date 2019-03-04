<template>
    <div class="aftersale">
        <headerNav title="售后"/>
        <van-tabs >
            <van-tab title="售后申请">
                <van-panel v-for="(order,index) in orders" :key="index" :title="'订单号：'+order.ordercode" style="margin-top:10px;">
                <div>
                    <product-card v-for="(product,i) in order.products" :key="i" :product='product' >
                        <div class="van-panel_product_footer">
                            <van-button size="small" type="danger" tag="a" :href="'#/user/aftersale/apply?id='+product.id" >申请售后</van-button>
                        </div>
                    </product-card>
                </div>
                </van-panel>
            </van-tab>
            <van-tab title="申请记录">
                <van-panel v-for="(order,index) in orders" :key="index" :title="'服务单号：'+order.ordercode" style="margin-top:10px;">
                    <div>
                        <product-card v-for="(product,i) in order.products" :key="i" :product='product'/>
                    </div>
                    <div>
                        <van-cell title="已完成" :to="'/user/aftersale/detail?ordercode='+order.ordercode"  is-link label="服务已完成，感谢您对京东的支持" style="background-color: #f8f8f8;"  />
                    </div>
                </van-panel>

            </van-tab>
        </van-tabs>
    </div>
</template>

<script>
export default {
    data(){
        return{
            orders:[
                {
                    ordercode:'79340944225',
                    products: [
                        {
                            id:1,
                            imageURL:
                                "https://img10.360buyimg.com/N2/jfs/t21733/218/377678809/177209/1b98ae56/5b0b96e1Nc0e37080.jpg",
                            title: "子初婴儿手口柔湿巾90片*3包  新生儿纸巾 宝宝婴儿湿巾",
                            quantity: 2
                        },
                    ]
                },
                {
                    ordercode:'79341094465',
                    products: [
                        {
                            id:1,
                            imageURL:
                                "https://img10.360buyimg.com/mobilecms/s88x88_jfs/t22720/128/1410375403/319576/8dbd859f/5b5e69b3Nf4f0e9e7.jpg",
                            title: "元朗 鸡蛋卷 饼干糕点 中秋礼盒 广东特产680g",
                            desc: "1.320kg/件",
                            quantity: 1,
                        },
                    ]
                },
                {
                    ordercode:'79341094462',
                    products: [
                        {
                            id:1,
                            imageURL:
                                "https://img10.360buyimg.com/mobilecms/s88x88_jfs/t17572/12/840082281/351445/e1828c58/5aab8dbbNedb77d88.jpg",
                            title: "良品铺子 肉肉聚汇猪肉脯 猪蹄卤 辣味小吃520g",
                            desc: "0.670kg/件，肉肉聚汇520g",
                            quantity: 2
                        },
                        {
                            id:1,
                            imageURL:
                                "https://img10.360buyimg.com/mobilecms/s88x88_jfs/t22720/128/1410375403/319576/8dbd859f/5b5e69b3Nf4f0e9e7.jpg",
                            title: "元朗 鸡蛋卷 饼干糕点 中秋礼盒 广东特产680g",
                            desc: "1.320kg/件",
                            quantity: 1,
                        },
                    ]
                },
            ],
      
        }
    }
}
</script>

<style lang="less">
.aftersale{
    font-size:12px;background: #f0f2f5;
    .van-panel{
        margin-top: 10px;
        &_product_footer{
            text-align: right;padding: 0 10px 15px;
        }
    }
}
</style>
