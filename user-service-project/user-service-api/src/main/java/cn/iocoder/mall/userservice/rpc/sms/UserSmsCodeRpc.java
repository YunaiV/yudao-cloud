package cn.iocoder.mall.userservice.rpc.sms;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.userservice.rpc.sms.vo.UserSendSmsCodeDTO;
import cn.iocoder.mall.userservice.rpc.sms.vo.UserVerifySmsCodeDTO;

/**
 * 用户短信验证码 Rpc 接口
 */
public interface UserSmsCodeRpc {

    CommonResult<Boolean> sendSmsCode(UserSendSmsCodeDTO sendSmsCodeDTO);

    CommonResult<Boolean> verifySmsCode(UserVerifySmsCodeDTO verifySmsCodeDTO);

}
