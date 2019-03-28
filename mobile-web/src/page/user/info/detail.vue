<template>
    <div>
        <headerNav title="个人信息"/>
        <van-cell-group>
            <!--<van-cell title="修改个人信息"  is-link />-->
            <!--<van-cell title="修改登录密码"  is-link />-->
            <!--<van-cell title="修改绑定手机"  is-link />-->
            <!--<van-cell title="关联账号"  is-link />-->
            <!--<van-cell title="切换账号"  is-link to="/login" />-->
            <van-cell title="昵称" :value="user.nickname" @click="onShowNicknameDialog" />
            <van-cell title="头像" @click="onShowAvatarDialog" >
                <img width="24px" :src="user.avatar" >
            </van-cell>

        </van-cell-group>

        <!-- 昵称修改弹出 -->
        <van-dialog
                v-model="showNicknameDialog"
                :before-close="onShowNicknameDialogClose"
                show-cancel-button

        >
            <van-field
                    :value="user.nickname"
                    label="昵称"
                    placeholder="请输入昵称"
                    @input="inputNickname"
            />
        </van-dialog>
        <!---->
    </div>
</template>

<script>
import { getUserInfo, doUserUpdateNickname } from '../../../api/user.js';
import { Dialog } from 'vant';

export default {
  data() {
    return {
      user: {},
      showNicknameDialog: false,
      updateNickname: undefined,
    };
  },
  methods: {
    onShowNicknameDialog: function () {
      this.showNicknameDialog = true;
      this.updateNickname = this.user.nickname;
    },
    inputNickname: function (value) {
      this.updateNickname = value;
    },
    onShowNicknameDialogClose: function (action, done) {
      if (action === 'confirm') {
        let that = this;
        let response = doUserUpdateNickname(this.updateNickname);
        response.then(data => {
          // 修改
          that.user.nickname = that.updateNickname;
          // 关闭弹窗
          done();
        });
      } else {
        done();
      }
    },

    onShowAvatarDialog: function () {
      // TODO 芋艿，头像上传
      alert('头像上传暂未开发');
    }
  },
  mounted() {
    let response = getUserInfo();
    response.then(data => {
      this.user = data;
    });
  }
}
</script>

<style>

</style>