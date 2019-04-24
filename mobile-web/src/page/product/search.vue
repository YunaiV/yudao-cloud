<template>
    <div class="product-list">
        <searchtop :keyword="keyword" @onSearch="onSearch"  />
        <div class="filterbar">
            <ul :class="filterSort?'show':''">
                <li :class="filterIndex===0?'selected':''" v-on:click="onFilterBar(0)"><span>{{filterIndex==11?'价格最低':(filterIndex==12?'价格最高':'综合')}}</span><van-icon name="arrow" class="down" /></li>
                <li :class="filterIndex===1?'selected':''" v-on:click="onFilterBar(1)"><span>销量</span></li>
                <li :class="filterIndex===2?'selected':''" v-on:click="onFilterBar(2)"><span>上新</span></li>
                <li :class="filterIndex===3?'selected':''" v-on:click="onFilterBar(3)"><span>筛选</span></li>
            </ul>
            <div :class="'item_options '+(filterSort?'show':'')">
                <ul>
                    <li :class="filterIndex==10?'selected':''" v-on:click="onFilterBar(10)">综合</li>
                    <li :class="filterIndex==11?'selected':''"  v-on:click="onFilterBar(11)">价格降序</li>
                    <li :class="filterIndex==12?'selected':''" v-on:click="onFilterBar(12)">价格最高</li>
                </ul>
            </div>
            <van-popup v-model="filterShow" position="right" class="filterlayer" >
                <div class="filterInner" style="overflow-y: scroll;max-height: 100%;">
                    <ul>
                        <li>
                            <van-cell title="清洁类型" is-link arrow-direction="down" />
                        </li>
                        <div style="clear: both;"></div>
                        <div class="tags_selection">
                            <div class="option">
                                <a href="javascript:void 0;">牙龈护理111</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">抛光</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">清洁</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">正畸专用</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">敏感</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">亮白</a>
                            </div>
                            <div style="clear: both;"></div>
                        </div>
                    </ul>
                    <ul>
                        <li>
                            <van-cell title="清洁类型" is-link arrow-direction="down" />
                        </li>
                        <div style="clear: both;"></div>
                        <div class="tags_selection">
                            <div class="option">
                                <a href="javascript:void 0;">牙龈护理111</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">抛光</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">清洁</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">正畸专用</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">敏感</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">亮白</a>
                            </div>
                            <div style="clear: both;"></div>
                        </div>
                    </ul>
                    <ul>
                        <li>
                            <van-cell title="清洁类型" is-link arrow-direction="down" />
                        </li>
                        <div style="clear: both;"></div>
                        <div class="tags_selection">
                            <div class="option">
                                <a href="javascript:void 0;">牙龈护理111</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">抛光</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">清洁</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">正畸专用</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">敏感</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">亮白</a>
                            </div>
                            <div style="clear: both;"></div>
                        </div>
                    </ul>
                    <ul>
                        <li>
                            <van-cell title="清洁类型" is-link arrow-direction="down" />
                        </li>
                        <div style="clear: both;"></div>
                        <div class="tags_selection">
                            <div class="option">
                                <a href="javascript:void 0;">牙龈护理111</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">抛光</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">清洁</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">正畸专用</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">敏感</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">亮白</a>
                            </div>
                            <div style="clear: both;"></div>
                        </div>
                    </ul>
                    <ul>
                        <li>
                            <van-cell title="清洁类型" is-link arrow-direction="down" />
                        </li>
                        <div style="clear: both;"></div>
                        <div class="tags_selection">
                            <div class="option">
                                <a href="javascript:void 0;">牙龈护理111</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">抛光</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">清洁</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">正畸专用</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">敏感</a>
                            </div>
                            <div class="option ">
                                <a href="javascript:void 0;">亮白</a>
                            </div>
                            <div style="clear: both;"></div>
                        </div>
                    </ul>
                    <div style="clear: both;"></div>
                    <van-button size="large"  style="height: 40px;margin-bottom: 15px;line-height: 40px;">清楚选项</van-button>
                    <div style="height:50px;"></div>
                </div>
                <div class="filterlayer_bottom_buttons">
                    <span class="filterlayer_bottom_button cancel">取消</span>
                    <span class="filterlayer_bottom_button confirm">确认</span>
                </div>
            </van-popup>
        </div>

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
import searchtop from "../../components/search/searchtop";
import {getProductPage} from "../../api/search";

export default {
  components: {
    searchtop
  },
  data() {
    return {
      page: 0,
      pageSize: 10,
      loading: false,
      finished: false,

      keyword: this.$route.params.keyword,

      filterIndex: 0,
      filterSort: false, // 是否展示几个【排序】
      filterShow: false, // 是否展示【筛选】

      sortField: undefined,
      sortOrder: undefined,

      products:[]
    };
  },
  methods: {
    onFilterBar(value) {
      if (value === 0) {
        this.filterSort = !this.filterSort;
      } else if (value === 3) {
        this.filterShow = !this.filterShow;
      } else {
        // 如果非合法 10、11、12
        if (value !== 10
            && value !== 11
            && value !== 12) {
          alert('暂不支持');
          return;
        }
        // 设置 filterSort 和 filterIndex 属性
        this.filterSort = false;
        this.filterIndex = value;
        // 标记加载中
        this.loading = true;
        // 根据 value 的值，设置 sortField、sortOrder
        switch (value) {
          case 10:
            this.sortField = undefined;
            this.sortOrder = undefined;
            break;
          case 11:
            this.sortField = 'buyPrice';
            this.sortOrder = 'desc';
            break;
          case 12:
            this.sortField = 'buyPrice';
            this.sortOrder = 'asc';
            break;
        }
        // 根据排序，重新搜索
        let page = 1;
        getProductPage({
          pageNo: page,
          pageSize: this.pageSize,
          keyword: this.keyword,
          sortField: this.sortField,
          sortOrder: this.sortOrder,
        }).then(data => {
          this.products = [];
          this.handleData(page, data);
        });
      }
    },
    showProduct(product){
        this.$router.push('/product/'+product.id);
    },
    onSearch(keyword) {
      this.loading = true;
      // 设置 keyword
      this.keyword = keyword;
      // 重置其它字段
      this.filterIndex = 0;
      this.filterSort = false;
      this.filterShow = false;
      this.sortField = undefined;
      this.sortOrder = undefined;
      // 查询
      let page = 1;
      getProductPage({
        pageNo: page,
        pageSize: this.pageSize,
        keyword: keyword,
      }).then(data => {
        this.products = [];
        this.handleData(page, data);
      });
    },
    onLoad() {
      // debugger;
      // 进入下一页
      let page = this.page + 1;
      getProductPage({
        pageNo: page,
        pageSize: this.pageSize,
        keyword: this.keyword,
      }).then(data => {
        this.handleData(page, data);
      });
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
    }
  },
  mounted() {
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
.filterbar {
  font-size: 14px;
  position: relative;
  ul {
    width: 100%;
    height: 40px;
  }
  li {
    color: #999;
    width: 25%;
    float: left;
    text-align: center;
    height: 40px;
    line-height: 40px;
    border-bottom: 1px solid #eee;
    i {
      font-size: 12px;
      margin-left: 2px;
    }
    .down {
      -webkit-transform: rotate(90deg);
      transform: rotate(90deg);
    }
  }
  .selected {
    color: #e93b3d;
  }
  .show {
    .down {
      -webkit-transform: rotate(-90deg);
      transform: rotate(-90deg);
    }
  }
  .item_options {
    width: 100%;
    position: absolute;
    top: 41px;
    background: #fff;
    display: none;
    z-index: 1;
    li {
      width: 100%;
      text-align: left;
      padding-left: 10px;
    }
  }

  .item_options.show {
    display: block;
  }
  .filterlayer {
    width: 85%;
    height: 100%;
    background-color: #f7f7f7;
    &_bottom {
      &_buttons {
        display: -webkit-box;
        display: -webkit-flex;
        display: flex;
        z-index: 10;
        position: absolute;
        bottom: 0;
        left: 0;
        right: 0;

        .cancel {
          color: #333;
          background-color: #fff;
          box-shadow: 0 -1px 2px 0 rgba(0, 0, 0, 0.07);
        }
        .confirm {
          color: #fff;
          background-color: #e93b3d;
        }
      }
      &_button {
        -webkit-box-flex: 1;
        -webkit-flex: 1;
        flex: 1;
        font-size: 16px;
        height: 49px;
        line-height: 49px;
        text-align: center;
      }
    }
    ul {
      height: auto;
      background: #fff;
      margin-bottom: 15px;
    }
    li {
      width: 100%;
      text-align: left;
        height: auto;
        line-height: initial;
      .van-cell{
        font-size: 16px;
      }
      .big {
        height: 25px;
        max-width: 250px;
        overflow: hidden;
        font-size: 16px;
        color: #333;
        text-overflow: ellipsis;
        margin-left: 10px;
      }
    }
    .filterInner{
        overflow-y: scroll;
        max-height: 100%;
        .van-button{
            height: 40px;
            margin-bottom: 15px;
            line-height: 40px;
        }
    }
    .tags_selection {
      font-size: 14px;
      text-align: center;
      background-color: #fff;
      position: relative;
      margin: 0;
      padding: 10px 0 0 10px;
      .option {
        box-sizing: border-box;
        float: left;
        width: 33.33%;
        padding-right: 10px;
        height: 30px;
        line-height: 30px;
        margin-bottom: 10px;
        a {
          position: relative;
          display: block;
          padding: 0 5px;
          color: #666;
          background-color: #f7f7f7;
          border-radius: 4px;
          overflow: hidden;
          text-overflow: ellipsis;
            height: 30px;
        }
      }
    }
  }
}
</style>
