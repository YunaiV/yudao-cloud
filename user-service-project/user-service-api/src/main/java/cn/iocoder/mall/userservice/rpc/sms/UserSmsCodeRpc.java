package cn.iocoder.mall.userservice.rpc.sms;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.userservice.rpc.sms.dto.UserSendSmsCodeReqDTO;
import cn.iocoder.mall.userservice.rpc.sms.dto.UserVerifySmsCodeReqDTO;

/**
 * 用户短信验证码 Rpc 接口
 */
public interface UserSmsCodeRpc {

    CommonResult<Boolean> sendSmsCode(UserSendSmsCodeReqDTO sendSmsCodeDTO);

    CommonResult<Boolean> verifySmsCode(UserVerifySmsCodeReqDTO verifySmsCodeDTO);

}
