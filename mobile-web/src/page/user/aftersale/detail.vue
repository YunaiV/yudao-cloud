</<template>
    <div>
        <headerNav title="服务单详情"/>
        <van-steps :active="active">
            <van-step>提交申请</van-step>
            <van-step>客服审核</van-step>
            <van-step>客户确认</van-step>
            <van-step>仓库收货</van-step>
            <van-step>完成</van-step>
        </van-steps>
        
        <van-cell class="logistics" to="/user/aftersale/track/1" title="您的服务单435703816的商品已收到" label="2018-08-31 21:04:03"  is-link />
        
        <div style="height:15px;"></div>
        <div v-for="(product,i) in products" :key="i">
          <product-card :product='product' />
        </div>
        
        <div style="height:15px;"></div>
        <van-cell-group>
            <van-cell title="服务单号" value="18081609422771742" />
            <van-cell title="申请时间" value="2018-08-16 09:42:27" />
            <van-cell title="服务类型" value="退货" />
        </van-cell-group>
        <div style="height:15px;"></div>
        <van-cell-group>
            <van-cell title="联系人" value="张三" />
            <van-cell title="联系电话" value="138****6514" />
        </van-cell-group>
    </div>
</template>

<script>
export default {
    data(){
        return {
            active:0,
            
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
                    quantity:2
                },
            ]
        }
    }
}
</script>

<style>

</style>
