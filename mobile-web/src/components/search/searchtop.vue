<template>
    <div>
        <van-nav-bar left-arrow class="product-serach"
        @click-left="onBack"
        >
            <van-search
            v-model="value"
            placeholder="请输入搜索关键词"
            background="#fff"
            show-action
            @search="onSearchClick"
            slot="title"
            >
                <div slot="action" @click="onSearchClick">搜索</div>
            </van-search>
        </van-nav-bar>


    </div>
</template>

<script>
import { Search } from "vant";

export default {
    name:'searchtop',
    components:{
        [Search.name]:Search,
    },
    props: {
        keyword: String,
        // onSearch: Function,
    },
    data(){
        return{
            value: this.keyword,
        }
    },
    methods:{
        onSearchClick() {
          // this.props.onSearch(this.keyword);
          this.$emit('onSearch', this.value);
        },
        onBack() {
            history.back();
        },
    }
}
</script>

<style lang="less">
.product-serach{
    .van-search{
        padding: 7px 0;
        background: #fff;
        .van-cell{
            background: rgb(242, 242, 242);
            margin-right: 10px;
        }
        .van-search__action{
            background: #e93b3d;
            color: #fff;
        }
    }
    .van-nav-bar__title{
        margin: 0;
        max-width: 100%;
        padding-left: 40px;
        padding-right: 20px;
    }
}
</style>
