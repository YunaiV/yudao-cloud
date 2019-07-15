<template>
<div>
    <headerNav title="我的收藏"/>
    <van-list
        v-model="loading"
        :finished="finished"
        @load="onLoad"
        >
        <div v-for="(item,index) in list" :key="index">
<!--            <van-swipe-cell :right-width="65" :on-close="onClose(item)">-->
<!--                <product-card :product='item' />-->
<!--                    <span>{{item.spuName}}</span>-->
<!--                    <span slot="right" >删除</span>-->
<!--                </van-swipe-cell>-->
            <van-card
                    :price="formatPrice(item.price)"
                    :desc="item.sellPoint"
                    :title="item.spuName"
                    :thumb="item.spuImage"
            />
        </div>
    </van-list>
</div>
</template>

<script>
    // import { Card } from 'vant';
import { GetFavoritePage ,DelFavorite} from "../../../api/user.js";

// Vue.use(Card);
export default {
    data(){
        return{
            loading:false,
            finished:false,
            list:[],
            page:0,
            pageSize:10
        }
    },
    methods:{
        formatPrice(data) {
            return (data / 100).toFixed(2);
        },
        onClose(item){
            return function(clickPosition, instance) {
                switch (clickPosition) {
                    case 'left':
                    case 'cell':
                    case 'outside':
                    instance.close();
                    break;
                    case 'right':
                        this.$dialog.confirm({
                            message: '确定删除吗？'
                        }).then(() => {
                            DelFavorite(item.spuId).then(response=>{
                                this.$toast('删除成功');
                                this.$router.go(0);
                            })
                            instance.close();
                        }).catch(() => {
                        // on cancel
                        });
                    break;
                }
            }
        },
        onLoad() {
            let pageNo = this.page + 1;
            GetFavoritePage(pageNo,this.pageSize).then(response=>{
                this.page = pageNo;
                this.list.push(...response.list);
                // response.list.forEach(item => {
                //     this.list.push(item);
                // });
                this.loading = false;
                if(this.list.length >= response.total){
                    this.finished = true;
                }

            })
        }
    },
}
</script>

<style lang="less">
.van-swipe-cell{
    &__left,
    &__right {
      color: #FFFFFF;
      font-size: 15px;
      width: 65px;
      height: 100px;
      display: inline-block;
      text-align: center;
      line-height: 100px;
      background-color: #F44;
    }
}
</style>
