package cn.iocoder.mall.user.service.api;

import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.vo.CommonResult;

public interface MobileCodeService {

    /**
     * 发送验证码
     *
     * @param mobile 手机号
     */
    CommonResult<Void> send(String mobile) throws ServiceException;

}
