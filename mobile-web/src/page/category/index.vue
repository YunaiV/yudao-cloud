<template>
    <div>
        <van-search
        v-model="keyword"
        placeholder="请输入搜索关键词"
        show-action
        @search="onSearch"
        >
            <div slot="action" @click="onSearch">搜索</div>
        </van-search>
        <van-badge-group :active-key="activeKey" class="tab" :style="'height:'+fullHeight+'px'">
            <!--<van-badge title="热门推荐" @click="onClick" />-->
            <!--<van-badge title="手机数码" @click="onClick" />-->
            <!--<van-badge title="家用电器" @click="onClick" />-->
            <!--<van-badge title="电脑办公" @click="onClick" />-->
            <!--<van-badge title="美妆护肤" @click="onClick" />-->
            <!--<van-badge title="个护清洁" @click="onClick" />-->
            <!--<van-badge title="汽车用品" @click="onClick" />-->
            <!--<van-badge title="男装" @click="onClick" />-->
            <!--<van-badge title="男鞋" @click="onClick" />-->
            <!--<van-badge title="女装" @click="onClick" />-->
            <!--<van-badge title="女鞋" @click="onClick" />-->
            <!--<van-badge title="母婴童装" @click="onClick" />-->
            <!--<van-badge title="图书音像" @click="onClick" />-->
            <!--<van-badge title="运动户外" @click="onClick" />-->
            <van-badge v-for="category in rootCategories" :title="category.name" @click="onClick" />
        </van-badge-group>
        <div class="content" :style="'width:'+fullWidth+'px;height:'+(fullHeight-7)+'px'" >
            <!-- TODO 营销活动，暂时注释掉 -->
            <!--<img src="https://img11.360buyimg.com/mcoss/jfs/t1/1072/23/3672/95463/5b9a0813E175891fa/e38fc2f7c2ddfec2.jpg" />-->
            <!-- TODO 常用分类，暂时注释掉 -->
            <!--<div class="category-div">-->
                <!--<h4>常用分类</h4>-->
                <!--<ul >-->
                    <!--<li>-->
                      <!--<router-link to="/search?keyword=xxxx">-->
                        <!--<img src="//img12.360buyimg.com/focus/jfs/t11824/150/2263801190/3392/8e69e1b3/5a167b8cNdcf71ae5.jpg">-->
                        <!--<span>蓝牙耳机</span>-->
                      <!--</router-link>-->
                    <!--</li>-->
                    <!--<li>-->
                        <!--<a >-->
                            <!--<img src="//img20.360buyimg.com/focus/jfs/t13759/194/897734755/2493/1305d4c4/5a1692ebN8ae73077.jpg">-->
                            <!--<span>iPhone</span>-->
                        <!--</a>-->
                    <!--</li>-->
                    <!--<div style="clear:both"></div>-->
                <!--</ul>-->
            <!--</div>-->
            <div class="category-div">
                <!--<h4>热门分类</h4>-->
                <ul>
                    <li v-for="category in childCategories">
                        <router-link :to="'/products/list?title=' + activeCategory.name + '&cidFirst=' + activeCategory.id + '&cidSecond=' + category.id">
                            <img :src="category.picUrl" />
                            <span>{{ category.name }}</span>
                        </router-link>
                    </li>
                    <div style="clear:both">
                    </div>
                </ul>
            </div>
        </div>
        <navigate />
    </div>
</template>

<script>
import { Search } from "vant";
// import { GetAddressById } from '../../api/user';
import { getProductCategoryList } from '../../api/product';

export default {
  name: "userindex",
  components: {
    [Search.name]: Search
  },
  data() {
    return {
      keyword: "",
      rootCategories: [],
      childCategories: [],
      activeKey: 0,
      activeCategory: {}, // 选中的分类
      fullHeight: document.documentElement.clientHeight - 93,
      fullWidth: document.documentElement.clientWidth - 99
    };
  },
  methods: {
    onSearch() {
      // debugger;
      // GetAddressById(1);
      // console.log(this.value);
      this.$router.push(
        {name: '/product/search', params: {keyword: this.keyword}}
      )
    },
    onClick(key) {
      // debugger;
      // GetAddressById(1);
      // 设置 activeKey
      this.activeKey = key;
      // 读取子节点的分类
      if (this.rootCategories.length > key) {
        this.activeCategory = this.rootCategories[key];
        let response = getProductCategoryList(this.activeCategory.id);
        response.then(data => {
          this.childCategories = data;
        });
      }
    }
  },
  mounted() {
    let response = getProductCategoryList(0);
    response.then(data => {
      // console.log(data);
      // debugger;
      // 设置根节点的分类
      this.rootCategories = data;
      // 获得首个根节点的分类
      if (data && data.length > 0) {
        this.activeCategory = data[0];
        let response = getProductCategoryList(data[0].id);
        response.then(data => {
          this.childCategories = data;
        });
      }
    });
  }
};
</script>

<style lang="less">
.tab {
  float: left;
  overflow-y: scroll;
  -webkit-overflow-scrolling: touch;
  min-height: 100%;
  .van-badge {
    padding: 15px 12px 15px 9px;
  }
  .van-badge:not(:last-child)::after {
    height: 199%;
  }
}
.content {
  float: left;
  overflow-y: scroll;
  -webkit-overflow-scrolling: touch;
  min-height: 100%;
  margin: 7px 7px 0;
  font-size: 12px;
  img {
    width: 100%;
  }
  .category-div {
    margin: 19px 0px 0;
    h4 {
      font-size: 14px;
      color: #333;
    }
    ul{
        margin-top: 10px;
    }
    li {
      width: 32.8%;
      float: left;
      text-align: center;
      img {
        width: 60px;
        height: 60px;
      }
      span{
          font-size: 12px;
    height: 36px;
    color: #686868;
    width: 100%;
    overflow: hidden;
    text-overflow: ellipsis;
    display: box;
    display: -webkit-box;
    display: -moz-box;
    display: -ms-box;
    display: -o-box;
    line-clamp: 2;
    -webkit-line-clamp: 2;
    -moz-line-clamp: 2;
    -ms-line-clamp: 2;
    -o-line-clamp: 2;
    box-orient: vertical;
    -webkit-box-orient: vertical;
    -ms-box-orient: vertical;
    -o-box-orient: vertical;
    word-break: break-all;
    box-align: center;
    -webkit-box-align: center;
    -moz-box-align: center;
    -ms-box-align: center;
    -o-box-align: center;
    box-pack: center;
    -webkit-box-pack: center;
    -moz-box-pack: center;
    -ms-box-pack: center;
    -o-box-pack: center;
    z-index: 2;
    position: relative;
      }
    }
  }
}
</style>
