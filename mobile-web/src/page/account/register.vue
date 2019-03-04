<template>
    <div>
      <headerNav title="注册"/>
      <div style="background:url(https://haitao.nos.netease.com/f866dd18-12f0-4bb2-be6d-5cac85cf2627.png) center 18px no-repeat;background-size:161px;">
          <div style="padding-top: 70px;">
            <van-cell-group>
                <van-field
                    placeholder="请输入手机号"
                />
                 <van-field
                    center
                    clearable
                    placeholder="请输入短信验证码"
                >
                    <van-button slot="button" size="small" type="primary">发送验证码</van-button>
                </van-field>
                <van-field
                    type="password"
                    placeholder="请输入密码"
                />
            </van-cell-group>
            <div style="margin: 10px;">
                <van-button size="large" type="primary" style="height: 45px;line-height:45px;">注册</van-button>
            </div>
          </div>
      </div>
    </div>
</template>

<script>
export default {

}
</script>

<style>

</style>
