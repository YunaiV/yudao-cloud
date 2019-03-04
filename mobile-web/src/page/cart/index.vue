<template>
  <div class="card">
    <headerNav title="购物车"/>
       <van-cell  value="编辑商品" class="head">
        <template slot="title">
          <van-checkbox v-model="checkedAll" >全选</van-checkbox>
        </template>
      </van-cell>
        
    <van-checkbox-group class="card-goods" v-model="checkedGoods">
      
      <div class="promotion-group">
        <div  v-for="(item,index) in goods"
          :key="index" class="card-goods__item"> 
          <van-checkbox :name="item.id"></van-checkbox>

          <product-card :product='item' :iscard='true' >
            <template slot>
              <van-cell value="修改" >
                  <template slot="title">
                      <van-tag type="danger">促销</van-tag>
                      <span class="van-cell-text" >满60元减5元</span>
                  </template>
              </van-cell>
            </template>
          </product-card>
        </div>
        </div>
    
      <div class="promotion-group">
        
       <van-cell  is-link class="head">
        <template slot="title">
          <van-checkbox v-model="checkedAll" >京东自营</van-checkbox>
        </template>
      </van-cell>
      
      
      <div  v-for="(item,index) in goods"
        :key="index+10" class="card-goods__item"> 
        <van-checkbox :name="item.id"></van-checkbox>
      
        <product-card :product='item' :iscard='true' >
          <template slot>
            <van-cell value="修改" >
                <template slot="title">
                    <van-tag type="danger">促销</van-tag>
                    <span class="van-cell-text" >满60元减5元</span>
                </template>
            </van-cell>
          </template>
        </product-card>
      </div>
      <van-cell value="去凑单" is-link class="promotion">
        <template slot="title">
          <p><van-tag type="danger">满减</van-tag>购满60元，可减5元</p>
        </template>
      </van-cell>
      <div  v-for="(item,index) in goods"
        :key="index+20" class="card-goods__item"> 
        <van-checkbox :name="item.id"></van-checkbox>
      
        <product-card :product='item' :iscard='true' >
          <template slot>
            <van-cell value="修改" >
                <template slot="title">
                    <van-tag type="danger">促销</van-tag>
                    <span class="van-cell-text" >满60元减5元</span>
                </template>
            </van-cell>
          </template>
        </product-card>
      </div>
        </div>
    </van-checkbox-group>
    
    <div style="height:50px;"></div>
    <van-submit-bar
      :price="totalPrice"
      :disabled="!checkedGoods.length"
      :button-text="submitBarText"
      @submit="onSubmit"
    >
    <template slot>
      <van-checkbox v-model="checkedAll" >全选</van-checkbox>
    </template>
    </van-submit-bar>
  </div>
</template>

<script>

export default {
  components: {
  },
  data() {
    return {
      checkedAll:true,
      checkedGoods: ['1', '2', '3'],
      goods: [{
        id: '1',
        title: '星巴克(Starbucks)星冰乐 轻盈香草味 咖啡饮料 281ml*6瓶礼盒装低脂减糖',
        desc: '3.18kg/件',
        price: '200.00',
        quantity: 1,
        imageURL: 'https://img.yzcdn.cn/public_files/2017/10/24/2f9a36046449dafb8608e99990b3c205.jpeg',
        imageTag:'比加入时降5元',
      }, {
        id: '2',
        title: '陕西蜜梨',
        desc: '约600g',
        price: '690.00',
        quantity: 1,
        imageURL: 'https://img.yzcdn.cn/public_files/2017/10/24/f6aabd6ac5521195e01e8e89ee9fc63f.jpeg',
          gift: [
            {
              title: "星巴克（Starbucks）星冰乐小熊吊饰星巴克（Starbucks）星冰乐小熊吊饰",
              quantity: 2
            },
            {
              title: "星巴克（Starbucks）星冰乐小熊吊饰星巴克（Starbucks）星冰乐小熊吊饰",
              quantity: 1
            }
          ]
      }, {
        id: '3',
        title: '美国伽力果',
        desc: '约680g/3个',
        price: '2680.00',
        quantity: 1,
        imageURL: 'https://img.yzcdn.cn/public_files/2017/10/24/320454216bbe9e25c7651e1fa51b31fd.jpeg'
      }]
    };
  },
  computed: {
    submitBarText() {
      const count = this.checkedGoods.length;
      return '结算' + (count ? `(${count})` : '');
    },
    totalPrice() {
      return this.goods.reduce((total, item) => total + (this.checkedGoods.indexOf(item.id) !== -1 ? parseFloat(item.price): 0), 0);
    },
  },
  methods: {
    onSubmit() {
      
      this.$router.push('/order')
    }
  }
};
</script>

<style lang="less">
.card-goods {
  font-size: 12px;
  &__item {
    position: relative;
    .van-checkbox{
      width: 20px;
      height: 20px;
      top: 40px;
      left: 5px;
      z-index: 1;
      position: absolute;
    }
    .additional{
      width: 100%;
        padding-left: 15px;
    box-sizing: border-box;
    }
  }
}
.head{
      padding-left: 5px;
  border-bottom: 1px solid #eee;
}
.card{
  background: #f7f7f7;
  .van-submit-bar__bar {
      border-top: 1px solid #f7f7f7;
      .van-checkbox{
        padding-left: 5px;
      }
  }
  .promotion{
      .van-tag {
          line-height: 12px;
          margin-right: 5px;
      }
      .van-cell__title{

      flex: 4;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
      }
    }
    .promotion-group{
      margin-top: 10px;
      box-shadow: 5px 5px 5px #e5e5e5;
    }
}

    
</style>