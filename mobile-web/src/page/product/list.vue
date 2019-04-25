<template>
    <div class="product-list">
        <van-nav-bar :title="rootCategory.name" left-text="返回" left-arrow
                     @click-left="onBack">
        </van-nav-bar>

        <van-tabs v-model="active" @click="onCategoryClick">
            <van-tab v-for="category in childCategories" :title="category.name"  />
        </van-tabs>

<!--        <div v-for="(product,i) in products" :key="i">-->
<!--          <product-card :product='product' @click="showProduct(product)" />-->
<!--        </div>-->

        <van-list
                v-model="loading"
                :finished="finished"
                finished-text="没有更多了"
                @load="onLoad"
        >
            <div v-for="(product,i) in products" :key="i">
                <product-card :product='product' @click="showProduct(product)" />
            </div>
        </van-list>
    </div>
</template>

<script>
import { getProductCategoryList, getProductSpuPage } from '../../api/product';
import {getProductPage} from "../../api/search";

export default {
  data() {
    return {
      rootCategory: {
        id: parseInt(this.$route.query.cidFirst),
        name: this.$route.query.title,
      },
      childCategory: {
        id: parseInt(this.$route.query.cidSecond),
      },
      childCategories: [],

      active: -1,
      products: [],

      page: 0,
      pageSize: 10,
      loading: false,
      finished: false,
    };
  },
  methods: {
    onBack() {
      history.back();
    },
    onCategoryClick(key) {
      // 设置激活的 key
      this.active = key;
      // 加载商品
      this.products = [];
      // 加载商品

      this.loadProductList(this.childCategories[key].id, 1);
    },
    loadProductList(categoryId, page) {
      this.childCategory.id = categoryId;
      getProductPage({
        pageNo: page,
        pageSize: this.pageSize,
        cid: this.childCategory.id,
      }).then(data => {
        this.handleData(page, data);
      });
    },
    onLoad() {
      // debugger;
      // 进入下一页
      let page = this.page + 1;
      // 加载商品
      this.loadProductList(this.childCategory.id, page);
    },
    handleData(page, data) {
      this.loading = true;
      // 设置下页面
      this.page = page;
      // 数据保存到 list 中
      this.products.push(...data.list);
      // 判断页数
      if (this.products.length >= data.total) {
        this.finished = true;
      }
      // 标记不在加载中
      this.loading = false;
    },
  },
  mounted() {
    let response = getProductCategoryList(this.rootCategory.id);
    response.then(data => {
      // console.log(data);
      // debugger;
      // 设置根节点的分类
      this.childCategories = data;
      // 设置激活的分类
      // debugger;
      for (let i = 0; i < data.length; i++) {
        if (data[i].id === this.childCategory.id) {
          this.active = i;
          break;
        }
      }
      // 加载商品列表
      // this.loadProductList(this.childCategory.id);
    });
  }
};
</script>

<style lang="less">
.product-list{
    .additional .price{
        position: absolute;
        bottom: 6px;
        height: 34px;
    }
}
</style>
