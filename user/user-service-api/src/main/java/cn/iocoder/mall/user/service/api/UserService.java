package cn.iocoder.mall.user.service.api;

import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.mall.user.service.api.bo.UserBO;

public interface UserService {

    /**
     * 创建用户。一般在用户注册时，调用该方法
     *
     * TODO 芋艿，此处要传递一些用户注册时的相关信息，例如说 ip、ua、客户端来源等等。用于数据分析、风控等等。
     *
     * @param mobile 手机号
     * @param code 手机验证码
     * @return 用户
     */
    UserBO createUser(String mobile, String code) throws ServiceException;

}