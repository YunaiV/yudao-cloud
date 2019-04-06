package cn.iocoder.mall.user.biz.dataobject;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class OAuth2AccessTokenDO {

    /**
     * 访问令牌
     */
    private String id;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 过期时间
     */
    private Date expiresTime;
    /**
     * 是否有效
     */
    private Boolean valid;
    /**
     * 创建时间
     */
    private Date createTime;

}
