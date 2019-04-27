<template>
  <div style="background: #f7f7f7;">
    <headerNav title="进度详情"/>

    <van-cell-group>
      <van-cell title="服务单号" :value="serviceNumber"/>
    </van-cell-group>
    <van-steps direction="vertical" :active="0" active-color="#f60" style="margin-top: 15px;">
      <van-step v-for="(item, itemIndex) in details" :key="detailIndex">
        <h3>{{item.logisticsInformation}}</h3>
        <p>{{item.logisticsTimeText}}</p>
      </van-step>
    </van-steps>

  </div>
</template>

<script>

  import moment from 'moment';
  import { getLogisticsInfo } from '../../../api/order';

  export default {
    data() {
      return {
        serviceNumber: '',
        details: [],
      }
    },
    mounted() {
      console.log(this.$route.params)
      const { id, serviceNumber } = this.$route.params;
      this.serviceNumber = serviceNumber
      getLogisticsInfo(id).then(res => {
        const { details } = res;

        this.details = details.map(item => {
          return {
            ...item,
            logisticsTimeText: moment(item.logisticsTime).format("YYYY-MM-DD HH:mm")
          }
        })
      })
    },
  }
</script>

<style>

</style>
