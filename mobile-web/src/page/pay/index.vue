<template>
    <div>
        <van-cell-group>
            <van-cell title="优惠劵编号" :value="couponTemplate.id" />
            <van-cell title="优惠劵名" :value="couponTemplate.title"/>
        </van-cell-group>
        <van-button slot="button" size="small" type="primary" @click="onFetchClick">领取优惠劵</van-button>
    </div>
</template>

<script>
  import { getCouponTemplate, doAddCouponCard } from '../../api/promotion';
  import { Dialog } from 'vant';
  import { setLoginToken } from '../../utils/cache';

  export default {

    data() {
      return {
        couponTemplate: {
        }
      }
    },

    mounted() {
      let id = this.$route.query.id;
      let response = getCouponTemplate(id);
      response.then(data => {
        this.couponTemplate = data;
      });
    },

    methods: {
      onFetchClick: function () {
        let that = this;
        let id = this.$route.query.id;
        let response = doAddCouponCard(id);
        response.then(data => {
          Dialog.alert({
            title: '系统提示',
            message: '领取成功',
            beforeClose: function (action, done) {
              // 关闭弹窗
              done();
              // 跳转到我的优惠劵
              that.$router.push('/user/coupon');
            }
          });
        });
      }
    }

  }
</script>
