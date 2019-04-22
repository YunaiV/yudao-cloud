<template>
    <div>
      <headerNav title="手机号登录"/>
      <div>
          <div style="padding-top: 70px;">
            <van-cell-group>
                <van-field
                    placeholder="请输入手机号"
                    @input="inputMobile"
                />
                 <van-field
                    center
                    placeholder="请输入短信验证码"
                    @input="inputCode"
                >
                    <van-button slot="button" size="small" type="primary" @click="sendCode">发送验证码</van-button>
                </van-field>
            </van-cell-group>
            <div style="margin: 10px;">
                <van-button size="large" type="primary" style="height: 45px;line-height:45px;" @click="submit">登录</van-button>
            </div>
              <van-panel title="友情提示">
                  <van-cell>1. 新注册的手机号验证后自动创建账户</van-cell>
                  <van-cell>2. 默认验证码是 9999</van-cell>
              </van-panel>
          </div>
      </div>
    </div>
</template>

<script>
import { doPassportMobileSendRegisterCode, doPassportMobileRegister } from '../../api/user';
import { Dialog } from 'vant';
import { setLoginToken } from '../../utils/cache';

export default {

  data() {
    return {
      mobile: '',
      code: '',
    }
  },

  methods: {
    inputMobile: function (value) {
      this.mobile = value;
    },
    inputCode: function (value) {
      this.code = value;
    },
    sendCode: function() {
      if (!this.mobile || this.mobile.length !== 11) {
        Dialog.alert({
          title: '系统提示',
          message: '手机号码不正确',
        });
        return;
      }
      let response = doPassportMobileSendRegisterCode(this.mobile);
      response.then(data => {
        Dialog.alert({
          title: '系统提示',
          message: '发送验证码成功',
        });
      });
    },
    submit: function () {
      let that = this;
      let response = doPassportMobileRegister(this.mobile, this.code);
      response.then(data => {
        setLoginToken(data.accessToken, data.refreshToken);
        Dialog.alert({
          title: '系统提示',
          message: '登陆成功',
          beforeClose: function (action, done) {
            done();
            // TODO 芋艿，简单的 callback 后续完善
            let redirect = that.$route.query.redirect || '/user/index';
            that.$router.push(redirect);
          }
        });
      });
    }
  }

}
</script>

<style>

</style>
