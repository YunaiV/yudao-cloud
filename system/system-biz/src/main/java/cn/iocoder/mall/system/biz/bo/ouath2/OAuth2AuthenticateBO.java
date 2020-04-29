package cn.iocoder.mall.system.biz.bo.ouath2;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 认证模块 - 认证结果 BO
 */
@Data
@Accessors(chain = true)
public class OAuth2AuthenticateBO {

    /**
     * 访问令牌
     */
    private String accessToken;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 账号编号
     */
    private Integer accountId;
    /**
     * 过期时间
     */
    private Date expiresTime;

}
