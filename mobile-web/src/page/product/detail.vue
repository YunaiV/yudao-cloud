<template>
  <div class="goods">
    <headerNav title="商品详情"/>
    <van-swipe class="goods-swipe" :autoplay="3000">
      <van-swipe-item v-for="thumb in spu.picUrls" :key="thumb">
        <img :src="thumb">
      </van-swipe-item>
    </van-swipe>
      <!-- TODO 这里需要优化下，芋艿 -->
      <van-cell-group>
          <van-cell>
            <div v-if="calSkuPriceResult.originalPrice && calSkuPriceResult.originalPrice !== calSkuPriceResult.buyPrice">
                <span class="goods-price">{{ formatPrice(calSkuPriceResult.buyPrice) }}</span>
                <span class="goods-market-price">{{ formatPrice(calSkuPriceResult.originalPrice) }}</span>
            </div>
            <div v-else>
                <span class="goods-price">{{ formatPrice(initialSku.price) }}</span>
            </div>

            <div class="goods-title">{{ spu.name }}</div>
            <div class="goods-subtit">{{spu.sellPoint}}</div>
          </van-cell>

      <!--<van-cell   @click="onClickShowTag" class="goods-tag" >-->
      <!--<template slot="title" style="font-size:10px;">-->
      <!--<img src="https://haitao.nos.netease.com/ba8a4c2fdaa54f82a45261293c116af61419663676663i46n3jlh10028.png"/>-->
      <!--<span >挪威品牌</span>-->
      <!--<img src="https://haitao.nosdn2.127.net/13bd59e6e29a4f06b278c586629e690d.png" />-->
      <!--<span >跨境商品</span>-->
      <!--<van-icon name="passed" color="red" />-->
      <!--<span >次日达</span>-->
      <!--<van-icon name="passed" color="red" />-->
      <!--<span >自提</span>-->
      <!--<van-icon name="passed" color="red" />-->
      <!--<span >闪电退款</span>-->
      <!--<van-icon name="passed" color="red" />-->
      <!--<span >前海保税仓</span>-->
      <!--<van-icon name="passed" color="red" />-->
      <!--<span >七天无理由退货（拆封后不支持）</span>-->
      <!--</template>-->
      <!--</van-cell>   -->
    </van-cell-group>


    <!--<van-cell-group class="goods-cell-group">-->
    <!--<van-cell is-link  @click="showPromotion" >-->
    <!--<template slot="title">-->
    <!--<span style="margin-right: 10px;">领券</span>-->
    <!--<van-tag type="danger" mark  style="margin-right: 5px;">满180减30</van-tag>-->
    <!--<van-tag type="danger" mark  style="margin-right: 5px;">满300减100</van-tag>-->
    <!--</template>-->
    <!--</van-cell>-->
    <!---->

    <!--<van-cell  is-link @click="showPromotion" >-->
    <!--<template slot="title">-->
    <!--<span style="margin-right: 10px;">促销</span>-->
    <!--<van-tag type="danger" style="margin-right: 5px;">多买优惠</van-tag>-->
    <!--<van-tag type="danger" style="margin-right: 5px;">满减</van-tag>-->
    <!--<van-tag type="danger" style="margin-right: 5px;">限购</van-tag>-->
    <!--</template>-->
    <!--</van-cell>-->
    <!--</van-cell-group>-->

    <van-cell-group class="goods-cell-group">
      <van-cell is-link @click="showSku">
        <template slot="title">
          <span style="margin-right: 10px;">已选</span>
          <span>{{ formatSkuText(initialSku) }}</span>
        </template>
      </van-cell>

    </van-cell-group>

<!--      <van-cell is-link @click="sorry">-->
<!--          <template slot="title">-->
<!--              <van-tag type="danger">多买优惠</van-tag>-->
<!--              <span> 满2件，总价打9折</span>-->
<!--          </template>-->
<!--      </van-cell>-->
      <!-- TODO 芋艿，后续【限时折扣】需要改下样式 -->
      <van-cell v-if="calSkuPriceResult.timeLimitedDiscount" is-link @click="sorry">
          <template slot="title">
              <van-tag type="danger">限时折扣</van-tag>
              <span> {{ formatTimeLimitedDiscountText(calSkuPriceResult.timeLimitedDiscount) }} </span>
          </template>
      </van-cell>
      <van-cell v-if="calSkuPriceResult.fullPrivilege" is-link @click="sorry">
          <template slot="title">
              <van-tag type="danger">满减送</van-tag>
              <span>  {{ formatFullPrivilegeText(calSkuPriceResult.fullPrivilege) }} </span>
          </template>
      </van-cell>

    <div class="goods-info">
      <p class="goods-info-title">图文详情</p>
      <div v-html="spu.description"></div>
    </div>
    <van-goods-action>

      <van-goods-action-mini-btn icon="like-o"  :class="{active:hasCollectionType === 1 }" @click="onFavoriteClicked">
        收藏
      </van-goods-action-mini-btn>
      <van-goods-action-mini-btn icon="cart" :info="cartCount > 0 ? cartCount : undefined" @click="onClickCart">
        购物车
      </van-goods-action-mini-btn>
      <van-goods-action-big-btn @click="showSku">
        加入购物车
      </van-goods-action-big-btn>
      <van-goods-action-big-btn primary @click="showSku">
        立即购买
      </van-goods-action-big-btn>
    </van-goods-action>

    <!--<van-actionsheet v-model="showTag" title="服务说明" style="font-size:14px;">-->
    <!---->
    <!--<van-cell>-->
    <!--<template slot="title">-->
    <!--<van-icon name="passed" color="red" style="margin-right: 10px;" />-->
    <!--<span >次日达</span>-->
    <!--<div style="margin-left: 24px;font-size:10px;color:#7d7d7d;">指定时间前下单，次日送达</div>-->
    <!--</template>-->
    <!--</van-cell>-->
    <!--<van-cell>-->
    <!--<template slot="title">-->
    <!--<van-icon name="passed" color="red" style="margin-right: 10px;" />-->
    <!--<span >自提</span>-->
    <!--<div style="margin-left: 24px;font-size:10px;color:#7d7d7d;">我们提供多种自提服务，包括自提点、自助提货柜、移动自提车等服务</div>-->
    <!--</template>-->
    <!--</van-cell>-->
    <!--<van-cell>-->
    <!--<template slot="title">-->
    <!--<van-icon name="passed" color="red" style="margin-right: 10px;" />-->
    <!--<span >闪电退款</span>-->
    <!--<div style="margin-left: 24px;font-size:10px;color:#7d7d7d;">签收7天内退货的，提供先退款后退货服务。</div>-->
    <!--</template>-->
    <!--</van-cell>-->
    <!--<van-cell>-->
    <!--<template slot="title">-->
    <!--<van-icon name="passed" color="red" style="margin-right: 10px;" />-->
    <!--<span >七天无理由退货（拆封后不支持）</span>-->
    <!--<div style="margin-left: 24px;font-size:10px;color:#7d7d7d;">七天无理由退货（拆封后不支持）</div>-->
    <!--</template>-->
    <!--</van-cell>-->
    <!--<van-cell>-->
    <!--<template slot="title">-->
    <!--<van-icon name="passed" color="red" style="margin-right: 10px;" />-->
    <!--<span >前海保税仓</span>-->
    <!--<div style="margin-left: 24px;font-size:10px;color:#7d7d7d;">本商品由前海保税仓发货</div>-->
    <!--</template>-->
    <!--</van-cell>-->
    <!--</van-actionsheet>-->

    <van-sku
      v-model="showBase"
      :sku="vanSku"
      :initial-sku="initialSku"
      :goods="vanSpu"
      :goods-id="spu.id"
      :hide-stock="hideStock"
      :close-on-click-overlay="closeOnClickOverlay"
      @stepper-change="stepperChange"
      @sku-selected="skuSelected"
      @buy-clicked="onBuyClicked"
      @add-cart="onAddCartClicked"
    />
    <!--:quota="skuData.quota"-->

  </div>
</template>

<script>
  // import skuData from '../../data/sku';
  import {getProductSpuInfo,collectionSpu} from '../../api/product';
  import {addCart, countCart, getCartCalcSkuPrice} from '../../api/order';
  import {hasUserSpuFavorite} from  '../../api/user';
  import {Dialog} from 'vant';
  import {checkLogin} from "../../utils/cache";

  export default {
    components: {},
    data() {
      // this.skuData = skuData;
      return {
        spu: {}, // 商品信息
        vanSku: {
          tree: [], // 规格数组
          list: [], // sku 数组
        },
        // TODO 后面，要加 sku 的 title 和 picture
        vanSpu: {
          title: '',
          picture: '',
        },
        initialSku: { // 选中的 sku
          // 具体规格
          // price 价格
          // quantity 选中的数量
        },
        attrValueMap: new Map(), // 规格值的映射

        showBase: false, // 是否显示 sku 弹层
        closeOnClickOverlay: true, // 是否在点击蒙层后关闭
        hideStock: true, // 是否显示商品剩余库存

        cartCount: 0,

        calSkuPriceResult: {

        },
          hasCollectionType:0

      };
    },
    methods: {
      formatPrice(data) {
        return '¥' + (data / 100).toFixed(2);
      },
      formatSkuText(data) { // 渲染已选择的 sku 的文本
        let text = '';
        for (let prop in data) {
          if (prop.indexOf('attr_') === 0) {
            text = text + this.attrValueMap.get(data[prop]) + ' ';
          }
        }
        text = text + 'x ' + data.quantity + ' 件';
        return text;
      },
      formatTimeLimitedDiscountText(activity) {
        let text = '';
        let timeLimitedDiscount = activity.timeLimitedDiscount.items[0];
        if (timeLimitedDiscount.preferentialType === 1) {
          text += '减 ' + timeLimitedDiscount.preferentialValue / 100.0 + ' 元';
        } else if (timeLimitedDiscount.preferentialType === 2) {
          text += '打 ' + timeLimitedDiscount.preferentialValue / 10.0 + ' 折';
        }
        if (activity.timeLimitedDiscount.quota > 0) {
          text += '【限购 ' + activity.timeLimitedDiscount.quota + ' 件】';
        }
        return text;
      },
      formatFullPrivilegeText(activity) {
        let text = '';
        let fullPrivilege = activity.fullPrivilege;
        for (let i in fullPrivilege.privileges) {
          let privilege = fullPrivilege.privileges[i];
          if (i > 0) {
            text += ';';
          }
          if (fullPrivilege.cycled) {
            text += '每';
          }
          if (privilege.meetType === 1) {
            text += '满 ' + privilege.meetValue / 100.0 + ' 元,';
          } else if (privilege.meetType === 2) {
            text += '满 ' + privilege.meetValue + ' 件,';
          }
          if (privilege.preferentialType === 1) {
            text += '减 ' + privilege.preferentialValue / 100.0 + ' 元';
          } else if (privilege.preferentialType === 2) {
            text += '打 ' + privilege.preferentialValue / 10.0 + ' 折';
          }
        }
        return text;
      },

      stepperChange(value) { // 选择 sku 数量时
        this.initialSku.quantity = value;
      },
      skuSelected({skuValue, selectedSku, selectedSkuComb}) { // 选择 sku
        // TODO 芋艿，需要改下，禁用用户取消选中。
        // console.log(skuValue);
        // console.log(selectedSku);
        // console.log(selectedSkuComb);
        this.initialSku = {
          ...selectedSkuComb,
          quantity: 1,
        };
        // 执行 sku 价格计算
        this.doCalcSkuPrice(this.initialSku.id)
      },
      doCalcSkuPrice(skuId) {
        getCartCalcSkuPrice(skuId).then(data => {
          this.calSkuPriceResult = data;
          // 修改 vanSku.list 里匹配的 sku 的价格（目的，将优惠价赋值到其上）
          for (let i in this.vanSku.list) {
            let sku = this.vanSku.list[i];
            if (sku.id === skuId) {
              sku.price = data.buyPrice;
              break;
            }
          }
        });
      },
        initHasUserSpuFavorite(spuId){
            if (!checkLogin()) {
                this.hasCollectionType = 0;
                return;
            }
            //初始化验证商品收藏
            hasUserSpuFavorite(spuId).then(data => {
                let hasCollection = data;
                // alert("是否收藏==" + hasCollection);
                if (hasCollection) {
                    this.hasCollectionType = 1;
                }
            });
        },

      onClickCart() {
        this.$router.push('/cart');
      },
      sorry() {
        Toast('暂无后续逻辑~');
      },
      showPromotion() {
        this.show = true;
      },
      showSku() { // 展示 sku 选择
        this.showBase = true;
      },
      onClickShowTag() {
        this.showTag = true;
      },
      onFavoriteClicked(){
        if (!checkLogin()) {
          Dialog.alert({
            title: '系统提示',
            message: '未登陆用户，暂时不支持使用购物车',
          });
          return;
        }
        let id = this.$route.params.id; // 商品编号
          let hasCollectionType = 1; // 收藏类型  默认收藏
          hasUserSpuFavorite(id).then(data => {
              let hasCollection = data;
              // alert("是否收藏==" + hasCollection);
              if (hasCollection){
                  hasCollectionType = 2;
              }

              // alert("hasCollectionType==" + hasCollectionType);
              collectionSpu(id,hasCollectionType).then(data =>{
                  let v = data;
                  this.hasCollectionType = hasCollectionType;
                  // if (hasCollectionType == 1 && v){
                  //     // alert("商品已收藏");
                  //     this.hasCollectionType = hasCollectionType;
                  // }else if (hasCollectionType == 2 && v){
                  //     // alert("商品已取消");
                  //     this.hasCollectionType = hasCollectionType;
                  // }
              })
          });

      },
      onBuyClicked(data) {
        const { selectedNum } = data;
        this.$router.push({
          path:'/order',
          query:{
            goodsId: data.id,
            skuId: data.selectedSkuComb.id,
            quantity: selectedNum,
          }
        });
      },
      onAddCartClicked(data) {
        if (!checkLogin()) {
          Dialog.alert({
            title: '系统提示',
            message: '未登陆用户，暂时不支持使用购物车',
          });
          return;
        }
        const { selectedNum } = data;
        // debugger;
        addCart(data.selectedSkuComb.id,selectedNum).then(data => {
          // 修改购物车数量
          this.cartCount = data;
          // 提示
          Dialog.alert({
            title: '系统提示',
            message: '添加购物车成功',
          });
        });
      },
    },
    mounted() {
      // 获得商品数据
      let id = this.$route.params.id; // 商品编号
      getProductSpuInfo(id).then(data => {
        // 设置 spu
        this.spu = data;
        // 初始化 vanSku
        let vanSku = {
          tree: [],
          list: [],
        };
        for (let i = 0; i < data.skus.length; i++) {
          let sku = data.skus[i];
          // list 商品 sku 信息
          let skuVO = {
            id: sku.id, // skuId，下单时后端需要
            price: sku.price, // 价格（单位分）
            stock_num: sku.quantity // 当前 sku 组合对应的库存
          };
          for (let j = 0; j < sku.attrs.length; j++) {
            let attr = sku.attrs[j];
            skuVO['attr_' + attr.attrId] = attr.attrValueId;
          }
          vanSku.list.push(skuVO);
          // tree 规格
          for (let j = 0; j < sku.attrs.length; j++) {
            let attr = sku.attrs[j];
            let attrVO;
            for (let k = 0; k < vanSku.tree.length; k++) {
              if (attr.attrName === vanSku.tree[k].k) {
                attrVO = vanSku.tree[k];
                break;
              }
            }
            if (!attrVO) { // 未找到，则初始化该规格
              attrVO = {
                k: attr.attrName, // skuKeyName：规格类目名称
                v: [],
                k_s: 'attr_' + attr.attrId,
              };
              vanSku.tree.push(attrVO);
            }
            let attrValueFound = false; // 如果规格值已经存在，则不再添加
            for (let k = 0; k < attrVO.v.length; k++) {
              if (attr.attrValueId === attrVO.v[k].id) {
                attrValueFound = true;
                break;
              }
            }
            if (!attrValueFound) {
              attrVO.v.push({
                id: attr.attrValueId, // skuValueId：规格值 id
                name: attr.attrValueName, // skuValueName：规格值名称
              });
            }
            // 初始化 attrValueMap
            this.attrValueMap.set(attr.attrValueId, attr.attrValueName);
          }

        }
        // debugger;
        this.vanSku = vanSku;
        // 初始化
        // TODO 芋艿，需要处理下第一个有效的 sku
        this.initialSku = vanSku.list[0];
        this.initialSku.quantity = 1;
        // 执行 sku 价格计算
        this.doCalcSkuPrice(this.initialSku.id);

        this.initHasUserSpuFavorite(id);

      });
      // 获得购物车数量
      if (checkLogin()) {
        countCart().then(data => {
          this.cartCount = data;
        })
      }
    }
  };
</script>

<style lang="less">
  .goods {
      .active {
          color: #f44;
      }
    padding-bottom: 50px;

    &-swipe {
      img {
        width: 7.5rem;
        height: 7.5rem;
        display: block;
      }
    }

    &-tag {
      font-size: 12px;
      border-top: 1px solid #e5e5e5;

      span {
        margin-right: 10px;
      }

      i {
        color: red;
        margin-right: 3px;
      }

      img {
        width: 12px;
        margin-right: 3px;
        margin-top: 6px;
      }
    }

    &-title {
      line-height: 18px;
      padding-top: 10px;
      margin-bottom: 6px;
      font-size: 14px;
      color: #333;
      font-weight: 700;
      border-top: 1px solid #f0f0f0;
    }

    &-subtit {
      font-size: 13px;
      color: #333;
      line-height: 21px;
    }

    &-price {
      color: #f44;
      font-size: 20px;
    }

    &-market-price {
      text-decoration: line-through;
      margin-left: 8px;
      font-size: 13px;
      color: #999;
    }

    &-cell-group {
      margin: 15px 0;

      .van-cell__value {
        color: #999;
      }
    }

    &-info-title {
      height: 44px;
      line-height: 44px;
      text-align: center;
      font-size: 14px;
      font-weight: 700;
      margin: 10px;
      border-top: 1px solid #e5e5e5;
    }

    &-info p {
      margin: 0;
      padding: 0;
      margin-block-end: 0;
      margin-block-start: 0;
      display: grid;
    }

    &-info img {
      width: 100%;
    }
  }
</style>
