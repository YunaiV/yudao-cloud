package cn.iocoder.mall.system.rpc.response.user;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * User 模块 - User 信息 Response
 */
@Data
@Accessors(chain = true)
public class UserResponse {

    /**
     * 用户编号
     */
    private Integer id;

}
