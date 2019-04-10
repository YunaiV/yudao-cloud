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
  import {GetAddressById, SaveAddress, UpdateAddress, DelAddress} from "../../../api/user.js";

  import {AddressEdit} from 'vant';

  export default {
    components: {
      [AddressEdit.name]: AddressEdit,
    },
    data() {
      return {
        areaList,
        showDelete: false,
        info: {
        },
      }
    },

    methods: {
      onSave(data) {
        const params = {
          ...data,
          address: data.addressDetail,
          areaNo: data.areaCode,
          city: data.city,
          county: data.county,
          country: data.country,
          mobile: data.tel,
          name: data.name,
          hasDefault: data.isDefault,
        };

        if (data.id) {
          UpdateAddress(params).then(response => {
            this.$toast('更新成功');
            this.$router.go(-1);
          })
        } else {
          SaveAddress(params).then(response => {
            this.$toast('保存成功');
            this.$router.go(-1);
          })
        }
      },
      onDelete(data) {
        const params = {
          id: data.id,
        };
        DelAddress(params).then(response => {
          this.$toast('删除成功');
          this.$router.go(-1);
        })
      },
    },
    created: function () {
      const id = this.$route.query.id;
      if (id > 0) {
        this.showDelete = true;
        GetAddressById(id).then(response => {
          this.info = {
            ...response,
            addressDetail: response.address,
            tel: response.mobile,
            areaCode: response.areaNo,
            isDefault: 0,
          };
        })
      }
    }

  }
</script>

<style>
  .van-picker__toolbar {
    font-size: 16px;
  }
</style>
