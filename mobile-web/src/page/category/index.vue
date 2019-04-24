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
                    <!--<li><a ><img src="//img11.360buyimg.com/focus/s140x140_jfs/t21388/146/237407622/26923/221da1b3/5b054fedN2ba90518.jpg"><span>手机</span></a></li>-->
                    <!--<li><a ><img src="//img20.360buyimg.com/focus/s140x140_jfs/t20128/208/216721929/9242/472993da/5b05522dNa2aae1bb.png"><span>耳机</span></a></li>-->
                    <!--<li><a ><img src="//img30.360buyimg.com/focus/s140x140_jfs/t21655/83/2186874549/15932/c273d29b/5b48802aN13fe73de.png"><span>剃须刀</span></a></li>-->
                    <!--<li><a ><img src="//img20.360buyimg.com/focus/s140x140_jfs/t21715/149/246679831/16257/ddbf2036/5b0565a7N8dbc0017.png"><span>路由器</span></a></li>-->
                    <!--<li><a ><img src="//img14.360buyimg.com/focus/s140x140_jfs/t1/4478/16/633/36008/5b923503E39b9dfa9/13b099f187576d8c.png"><span>月饼</span></a></li>-->
                    <!--<li><a ><img src="//img10.360buyimg.com/focus/s140x140_jfs/t1/1410/32/643/38009/5b9236b2Eb02fbf02/1e7de6987578dcdd.jpg" ><span>牛奶</span></a></li>-->
                    <!--<li><a ><img src="//img20.360buyimg.com/focus/s140x140_jfs/t1/4674/14/665/25245/5b9236bbE088d5efb/6c7c2f9857736c65.jpg"><span>男士内裤</span></a></li>-->
                    <!--<li><a ><img src="//img20.360buyimg.com/focus/s140x140_jfs/t1/1710/26/666/26147/5b9236c3E5fd1cd42/86c6bca8f4fe1efa.png"><span>小米8</span></a></li>-->
                    <!--<li><a ><img src="//img11.360buyimg.com/focus/s140x140_jfs/t1/3653/6/655/42593/5b9236caEfef6235b/9e118f12705f52bb.png"><span>大闸蟹</span></a></li>-->
                    <!--<li><a ><img src="//img20.360buyimg.com/focus/s140x140_jfs/t23881/349/2204372862/9923/4c62864a/5b7693eeNf6883734.png"><span>三只松鼠</span></a></li>-->
                    <!--<li><a ><img src="//img20.360buyimg.com/focus/s140x140_jfs/t24253/294/2182777138/4059/429945c9/5b76990bNde226fbc.png"><span>充电宝</span></a></li>-->
                    <!--<li><a ><img src="//img30.360buyimg.com/focus/s140x140_jfs/t22051/318/235303191/9297/c5ea2761/5b055000N410a7553.png"><span>空调</span></a></li>-->
                    <!--<li><a ><img src="//img10.360buyimg.com/focus/s140x140_jfs/t19960/243/653029866/38879/91bb398b/5b055555N9245f8aa.jpg"><span>电饭煲</span></a></li>-->
                    <!--<li><a ><img src="//img12.360buyimg.com/focus/s140x140_jfs/t1/345/33/944/5582/5b9236d2E62d8da2e/99f72d51b8f195ed.jpg"><span>电话手表</span></a></li>-->
                    <!--<li><a ><img src="//img30.360buyimg.com/focus/s140x140_jfs/t1/1446/14/631/8500/5b9237e5E0d1f9e16/b1a627b92323b5ed.png"><span>华为</span></a></li>-->
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
