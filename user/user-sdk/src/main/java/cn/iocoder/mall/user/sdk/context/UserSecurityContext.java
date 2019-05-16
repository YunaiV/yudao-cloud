package cn.iocoder.mall.user.sdk.context;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * User Security 上下文
 */
@Data
@Accessors(chain = true)
public class UserSecurityContext {

    /**
     * 用户编号
     */
    private Integer userId;

}
