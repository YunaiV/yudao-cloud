package cn.iocoder.mall.user.biz.dataobject;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 用户注册信息
 */
@Data
@Accessors(chain = true)
public class UserRegisterDO {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 创建时间
     */
    private Date createTime;

    // TODO 芋艿 ip
    // TODO 芋艿 ua
    // TODO 芋艿 方式，手机注册、qq 等等

}
