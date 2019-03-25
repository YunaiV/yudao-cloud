<template>
    <div class="product-list">
        <van-nav-bar :title="rootCategory.name" left-text="返回" left-arrow
                     @click-left="onBack">
        </van-nav-bar>

        <van-tabs v-model="active" @click="onCategoryClick">
            <van-tab v-for="category in childCategories" :title="category.name"  />
        </van-tabs>

        <div v-for="(product,i) in products" :key="i">
          <product-card :product='product' @click="showProduct(product)" />
        </div>
    </div>
</template>

<script>
import { getProductCategoryList, getProductSpuPage } from '../../api/product';

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
      active: 2,
      products: [],
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
      this.loadProductList(this.childCategories[key].id);
    },
    loadProductList(categoryId) {
      // 设置当前选中的分类
      this.childCategory.id = categoryId;
      // 读取商品
      // alert('商品分类：' + categoryId);
      let response = getProductSpuPage(categoryId);
      response.then(data => {
        this.products.push(...data.spus);
      })
    }
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
      this.loadProductList(this.childCategory.id);
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
