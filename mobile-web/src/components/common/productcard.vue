<template>
    <router-link :to="'/product/' + product.id">
        <van-cell-group class="additional">
            <van-card
                    :title="product.name"
                    :desc="product.sellPoint"
                    :num="(iscard?null:product.quantity)"
                    style="background:#fff"
            >
                <template slot="thumb">
                    <img :src="product.picUrls && product.picUrls ? product.picUrls[0] : ''"/>
                </template>
                <template slot="tags">
                    <p class="price" v-if="product.buyPrice || product.price">
                        ￥<span>{{product.buyPrice ? product.buyPrice / 100.00 : product.price / 100.00}}</span>
                        <van-tag v-if="product.promotionActivityTitle" plain type="danger">
                            {{ product.promotionActivityTitle }}
                        </van-tag>

                    </p>
                    <!--  TODO 芋艿 暂时去掉 -->
<!--                    <van-stepper v-if="iscard" v-model="product.quantity" :max="product.max" :min="product.min"/>-->
                </template>
            </van-card>
            <!-- TODO 芋艿，暂时去掉赠品 -->
            <!--<van-cell  v-for="(gift,j) in product.gift" :key="j"  :value="'x'+gift.quantity" >-->
            <!--<template slot="title">-->
            <!--<van-tag type="danger" v-if="j==0" >赠品</van-tag>-->
            <!--<span class="van-cell-text" :style="(j>0?'margin-left: 35px;':'')" >{{gift.title}}</span>-->
            <!--</template>-->
            <!--</van-cell>-->
            <slot/>
        </van-cell-group>
    </router-link>
</template>

<script>
  export default {
    name: 'product-card',
    props: {
      product: Object,
      iscard: {
        type: Boolean,
        default: false
      },
    }
  }
</script>

<style lang="less">
    .additional {
        .van-cell {
            padding: 0 15px;
            font-size: 12px;
        }

        .van-cell:not(:last-child)::after {
            border: 0;
        }

        .van-card__title {
            font-size: 14px;
        }

        .van-cell__title {
            flex: 10;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }

        .van-tag {
            line-height: 12px;
            margin-right: 5px;
        }

        .price {
            color: #e93b3d;
            font-size: 10px;
            overflow: hidden;
            height: 18px;

            span {
                font-size: 16px;
                margin-right: 5px;
            }

            .van-tag {
                font-size: 12px;
            }
        }

        .van-stepper {
            position: absolute;
            bottom: 5px;
            right: 5px;

            &__plus {
                width: 30px;
            }

            &__minus {
                width: 30px;
            }
        }

        .image_tag {
            position: absolute;
            bottom: 0;
            width: 90px;
            height: 20px;
            line-height: 20px;
            font-size: 10px;
            color: #fff;
            text-align: center;
            background-color: rgba(0, 0, 0, .7);
        }
    }

    .additional::after {
        border-color: #f7f7f7;

    }
</style>
