<template>
    <div>
     <headerNav title="修改地址"/>
      <van-address-edit
      :area-list="areaList"
      :showDelete="showDelete"
      show-set-default
      @save="onSave"
      @delete="onDelete"
      :addressInfo="info"
      />
    </div>
</template>

<script>
import areaList from '../../../data/area';
import { GetAddressById,SaveAddress,DelAddress } from "../../../api/user.js";

import { AddressEdit } from 'vant';
export default {
    components:{
        [AddressEdit.name]:AddressEdit,
    },
    data() {
    return {
      areaList,
      showDelete:false,
      info:{},
    }
  },

  methods: {
    onSave(data) {
      SaveAddress(data).then(response=>{
        this.$toast('保存成功');
        this.$router.go(-1);
      })
    },
    onDelete(data) {
      DelAddress(data).then(response=>{
        this.$toast('删除成功');
        this.$router.go(-1);
      })
    },
  },
  created:function(){
    var id=this.$route.query.id;
    if(id>0){
      this.showDelete=true;
      GetAddressById(id).then(response=>{
        console.log(response);
        this.info=response;
      })
    }
  }

}
</script>

<style>
.van-picker__toolbar{
  font-size: 16px;
}
</style>
