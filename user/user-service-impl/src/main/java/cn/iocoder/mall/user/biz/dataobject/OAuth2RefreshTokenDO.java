package cn.iocoder.mall.user.biz.dataobject;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 刷新令牌
 *
 * idx_uid
 */
@Data
@Accessors(chain = true)
public class OAuth2RefreshTokenDO {

    /**
     * 刷新令牌
     */
    private String id;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 是否有效
     */
    private Boolean valid;
    /**
     * 过期时间
     */
    private Date expiresTime;
    /**
     * 创建时间
     */
    private Date createTime;

}
