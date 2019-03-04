</<template>
    <div  style="font-size:12px;">
        <headerNav title="申请售后"/>
        <van-radio-group v-model="servicetype">
            <van-cell-group>
                <van-cell title="服务类型"  />
                <van-cell title="退款" clickable @click="servicetype = '1'">
                <van-radio name="1" />
                </van-cell>
                <van-cell title="退货" clickable @click="servicetype = '2'">
                <van-radio name="2" />
                </van-cell>
            </van-cell-group>
        </van-radio-group>
        <div style="margin-top: 10px;" >
            <product-card v-for="(product,i) in products" :key="i" :product='product' iscard ></product-card>
        </div>

        <van-cell-group style="margin-top: 10px;">
            <van-cell title="申请原因" :label="remark" is-link @click="onClickShowPicker" />
            
            <van-field label="退款金额" placeholder="请输入金额" v-model="amount" :error-message="'可退金额:'+maxamount" type='number' />

            <van-field
                label="问题描述"
                type="textarea"
                placeholder="请您在此描述问题"
                rows="3"
                autosize
            />
            <van-cell class="uploader" style="font-size: 16px;">
                <van-uploader :after-read="onRead" accept="image/gif, image/jpeg" multiple>
                    <van-icon name="photograph" />
                </van-uploader>
                <div v-for="(image,i) in images" :key="image" class="uploader-image" >
                    <img :src="image" />
                    <van-icon name="close" @click="removeImage(i,image)" />
                </div>
            </van-cell>
            
        </van-cell-group>

        
        <van-cell-group style="margin-top: 10px;">
            <van-field label="联系人"  />
            <van-field label="联系电话"  />
        </van-cell-group>

        <van-button size="large" type="primary" style="margin-top: 10px;" >提交</van-button>
        <van-actionsheet v-model="showPicker" >
            <van-picker show-toolbar
  title="申请原因" :columns="columns" @cancel="onCancel" @confirm="onConfirm" />
        </van-actionsheet>
    </div>
</template>

<script>
export default {
    data(){
        return{
            servicetype:'1',
            showPicker:false,
            remark:'我不想要了',
            amount:'50.50',
            maxamount:'50.50',
            images:[],
            columns: ['我不想要了','退运费','少件/漏发','未按约定时间发货','发错货', '质量问题', '商品与页面描述不符', '商品损坏', '缺少件'],
            products: [
                {
                    id:1,
                    imageURL:
                        "https://img10.360buyimg.com/mobilecms/s88x88_jfs/t17572/12/840082281/351445/e1828c58/5aab8dbbNedb77d88.jpg",
                    title: "良品铺子 肉肉聚汇猪肉脯 猪蹄卤 辣味小吃520g",
                    desc: "0.670kg/件，肉肉聚汇520g",
                    quantity: 2,
                    price:'50.5',
                    max:2,
                    min:0,
                },
                {
                    id:1,
                    imageURL:
                        "https://img10.360buyimg.com/mobilecms/s88x88_jfs/t22720/128/1410375403/319576/8dbd859f/5b5e69b3Nf4f0e9e7.jpg",
                    title: "元朗 鸡蛋卷 饼干糕点 中秋礼盒 广东特产680g",
                    desc: "1.320kg/件",
                    quantity: 1,
                    price:'225.5',
                    max:1,
                    min:0,
                },
            ]
        }
    },
    methods:{
        onClickShowPicker(){
                this.showPicker=true;
        },
        onCancel() {
            this.showPicker=false;
        },
        onConfirm(value, index) {
            this.remark=value;
            this.showPicker=false;
        },
        onRead(file) {
            console.log(file.length);
            if(file.length==undefined){
                this.images.push(file.content);
            }else{
                file.forEach(item => {
                    this.images.push(item.content);
                });
            }
            console.log(file)
        },
        removeImage(index,image){
            this.images.splice(index,1);
        }
    }

}
</script>

<style lang="less">
.uploader{
    font-size: 16px;
    .van-uploader{
        float: left;
    }
    &-image{
        margin-left: 10px;position: relative;height: 50px;width: 50px;border: 1px solid #e5e7ea;float: left;margin-top: 5px;
        img{
           width: 50px; 
        }
        i{
            position: absolute;
            top: -6px;
            right: -6px;
            font-size: 8px;
        }
    }
}
</style>
