package cn.iocoder.mall.userservice.rpc.user.dto;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息 Response DTO
 */
@Data
@Accessors(chain = true)
public class UserRespDTO implements Serializable {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 注册时间
     */
    private Date createTime;

}
